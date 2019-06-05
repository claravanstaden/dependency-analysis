import com.claravanstaden.DiGraph;
import com.claravanstaden.Edge;
import com.claravanstaden.Vertex;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String userInput;

        Scanner scanner = new Scanner(System.in);

        DiGraph diGraph = new DiGraph();

        System.out.println("Enter tokens, where the first token is the name of the token and the remaining tokens" +
                " are the things that the first token depends on, separated by a whitespace.");
        System.out.println("Type 'done' when completed.");

        while (true) {

            userInput = scanner.next();

            if ("done".equals(userInput)) {
                showDependencies(diGraph);

                System.exit(0);
            }

            addToGraph(userInput, scanner, diGraph);
        }
    }

    /**
     * Build the digraph as the user enters input.
     * @param userInput - The first user input token (first word followed by a white space.)
     * @param scanner - The scanner object.
     * @param diGraph - The current digraph.
     */
    private static void addToGraph(String userInput, Scanner scanner, DiGraph diGraph) {
        Vertex firstItem = new Vertex(userInput);

        diGraph.addVertex(firstItem);

        String input = scanner.nextLine();

        String[] tokens = input.split(" ");

        System.out.println("Adding vertex [" + userInput + "].");

        for (String token : tokens) {
            if (token.equals("")) {
                continue;
            }

            Vertex vertex = new Vertex(token);

            diGraph.addVertex(vertex);

            diGraph.addEdge(firstItem, vertex);

            System.out.println("Adding vertex [" + token + "]. Adding edge [" + userInput + "] to [" + token + "].");
        }
    }

    /**
     * Show the digraph's dependencies for all added vertices.
     * @param diGraph - The digraph object.
     */
    private static void showDependencies(DiGraph diGraph) {
        System.out.println("Graph Edges:");
        System.out.println("================================");
        System.out.println();

        diGraph.printEdges();

        System.out.println("Dependencies:");
        System.out.println("================================");

        for (Map.Entry<Vertex, List<Edge>> vertexListEntry : diGraph.getDigraph().entrySet()) {

            Vertex vertex = (Vertex) ((Map.Entry) vertexListEntry).getKey();

            diGraph.printDependencies(diGraph.calculateDependencies(vertex));
        }

        System.out.println("Inverse Dependencies:");
        System.out.println("================================");

        for (Map.Entry<Vertex, List<Edge>> vertexListEntry : diGraph.getDigraph().entrySet()) {

            Vertex vertex = (Vertex) ((Map.Entry) vertexListEntry).getKey();

            diGraph.printDependencies(diGraph.calculateInverseDependencies(vertex));
        }
    }
}
