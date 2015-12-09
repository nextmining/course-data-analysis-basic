package com.nextmining.course.collector.twitter.kpop;

import com.nextmining.common.util.StringUtil;
import com.nextmining.course.collector.twitter.api.TwitterFilterQuery;
import com.nextmining.course.collector.twitter.api.TwitterFilterStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Twitter filter stream collector.
 * 
 * @author Younggue Bae
 */
public class TwitterFilterStreamCollector implements Runnable {
	
	private PrintStream sysOut = null;
	private FileOutputStream fos = null;

	/**
	 * Constructor.
	 */
	public TwitterFilterStreamCollector() { }
	
	/**
	 * Initializes collector.
	 */
	private void initCollector() throws Exception {
		// initialize stop signal
		this.initStopSignal();
		System.out.println("TwitterFilterStreamCollector started!");
		
		String dir = "./data/twitter/";
		TwitterFilterQuery filter = new TwitterFilterQuery();
		filter.setTrack(getTracks());
		filter.setFollowScreenNames(getFollowScreenNames());
		TwitterFilterStream.writeTrackFilterStream(dir, filter);
	}

	/**
	 * Starts server instance.
	 * 
	 * @exception
	 */
	public void start() throws Exception {
		try {
			this.initCollector();
		} catch (Exception ex) {
			showConsole();
			System.out.println("Failed in starting TwitterFilterStreamCollector!");
			throw ex;
		}

		Thread serverThread = new Thread(this);
		serverThread.start();
		return;
	}

	/**
	 * Stops server instance.
	 * 
	 * @exception
	 */
	public void stop() throws Exception {
		try {
			File checkFile = new File(System.getProperty("user.home") + "/.TwitterFilterStreamCollector");
			if (!checkFile.exists()) {
				// not started yet...
				System.out.println("TwitterFilterStreamCollector is shutdown. Start it first.");
				return;
			}
		} catch (Exception ex) {
		}

		setStopSignal();
	}

	/**
	 * Initializes stop signal.
	 */
	private void initStopSignal() {
		try {
			File checkFile = new File(System.getProperty("user.home") + "/.TwitterFilterStreamCollector");
			if (checkFile.exists()) {
				checkFile.delete();
			}

			checkFile.createNewFile();
			checkFile.deleteOnExit();
		} catch (Exception ex) {
		}
	}

	/**
	 * Checks if stop signal is sent.
	 */
	private boolean checkStopSignal() {
		try {
			File checkFile = new File(System.getProperty("user.home") + "/.TwitterFilterStreamCollector");
			BufferedReader br = new BufferedReader(new FileReader(checkFile));
			String line = br.readLine();
			if ("-1".equals(line)) {
				return true;
			}
			br.close();
		} catch (Exception ex) {
		}

		return false;
	}

	/**
	 * Sets stop signal.
	 */
	private void setStopSignal() {
		try {
			File checkFile = new File(System.getProperty("user.home") + "/.TwitterFilterStreamCollector");
			BufferedWriter bw = new BufferedWriter(new FileWriter(checkFile));
			bw.write("-1");
			bw.newLine();
			bw.close();
		} catch (Exception ex) {
		}
	}

	/**
	 * Show status.
	 */
	public void status() {
		try {
			File checkFile = new File(System.getProperty("user.home") + "/.TwitterFilterStreamCollector");
			if (checkFile.exists()) {
				System.out.println("TwitterFilterStreamCollector status : STARTED");
			} else {
				System.out.println("TwitterFilterStreamCollector status : STOPPPED");
			}
		} catch (Exception ex) {
		}
	}

	/**
	 * Runs collector.
	 */
	@SuppressWarnings("static-access")
	public void run() {
		while (!checkStopSignal()) {
			try {
				Thread.currentThread().sleep(300);
			} catch (Exception ex) {
			}
		}
		
		System.out.println("TwitterFilterStreamCollector stopped successfully!");
		System.exit(0);
	}
	
	/**
	 * Utility method for hiding System.out.print...
	 */
	@SuppressWarnings("unused")
	private void hideConsole() {
		sysOut = System.out;
		System.setOut(new PrintStream(fos));
	}

	/**
	 * Utility method for showing System.out.print...
	 */
	private void showConsole() {
		if (sysOut != null)
			System.setOut(sysOut);
	}

