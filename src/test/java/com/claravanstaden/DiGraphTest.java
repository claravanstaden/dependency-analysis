package com.claravanstaden;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DiGraphTest {
    private DiGraph diGraph;

    @Before
    public void setUp() throws Exception {
        diGraph = new DiGraph();

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
        diGraph.addEdge(B, E);

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
    }


    @Test
    public void testTokenADependencies() {

        List<String> dependencies = diGraph.calculateDependencies(new Vertex("A"));
        List<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("E");
        expected.add("F");
        expected.add("G");
        expected.add("H");

        assertEquals(expected, dependencies);
    }

    @Test
    public void testTokenBDependencies() {

        List<String> dependencies = diGraph.calculateDependencies(new Vertex("B"));
        List<String> expected = new ArrayList<>();
        expected.add("B");
        expected.add("C");
        expected.add("E");
        expected.add("F");
        expected.add("G");
        expected.add("H");

        assertEquals(expected, dependencies);
    }

    @Test
    public void testTokenCDependencies() {

        List<String> dependencies = diGraph.calculateDependencies(new Vertex("C"));
        List<String> expected = new ArrayList<>();
        expected.add("C");
        expected.add("G");

        assertEquals(expected, dependencies);
    }

    @Test
    public void testTokenDDependencies() {

        List<String> dependencies = diGraph.calculateDependencies(new Vertex("D"));
        List<String> expected = new ArrayList<>();
        expected.add("D");
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("E");
        expected.add("F");
        expected.add("G");
        expected.add("H");

        assertArrayEquals(expected.toArray(), dependencies.toArray());
    }

    @Test
    public void testTokenEDependencies() {

        List<String> dependencies = diGraph.calculateDependencies(new Vertex("E"));
        List<String> expected = new ArrayList<>();
        expected.add("E");
        expected.add("F");
        expected.add("H");

        assertEquals(expected, dependencies);
    }

    @Test
    public void testTokenFDependencies() {

        List<String> dependencies = diGraph.calculateDependencies(new Vertex("F"));
        List<String> expected = new ArrayList<>();
        expected.add("F");
        expected.add("H");

        assertEquals(expected, dependencies);
    }

    @Test
    public void testCircularReference() {

        Vertex F = new Vertex("F");
        diGraph.addVertex(F);
        Vertex H = new Vertex("H");
        diGraph.addVertex(H);
        diGraph.addEdge(H, F);

        diGraph.calculateDependencies(new Vertex("F"));
    }
}