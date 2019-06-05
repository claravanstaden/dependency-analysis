package com.claravanstaden;

import java.util.*;

public class DiGraph {
    public Map<Vertex, List<Edge>> getDigraph() {
        return digraph;
    }

    private Map<Vertex, List<Edge>> digraph = new HashMap<>();

    public DiGraph() {
    }

    /**
     * Add a vertex to the digraph. Is only added if a vertex with the same label does not exist in the digraph.
     * @param vertex - The first to be added.
     */
    public void addVertex(Vertex vertex) {
        digraph.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     *
     * @param from
     * @param to
     */
    public void addEdge(Vertex from, Vertex to) {
        List<Edge> edges = digraph.get(from);

        if (edges.contains(new Edge(from, to))) {
            return;
        }

        edges.add(new Edge(from, to));
    }

    public List<String> calculateDependencies(Vertex vertex) {

        List<Edge> edges = digraph.get(vertex);

        List<String> dependencyTokens = new ArrayList<>();

        dependencyTokens.add(vertex.getLabel());

        if (edges.size() == 0) {
            return dependencyTokens;
        }

        LinkedList<Edge> queue = new LinkedList<>(edges);

        List<Edge> edgeDependencies = this.calculateVertexDependencies(queue);

        for (Edge edge : edgeDependencies) {
            if (!dependencyTokens.contains(edge.getTo().getLabel())) {
                dependencyTokens.add(edge.getTo().getLabel());
            }
        }

        return this.sortDependencyTokensAlphabetically(dependencyTokens);
    }

    private List<Edge> calculateVertexDependencies(LinkedList<Edge> queue) {

        List<Edge> dependencies = new ArrayList<>();

        for (Edge item : queue) {
            item.setVisited(true);
        }

        while (!queue.isEmpty()) {
            Edge element = queue.remove();
            if (!dependencies.contains(element)) {
                dependencies.add(element);
            }

            List<Edge> neighbours = digraph.get(element.getTo());

            for (Edge neighbour : neighbours) {
                if (neighbour != null && !neighbour.getVisited()) {
                    queue.add(neighbour);
                }
            }
        }

        this.resetVisited();

        return dependencies;
    }

    public void printDependencies(List<String> dependencyTokens) {
        for (String token : dependencyTokens) {
            System.out.print(token + " ");
        }

        System.out.println();
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

    private void resetVisited() {
        for (Map.Entry<Vertex, List<Edge>> vertexListEntry : digraph.entrySet()) {

            List<Edge> edges = (List<Edge>) ((Map.Entry) vertexListEntry).getValue();

            for (Edge edge : edges) {
                edge.setVisited(false);
            }
        }
    }

    /**
     * Sort the dependency tokens alphabetically. Don't sort the first token in the list, since it indicates
     * the token of which dependencies will be listed.
     * @param list - The unsorted dependency list.
     * @return - The sorted dependency list.
     */
    private List<String> sortDependencyTokensAlphabetically(List<String> list) {
        String firstToken = list.remove(0);

        list.sort(String::compareToIgnoreCase);

        list.add(0, firstToken);

        return list;
    }
}
