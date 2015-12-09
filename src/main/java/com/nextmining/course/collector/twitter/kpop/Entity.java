package com.nextmining.course.collector.twitter.kpop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is model class for top entity.
 * 
 * @author Younggue Bae
 */
public class Entity { 
	
	protected String id;
	protected String namedEntityPattern;	// comma separated
	protected String filterPattern;	// comma separated
	protected String twitterCollectQuery;
	protected boolean isYouTubeAccountCollect;
	protected boolean isTwitterAccountCollect;
	protected boolean isTwitterAccountMentionCollect;
	protected String youTubeAccount;	// comma separated
	protected String twitterAccount;	// comma separated
	protected String facebookAccount;	// comma separated
	protected String googlePlusAccount;	// comma separated
	protected String me2dayAccount;	// comma separated
	protected boolean isUse;
	protected Date registerDate;
	protected Date updateDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNamedEntityPattern() {
		return namedEntityPattern;
	}
	public void setNamedEntityPattern(String namedEntityPattern) {
		this.namedEntityPattern = namedEntityPattern;
	}
	public String getFilterPattern() {
		return filterPattern;
	}
	public void setFilterPattern(String filterPattern) {
		this.filterPattern = filterPattern;
	}
	public String getTwitterCollectQuery() {
		return twitterCollectQuery;
	}
	public void setTwitterCollectQuery(String twitterCollectQuery) {
		this.twitterCollectQuery = twitterCollectQuery;
	}
	public boolean isYouTubeAccountCollect() {
		return isYouTubeAccountCollect;
	}
	public void setYouTubeAccountCollect(boolean isYouTubeAccountCollect) {
		this.isYouTubeAccountCollect = isYouTubeAccountCollect;
	}
	public boolean isTwitterAccountCollect() {
		return isTwitterAccountCollect;
	}
	public void setTwitterAccountCollect(boolean isTwitterAccountCollect) {
		this.isTwitterAccountCollect = isTwitterAccountCollect;
	}
	public boolean isTwitterAccountMentionCollect() {
		return isTwitterAccountMentionCollect;
	}
	public void setTwitterAccountMentionCollect(
			boolean isTwitterAccountMentionCollect) {
		this.isTwitterAccountMentionCollect = isTwitterAccountMentionCollect;
	}
	public String getYouTubeAccount() {
		return youTubeAccount;
	}
	public void setYouTubeAccount(String youTubeAccount) {
		this.youTubeAccount = youTubeAccount;
	}
	public String getTwitterAccount() {
		return twitterAccount;
	}
	public void setTwitterAccount(String twitterAccount) {
		this.twitterAccount = twitterAccount;
	}
	public String getFacebookAccount() {
		return facebookAccount;
	}
	public void setFacebookAccount(String facebookAccount) {
		this.facebookAccount = facebookAccount;
	}
	public String getGooglePlusAccount() {
		return googlePlusAccount;
	}
	public void setGooglePlusAccount(String googlePlusAccount) {
		this.googlePlusAccount = googlePlusAccount;
	}
	public String getMe2dayAccount() {
		return me2dayAccount;
	}
	public void setMe2dayAccount(String me2dayAccount) {
		this.me2dayAccount = me2dayAccount;
	}
	public boolean isUse() {
		return isUse;
	}
	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public static List<String> parseItems(String commaSeparatedText) {
		List<String> list = new ArrayList<String>();
		if (commaSeparatedText != null) {
			String[] tokens = commaSeparatedText.split(",");
			for (String token : tokens) {
				list.add(token.trim());
			}
		}
		
		return list;
	}
	
	public static String parseMainItem(String commaSeparatedText) {
		if (commaSeparatedText != null) {
			String[] tokens = commaSeparatedText.split(",");
			return tokens[0].trim();
		}
		
		return null;
	}

}
