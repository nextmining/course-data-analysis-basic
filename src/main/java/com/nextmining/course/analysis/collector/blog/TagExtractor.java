package com.nextmining.course.analysis.collector.blog;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extractor for tags.
 *
 * @author Younggue Bae
 */
public class TagExtractor {

    private static Pattern PATTERN_TEXT = Pattern.compile("(.*)(\\(.*\\))");

    public TagExtractor() {

    }

    public Set<String> extract(Site site) {
        Set<String> tags = new HashSet<String>();

        Set<String> siteNameTags = extractSiteNameTags(site);

        Set<String> tagsWithCategory = extractTagsWithCategory(site, siteNameTags);

        Set<String> tagsWithMenu = extractTagsWithMenu(site, siteNameTags);

        tags.addAll(siteNameTags);
        tags.addAll(tagsWithCategory);
        tags.addAll(tagsWithMenu);

        return tags;
    }

    private Set<String> extractSiteNameTags(Site site) {
        Set<String> tags = new HashSet<String>();

        String name = site.getName().toLowerCase();
        String siteName = site.getSiteName().toLowerCase();
        String location = site.getLocation().toLowerCase();


        String nameWithLocation = location + " " + name;
        String siteNameWithLocation = location + " " + siteName;

        tags.add(name);
        if (!name.contains(location) && !tags.contains(nameWithLocation)) {
            tags.add(nameWithLocation);
        }

        if (!tags.contains(siteName)) {
            tags.add(siteName);
        }
        if (!siteName.contains(location) && !tags.contains(siteNameWithLocation)) {
            tags.add(siteNameWithLocation);
        }

        // 사이트명에 지점명이 있으면 지점명을 추출하여 태그 생성.
        Matcher m = PATTERN_TEXT.matcher(name);
        if (m.find()) {
            String mainName = m.group(1).trim();
            String branchName = m.group(2).trim();
            branchName = branchName.replaceAll("[\\(\\)]", "").trim();

            String mainBrachName = mainName + " " + branchName;
            String mainBrachNameWithLocation1 = location + " " + mainBrachName;
            String mainBrachNameWithLocation2 = mainName + " " + location + " " + branchName;

            if (!tags.contains(mainBrachName)) {
                tags.add(mainBrachName);
            }

            if (!mainBrachName.contains(location) && !tags.contains(mainBrachNameWithLocation1)) {
                tags.add(mainBrachNameWithLocation1);
            }
            if (!mainBrachName.contains(location) && !tags.contains(mainBrachNameWithLocation2)) {
                tags.add(mainBrachNameWithLocation2);
            }

            if (!siteName.contains(branchName)) {
                String siteBrachName = siteName + " " + branchName;
                String siteBrachNameWithLocation1 = location + " " + siteBrachName;
                String siteBrachNameWithLocation2 = siteName + " " + location + " " + branchName;

                if (!tags.contains(siteBrachName)) {
                    tags.add(siteBrachName);
                }

                if (!siteBrachName.contains(location) && !tags.contains(siteBrachNameWithLocation1)) {
                    tags.add(siteBrachNameWithLocation1);
                }
                if (!siteBrachName.contains(location) && !tags.contains(siteBrachNameWithLocation2)) {
                    tags.add(siteBrachNameWithLocation2);
                }
            }
        }

        return tags;
    }

