package com.claravanstaden;

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
}
