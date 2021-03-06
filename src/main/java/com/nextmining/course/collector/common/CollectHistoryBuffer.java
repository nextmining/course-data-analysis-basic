package com.nextmining.course.collector.common;


import com.nextmining.common.util.FileUtil;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * This class is a collect history buffer.
 * 
 * @author Younggue Bae
 */
public class CollectHistoryBuffer {
	
	BufferedWriter br;
	int maxRound = 1;
	int currentRound = 1;
	Set<String> historySet = new HashSet<String>();
	Set<String> historyRoundSet = new LinkedHashSet<String>();
	private static final String DELIMITER = "\t";
	
	public CollectHistoryBuffer(String file, int round) throws IOException {
		this.maxRound = round;
		
		this.loadCollectHistory(file);
		
		// backup history file
		FileUtil.copy(file, file + ".bak");
		
		FileUtil.mkdirsFromFullpath(file);
		this.br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
	}
	
	private void loadCollectHistory(String file) throws IOException {
		historySet = new HashSet<String>();
		historyRoundSet = new LinkedHashSet<String>();
		
		try {			
			InputStream is = new FileInputStream(file);
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8")); 
			
			int latestRound = 0;
			String line;
			while ((line = in.readLine()) != null) {
				String[] tokens = Pattern.compile(DELIMITER).split(line);	
				int round = Integer.valueOf(tokens[0]);		
				String id = tokens[1];						
				
				historySet.add(id);
				historyRoundSet.add(String.valueOf(round) + DELIMITER + id);
				
				if (round > latestRound)
					latestRound = round;
			}
			is.close();				
			currentRound = latestRound + 1;
			
			System.out.println("current round == " + currentRound);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void close() throws IOException {
		br.close();
	}
	
	public boolean checkDuplicate(String newId) {
		return historySet.contains(newId);
	}
	
	public void writeCollectHistory(Set<String> idSet) throws IOException {			
		this.rewritePrevHistory(historyRoundSet);
		
		int round = 1;
		if(currentRound <= maxRound)
			round = currentRound;
		else
			round = currentRound - 1;
		
		for (Iterator<String> it = idSet.iterator(); it.hasNext();) {
			br.write(round + DELIMITER + it.next());
			br.newLine();
		}
		
		br.close();		
	}
	
	private void rewritePrevHistory(Set<String> list)
			throws IOException {
		if (currentRound <= maxRound) {
			for (Iterator<String> it = list.iterator(); it.hasNext();) {
				br.write(it.next());
				br.newLine();
			}
		} else {
			for (Iterator<String> it = list.iterator(); it.hasNext();) {
				String line = it.next();
				String[] tokens = Pattern.compile(DELIMITER).split(line);	
				int round = Integer.valueOf(tokens[0]);		
				String id = tokens[1];
				if (round > 1) {
					br.write((round - 1) + DELIMITER + id);
					br.newLine();					
				}
			}
		}
	}
	
}
