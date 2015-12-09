package com.nextmining.course.analysis.collector.blog;

import java.util.*;

/**
 * Simple object for site.
 *
 * @author Younggue Bae
 */
public class Site {

    String id;
    String name;
    String location;
    String naverSiteCode;
    String siteName;
    String category;
    String address;
    String jibun;
    String phone;
    String map;
    String description;
    Map<String, String> menus = new HashMap<String, String>();
    Set<String> tags = new HashSet<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNaverSiteCode() {
        return naverSiteCode;
    }

    public void setNaverSiteCode(String naverSiteCode) {
        this.naverSiteCode = naverSiteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJibun() {
        return jibun;
    }

    public void setJibun(String jibun) {
        this.jibun = jibun;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getMenus() {
        return menus;
    }

    public void setMenus(Map<String, String> menus) {
        this.menus = menus;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
