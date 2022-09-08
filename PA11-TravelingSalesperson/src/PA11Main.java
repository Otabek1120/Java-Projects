/* 
 * File:     PA11Main.java
 * Author:   Otabek Abduraimov
 * Purpose:  This main class tests three different versions of 
 *                 Travelling Salesperson problem,
 *           finding what is the shortest path through a 
 *           sequence of locations and back to the beginning 
 *           while only visiting each location once:
 *                      1. heuristic version
 *                      2. recursive backtarcking version
 *                      3. the version I came up with
 *           It also compares the time complexity of these 
 *           three ways of solving the problem.
 *              
 * Course #: CS 210, Spring 2022
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PA11Main {
    public static void main(String[] args) throws FileNotFoundException {
        String[] commands = {"HEURISTIC", "BACKTRACK", "MINE", "TIME"};
        List<String> graphContent = readFile(args[0]); 
        DGraph graph = buildDGraph(graphContent);
        int numCities = graph.getNumVertices();

        if (args[1].equals(commands[0])) {
            System.out.println(heuristic(numCities, graph).toString(graph));
        } else if (args[1].equals(commands[1])) {
            System.out.println(backtrack(numCities, graph).toString(graph));
        } else if (args[1].equals(commands[2]))  {
            System.out.println(mine(graph, numCities).toString(graph));
        } else if (args[1].equals(commands[3])) {
            time(graph, numCities);
        }
    }

    /**
     * Solves the Travelling Salesperson problem heuristiclly.
     * @param numCities: the number of cities in the directed graph.
     *                   each of which is to be visited. 
     * @param graph: directed graph that includes directed edges 
     *               the weight of travel between the cities.
     * @return trip: the shortest path from city 1 to the last city and
     *               back to city 1.
     */
    public static Trip heuristic(int numCities, DGraph graph) {
        Trip trip = new Trip(numCities);
        trip.chooseNextCity(1);
        int currCity = 1;
        for (int i = 2; i <= graph.getNumNodes(); i++) {
            int closestCity = findClosestCity(trip, currCity, graph);
            if (closestCity != currCity) {
                trip.chooseNextCity(closestCity);
                currCity = closestCity;
            } else {
                break;
            }
        }
        return trip;
    }
    /**
     * Given the current city, so far trip, and the graph, finds
     * the closest neighboring city to the current city.
     * @param trip: Trip object that represents what cites are in 
     *              the trip, which of them have been viseted, and 
     *              which have not been visited to.
     * @param currCity: the city the Salesperson is currently at. 
     * @param graph: directed graph that includes directed edges 
     *               the weight of travel between the cities.
     * @return closestCity: the closest city to the current city.
     */
    private static int findClosestCity(Trip trip, int currCity, DGraph graph) {
        List<Integer> neighbors = graph.getNeighbors(currCity);
        double cost = Double.MAX_VALUE;
        int closestCity = currCity;
        for (int neighbor : neighbors) {
            if (trip.isCityAvailable(neighbor)) {
                if (graph.getWeight(currCity, neighbor) < cost) {
                    closestCity = neighbor;
                    cost = graph.getWeight(currCity, neighbor);
                }
            } 
        }
        return closestCity;
    }

     /**
     * Solves the Travelling Salesperson problem using backtrack recursion.
     * @param numCities: the number of cities in the directed graph.
     *                   each of which is to be visited. 
     * @param graph: directed graph that includes directed edges 
     *               the weight of travel between the cities.
     * @return trip: the shortest path from city 1 to the last city and
     *               back to city 1.
     */
    public static Trip backtrack(int numCities, DGraph graph) {  
        Trip trip  = new Trip(numCities);
        trip.chooseNextCity(1);
        trip = backtrackHelper(graph, trip, new Trip(numCities));
        return trip;
        
    }
    /**
     * This helper method finds the shortest path using a backtack 
     * recursion algorithm.
     * @param graph: directed graph that includes directed edges 
     *               the weight of travel between the cities.
     * @param trip: Trip object that represents what cites are in 
     *              the trip, which of them have been visited, and 
     *              which have not been visited to.
     * @param minTrip: the shortest trip that has been found so far.
     * @return: minTrip: the shorest possible trip.
     */
    private static Trip backtrackHelper(DGraph graph, Trip trip,
            Trip minTrip) {
        if (trip.citiesLeft().size() == 0) { 
            if (trip.tripCost(graph) < minTrip.tripCost(graph)) {
                minTrip.copyOtherIntoSelf(trip);
                return minTrip;
            }
        }  

        if (trip.tripCost(graph) < minTrip.tripCost(graph)) {
            for (int city : trip.citiesLeft()) {
                // Choose
                trip.chooseNextCity(city);
                // Explore
                backtrackHelper(graph, trip, minTrip);
                // Unchoose
                trip.unchooseLastCity();
            }
        }
        return minTrip;
    }

     /**
     * Solves the Travelling Salesperson problem using recursion.
     * @param numCities: the number of cities in the directed graph.
     *                   each of which is to be visited. 
     * @param graph: directed graph that includes directed edges 
     *               the weight of travel between the cities.
     * @return trip: the shortest path from city 1 to the last city and
     *               back to city 1.
     */
    private static Trip mine(DGraph graph, int numCities) {
        Trip trip = new Trip(numCities);
        int currCity = 1;
        Set<Integer> visitedCities = new HashSet<Integer>();
        helper(graph, trip, currCity, visitedCities);
        return trip;
    }
    /**
     * Recursively finds the shortest path from city 1 to the last city and
     * back to the first city.
     * @param graph: directed graph that includes directed edges 
     *               the weight of travel between the cities.
     * @param trip: Trip object that represents what cites are in 
     *              the trip, which of them have been visited, and 
     *              which have not been visited to. 
     * @param currCity: the city the salesperson is currently at.
     * @param visite: the cities that have been visited to.
     * @return true if the given trip is the shortest on, false if not. 
     */
    private static boolean helper(DGraph graph, Trip trip, int currCity, Set<Integer> visited) {
        if (trip.citiesLeft().size() == 0) {
            return true;
        }
        visited.add(currCity);
        trip.chooseNextCity(currCity);
        List<Integer> neighbors = graph.getNeighbors(currCity);
        int neighbor1 = neighbors.get(0);
        for (int i = 1; i < neighbors.size(); i++) {
            int neighbor2 = neighbors.get(i);
            if (visited.contains(neighbor1) && !visited.contains(neighbor2)) {
                neighbor1 = neighbor2;
            } else if (!visited.contains(neighbor2)
                    && graph.getWeight(currCity, neighbor1) > graph.getWeight(currCity, neighbor2)) {
                neighbor1 = neighbor2;
            }
        }
        if (helper(graph, trip, neighbor1, visited)) {
            return true;
        }
        return false;
    }
 

    /**
     * Reads in the mtx file and returns 
     * the string representation of it.
     * @param fileName: the name of the mtx file
     * @return graphStr: the str representation of the mtx file
     * @throws FileNotFoundException: throws an error if file not found.
     */
    public static List<String> readFile(String fileName) throws FileNotFoundException {
        List<String> graphStr = new LinkedList<>();
        Scanner scanner = new Scanner(new File(fileName));
        
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.startsWith("%"))
                graphStr.add(nextLine);   
        }   
        scanner.close();
        return graphStr;
    }
    /**
     * Takes the str represention of the mtx file and 
     * builds the correnpondent directed graph for it.
     * @param graphContent: the str representation of the mtx file.
     * @return graph: directed graph object that includes directed edges and 
     *               the weight of travel between the cities. 
     */
    private static DGraph buildDGraph(List<String> graphContent) {
        DGraph graph = new DGraph(graphContent.size());
        for (int i = 1; i < graphContent.size(); i++) {
            String[] vals = graphContent.get(i).split("\\s+");
            graph.addEdge(Integer.parseInt(vals[0]), 
                          Integer.parseInt(vals[1]),
                          Float.parseFloat(vals[2]));
        }
        return graph;
    }

    /**
     * Measures the time compexity of these three algorithms
     * @param graph: directed graph object that includes directed edges and 
     *               the weight of travel between the cities.  
     * @param numCities: the number of the cities in the graph.
     */
    private static void time(DGraph graph, int numCities) {
        long startTime = System.nanoTime();
        Trip trip = heuristic(numCities, graph);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("heuristic:      cost =      " + trip.tripCost(graph) + ", " + duration +
        " milliseconds");

        long startTime2 = System.nanoTime();
        Trip trip2 = backtrack(numCities, graph);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2) / 1000000;
        System.out.println("backtack:       cost =      " + trip2.tripCost(graph) + ", " + duration2 +
        " milliseconds");

        long startTime3 = System.nanoTime();
        Trip trip3 = mine(graph, numCities);
        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3) / 1000000;
        System.out.println("mine:           cost =      " + trip3.tripCost(graph) + ", " + duration3 +
        " milliseconds");
    }
}


