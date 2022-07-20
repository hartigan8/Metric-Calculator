import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Iterator;
public class CentralityMetricCalculator {
    private final Graph karateClubNetwork = new Graph();
    private final Graph facebookSocialNetwork = new Graph();
    CentralityMetricCalculator(){
        System.out.println("2019510133 Mustafa Can Yılmaz:");
        fileToGraph(karateClubNetwork, "karate_club_network.txt");
        fileToGraph(facebookSocialNetwork, "facebook_social_network.txt");
        karateClubNetwork.calculateMetrics();
        facebookSocialNetwork.calculateMetrics();
        printMetrics(karateClubNetwork, "Zachary Karate Club Network");
        System.out.println();
        printMetrics(facebookSocialNetwork, "Facebook Social Network");
    }
    private void printMetrics(Graph graph, String nameOfTheGraph){
        Vertex biggestBetweenness = new Vertex("init");
        Vertex biggestCloseness = new Vertex("init");
        Iterator<Vertex> it = graph.vertices().iterator();
        while (it.hasNext()){
            Vertex v = it.next();
            if(biggestBetweenness.getBetweenness() < v.getBetweenness()){
                biggestBetweenness = v;
            }
            if(biggestCloseness.getCloseness() < v.getCloseness()){
                biggestCloseness = v;
            }
            //System.out.println(v.getName() + " " + v.getBetweenness() + " " + v.getCloseness());
        }
        System.out.println(nameOfTheGraph + " – The Highest Node for Betweennes and the value " + biggestBetweenness.getName() + " " + biggestBetweenness.getBetweenness());
        System.out.println(nameOfTheGraph + " – The Highest Node for Closeness and the value " + biggestCloseness.getName() + " " + biggestCloseness.getCloseness());
    }
    private void fileToGraph(Graph graph, String filename){
        try{
            File f = new File(filename);
            Scanner scn = new Scanner(f);
            while(scn.hasNextLine()){
                String[] data = scn.nextLine().split(" ");
                graph.addEdge(data[0], data[1]);
            }
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }
}
