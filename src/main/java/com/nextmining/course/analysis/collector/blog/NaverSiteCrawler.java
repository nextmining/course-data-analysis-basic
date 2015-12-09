package com.nextmining.course.analysis.collector.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Crawler for collection Naver's shop sites.
 *
 * @author Younggue Bae
 */
public class NaverSiteCrawler {

    private static final String SITE_URL = "http://m.map.naver.com/siteview.nhn?code=";

    private WebCrawlerClient webClient;
    private List<Site> sites;

    public NaverSiteCrawler() throws Exception {
        this.webClient = new WebCrawlerClient();
        this.sites = this.loadCollectEntities("./conf/sites.txt");
    }

    public void crawl() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        for (Site site : sites) {
            HtmlPage htmlPage = webClient.getPage(SITE_URL + site.getNaverSiteCode());
            site = NaverSiteParser.parseBasicInfo(htmlPage, site);
            site = NaverSiteParser.parseDetailInfo(htmlPage, site);

            List<ReviewPost> reviewPosts = NaverSiteParser.parseReviewPosts(htmlPage);
            if (reviewPosts.size() > 0) {
                mapper.writeValue(new File("./data/reviews/" + site.getId() + ".json"), reviewPosts);
            }
        }

        mapper.writeValue(new File("./data/sites.json"), sites);
    }

    private List<Site> loadCollectEntities(String file) throws IOException, Exception {
        List<Site> sites = new ArrayList<Site>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }

            Site site = new Site();

            String field[] = line.split("\t");
            String id = field[0];
            String name = field[1];
            String location = field[2];
            String naverSiteCode = field[3];

            site.setId(id);
            site.setName(name);
            site.setLocation(location);
            site.setNaverSiteCode(naverSiteCode);

            System.out.println("site id = " + id + ", name = " + name);

            sites.add(site);
        }

        return sites;
    }

    public static void main(String[] args) throws Exception {
        NaverSiteCrawler crawler = new NaverSiteCrawler();

        crawler.crawl();
    }
}