	/**
	 * Shows usage to console.
	 */
	public static void showUsage() {
		System.out.println("Usage : mvn exec:java -Dexec.mainClass=" + TwitterFilterStreamCollector.class.getName() + " action");
		System.out.println("action should be one of following values");
		System.out.println("\tstart : Start up collector");
		System.out.println("\tstop : Stop collector");
		System.out.println("\tstatus : Status of collector");
	}
	
	/**
	 * Gets the track query.
	 * 
	 * @return
	 * @throws IOException
	 */
	private String[] getTracks() throws IOException {
		List<String> tracks = new ArrayList<String>();

		List<? extends Entity> companies = EntityReader.loadEntities("company", "/kpop/company.txt");

		for (Entity entity : companies) {
			Company company = (Company) entity;

			if (company.isUse()) {
				String query = "";
				if (!StringUtil.isEmpty(company.getTwitterCollectQuery())) {
					query = company.getTwitterCollectQuery();
					query = query.replaceAll("[\\s]+AND[\\s]+", " ");
				}

				if (company.isTwitterAccountMentionCollect()
						&& !StringUtil.isEmpty(company.getTwitterAccount())) {
					if (!StringUtil.isEmpty(query))
						query += ", " + "@" + company.getTwitterAccount();
					else
						query += "" + "@" + company.getTwitterAccount();
				}

				if (!StringUtil.isEmpty(query))
					tracks.add(query);
			}
		}

		List<? extends Entity> artists = EntityReader.loadEntities("artist", "/kpop/artist.txt");

		for (Entity entity : artists) {
			Artist artist = (Artist) entity;

			if (artist.isUse()) {
				String query = "";
				if (!StringUtil.isEmpty(artist.getTwitterCollectQuery())) {
					query = artist.getTwitterCollectQuery();
					query = query.replaceAll("[\\s]+AND[\\s]+", " ");
				}

				if (artist.isTwitterAccountMentionCollect()
						&& !StringUtil.isEmpty(artist.getTwitterAccount())) {
					if (!StringUtil.isEmpty(query))
						query += ", " + "@" + artist.getTwitterAccount();
					else
						query += "" + "@" + artist.getTwitterAccount();
				}

				if (!StringUtil.isEmpty(query))
					tracks.add(query);
			}
		}

		tracks.add("kpop, k-pop, kdrama, k-drama");

		return tracks.toArray(new String[tracks.size()]);
	}
	
	/**
	 * Returns the follow screen names.
	 * 
	 * @return
	 * @throws Exception
	 */
	private String[] getFollowScreenNames() throws IOException {
		List<String> screenNames = new ArrayList<String>();
		
		List<? extends Entity> companies = EntityReader.loadEntities("company", "/kpop/company.txt");
		
		for (Entity entity : companies) {
			Company company = (Company) entity;
			
			if (company.isUse() && company.isTwitterAccountCollect()) {
				String screenName = company.getTwitterAccount();
				
				if (!StringUtil.isEmpty(screenName)) {
					screenNames.add(screenName);
				}
			}
		}
		
		List<? extends Entity> artists = EntityReader.loadEntities("artist", "/kpop/artist.txt");
		
		for (Entity entity : artists) {
			Artist artist = (Artist) entity;

			if (artist.isUse() && artist.isTwitterAccountCollect()) {
				String screenName = artist.getTwitterAccount();
				
				if (!StringUtil.isEmpty(screenName)) {
					screenNames.add(screenName);
				}
			}
		}

		return screenNames.toArray(new String[screenNames.size()]);
	}
	
	public static void main(String[] args) throws Exception {
		try {
			if (args.length != 1) {
				TwitterFilterStreamCollector.showUsage();
				System.exit(-1);
			}

			String action = args[0];

			TwitterFilterStreamCollector instance = new TwitterFilterStreamCollector();

			if ("start".equalsIgnoreCase(action)) {
				instance.start();
			} 
			else if ("stop".equalsIgnoreCase(action)) {
				instance.stop();
			} 
			else if ("status".equalsIgnoreCase(action)) {
				instance.status();
			} 
			else {
				TwitterFilterStreamCollector.showUsage();
				System.exit(-1);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
