import com.claravanstaden.DiGraph;
import com.claravanstaden.Edge;
import com.claravanstaden.Vertex;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        /*
        com.claravanstaden.Vertex A = new com.claravanstaden.Vertex("A");
        com.claravanstaden.Vertex B = new com.claravanstaden.Vertex("B");
        com.claravanstaden.Vertex C = new com.claravanstaden.Vertex("C");
        com.claravanstaden.Vertex D = new com.claravanstaden.Vertex("D");
        com.claravanstaden.Vertex E = new com.claravanstaden.Vertex("E");
        com.claravanstaden.Vertex F = new com.claravanstaden.Vertex("F");
        com.claravanstaden.Vertex G = new com.claravanstaden.Vertex("G");
        com.claravanstaden.Vertex H = new com.claravanstaden.Vertex("H");

        DiGraph diGraph = new DiGraph();

        diGraph.addVertex(A);
        diGraph.addVertex(B);
        diGraph.addVertex(C);
        diGraph.addVertex(D);
        diGraph.addVertex(E);
        diGraph.addVertex(F);
        diGraph.addVertex(G);
        diGraph.addVertex(H);

        diGraph.addEdge(A, B);
        diGraph.addEdge(A, C);
        diGraph.addEdge(B, C);
        diGraph.addEdge(B, E);
        diGraph.addEdge(C, G);
        diGraph.addEdge(D, A);
        diGraph.addEdge(D, F);
        diGraph.addEdge(E, F);
        diGraph.addEdge(F, H);

        diGraph.printDependenciesAlphabetically(A);
        diGraph.printDependenciesAlphabetically(B);
        diGraph.printDependenciesAlphabetically(C);
        diGraph.printDependenciesAlphabetically(D);
        diGraph.printDependenciesAlphabetically(E);
        diGraph.printDependenciesAlphabetically(F);
        diGraph.printDependenciesAlphabetically(G);
        diGraph.printDependenciesAlphabetically(H);

         */

        String userInput;

        Scanner scanner = new Scanner(System.in);

        DiGraph diGraph = new DiGraph();

        System.out.println("Enter tokens, where the first token is the name of the token and the remaining tokens" +
                " are the things that the first token depends on, separated by a whitespace.");
        System.out.println("Type 'done' when completed.");
        while (true) {
            //Print the options for the user to choose from

            //Capture the user input in scanner object and store it in a pre decalred variable
            userInput = scanner.next();

            //Check the user input
            if ("done".equals(userInput)) {

                diGraph.printEdges();

                System.out.println("************************");

                for (Map.Entry<Vertex, List<Edge>> vertexListEntry : diGraph.getDigraph().entrySet()) {

                    Vertex vertex = (Vertex) ((Map.Entry) vertexListEntry).getKey();

                    diGraph.printDependenciesAlphabetically(vertex);
                }
                System.exit(0);

            }

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
    }
}
