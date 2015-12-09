package com.nextmining.course.collector.twitter.kpop;


/**
 * This is model class for company.
 * 
 * @author Younggue Bae
 */
public class Company extends Entity { 

	protected String name;
	protected String nameEn;
	protected String category;	// comma separated
	protected String parentCompany;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getParentCompany() {
		return parentCompany;
	}
	public void setParentCompany(String parentCompany) {
		this.parentCompany = parentCompany;
	}
	
	@Override
	public String toString() {
		return "Company [name=" + name + ", nameEn=" + nameEn + ", category="
				+ category + ", parentCompany=" + parentCompany + ", id=" + id
				+ ", namedEntityPattern=" + namedEntityPattern
				+ ", twitterCollectQuery=" + twitterCollectQuery
				+ ", isYouTubeAccountCollect=" + isYouTubeAccountCollect
				+ ", isTwitterAccountCollect=" + isTwitterAccountCollect
				+ ", isTwitterAccountMentionCollect="
				+ isTwitterAccountMentionCollect + ", youTubeAccount="
				+ youTubeAccount + ", twitterAccount=" + twitterAccount
				+ ", facebookAccount=" + facebookAccount
				+ ", googlePlusAccount=" + googlePlusAccount
				+ ", me2dayAccount=" + me2dayAccount + ", isUse=" + isUse
				+ ", registerDate=" + registerDate + ", updateDate="
				+ updateDate + "]";
	}
	
}
