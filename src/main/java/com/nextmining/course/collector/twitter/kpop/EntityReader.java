package com.nextmining.course.collector.twitter.kpop;


import com.nextmining.common.util.DateUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EntityReader { 

	public static final List<? extends Entity> loadEntities(String name, String file) throws IOException {
		List<Entity> list = new ArrayList<Entity>();
		
		//BufferedReader reader = new BufferedReader(new FileReader(file));
		InputStream is = EntityReader.class.getResourceAsStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;

		while ((line = reader.readLine()) != null) {
			if (line.startsWith("#")) {
				continue;
			}
			
			if (name.equalsIgnoreCase("company")) {
				Company company = getCompany(line);
				System.out.println(company);
				list.add(company);
			}
			else if (name.equalsIgnoreCase("artist")) {
				Artist artist = getArtist(line);
				System.out.println(artist);
				list.add(artist);
			}
		}
		
		reader.close();
		
		return list;
	}
	
	private static Company getCompany(String line) {
		Company company = new Company();
		
		String columns[] = line.split("\t");
		
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];
			column = column.trim();
			
			if (i == 0) {
				company.setId(column);
			}
			else if (i == 1) {
				company.setName(column);
			}
			else if (i == 2) {
				company.setNameEn(column);
			}
			else if (i == 3) {
				company.setCategory(column);
			}
			else if (i == 4) {
				company.setParentCompany(column);
			}
			else if (i == 5) {
				company.setNamedEntityPattern(column);
			}
			else if (i == 6) {
				company.setFilterPattern(column);
			}
			else if (i == 7) {
				company.setTwitterCollectQuery(column);
			}
			else if (i == 8) {
				if (column.equals("1"))
					company.setYouTubeAccountCollect(true);
				else
					company.setYouTubeAccountCollect(false);
			}
			else if (i == 9) {
				if (column.equals("1"))
					company.setTwitterAccountCollect(true);
				else
					company.setTwitterAccountCollect(false);
			}
			else if (i == 10) {
				if (column.equals("1"))
					company.setTwitterAccountMentionCollect(true);
				else
					company.setTwitterAccountMentionCollect(false);
			}
			else if (i == 11) {
				company.setYouTubeAccount(column);
			}
			else if (i == 12) {
				company.setTwitterAccount(column);
			}
			else if (i == 13) {
				company.setFacebookAccount(column);
			}
			else if (i == 14) {
				company.setGooglePlusAccount(column);
			}
			else if (i == 15) {
				company.setMe2dayAccount(column);
			}
			else if (i == 16) {
				if (column.equals("1"))
					company.setUse(true);
				else
					company.setUse(false);
			}
			else if (i == 17) {
				company.setRegisterDate(DateUtil.convertStringToDate("yyyyMMdd", column));
			}
			else if (i == 18) {
				company.setUpdateDate(DateUtil.convertStringToDate("yyyyMMdd", column));
			}
		}
		
		return company;
	}
	
	private static Artist getArtist(String line) {
		Artist artist = new Artist();
		
		String columns[] = line.split("\t");
		
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];	
			column = column.trim();
			
			if (i == 0) {
				artist.setId(column);
			}
			else if (i == 1) {
				artist.setNameKo(column);
			}
			else if (i == 2) {
				artist.setLastnameKo(column);
			}
			else if (i == 3) {
				artist.setFirstnameKo(column);
			}
			else if (i == 4) {
				artist.setRealnameKo(column);
			}
			else if (i == 5) {
				artist.setNameEn(column);
			}
			else if (i == 6) {
				artist.setLastnameEn(column);
			}
			else if (i == 7) {
				artist.setFirstnameEn1(column);
			}
			else if (i == 8) {
				artist.setFirstnameEn2(column);
			}
			else if (i == 9) {
				artist.setNameJa(column);
			}
			else if (i == 10) {
				artist.setLastnameJa(column);
			}
			else if (i == 11) {
				artist.setFirstnameJa(column);
			}
			else if (i == 12) {
				artist.setCategory(column);
			}
			else if (i == 13) {
				artist.setType(column);
			}
			else if (i == 14) {
				artist.setJob(column);
			}
			else if (i == 15) {
				artist.setAffiliatedGroup(column);
			}
			else if (i == 16) {
				artist.setCompany(column);
			}
			else if (i == 17) {
				artist.setDebut(column);
			}
			else if (i == 18) {
				artist.setNamedEntityPattern(column);
			}
			else if (i == 19) {
				artist.setFilterPattern(column);
			}
			else if (i == 20) {
				artist.setTwitterCollectQuery(column);
			}
			else if (i == 21) {
				if (column.equals("1"))
					artist.setYouTubeAccountCollect(true);
				else
					artist.setYouTubeAccountCollect(false);
			}
			else if (i == 22) {
				if (column.equals("1"))
					artist.setTwitterAccountCollect(true);
				else
					artist.setTwitterAccountCollect(false);
			}
			else if (i == 23) {
				if (column.equals("1"))
					artist.setTwitterAccountMentionCollect(true);
				else
					artist.setTwitterAccountMentionCollect(false);
			}
			else if (i == 24) {
				artist.setYouTubeAccount(column);
			}
			else if (i == 25) {
				artist.setTwitterAccount(column);
			}
			else if (i == 26) {
				artist.setFacebookAccount(column);
			}
			else if (i == 27) {
				artist.setGooglePlusAccount(column);
			}
			else if (i == 28) {
				artist.setMe2dayAccount(column);
			}
			else if (i == 29) {
				if (column.equals("1"))
					artist.setUse(true);
				else
					artist.setUse(false);
			}
			else if (i == 30) {
				artist.setRegisterDate(DateUtil.convertStringToDate("yyyyMMdd", column));
			}
			else if (i == 31) {
				artist.setUpdateDate(DateUtil.convertStringToDate("yyyyMMdd", column));
			}
		}
		
		return artist;
	}

}
