package com.nextmining.course.collector.twitter.kpop;



/**
 * This is model class for artist.
 * 
 * @author Younggue Bae
 */
public class Artist extends Entity { 
	
	protected String nameKo;
	protected String lastnameKo;
	protected String firstnameKo;
	protected String realnameKo;
	protected String nameEn;
	protected String lastnameEn;
	protected String firstnameEn1;
	protected String firstnameEn2;
	protected String nameJa;
	protected String lastnameJa;
	protected String firstnameJa;
	protected String category;	// comma separated
	protected String type;
	protected String job;
	protected String affiliatedGroup;	// comma separated
	protected String company;	// comma separated
	protected String debut;
	
	public String getNameKo() {
		return nameKo;
	}
	public void setNameKo(String nameKo) {
		this.nameKo = nameKo;
	}
	public String getLastnameKo() {
		return lastnameKo;
	}
	public void setLastnameKo(String lastnameKo) {
		this.lastnameKo = lastnameKo;
	}
	public String getFirstnameKo() {
		return firstnameKo;
	}
	public void setFirstnameKo(String firstnameKo) {
		this.firstnameKo = firstnameKo;
	}
	public String getRealnameKo() {
		return realnameKo;
	}
	public void setRealnameKo(String realnameKo) {
		this.realnameKo = realnameKo;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getLastnameEn() {
		return lastnameEn;
	}
	public void setLastnameEn(String lastnameEn) {
		this.lastnameEn = lastnameEn;
	}
	public String getFirstnameEn1() {
		return firstnameEn1;
	}
	public void setFirstnameEn1(String firstnameEn1) {
		this.firstnameEn1 = firstnameEn1;
	}
	public String getFirstnameEn2() {
		return firstnameEn2;
	}
	public void setFirstnameEn2(String firstnameEn2) {
		this.firstnameEn2 = firstnameEn2;
	}
	public String getNameJa() {
		return nameJa;
	}
	public void setNameJa(String nameJa) {
		this.nameJa = nameJa;
	}
	public String getLastnameJa() {
		return lastnameJa;
	}
	public void setLastnameJa(String lastnameJa) {
		this.lastnameJa = lastnameJa;
	}
	public String getFirstnameJa() {
		return firstnameJa;
	}
	public void setFirstnameJa(String firstnameJa) {
		this.firstnameJa = firstnameJa;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getAffiliatedGroup() {
		return affiliatedGroup;
	}
	public void setAffiliatedGroup(String affiliatedGroup) {
		this.affiliatedGroup = affiliatedGroup;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}
	
	@Override
	public String toString() {
		return "Artist [nameKo=" + nameKo + ", lastnameKo=" + lastnameKo
				+ ", firstnameKo=" + firstnameKo + ", realnameKo=" + realnameKo
				+ ", nameEn=" + nameEn + ", lastnameEn=" + lastnameEn
				+ ", firstnameEn1=" + firstnameEn1 + ", firstnameEn2="
				+ firstnameEn2 + ", nameJa=" + nameJa + ", lastnameJa="
				+ lastnameJa + ", firstnameJa=" + firstnameJa + ", category="
				+ category + ", type=" + type + ", job=" + job
				+ ", affiliatedGroup=" + affiliatedGroup + ", company="
				+ company + ", debut=" + debut + ", id=" + id
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
