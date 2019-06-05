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
     * Add a new edge in the digraph.
     * @param from - The vertex where the edge should start.
     * @param to - The vertex where the edge should end.
     */
    public void addEdge(Vertex from, Vertex to) {
        List<Edge> edges = digraph.get(from);

        if (edges.contains(new Edge(from, to))) {
            return;
        }

        edges.add(new Edge(from, to));
    }

    /**
     * Calculate the dependencies of the vertex given.
     * @param vertex - The vertex whose dependencies should be calculated.
     * @return - An array list with the labels of the dependencies.
     */
    public List<Vertex> calculateDependencies(Vertex vertex) {

        List<Edge> edges = digraph.get(vertex);

        List<Vertex> dependencyTokens = new ArrayList<>();

        dependencyTokens.add(vertex);

        if (edges.size() == 0) {
            return dependencyTokens;
        }

        LinkedList<Edge> queue = new LinkedList<>(edges);

        List<Edge> edgeDependencies = this.calculateVertexDependencies(queue);

        for (Edge edge : edgeDependencies) {
            if (!dependencyTokens.contains(edge.getTo())) {
                dependencyTokens.add(edge.getTo());
            }
        }

        return this.sortDependencyTokensAlphabetically(dependencyTokens);
    }

    /**
     * Calculate the dependencies using a breadth-first graph search.
     * @param queue - The queue with the first neighbours of the vertex.
     * @return - A list of edges that make up dependencies.
     */
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
                    neighbour.setVisited(true);
                }
            }
        }

        this.resetVisited();

        return dependencies;
    }

    /**
     * Print the dependencies, separated by a space.
     * @param dependencyTokens - The list of dependencies.
     */
    public void printDependencies(List<Vertex> dependencyTokens) {
        for (Vertex vertex : dependencyTokens) {
            System.out.print(vertex.getLabel() + " ");
        }

        System.out.println();
    }

    /**
     * Print the graph edges.
     */
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

    /**
     * Reset the visited flag on all the edges.
     */
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
    private List<Vertex> sortDependencyTokensAlphabetically(List<Vertex> list) {
        Vertex firstToken = list.remove(0);

        list.sort((object1, object2) -> object1.getLabel().compareTo(object2.getLabel()));
        
        list.add(0, firstToken);

        return list;
    }
}
