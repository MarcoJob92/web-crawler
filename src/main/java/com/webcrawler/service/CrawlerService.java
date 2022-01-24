package com.webcrawler.service;

import java.io.IOException;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.webcrawler.model.WebPage;

@Service
public class CrawlerService {
	
	@Value("${jsoup.userAgent}")
	private String userAgent;
	
	public void crawl(WebPage webPage, Set<WebPage> urlVisited) {
		try {
			Document document = Jsoup.connect(webPage.getUrl()).userAgent(userAgent).get();
			
			webPage.updateChildWebPages(document);
			webPage.setStaticAssets(document);
			
			urlVisited.add(webPage);
			
			webPage.getChildWebPages().forEach(child -> {
				boolean anyMatch = containsUrl(urlVisited, child.getUrl());
				if (!anyMatch)
					crawl(child, urlVisited);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean containsUrl(Set<WebPage> urlVisited, String url) {
		for (WebPage visited : urlVisited) {
			if (visited.getUrl().equals(url))
				return true;
		}
		return false;
	}
	
}
