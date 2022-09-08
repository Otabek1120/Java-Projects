/* 
 * File:     WikiScraper.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class takes the name of a wiki page,
 * 			 reads in the html file of the page, finds 
 * 			 all the valid wiki links on that page, and 
 * 			 returns their titles as a set.
 *              
 * Course #: CS 210, Spring 2022
 */

import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;


public class WikiScraper {
			
	/*
	 * Takes the name of the wiki link and returns 
	 * all the vaid wiki link on that page as a set
	 * 
	 * @param link: the name of the wiki link
	 * @return link: a set of names of the 
	 * 				valid wiki links on that page
	 */
	public static Set<String> findWikiLinks(String link) {
		String html = fetchHTML(link);
		Set<String> links = scrapeHTML(html);
		return links;
	}
	
	/*
	 * Takes the name of a wiki link, gets the html file 
	 * with that title, and returns the html file 
	 * as a string.
	 */
	private static String fetchHTML(String link) {
		StringBuffer buffer = null;
		try {
			URL url = new URL(getURL(link));
			InputStream is = url.openStream();
			int ptr = 0;
			buffer = new StringBuffer();
			while ((ptr = is.read()) != -1) {
			    buffer.append((char)ptr);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return buffer.toString();
	}
	
	/*
	 * Takes the name of a wiki link and 
	 * returns it as a valid wiki link in the 
	 * given format.
	 */
	private static String getURL(String link) {
		return "https://en.wikipedia.org/wiki/" + link;
	}
	
	/*
	 * Takes the hmtl file as a string, reads through it 
	 * finding all the valid wiki link titles.
	 * Returns the valid wiki titles a set.
	 */
	private static Set<String> scrapeHTML(String html) {
		Set<String> validLinkTitles = new HashSet<>();

		String[] allPossLinkes = html.split("<a href=\"/wiki/");
		for (int i = 1; i < allPossLinkes.length; i ++) {
			String title = allPossLinkes[i].split("\"")[0];
			if (isValidWikiLink(title)) {
				validLinkTitles.add(title);
			}
		} 
		return validLinkTitles;
	}

	/*
 	 * Returns true if the given wiki title is a valid
	 * one. False if not 
 	 */
	private static boolean isValidWikiLink(String link) {
		if (!link.contains("#") && !link.contains(":")) {
			return true;
		}
		return false;
	}
}
