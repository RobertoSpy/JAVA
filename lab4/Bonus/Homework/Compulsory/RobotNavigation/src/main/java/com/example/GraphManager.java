package com.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.util.*;

public class GraphManager {
    private final Graph<Location, DefaultWeightedEdge> graph =
            new DirectedWeightedPseudograph<>(DefaultWeightedEdge.class);

    
    public Graph<Location, DefaultWeightedEdge> getGraph() {
        return graph;
    }

    
    public void addLocation(Location location) {
        graph.addVertex(location);
    }

   //adaugam muchia cu cost
    public void addDirectedConnection(Location from, Location to, double distance) {
        DefaultWeightedEdge edge = graph.addEdge(from, to);
        if (edge != null) {
            graph.setEdgeWeight(edge, distance);
        }
    }

    public Map<Location, Double> computeShortestPaths(Location start) {
        DijkstraShortestPath<Location, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        Map<Location, Double> shortestPaths = new HashMap<>();

        for (Location location : graph.vertexSet()) {
            if (!location.equals(start)) {
                double distance = dijkstra.getPathWeight(start, location);
                shortestPaths.put(location, distance);
            }
        }
        return shortestPaths;
    }
}
