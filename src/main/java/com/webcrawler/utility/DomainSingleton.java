package com.webcrawler.utility;

public class DomainSingleton {

    private static DomainSingleton instance;
    private String domainName;
    
    public static synchronized DomainSingleton getInstance(){
        if(instance == null){
            instance = new DomainSingleton();
        }
        return instance;
    }

	public void setValue(String domainName) {
		this.domainName = domainName;
	}
	
	public String getValue() {
		return this.domainName;
	}
    
}