    private Set<String> extractTagsWithCategory(Site site, Set<String> siteNameTags) {
        Set<String> tags = new HashSet<String>();

        String category = site.getCategory().toLowerCase();

        Matcher m = PATTERN_TEXT.matcher(category);
        if (m.find()) {
            String cat = m.group(1).trim();
            String[] arrCat = cat.split(",");

            StringBuilder sbCat1 = new StringBuilder();
            StringBuilder sbCat2 = new StringBuilder();

            for (int i = 0; i < arrCat.length; i++) {
                if (sbCat1.length() > 0) {
                    sbCat1.append(" ");
                }
                sbCat1.append(arrCat[i].trim());
            }
            for (int i = arrCat.length - 1; i >= 0; i--) {
                if (sbCat2.length() > 0) {
                    sbCat2.append(" ");
                }
                sbCat2.append(arrCat[i].trim());
            }
            for (String siteNameTag : siteNameTags) {
                if (sbCat1.length() > 0) {
                    String siteNameTagWithCategory1 = siteNameTag + " " + sbCat1;
                    if (!siteNameTag.contains(sbCat1.toString()) && !siteNameTags.contains(siteNameTagWithCategory1)) {
                        tags.add(siteNameTagWithCategory1);
                    }
                }
                if (sbCat2.length() > 0) {
                    String siteNameTagWithCategory2 = siteNameTag + " " + sbCat2;
                    if (!siteNameTag.contains(sbCat2.toString()) && !siteNameTags.contains(siteNameTagWithCategory2)) {
                        tags.add(siteNameTagWithCategory2);
                    }
                }
            }

            for (String strCat : arrCat) {
                for (String siteNameTag : siteNameTags) {
                    String siteNameTagWithCategory = siteNameTag + " " + strCat;
                    if (!siteNameTag.contains(strCat) && !siteNameTags.contains(siteNameTagWithCategory)) {
                        tags.add(siteNameTagWithCategory);
                    }
                }
            }
        }
        else {
            String[] arrCat = category.split(",");

            StringBuilder sbCat1 = new StringBuilder();
            StringBuilder sbCat2 = new StringBuilder();

            for (int i = 0; i < arrCat.length; i++) {
                if (sbCat1.length() > 0) {
                    sbCat1.append(" ");
                }
                sbCat1.append(arrCat[i]);
            }
            for (int i = arrCat.length - 1; i >= 0; i--) {
                if (sbCat2.length() > 0) {
                    sbCat2.append(" ");
                }
                sbCat2.append(arrCat[i]);
            }
            for (String siteNameTag : siteNameTags) {
                if (sbCat1.length() > 0) {
                    String siteNameTagWithCategory1 = siteNameTag + " " + sbCat1;
                    if (!siteNameTag.contains(sbCat1.toString()) && !siteNameTags.contains(siteNameTagWithCategory1)) {
                        tags.add(siteNameTagWithCategory1);
                    }
                }
                if (sbCat2.length() > 0) {
                    String siteNameTagWithCategory2 = siteNameTag + " " + sbCat2;
                    if (!siteNameTag.contains(sbCat2.toString()) && !siteNameTags.contains(siteNameTagWithCategory2)) {
                        tags.add(siteNameTagWithCategory2);
                    }
                }
            }

            for (String strCat : arrCat) {
                for (String siteNameTag : siteNameTags) {
                    String siteNameTagWithCategory = siteNameTag + " " + strCat;
                    if (!siteNameTag.contains(strCat) && !siteNameTags.contains(siteNameTagWithCategory)) {
                        tags.add(siteNameTagWithCategory);
                    }
                }
            }
        }

        return tags;
    }

    private Set<String> extractTagsWithMenu(Site site, Set<String> siteNameTags) {
        Set<String> tags = new HashSet<String>();

        Set<String> menus = site.getMenus().keySet();

        Set<String> menuSet = new HashSet<String>();
        for (String menu : menus) {
            Matcher matcherCategory = PATTERN_TEXT.matcher(menu);
            if (matcherCategory.find()) {
                String strMenu = matcherCategory.group(1).trim();
                menuSet.add(strMenu);
            }
            else {
                menuSet.add(menu.trim());
            }
        }

        for (String menu : menuSet) {
            for (String siteNameTag : siteNameTags) {
                String tagWithMenu = siteNameTag + " " + menu;
                if (!siteNameTags.contains(tagWithMenu) && !siteNameTags.contains(menu)) {
                    tags.add(tagWithMenu);
                }
            }
        }

        return tags;
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Site>> typeRef = new TypeReference<List<Site>>() {};

        List<Site> sites = mapper.readValue(new File("./data/sites.json"), typeRef);

        TagExtractor tagExtractor = new TagExtractor();

        for (int i = 0; i < sites.size(); i++) {
            Site site = sites.get(i);
            Set<String> tags = tagExtractor.extract(site);
            site.setTags(tags);

            System.out.println(tags);
        }

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File("./data/sites.json"), sites);
    }
}
