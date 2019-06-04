package com.claravanstaden;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiGraphTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getDigraph() {
    }

    @Test
    public void addVertex() {
    }

    @Test
    public void addEdge() {
    }

    @Test
    public void printEdges() {
    }

    @Test
    public void printDependenciesAlphabetically() {
        DiGraph diGraph = new DiGraph();

        Vertex A = new Vertex("A");
        diGraph.addVertex(A);
        Vertex B = new Vertex("B");
        diGraph.addVertex(B);
        diGraph.addEdge(A, B);
        Vertex C = new Vertex("C");
        diGraph.addEdge(A, C);

        B = new Vertex("B");
        diGraph.addVertex(B);
        C = new Vertex("C");
        diGraph.addVertex(C);
        diGraph.addEdge(B, C);
        Vertex E = new Vertex("E");
        diGraph.addVertex(E);

        C = new Vertex("C");
        diGraph.addVertex(C);
        Vertex G = new Vertex("G");
        diGraph.addVertex(G);
        diGraph.addEdge(C, G);

        Vertex D = new Vertex("D");
        diGraph.addVertex(D);
        A = new Vertex("A");
        diGraph.addVertex(A);
        diGraph.addEdge(D, A);
        Vertex F = new Vertex("F");
        diGraph.addVertex(F);
        diGraph.addEdge(D, F);

        E = new Vertex("E");
        diGraph.addVertex(E);
        F = new Vertex("F");
        diGraph.addVertex(F);
        diGraph.addEdge(E, F);

        F = new Vertex("F");
        diGraph.addVertex(F);
        Vertex H = new Vertex("H");
        diGraph.addVertex(H);
        diGraph.addEdge(F, H);

        diGraph.printDependenciesAlphabetically(A);
    }
}