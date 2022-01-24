package com.webcrawler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.webcrawler.model.WebPage;
import com.webcrawler.service.CrawlerService;
import com.webcrawler.utility.DomainSingleton;

@SpringBootTest
class UnitTest {
	
	@Autowired
	CrawlerService crawlerService;
	
	@Value("${jsoup.userAgent}")
	private String userAgent;
	
	private static final String URL = "https://greatbigsmall.co.uk";
	
	
	@Test
	void testWebPageInitialization() {
		WebPage webPage = new WebPage(URL);
		assertEquals(URL, webPage.getUrl());
	}
	
	@Test
	void testFetchingURL() throws IOException {
		DomainSingleton.getInstance().setValue(URL);
		WebPage webPage = new WebPage(URL);
		Document document = Jsoup.connect(webPage.getUrl()).userAgent(userAgent).get();
		
		webPage.updateChildWebPages(document);
		
		assertEquals(12, webPage.getChildWebPages().size());
	}

}
