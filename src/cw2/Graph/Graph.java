package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Vertex, List<Vertex>> wierzcholki;

    public Graph() {
        this.wierzcholki = new HashMap<Vertex, List<Vertex>>();
    }

    public void addVertex(String lokalizacja, Boolean brud){
        wierzcholki.putIfAbsent(new Vertex(lokalizacja, brud), new ArrayList<>());
    }

    public void removeVertex(String lokalizacja, Boolean brud){
        Vertex v = new Vertex(lokalizacja, brud);
        wierzcholki.values().stream().forEach(e -> e.remove(v));
        wierzcholki.remove(new Vertex(lokalizacja, brud));
    }

    public void addEdge(String lokalizacja1, Boolean brud1, String lokalizacja2, Boolean brud2) {
        Vertex v1 = new Vertex(lokalizacja1, brud1);
        Vertex v2 = new Vertex(lokalizacja2, brud2);
        wierzcholki.get(v1).add(v2);
        wierzcholki.get(v2).add(v1);
    }

    public void removeEdge(String lokalizacja1, Boolean brud1, String lokalizacja2, Boolean brud2) {
        Vertex v1 = new Vertex(lokalizacja1, brud1);
        Vertex v2 = new Vertex(lokalizacja2, brud2);
        List<Vertex> eV1 = wierzcholki.get(v1);
        List<Vertex> eV2 = wierzcholki.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    public List<Vertex> getAdjVertices(String lokalizacja, Boolean brud){
        return wierzcholki.get(new Vertex(lokalizacja, brud));
    }

    public class Vertex {
        public String lokalizacja;
        public boolean brud;

        Vertex(String lokalizacja, Boolean brud){
            this.brud = brud;
            this.lokalizacja = lokalizacja;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((lokalizacja == null) ? 0 : lokalizacja.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (lokalizacja == null) {
                if (other.lokalizacja != null)
                    return false;
            } else if (!lokalizacja.equals(other.lokalizacja))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return lokalizacja;
        }

        private Graph getOuterType() {
            return Graph.this;
        }
    }
}
