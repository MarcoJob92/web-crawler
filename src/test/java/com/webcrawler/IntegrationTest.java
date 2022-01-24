package com.webcrawler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webcrawler.service.Crawler;

@SpringBootTest
class IntegrationTest {
	
	@Autowired
	Crawler crawler;
	
	@Test
	void crawlerTest() {
		crawler.search("https://greatbigsmall.co.uk");
	}
	
	@Test
	void crawlerTest2() {
		crawler.search("http://www.timesheetevo.it");
	}
	
	@Test
	void crawlerTest3() {
		crawler.search("https://sedna.com");
	}

}
