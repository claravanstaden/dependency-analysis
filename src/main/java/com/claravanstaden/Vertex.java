package com.claravanstaden;

import java.util.Objects;

public class Vertex {

    private String label;
    private Boolean visited = false;

    public Vertex(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex token = (Vertex) o;
        return Objects.equals(label, token.label);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public void printLabel() {
        System.out.print(this.getLabel() + " ");
    }
}
