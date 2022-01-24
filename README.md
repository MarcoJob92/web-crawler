### Overview
This is a simple Web Crawler which, given an web domain, browses all pages within the domain and prints a site map and a list of all static assets each page depends on.

#### Technologies

This application was developed with **Spring Boot**.

**Maven** will take care of building the *jar* file and run all the tests.

**Jsoup** is the a Java HTML Parser library that makes possible to fetch a URL and extract data from it in a really simple way, thanks to its API.

**Log4J 2** to write information into a log file as these may be particularly long.

#### Data structure
The data structure behind this project is a *non-binary Tree*.

It is a data model in which each instance of a node maintains a link to a list that contains objects of the same data type as that of the node itself.

Given a root node, a recursive approach is used to both populate the Tree and iterate trough all nodes and then print the relevant data.


#### Getting started
Clone the Java project from Git or download it directly from GitHub.

Run the following Maven comand from project root directory to execute all the tests.
	
```
mvn clean install
```

