package GraphSearch;

import Graph.Graph;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GraphSearch {

    public static Set<String> breadthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            for (Graph.Vertex v : graph.getAdjVertices(vertex, true)) {
                if (!visited.contains(v.lokalizacja)) {
                    visited.add(v.lokalizacja);
                    queue.add(v.lokalizacja);
                    if(v.brud){
                        System.out.println("Odwiedzono i wyczyszczono wezel: " + v.lokalizacja + ".");
                        v.brud = false;
                    }
                }
            }
        }
        return visited;
    }
}
