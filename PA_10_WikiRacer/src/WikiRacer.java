/* 
 * File:     WikiRacer.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class finds the ladder (List<String>)
 *           between two wiki pages. Ladder is a list, or a path, 
 *           of wiki links that take from one page to another.
 *              
 * Course #: CS 210, Spring 2022
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WikiRacer {
	public static void main(String[] args) {
		List<String> ladder = findWikiLadder(args[0], args[1]);
		System.out.println(ladder);
	}



	/*
	 * Given the start page and target page, finds 
     * the shortest path between them
     * 
     * @param start: the name of the start wiki link
     * @param end: the name of the target wiki link
     * @return wikiLadder: list of wiki links that take 
     *                  from the start page to the end page
	 */
	private static List<String> findWikiLadder(String start, String end) {     
        MaxPQ priorityQueue = new MaxPQ();
        List<String> wikiLadder = new ArrayList<String>(); 
        wikiLadder.add(start);
        Set<String> visitedLinks =  new HashSet<>();
        int similarity = findSimilarity(start, end);
        priorityQueue.enqueue(wikiLadder, similarity);

        while (!priorityQueue.isEmpty()) {
            List<String> currLadder = priorityQueue.dequeue();
            visitedLinks.add(currLadder.get(currLadder.size() - 1));
            String lastPage = currLadder.get(currLadder.size() - 1);
            Set<String> neighbourPages = WikiScraper.findWikiLinks(lastPage);
            if (neighbourPages.contains(end)) {
                currLadder.add(end);
                return currLadder;
            }   
            neighbourPages.parallelStream().forEach(eachLink -> {
                WikiScraper.findWikiLinks(eachLink);
                if (!visitedLinks.contains(eachLink)) {
                    visitedLinks.add(eachLink);
                    List<String> copy = new ArrayList<>(currLadder);
                    copy.add(eachLink);
                    int priority = findSimilarity(eachLink, end);
                    priorityQueue.enqueue(copy, priority);
                }
            });
        } 
        return wikiLadder;
	}

    /*
     * Finds the number of common links between 
     * two wiki pages.
     * 
     * @param currPage: the name of the start wiki page.
     * @param endPage: the name of the end wiki page.
     * @return similariy: the number of common links between
     *                      the given two pages.
     */
    private static int findSimilarity(String currPage, String endPage) {
        Set<String> startPage = WikiScraper.findWikiLinks(currPage);
        Set<String> targetPage = WikiScraper.findWikiLinks(endPage);
        Set<String> copy = new HashSet<>(startPage);
        copy.retainAll(targetPage);
        int similarity = copy.size();
        return similarity;
    }
}
