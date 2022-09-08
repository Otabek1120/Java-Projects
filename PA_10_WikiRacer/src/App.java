import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {


        
        // WikiScraper.findWikiLinks("Lion");
        


        HashSet <String> set1 = new HashSet <String>();
    
        HashSet <String> set2 = new HashSet <String>();
        set1.add("Mat");
        set1.add("Sat");
        set1.add("Cat");
        System.out.println("Set1 = "+ set1);
        set2.add("Mat");
        set2.add("Cat");
        set2.add("Fat");
        set2.add("Hat");
        System.out.println(set1.contains("Cat"));
        System.out.println("Set2 = "+ set2);
        Set<String> copy = new HashSet<>(set1);
        set1.retainAll(set2);
        System.out.println("Intersection = "+ set1);
        System.out.println(copy);









    // public static Set<String> findWikiLinks(String html) {
    //     Set<String> validLinkTitles = new HashSet<>();
    //     String[] allPossLinkes = html.split("\n");
    //     for (String possLink: allPossLinkes) {
    //         if (isValidWikiLink(possLink)) {
    //             String title = getTitle(possLink);
    //             validLinkTitles.add(title);
    //         }
    //     }
    //     return validLinkTitles;
    // }

    // private static boolean isValidWikiLink(String link) {
    //     if (link.contains("<a href=\"/wiki/") 
    //                         && !link.contains("#") 
    //                         && !link.contains(":")) {
    //         return true;
    //     }
    //     return false;
    // }

    // private static String getTitle(String link) {
    //     String[] content = link.split("\"");
    //     return content[1].split("/")[2];
    // }
    
    }
}
