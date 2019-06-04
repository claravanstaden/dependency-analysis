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

        List<Edge> dependencies = this.calculateVertexDependencies(queue);

        this.sortAlphabetically(dependencies);

        for (Edge edge : dependencies) {
            edge.getTo().printLabel();
        }

        System.out.println();
    }

    private List<Edge> calculateVertexDependencies(LinkedList<Edge> queue) {

        List<Edge> dependencies = new ArrayList<>();
        List<String> visited = new ArrayList<>();

        for (Edge item : queue) {
            visited.add(item.getTo().getLabel());
        }

        while (!queue.isEmpty()) {
            Edge element = queue.remove();
            dependencies.add(element);

            List<Edge> neighbours = digraph.get(element.getTo());

            for (Edge neighbour : neighbours) {
                if (neighbour != null && !visited.contains(neighbour.getTo().getLabel())) {
                    queue.add(neighbour);
                    visited.add(neighbour.getTo().getLabel());
                }
            }
        }

        return dependencies;
    }

    private void sortAlphabetically(List<Edge> sorted) {
        sorted.sort(Comparator.comparing(edge -> edge.getTo().getLabel()));
    }
}
