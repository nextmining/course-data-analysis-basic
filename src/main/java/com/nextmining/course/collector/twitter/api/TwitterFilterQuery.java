package com.nextmining.course.collector.twitter.api;

/**
 * This class is a Twitter filter query.
 * 
 * @author Younggue Bae
 */
public class TwitterFilterQuery {
	
	private String[] track;
	private String[] followScreenNames;

	public TwitterFilterQuery() {
		
	}
	
	public String[] getTrack() {
		return track;
	}

	public void setTrack(String[] track) {
		this.track = track;
	}

	public String[] getFollowScreenNames() {
		return followScreenNames;
	}

	public void setFollowScreenNames(String[] followScreenNames) {
		this.followScreenNames = followScreenNames;
	}

}
