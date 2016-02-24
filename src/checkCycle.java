/*
 * Jongbu Sherpa
 * CS 323 (Summer,2015)
 * Prof. Robert Goldberg
 * Algorithm Project: Cycle exists?(using topological sort)
 * Submitted on: 8/2/15
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class checkCycle
{
    private Stack<Integer> vertices;//all the vertices to be stored in this stack
 
    /* default constructor */
    public checkCycle(){
    	vertices = new Stack<Integer>();
    }
  
    /*
     * Method Name:             TopologicalSorting
     * Parameters : 			int adjMatrix[][], int start,FileWriter w
     * Method Description: 
     * 	It's main function is to find the topologically sorted list and store it in the stack.
     * 	It takes the input from the integer adjacency Matrix and a initial point/vertex. Since 
     * 	we are printing the output in the text file, we take extra "FileWriter" as an extra 
     * 	parameter to print error in text file if we find cycle.
     * 
     *  Return: 
     *  It returns an integer array of topologically sorted vertices.
     * 
     */
    public int [] topologicalSorting(int adjMatrix[][], int start,FileWriter w) throws IOException{
        int totalVertices = adjMatrix[start].length-1;
        int[] result = new int [totalVertices+1];
        int i = 1;//indexing result array
        int x = start;//the index numbers for array
        int y;// stores the integer from stack pop
        int visited[] = new int[totalVertices+1];
        int root = start;//starting vertex 
        visited[start] = 1;
        vertices.push(start);
 
        while (!vertices.isEmpty()){//until we pop out the last element in the stack
            root = vertices.peek();//the top of the stack
            
            while (x <= totalVertices){//to check all the vertices
                if (adjMatrix[root][x]==1 && visited[x]==1){
                	//checking if the vertex is already in the vertices stack
                	if (vertices.contains(x))
                       printError(x,w);//printing error since it causes a cycle                  
                } 
                	//if there's a link and it is not visited
                if (adjMatrix[root][x]==1 && visited[x]==0){
                	vertices.push(x);//store that vertex
                    visited[x] = 1;//turn on the visited flag
                    root = x;//now we look for the adjacent vertices from that vertex
                    x = 1;
                }
                x++;
            }
            y = vertices.pop();	
            result[i++] = y;//storing the value popped from the stack
            x = ++y;//next vertex
        }
        return result;
    }	
    
    /*
     * Method Name:             cycleFinder
     * Parameters : 			Scanner inputScanner,FileWriter outputWriter
     * Method Description: 
     * 	It's main function is to store the input binary string from the text file in 
     *  multidimensional array so that it represents as an adjacency matrix.
     *  	It calls the function "topologicalSorting" to get the resulting topologically
     *  sorted array and print the output in the console as well as in the Text File.
     * 
     */
    public static void cycleFinder(Scanner inputScanner,FileWriter outputWriter) throws IOException{
    	int totNodes, start;  
        int result[]  = null; 
    	inputScanner.useDelimiter("\\s");
    	totNodes=Integer.parseInt(inputScanner.next());//number of vertices
        System.out.println("Number of Vertices: "+totNodes);
        inputScanner.nextLine();
        
        int adjMatrix[][] = new int[totNodes + 1][totNodes + 1];//to store the adjacency list in the array
        System.out.println("Adjacency matrix:");
            
	    for (int i=1;i<=totNodes;i++){
                for (int j=1; j<=totNodes; j++){
                    adjMatrix[i][j] = Integer.parseInt(inputScanner.next());//storing from the input file
                    System.out.print(adjMatrix[i][j]+ " ");
                }
                System.out.println();//new line
                if(inputScanner.hasNextLine())
                	inputScanner.nextLine();//move to next row
	    }
            System.out.println("Starting from vertex 1....");
            outputWriter.write("Starting from vertex 1....\r\n");
            start = 1;//we start with node 1 as default 
            
            checkCycle graph = new checkCycle();
            result = graph.topologicalSorting(adjMatrix, start,outputWriter);//to store the sorted graph in the array
            
            //printing out the result
            outputWriter.write("Inputted graph does not have any cycle\r\n");
            System.out.println("Inputted graph does not have any cycle");
            outputWriter.write("Topological Sort: \r\n");
            System.out.println("Topological Sort: ");
            
            for (int i=result.length-1;i>0;i--){
                if (result[i] != 0){
                    System.out.print(result[i]+"\t");
                    outputWriter.write(result[i]+"\t");
                }            
            } 	  
        inputScanner.close();
        outputWriter.close();
    }
    
    /*
     * Method Name: printError
     * Parameters : int s,FileWriter w
     * Method Description:
     * 	To print out the error message and the vertex "s" causing the error/cycle 
     *  in the console and also write it in the Output Text file.
     */
    public static void printError(int s,FileWriter w) throws IOException{
    	System.out.println("Inputted graph has a cycle:");
    	System.out.println("first cycle occurence in vertex "+s);
    	w.write("Inputted graph has a cycle:\r\n");
    	w.write("first cycle occurence in vertex "+s+"\r\n");
        System.exit(0);
    }
 
    /*main method*/
    public static void main(String args[]) throws IOException{        
        FileWriter writer = new FileWriter(new File("output.txt"));//use your own path
        Scanner reader=new Scanner(new File("input.txt"));  
        
        cycleFinder(reader,writer);//calling method with two parameters as input and output
        
        writer.close();
        reader.close();
    }
}