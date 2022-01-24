package com.webcrawler.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webcrawler.model.WebPage;
import com.webcrawler.utility.DomainSingleton;

@Component
public class Crawler {
	
	@Autowired CrawlerService crawlerService;
	private static Logger logger = LogManager.getLogger();
	
	public void search(String url) {
		DomainSingleton.getInstance().setValue(url);
		Set<WebPage> urlVisited = new HashSet<>();
		WebPage webPage = new WebPage(url);
		
		crawlerService.crawl(webPage, urlVisited);
		
		logger.info("\n----- Site Map of " + url + " -----\n");
		printSiteMap(webPage, "");
		
		logger.info("\n\n----- Static Assets -----\n");
		printStaticAssets(urlVisited);
	}
	
	private void printSiteMap(WebPage webPage, String whiteSpace) {
		logger.info(whiteSpace + webPage.getUrl());

		for (WebPage child : webPage.getChildWebPages()) {
			printSiteMap(child, whiteSpace+"  ");
		}
	}
	
	private void printStaticAssets(Set<WebPage> urlVisited) {
		for (WebPage wp : urlVisited) {
			logger.info(wp.getUrl());
			wp.getStaticAssets().forEach(logger::info);
			logger.info("\n\n");
		}
	}
	
}
