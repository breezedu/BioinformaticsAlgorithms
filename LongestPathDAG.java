package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Edge{
	
	int From;
	int To;
	int Length;
} // end Edge class;

/*************
 * 
 * @_@ author Frog Du
 * 
 * CODE CHALLENGE: Solve the Longest Path in a DAG Problem.
 *      Input: 
 *      An integer representing the source node of a graph, 
 *      followed by an integer representing the sink
 *      node of the graph, followed by a list of edges in the graph. 
 *      The edge notation 0->1:7 indicates that
 *      an edge connects node 0 to node 1 with weight 7.
 *      
 *      Output: 
 *      The length of a longest path in the graph, followed by a longest path.
 *      
 */

public class LongestPathDAG {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("read edge set with P->P:distance. from file: ");
		// create a new scanner with the specified String Object
	    Scanner Input = new Scanner(new File("longest_path_DAG.txt"));
		//Scanner Input = new Scanner(new File("dataset_74_71.txt"));
		
	    int Start = Integer.parseInt( Input.nextLine()); 
	    int End = Integer.parseInt( Input.nextLine());
	    
	    ArrayList<Edge> EdgeList = new ArrayList<Edge>();
	    
	    while(Input.hasNextLine()){
	    
	    	EdgeList.add(buildEdge(Input.next()));
	    } // end while, readIn all lines in the txt document;
	    
	    Input.close();      // close the scanner
	    
	    System.out.println("Printout all elements in the ArrayList.");
	    System.out.println("From: " + Start +" to " + End +".");
	    for(Edge e:EdgeList){
	    	System.out.println("edge[" + e.From + ", " + e.To +"] length= " + e.Length +"");
	    } // end for e:edgeList loop; all edges and their lengths printed;
	    	
	    System.out.println("Calculate the logest path from Vertice["+Start+"] to Vertice[" + End+"].");
	    
	    ArrayList<Integer> Vertices = new ArrayList<Integer>();
	    Vertices.add(End);
	    int Length = 0;
	    
	    backTractEdges(End, Start, EdgeList, Length, Vertices);
	    
	    
	} // end of main()

	private static void backTractEdges(int end, int start, ArrayList<Edge> edgeList, int length, ArrayList<Integer> vertices) {
		// TODO Back Tract the Routine from End point to Start point;
		/*************************
		 * pass the start point, end point, all edges in the graph, 
		 * the length of total-edges already on the routine,
		 * and vertices included to the method;
		 * 
		 * if (end == start), we got a routine, print the total length of all edges,
		 * followed with all vertices;
		 * 
		 * every time calling the backTractEdges method;
		 * calculate the predecessor edges to the 'end' point, 
		 * then add new edges and new vertices to the vertices ArrayList;
		 * 
		 */
		
		
		if (start == end){
			/********
			 * when the recursion reached the Starting point,
			 * the recursion reaches the end;
			 * print out total length of all edges on the routine;
			 * print out the vertices one by one;
			 */
			
			System.out.print("\nOne routine, length= " + length +". Routine is: ");
			for(int Vers:vertices){
				System.out.print("" + Vers +"->");
			} // end for Vers loop; printout all vertices in the ArrayList;
			//return;
		}  else {
			
			for(Edge e:edgeList){
			
				if(e.To == end){
					
					int newLength = length + e.Length;
					
					ArrayList<Integer> newVertices = new ArrayList<Integer>();
					/********
					 * Declare create and assign a new arrayList with Vertices;
					 * When call the recursion, pass the new arrayList to backTractEdge() method;
					 */
					
					newVertices.add(e.From);
					
					for(int V:vertices){
						newVertices.add(V);
					} // end inner for loop; all vertices from old arrayList added to newVertices;
					
					backTractEdges(e.From, start, edgeList, newLength, newVertices);
					
					// System.out.print(" [" + e.From +"-" +end +"]");
				
				} // end if(e.To == end);
			} // end for e:edgeList loop;
		} // end if-else condition;
		
	} // end backTractEdges() method;

	private static Edge buildEdge(String edgeStr) {
		// TODO to build an Edge from a String with 3 integers;
		/**************
		 * the 'strings' passed to the buildEdge method are Edges well formatted;
		 * like "2->13:12", here 2 means from point, 13 means to point; 12 means length;
		 * here we use buildEdge method to split the string into three integers;
		 * and store the three integers into a Edge data structure;
		 * 
		 */

		//System.out.print(" " + edgeStr);
		
    	Edge edgeTemp = new Edge();
    
       	String[] stringOne = edgeStr.split(":"); // split the vertices and the length;
	    String[] stringTwo = stringOne[0].split("->"); // split the two vertices;
	      
	    int[] num = new int[stringOne.length + stringTwo.length -1];
	    num[0] = Integer.parseInt(stringTwo[0]); // the from point;
	    num[1] = Integer.parseInt(stringTwo[1]); // the to point;
	    num[2] = Integer.parseInt(stringOne[1]); // the length of the edge;
	      
	    edgeTemp.From = num[0];   	// assign the from vertices;
	    edgeTemp.To = num[1]; 		// assign the to vertices;
	    edgeTemp.Length = num[2];	// assign the length;
		
		return edgeTemp;
	} // end buildEdge() method;


} // end of everything: LongestpathDAG;

