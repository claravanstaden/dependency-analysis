package com.claravanstaden;

import java.util.*;

public class DiGraph {
    public Map<Vertex, List<Edge>> getDigraph() {
        return digraph;
    }

    private Map<Vertex, List<Edge>> digraph = new HashMap<>();

    public DiGraph() {
    }

    public void addVertex(Vertex vertex) {
        digraph.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex from, Vertex to) {
        List<Edge> edges = digraph.get(from);

        if (edges.contains(new Edge(from, to))) {
            return;
        }

        edges.add(new Edge(from, to));
    }

    public void printEdges() {
        for (Map.Entry<Vertex, List<Edge>> vertexListEntry : digraph.entrySet()) {

            Vertex vertex = (Vertex) ((Map.Entry) vertexListEntry).getKey();
            List<Edge> edges = (List<Edge>) ((Map.Entry) vertexListEntry).getValue();
            System.out.print(vertex.getLabel() + " ");

            for (Edge edge : edges) {
                System.out.print(" > " + edge.getTo().getLabel() + " ");
            }
            System.out.println();
        }
    }

    public void printDependenciesAlphabetically(Vertex vertex) {

        vertex.printLabel();

        List<Edge> edges = digraph.get(vertex);

        if (edges.size() == 0) {
            return;
        }

        LinkedList<Edge> queue = new LinkedList<>(edges);
        for (Edge neighbour : edges) {
            neighbour.setVisited(true);
        }

        List<Edge> dependencies = this.calculateVertexDependencies(queue);

        this.sortAlphabetically(dependencies);

        for (Edge edge : dependencies) {
            edge.getTo().printLabel();
        }

        this.resetVisited();

        System.out.println();
    }

    private List<Edge> calculateVertexDependencies(LinkedList<Edge> queue) {
        List<Edge> dependencies = new ArrayList<>();

        while (!queue.isEmpty()) {
            Edge element = queue.remove();
            dependencies.add(element);

            List<Edge> neighbours = digraph.get(element.getTo());

            for (Edge n : neighbours) {
                if (n != null && !n.getVisited()) {
                    queue.add(n);
                    n.setVisited(true);
                }
            }
        }

        return dependencies;
    }

    private void resetVisited() {
        for (Map.Entry<Vertex, List<Edge>> vertexListEntry : digraph.entrySet()) {

            List<Edge> edges = (List<Edge>) ((Map.Entry) vertexListEntry).getValue();

            for (Edge edge : edges) {
                edge.setVisited(false);
            }
        }
    }

    private void sortAlphabetically(List<Edge> sorted) {
        sorted.sort(Comparator.comparing(edge -> edge.getTo().getLabel()));
    }
}
