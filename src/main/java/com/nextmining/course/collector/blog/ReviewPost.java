package com.nextmining.course.collector.blog;

/**
 * Simple object for review post.
 *
 * @author Younggue Bae
 */
public class ReviewPost {

    private String title;
    private String url;
    private String snippet;
    private String postDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
