import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
/**
 * a Graph class that implements the GraphInterface
 * For Graph V is the vertex type (a Town), E is the edge type (a Road)
 * @author hyejeongkim
 *
 */

public class Graph implements GraphInterface<Town, Road>{
    private HashSet<Town> towns = new HashSet<Town>();
    private HashSet<Road> roads= new HashSet<Road>();
    private HashSet<Town> adjTowns= new HashSet<Town>();
    private String [] townList ;
	private int townId=0;
    private int [][] edgeMatrix;
	private int max=100;
	
    private int history[];

	/**
	 * construct Graph 
	 */
	public Graph()
	{
		edgeMatrix = new int[max][max];	
	    townList = new String[max];

	}
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		Road returnValue = null;

		if (!towns.contains(sourceVertex)||!towns.contains(destinationVertex))
				{
			      return null;
				}
		else if (sourceVertex==null || destinationVertex==null)
		{
			return null;
		}
		else
		{
			Iterator itr= (Iterator) roads.iterator();
			while (itr.hasNext())
			{
				Town strVertex;
				Town endVertex;
				Road rR = (Road) itr.next();
				strVertex=rR.getSource();
				endVertex=rR.getDestination();
				
				if ((strVertex.equals(sourceVertex)&&endVertex.equals(destinationVertex))||(strVertex.equals(destinationVertex)&&endVertex.equals(sourceVertex)))
				{
					returnValue = rR;
				}
			}
			
			return returnValue;
		}
	}
	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (!towns.contains(sourceVertex)||!towns.contains(destinationVertex))
		{
		    throw new IllegalArgumentException();
		}
		else if(sourceVertex==null || destinationVertex==null)
		{
			throw new NullPointerException();
		}
		else
		{
			Iterator<Road> it = roads.iterator();
			Road rR = null;

		     while(it.hasNext())
		     {
		    	rR = it.next();

		        if(rR.getName().equals(description))
				{
		        	return rR;
				}
		     }	
		     
			Road newRoad = new Road (sourceVertex, destinationVertex, weight, description);
	
			roads.add(newRoad);

			edgeMatrix[sourceVertex.getTownId()][destinationVertex.getTownId()]=weight;
			edgeMatrix[destinationVertex.getTownId()][sourceVertex.getTownId()]=weight;
				
			return newRoad;	
		 }
			
	}
	
	 /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		
		if(!towns.contains(v))
		{
			towns.add(v);
			
			((Town) v).setTownId(townId);
			int index = v.getTownId();
			townList[index] = v.getName();
			townId++;
			return true;
		}
		else if (v==null)
		{
			throw new NullPointerException();
		}
		else
		    return false;
		
	} 
	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
	    Road road = null;
		Iterator itr= (Iterator) roads.iterator();
		
		while (itr.hasNext())
		{
			Town strVertex;
			Town endVertex;
			Road rR = (Road)itr.next();
			strVertex=rR.getSource();
			endVertex=rR.getDestination();
			
			if ((strVertex.equals(sourceVertex)&&endVertex.equals(destinationVertex))||(strVertex.equals(destinationVertex)&&endVertex.equals(sourceVertex)))
			{
				road = (Road) rR;
			}
		}
		if (roads.contains(road))
			return true;
		else
		   return false;
	}
	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		
		if (towns.contains(v))
		{
		     return true;
		}
		else if (!towns.contains(v) || v==null)
		{
			return false;
		}
		else
			return false;
	}
	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
	Iterator itr =(Iterator) roads.iterator();
		
		while (itr.hasNext())
		{
			Road rR = (Road) itr.next();
		}	
		return roads;
	}
	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> edgesOf = new HashSet<Road>();

		Iterator itr= (Iterator) roads.iterator();
		while (itr.hasNext())
		{
			Town strVertex;
			Town endVertex;
			Road rR = (Road)itr.next();

			strVertex=rR.getSource();
			endVertex=rR.getDestination();
			if (strVertex.equals(vertex)||endVertex.equals(vertex))
			{
				edgesOf.add((Road) rR);
			}
		}
		return edgesOf;
	}
	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight greater than - 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		Road targetR = null;
		if(weight>-1 && description != null)
		{   
			Iterator itr= (Iterator) roads.iterator();
			
			while (itr.hasNext())
			{
				Town strVertex;
				Town endVertex;
				targetR = (Road)itr.next();
				strVertex=targetR.getSource();
				endVertex=targetR.getDestination();
				if ((strVertex.equals(sourceVertex)&&endVertex.equals(destinationVertex))||(strVertex.equals(destinationVertex)&&endVertex.equals(sourceVertex)))
				{
					itr.remove();
				}
			}
		  return targetR;
		}
		else
		  return null;
	}
	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		if (towns.contains(v))
		{
			towns.remove(v);
			Iterator itr= (Iterator) roads.iterator();
			while (itr.hasNext())
			{
				Town strVertex;
				Town endVertex;
				Road rR = (Road)itr.next();

				strVertex=rR.getSource();
				endVertex=rR.getDestination();
				if(strVertex.equals(v)||endVertex.equals(v))
				{
					itr.remove();
				}
			}
			return true;
		}
		else if (v==null)
		{
			return false;
		}
		else 
		{
			return false;
		}
	}
	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() 
	{
		return towns;
	}
	 /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     */
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) 
	{
		int x =sourceVertex.townId;
		int y = destinationVertex.townId;
	
		Stack<Integer> myStack = new Stack<Integer>();
		myStack.push(y);
		ArrayList<String> shortestPath = new ArrayList<String>();
		int n = towns.size();
        history = new int[n];
        int previous = -1;
        
		dijkstraShortestPath(sourceVertex);

		while(previous!=x)
		{
		    int temp = history[y];
		    y = temp;
		    myStack.push(y);
			previous = history[y]; 
			
		}
		
		myStack.push(x);
        while(!myStack.isEmpty())
        {
        	int newTownId1, newTownId2;
        	Town start = null, finish = null;
			newTownId1 = myStack.pop();
			if (myStack.isEmpty())
			{
				break;
			}
			newTownId2 = myStack.peek();
			//to make string array list
			Iterator itrTown = (Iterator) towns.iterator();
			
			while (itrTown.hasNext())
			{   
				Town t = (Town)itrTown.next();
				if(t.getTownId() == newTownId1)
				{
					start = t;
				}
				if(t.getTownId() == newTownId2)
				{
					finish = t;
				}
			}
			
			//find road with start and finish
			Iterator itrRoad = (Iterator) roads.iterator();
			while(itrRoad.hasNext())
			{
				Road r = (Road) itrRoad.next();
				if((r.getDestination().equals(finish)&&r.getSource().equals(start)) || (r.getDestination().equals(start)&&r.getSource().equals(finish)))
				{
					String print = start.getName()+" via "+ r.getName()+" to "+ finish.getName()+ " "+r.getWeight()+ " mi";
					shortestPath.add(print);
				}
			}
			
				
        }
    	return shortestPath;
	}
	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) 
	{
		int v = sourceVertex.getTownId();
		int n = towns.size();

        int distance[] = new int[n];          
        boolean[] check = new boolean[n];    
        for(int i=0;i<n;i++){
            distance[i] = Integer.MAX_VALUE;
        }
         
        distance[v] =0;
        check[v] =true;
         
        for(int i=0;i<n;i++){
            if(!check[i] && edgeMatrix[v][i] !=0){
                distance[i] = edgeMatrix[v][i];
                history[i] = v;
            }
        }
         
         
        for(int a=0;a<n-1;a++){

            int min=Integer.MAX_VALUE;
           // int min_index=-1;
             int min_index=0;

            for(int i=0;i<n;i++){
                if(!check[i] && distance[i]!=Integer.MAX_VALUE){
                    if(distance[i]<min ){
                        min=distance[i];
                        min_index = i;
                    }
                }
            }
             
            check[min_index] = true;
            for(int i=0;i<n;i++){
                if(!check[i] && edgeMatrix[min_index][i]!=0){
                    if(distance[i]>distance[min_index]+edgeMatrix[min_index][i]){
                        distance[i] = distance[min_index]+edgeMatrix[min_index][i];
                        history[i] = min_index;
                    }
                }
            }
        }

    }

}
