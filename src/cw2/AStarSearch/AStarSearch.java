package AStarSearch;

import Graph.Graph;
import java.util.*;

public class AStarSearch {
    public List<String> aStarSearch(Graph graph, String start, String koniec) {
        if (start == null || koniec == null) {
            throw new IllegalArgumentException();
        }
        if (start.equals(koniec)) {
            return new ArrayList<>();
        }
        Map<String, Double> gScore = new HashMap<>();
        Map<String, String> cameFrom = new HashMap<>();
        Map<String, Double> fScore = new HashMap<>();
        PriorityQueue<String> open = new PriorityQueue<>();
        Set<String> closed = new HashSet<>();
        gScore.put(start, 0.0);
        fScore.put(start, funkHeurystyczna(start, koniec));
        open.add(start);
        while (!open.isEmpty()) {
            String current = open.poll();
            if (current.equals(koniec)) {
                return odtworzDroge(cameFrom, current);
            }
            closed.add(current);
            for (Graph.Vertex v : graph.getAdjVertices(current, true)) {
                if (closed.contains(v.lokalizacja)) {
                    continue;
                }
                double tentativeGScore = gScore.get(current) + 1;
                if (!open.contains(v.lokalizacja) || tentativeGScore < gScore.get(v.lokalizacja)) {
                    open.add(v.lokalizacja);
                } else if (tentativeGScore >= gScore.get(v.lokalizacja)) {
                    continue;
                }
                cameFrom.put(v.lokalizacja, current);
                gScore.put(v.lokalizacja, tentativeGScore);
                fScore.put(v.lokalizacja, gScore.get(v.lokalizacja) + funkHeurystyczna(v.lokalizacja, koniec));
            }
        }
        return null;
    }

    private List<String> odtworzDroge(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        return path;
    }

    private double funkHeurystyczna(String current, String koniec) {
        System.out.println("Sprawdzenie kosztu podrozy z: " + current + " do: " + koniec);
        return Math.abs(current.charAt(0) - koniec.charAt(0)) + Math.abs(current.charAt(1) - koniec.charAt(1));
    }
}