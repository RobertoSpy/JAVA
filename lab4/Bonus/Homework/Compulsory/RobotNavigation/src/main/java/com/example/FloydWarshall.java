package com.example;

import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

public class FloydWarshall {
    private final GraphManager graphManager;

    public FloydWarshall(GraphManager graphManager) {
        this.graphManager = graphManager;
    }

    
    public Map<Location, Map<Location, Double>> computeAllPairShortestPaths() {
        Map<Location, Map<Location, Double>> dist = new HashMap<>();
        
        
        for (Location v : graphManager.getGraph().vertexSet()) {
            dist.put(v, new HashMap<>());
            for (Location u : graphManager.getGraph().vertexSet()) {
                dist.get(v).put(u, Double.POSITIVE_INFINITY);
            }
            dist.get(v).put(v, 0.0); 
        }

       
        for (DefaultWeightedEdge edge : graphManager.getGraph().edgeSet()) {
            Location source = graphManager.getGraph().getEdgeSource(edge);
            Location target = graphManager.getGraph().getEdgeTarget(edge);
            dist.get(source).put(target, graphManager.getGraph().getEdgeWeight(edge));
        }

       
        for (Location k : graphManager.getGraph().vertexSet()) {
            for (Location i : graphManager.getGraph().vertexSet()) {
                for (Location j : graphManager.getGraph().vertexSet()) {
                    double newDist = dist.get(i).get(k) + dist.get(k).get(j);
                    if (newDist < dist.get(i).get(j)) {
                        dist.get(i).put(j, newDist);
                    }
                }
            }
        }

        return dist;
    }
}
