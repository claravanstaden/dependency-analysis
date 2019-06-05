package com.claravanstaden;

import java.util.Objects;

public class Edge {

    private Vertex to;
    private Vertex from;
    private Boolean visited = false;

    public Edge(Vertex from, Vertex to) {
        this.to = to;
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(to, edge.to) &&
                Objects.equals(from, edge.from) &&
                Objects.equals(visited, edge.visited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, from, visited);
    }
}
