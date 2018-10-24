package boursier.louis.coursework1;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Use Fibonacci heap to optimize the data structure (no need to sort anymore)
// Memorize the distance calculations to optimize

public class Main {

    private static final String ALGO = "ASTAR";

    public static void main(String[] args){

        List<String> file;
        String content = null;
        Cave[] caves;
        Path path = FileSystems.getDefault().getPath("./", "input1.cav");

        try {
            file = Files.readAllLines(path);
            content = file.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] values = content.split(",");
        int cavesNumber = Integer.parseInt(values[0]);
        caves = new Cave[cavesNumber];

        int offset = 1; // we jump the first number which indicates the cave's number
        for(int i=0 ; i<cavesNumber ; i++){
            caves[i] = new Cave(Double.parseDouble(values[i*2+offset]), Double.parseDouble(values[i*2+offset+1]), ""+(i+1));
        }

        offset = 1+2*cavesNumber; // we jump the cave's number and the cave's locations
        for(int i=offset ; i<values.length ; i++){ // creating the connections between caves from the matrix
            int startingCaveIndex = (i-1)%cavesNumber;
            int endingCaveIndex = (i-offset) / cavesNumber;
            if(values[i].equals("1")){
                caves[startingCaveIndex].addLink(caves[endingCaveIndex]); // add connection
            }
        }

        Cave startingCave = caves[0];
        Cave endingCave = caves[cavesNumber-1];
        ArrayList<Cave> toExplore = new ArrayList<>();
        ArrayList<Cave> explored = new ArrayList<>();
        toExplore.add(startingCave); // we start the exploration, from the start...
        startingCave.setDistanceFromStart(0);
        Cave currentCave;

        do{
            currentCave = toExplore.get(0);

            for(Cave link : currentCave.getLinks()){ // we explore each links in the current cave
                // if the cave we discover hasn't been explored, we add it to the exploration list
                if(!explored.contains(link) && link != currentCave){


                    if(!toExplore.contains(link)){
                        toExplore.add(link);
                    }

                    double distanceToThatLink = currentCave.getDistance(link) +
                                                currentCave.getDistanceFromStart();

                    if(distanceToThatLink<link.getDistanceFromStart()){
                        link.setDistanceFromStart(distanceToThatLink);
                        link.setClosestCave(currentCave);
                    }
                }
            }
            toExplore.remove(currentCave); // the current node has been explored, we don't care about it anymore
            explored.add(currentCave); // so that we don't work on that node again
            // We sort the exploration list by their distance from the starting point
            // We can pass from A* to Dijkstra by removing the heuristic
            // The heuristic is the distance from the current cave to the end cave

            if(ALGO == "DIJKSTRA"){
                // DIJKSTRA
                Collections.sort(toExplore, (o1, o2) -> (int) ((o1.getDistanceFromStart() - o2.getDistanceFromStart())));
            }else{
                // ASTAR
                Collections.sort(toExplore, (o1, o2) -> (int) ((o1.getDistanceFromStart() - o2.getDistanceFromStart()) +
                                                               (o1.getDistance(endingCave) - o2.getDistance(endingCave))));
            }

        }while (currentCave!=endingCave && toExplore.size()>0);

        String solution = "";
        double distance = 0.0;
        if(explored.contains(endingCave)){ // print the shortest path backward
            do{
                solution += currentCave.getId() + " ";
                if(currentCave.getClosestCave()!=null){
                    distance += currentCave.getDistance(currentCave.getClosestCave());
                }
            }while((currentCave = currentCave.getClosestCave())!=null);
        }else{
            solution = "No possible path.";
        }

        System.out.println(solution + " --> " + Math.round(distance));
    }
}
