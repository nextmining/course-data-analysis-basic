package com.nextmining.course.collector.blog;

import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parser for Naver site html.
 *
 * @author Younggue Bae
 */
public class NaverSiteParser {

    public static Site parseBasicInfo(HtmlPage htmlPage, Site site) {
        //HtmlAnchor buttonMore = (HtmlAnchor) htmlPage.getFirstByXPath("//a[@data-nclicks='crv.more']");
        //htmlPage = (HtmlPage) buttonMore.click();
        //System.out.println(htmlPage.getElementById("_visitsCard").getTextContent());

        HtmlDivision divSiteTop = htmlPage.getFirstByXPath("//div[@id='_siteviewTopArea']");
        if (divSiteTop == null) {
            return null;
        }
        //System.out.println(divSiteTop.getTextContent());

        DomElement elemAddr = divSiteTop.getFirstByXPath("//div[@class='search_address']");
        String siteName = "";
        try {
            siteName = ((DomElement) elemAddr.getFirstByXPath("strong"))
                .getFirstChild().getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String category = "";
        try {
            category = ((DomElement) elemAddr.getFirstByXPath("strong/span[@class='cate']"))
                    .getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String address = "";
        try {
            address = ((DomElement) elemAddr.getFirstByXPath("div[@class='bx_address2']/p"))
                    .getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String jibun = "";
        try {
            jibun = ((DomElement) elemAddr.getFirstByXPath("div[@class='bx_address2']/p/span[@class='lb_address']"))
                    .getParentNode().getTextContent().trim().replaceAll("^지번[\\s]+", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String phone = "";
        try {
            phone = ((DomElement) elemAddr.getFirstByXPath("div[@class='item_btn']/a[@class='btn_phone2 sp_map']"))
                    .getAttribute("href").replaceAll("tel:", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String map = "";
        try {
            map = ((DomElement) divSiteTop.getFirstByXPath("div[@id='_siteviewMapViewer']/a/img"))
                    .getAttribute("src");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("name = " + siteName);
        System.out.println("category = " + category);
        System.out.println("address = " + address);
        System.out.println("jibun = " + jibun);
        System.out.println("phone = " + phone);
        System.out.println("map = " + map);

        site.setSiteName(siteName);
        site.setCategory(category);
        site.setAddress(address);
        site.setJibun(jibun);
        site.setPhone(phone);
        site.setMap(map);

        return site;
    }

    public static Site parseDetailInfo(HtmlPage htmlPage, Site site) {
        HtmlDivision divSiteCard = htmlPage.getFirstByXPath("//div[@id='_siteviewCardArea']");

        if (divSiteCard == null) {
            return null;
        }

        DomElement elemMenus = divSiteCard.getFirstByXPath("//ul[@class='end_list_basic']");

        if (elemMenus == null) {
            return site;
        }

        DomNodeList<HtmlElement> menus = elemMenus.getElementsByTagName("li");

        Map<String, String> mapMenu = new HashMap<String, String>();
        for (HtmlElement elemMenu : menus) {
            try {
                String menuName = ((DomElement) elemMenu.getFirstByXPath("div/strong[@class='_detailItem']"))
                        .getTextContent();
                String menuPrice = ((DomElement) elemMenu.getFirstByXPath("div/span"))
                        .getTextContent().replaceAll("추천", "").trim();

                System.out.println("menu name = " + menuName);
                System.out.println("menu price = " + menuPrice);

                mapMenu.put(menuName, menuPrice);
            } catch (Exception e) {
                System.err.println("No menu!!!");
            }
        }

        String desc = "";
        try {
            desc = ((DomElement) divSiteCard.getFirstByXPath("//p[@class='_detailItem']"))
                    .getTextContent().trim().replaceAll("\\n[\\s]+", "<br>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("description = " + desc);

        site.setMenus(mapMenu);
        site.setDescription(desc);

        return site;
    }

    public static List<ReviewPost> parseReviewPosts(HtmlPage htmlPage) {
        List<ReviewPost> posts = new ArrayList<ReviewPost>();

        DomElement elemVisitReviews = htmlPage
                .getFirstByXPath("//div[@id='_visitsCard']/div/div[@class='end_blog']/ul");

        if (elemVisitReviews == null) {
            return posts;
        }

        DomNodeList<HtmlElement> visitReviews = elemVisitReviews.getElementsByTagName("li");
        for (HtmlElement elemReview : visitReviews) {
            DomElement elemAnchor = (DomElement) elemReview.getFirstByXPath("a");

            if (elemAnchor == null) {
                continue;
            }

            ReviewPost post = new ReviewPost();

            String url = elemAnchor.getAttribute("href");
            String title = ((DomElement) elemAnchor.getFirstByXPath("h6"))
                    .getTextContent().trim();
            String snippet = ((DomElement) elemAnchor.getFirstByXPath("p"))
                    .getTextContent().trim();
            String postDate = ((DomElement) elemAnchor.getFirstByXPath("div[@class='blog_date']"))
                    .getTextContent().replaceAll("블로그", "").trim();

            System.out.println("post url = " + url);
            System.out.println("title = " + title);
            System.out.println("snippet = " + snippet);
            System.out.println("post date = " + postDate);

            post.setTitle(title);
            post.setUrl(url);
            post.setSnippet(snippet);
            post.setPostDate(postDate);

            posts.add(post);
        }

        return posts;
    }
}