/****************
 read edge set with P->P:distance. from file: 
Printout all elements in the ArrayList.
From: 1 to 38.
edge[12, 27] length= 5
edge[35, 38] length= 32
edge[11, 23] length= 29
edge[27, 30] length= 10
edge[12, 21] length= 29
edge[39, 40] length= 18
edge[17, 40] length= 9
edge[17, 22] length= 38
edge[24, 29] length= 28
edge[20, 33] length= 16
edge[10, 15] length= 20
edge[0, 5] length= 7
edge[4, 29] length= 12
edge[9, 12] length= 28
edge[7, 24] length= 5
edge[14, 36] length= 13
edge[7, 13] length= 2
edge[16, 20] length= 12
edge[16, 27] length= 4
edge[19, 36] length= 33
edge[16, 25] length= 1
edge[37, 38] length= 4
edge[16, 41] length= 6
edge[3, 15] length= 23
edge[19, 38] length= 25
edge[30, 41] length= 39
edge[21, 24] length= 20
edge[21, 27] length= 33
edge[11, 26] length= 31
edge[7, 26] length= 6
edge[35, 41] length= 9
edge[13, 20] length= 21
edge[7, 25] length= 13
edge[17, 35] length= 6
edge[9, 32] length= 8
edge[26, 32] length= 1
edge[2, 30] length= 5
edge[2, 10] length= 22
edge[1, 27] length= 23
edge[2, 16] length= 25
edge[31, 41] length= 25
edge[29, 30] length= 20
edge[5, 16] length= 37
edge[0, 11] length= 21
edge[5, 12] length= 16
edge[1, 23] length= 13
edge[0, 33] length= 3
edge[3, 32] length= 38
edge[9, 22] length= 5
edge[0, 37] length= 12
edge[7, 40] length= 30
edge[13, 41] length= 20
edge[21, 40] length= 38
edge[27, 33] length= 19
edge[20, 22] length= 33
edge[33, 37] length= 13
edge[12, 16] length= 20
edge[1, 15] length= 10
edge[4, 33] length= 0
edge[12, 15] length= 19
edge[12, 13] length= 17
edge[10, 40] length= 12
edge[35, 40] length= 17
edge[32, 33] length= 18
edge[18, 30] length= 11
edge[26, 29] length= 22
edge[19, 40] length= 23
edge[20, 36] length= 17
edge[10, 24] length= 36
edge[32, 38] length= 33
edge[10, 21] length= 9
edge[11, 18] length= 6
edge[5, 9] length= 28
edge[3, 22] length= 7
edge[23, 38] length= 3
edge[3, 20] length= 28
edge[7, 10] length= 20
edge[5, 29] length= 25
edge[22, 37] length= 19
edge[9, 41] length= 6
edge[17, 29] length= 7
edge[16, 28] length= 7
edge[5, 26] length= 10
edge[7, 32] length= 29
edge[15, 38] length= 4
Calculate the logest path from Vertice[1] to Vertice[38].

One routine, length= 59. Routine is: 1->27->33->37->38->
One routine, length= 16. Routine is: 1->23->38->
One routine, length= 14. Routine is: 1->15->38->
 */
