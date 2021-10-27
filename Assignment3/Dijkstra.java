import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Dijkstra {

		static class Edge {
	        int source;
	        int destination;
	        int weight;

	        public Edge(int source, int destination, int weight) {
	            this.source = source;
	            this.destination = destination;
	            this.weight = weight;
	        }
			public void PrintEdge() {
				System.out.print(source+"----->");
				System.out.print(destination+"    ");
				System.out.print("weight:"+weight);
			}
	    }


	    static class Graph {
	        int vertices;
	        LinkedList<Edge>[] adjacency_list;

	        Graph(int vertex_num) {
	            this.vertices = vertex_num;
	            adjacency_list = new LinkedList[vertex_num];
	            //initialize adjacency lists for all the vertices
	            for (int i = 0; i <vertex_num ; i++) {
	                adjacency_list[i] = new LinkedList<>();
	            }
	        }

	        public void addEdge(int source, int destination, int weight) {
	            Edge edge = new Edge(source, destination, weight);
	            adjacency_list[source].addFirst(edge);

//	            edge = new Edge(destination, source, weight);
//	            adjacencylist[destination].addFirst(edge); //for undirected graph
	        }

			public void printGraph() {
				for (int i = 1; i < adjacency_list.length; i++)
				{
					System.out.println("\nVertex " + i + ":");
//					adjacencylist[i].getFirst().PrintEdge();
					System.out.println("edges: "+adjacency_list[i].size());
					for (int j = 0; j < adjacency_list[i].size(); j++)
					{
						adjacency_list[i].get(j).PrintEdge();
						System.out.println();
					}
					System.out.println();
				}
			}
			public void dijkstra(int source) {
				int INFINITY = Integer.MAX_VALUE;
				//keep track of the vertices which are currently in the heap
				boolean[] current_vertices = new boolean[vertices];
				//initialize a new empty integer array that has a size of the vertices
				int[] initial = new int[vertices];
				//set the values of each element in the heap into INFINITY
				//This is because for Dijkstra algorithm, every node except the start node is set to INF
				for (int i = 0; i < vertices; i++) {
					initial[i] = INFINITY;
				}
				//create a empty heap that with previously created INF array
				Heap heap = new Heap(initial);
				//set the source node value into 0, there is no distance from source to source
				heap.decrease_key(source, 0);
				//while the heap is not empty, that is, we haven't iterate all the nodes
				while (!heap.isEmpty()) {
					//get the minimum element of the heap
					//in the examples found on google, they implement a node-heap structure which for every node
					//in heap there is two data elements which are vertex# and distance, but here I used heap.min_id()
					//instead, because we can make the vertex# as the id of the node, and the distance as the key value.
					int extractedNode = heap.min_id();
					//extract the node even if we dont use the value, we wanna make heap.currentsize--
					int extractedNodevalue = heap.delete_min();
					//then mark the node as "extracted" so we wont process it furthermore
					current_vertices[extractedNode] = true;
					//extract the adjacency list of the extracted Node, so we can access the connected elements
					LinkedList<Edge> list = adjacency_list[extractedNode];
					//traverse the linkedlist, so we can access all the edges connected to that node
					for (int i = 0; i < list.size(); i++) {
						//get the individual edge
						Edge edge = list.get(i);
						//the destination of that edge
						int destination = edge.destination;
						//only if this vertex is still present(not extracted before)
						if (current_vertices[destination]==false) {
							//check if the distance needs an update or not
							//check total weight from source to vertex_V is less than
							//the current distance value, if yes then update the distance
							int newKey = heap.key(extractedNode) + edge.weight;
							int currentKey = heap.key(destination);
							if (currentKey > newKey) {
								heap.decrease_key(destination, newKey);
							}
						}
					}
				}
				//print the result of Dijkstra algorithm
				for (int i = 0; i <vertices ; i++) {
					System.out.println(source + " -------> " + i + "    minimum distance: " + heap.key(i));
				}
				System.out.println();
			}
//			public void dijkstra(int source) {
//				int INFINITY = Integer.MAX_VALUE;
//				int[] initial = new int[vertices];
//				for (int i = 0; i < vertices; i++) {
//					initial[i] = INFINITY;
//				}
//				Heap heap = new Heap(initial);
//				Heap heap2 = new Heap(initial);
//				heap.decrease_key(1, 0);
//				while (heap.isEmpty()) {
//					int extractedNode = heap.delete_min();
//					LinkedList<Edge> list = adjacencylist[extractedNode];
//					for (int i = 0; i < list.size(); i++) {
//						Edge edge = list.get(i);
//						int destination = edge.destination;
//						//only if  destination vertex is not present in SPT
//						if (heap.in_heap(destination) == false) {
//							///check if distance needs an update or not
//							//means check total weight from source to vertex_V is less than
//							//the current distance value, if yes then update the distance
//							int newKey = heap.key(extractedNode) + edge.weight;
//							int currentKey = heap.key(destination);
//							if (currentKey > newKey) {
//								heap.decrease_key(newKey, destination);
//								heap2.decrease_key(newKey, destination);
//							}
//							System.out.println("Source Vertex: " + source + " to vertex " +   + i +
//									" distance: " + heap.key(i));
//						}
//					}
//				}
//				for (int i = 0; i <vertices ; i++) {
//					System.out.println("Source Vertex: " + source + " to vertex " +   + i +
//							" distance: " + heap.key(i));
//				}
//			}



}
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		//reads the file
		reader=new BufferedReader(new FileReader("sssp_graph_medium.txt"));
		//spilt line
		String line = reader.readLine();
		//get the total vertex
		int num_of_v=Integer.parseInt(line);
		//create the graph
		Graph g=new Graph(num_of_v+1);
		String[] numbers;
		while((line=reader.readLine())!=null) {
			//this line ignores all the heading or ending whitespaces and spilt whitespaces with single or
			//multiple occurrences
			numbers = line.replaceAll("(^\\s+|\\s+$)", "").split(" +");
			//add edges into the graph
			g.addEdge(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]));
		}
		//driver code
		g.printGraph();
		g.dijkstra(1);
		g.dijkstra(2);

	}
}