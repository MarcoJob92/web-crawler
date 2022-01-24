package com.webcrawler.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;

import com.webcrawler.utility.DomainSingleton;

import lombok.Data;

public @Data
class WebPage {
	
	private String url;
	private List<WebPage> childWebPages = new ArrayList<>();
	private Set<String> staticAssets = new HashSet<>();
	
	public WebPage(String url) {
		this.url = url;
	}
	
	public WebPage(String url, Document document) {
		this.url = url;
		this.updateChildWebPages(document);
	}
	
	
	public void updateChildWebPages(Document document) {
		String domain = DomainSingleton.getInstance().getValue();
		document.select("a[href]")
				.stream()
				.map(link -> link.absUrl("href"))
				.filter(link -> link.startsWith(domain) && !link.startsWith(domain+"#"))
				.collect(Collectors.toSet())
				.forEach(link -> { childWebPages.add(new WebPage(link)); });
	}
	
	public void setStaticAssets(Document document) {
		addToSet(document, "link", "href");
		addToSet(document, "style", "src");
		addToSet(document, "script", "src");
		addToSet(document, "img", "src");
	}
	
	private void addToSet(Document document, String tag, String attribute) {
		document.select(tag)
				.stream()
				.map(link -> link.absUrl(attribute))
				.forEach(link -> { staticAssets.add(link); });
	}
	
}
