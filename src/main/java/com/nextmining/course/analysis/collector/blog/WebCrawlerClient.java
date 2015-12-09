package com.nextmining.course.analysis.collector.blog;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Client for crawling html page.
 *
 * @author Younggue Bae
 */
public class WebCrawlerClient {

    private final WebClient webClient;

    public WebCrawlerClient() {
        webClient = new WebClient(BrowserVersion.FIREFOX_17);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //webClient.waitForBackgroundJavaScript(1000);
    }

    public WebClient getWebClient() {
        return this.webClient;
    }

    public HtmlPage getPage(String url) {
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Get page error");
        }
        int z = webClient.waitForBackgroundJavaScript(1000);
        int counter = 1000;
        while (z > 0) {
            counter += 1000;
            z = webClient.waitForBackgroundJavaScript(counter);
            if (z == 0) {
                break;
            }
            synchronized (page) {
                System.out.println("wait");
                try {
                    page.wait(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println(page.asXml());
        return page;
    }

    public void close() {
        webClient.closeAllWindows();
    }

}
