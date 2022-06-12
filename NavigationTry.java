package meowsliketrains;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class NavigationTry {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Graph<String> graph = new Graph<>();
        BufferedReader reader = new BufferedReader(new FileReader("0.txt"));
        String text = reader.readLine();
        int nRoutes = Integer.parseInt(text);
        int i = 0;

        while (i < nRoutes) {
            String line = reader.readLine();
            String[] array = line.split("=>");
            String source = array[0].trim();
            String destination = array[1].trim();

            if (!graph.hasVertex(source)) {
                graph.addVertex(source);
            }
            if (!graph.hasVertex(destination)) {
                graph.addVertex(destination);
            }
            graph.addUndirectedEdge(source, destination);
            i++;
        }

        reader.readLine(); // "Queries"
        String str = reader.readLine();

        int queries = Integer.parseInt(str);
        int noOfTrue = 0;
        int j = 0;
        int sourceIndex=0;
        int destinationIndex=0;
        ArrayList<String> BFS= graph.bfs();
        
        while (j < queries) {
            String connection = reader.readLine();
            String[] arr = connection.split("->");
            String src = arr[0].trim();
            String dest = arr[1].trim();
            
            System.out.println(graph.getVertexObject(src).vertexInfo);
            System.out.println(graph.getVertexObject(dest).vertexInfo);
            new shortestPath(graph.getVertexObject(src), graph.getVertexObject(dest));
            j++;
        }
    }
}
