package cw2;

import AStarSearch.AStarSearch;
import Graph.Graph;
import GraphSearch.GraphSearch;

public class Main {

    public static void main(String[] args){

        Graph graph = new Graph();
        graph.addVertex("v1", true);
        graph.addVertex("v2", true);
        graph.addVertex("v3", true);
        graph.addVertex("v4", true);
        graph.addVertex("v5", true);
        graph.addVertex("v6", true);
        graph.addVertex("v7", true);
        graph.addVertex("v8", true);
        graph.addVertex("v9", true);

        graph.addEdge("v1", true, "v2", true);
        graph.addEdge("v1", true, "v3", true);
        graph.addEdge("v3", true, "v4", true);
        graph.addEdge("v2", true, "v6", true);
        graph.addEdge("v6", true, "v5", true);
        graph.addEdge("v5", true, "v7", true);
        graph.addEdge("v4", true, "v8", true);
        graph.addEdge("v8", true, "v9", true);

        System.out.println(
                "Breadth First Traversal: Przechodzenie grafu rozpoczyna się od zadanego wierzchołka i polega na odwiedzeniu wszystkich osiągalnych z niego wierzchołków."
        );
        System.out.println("Przebyta Droga: " + GraphSearch.breadthFirstTraversal(graph, "v1"));

        System.out.println();
        System.out.println(
                "Przeszukiwanie A*: Droga zaczyna się od v1 a kończy na v9, odkurzacz powinien poruszać się po grafie po kolei od v1 do v9."
        );
        System.out.println("Odczytaj drogę z prawej do lewej");

        AStarSearch aSearchMethod = new AStarSearch();

        System.out.println(aSearchMethod.aStarSearch(graph, "v1", "v9"));

        int[][] board = {
                {1, 2, 3},
                {0, 1, 0},
                {1, 0, 1}
        };
    }
}