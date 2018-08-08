import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
/**
 * TownGraphManager will hold an object of your Graph. Implement the TownGraphManagerInterface.
 * @author hyejeongkim
 *
 */
public class TownGraphManager implements TownGraphManagerInterface{
    
	private  Graph graph = new Graph();
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town newTown1 = null ;
		Town newTown2 = null;
		
		HashSet<Town> towns = (HashSet<Town>)graph.vertexSet();
		Iterator itrTown = (Iterator) towns.iterator();
		
		while (itrTown.hasNext())
		{   
			Town t = (Town)itrTown.next();
			if(t.getName().equals(town1))
			{
				newTown1 = t;
			}
			if(t.getName().equals(town2))
			{
				newTown2 = t;
			}
		}
		

		if(graph.addEdge(newTown1, newTown2, weight, roadName)!=null)
		{
			return true;
		}
		else
			return false;
	}
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Town newTown1 = new Town(town1);
		Town newTown2 = new Town(town2);
		
		String road = (graph.getEdge(newTown1, newTown2)).getName();
		return road;
	}
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		Town newTown1 = new Town(v);
		boolean result = graph.addVertex(newTown1);

		return result;
	}
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		Town newTown1 = new Town(v);
		boolean result = graph.containsVertex(newTown1);
		return result;
	}
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town newTown1 = new Town(town1);
		Town newTown2 = new Town(town2);
		boolean result = graph.containsEdge(newTown1, newTown2);
		return result;
	}
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> allRoads = new ArrayList<String>();
		
		Set<Road> roadSet = graph.edgeSet();
		TreeSet<Road> myTreeSet = new TreeSet<Road>(new MyNameComp());
		myTreeSet.addAll(roadSet);
		//System.out.println(myTreeSet);
		Iterator itr =(Iterator) myTreeSet.iterator();
		while (itr.hasNext())
		{
			Road rR = (Road) itr.next();
			allRoads.add(rR.getName());
		}	
		return allRoads;
	}
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town newTown1 = new Town(town1);
		Town newTown2 = new Town(town2);
		Road road1  = graph.getEdge(newTown1, newTown2 );
		int weight= road1.getWeight();
		if(graph.removeEdge(newTown1, newTown2, weight, road)!=null)
		{
			return true;
		}
		else
			return false;
	}
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		Town newTown1 = new Town(v);
		boolean result = graph.removeVertex(newTown1);
		return result;
	}
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> allTowns = new ArrayList<String>();
		Set<Town> townSet = graph.vertexSet();
		TreeSet<Town> myTreeSet = new TreeSet<Town>(new MyNameCompa());
		myTreeSet.addAll(townSet);
		Iterator itr =(Iterator) myTreeSet.iterator();
		while (itr.hasNext())
		{
			//System.out.println("whilte1");

			allTowns.add(((Town) itr.next()).getName());
		}	
		return allTowns;
	}
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	//@Override
	public ArrayList<String> getPath1(String town1, String town2) {
		Town town11 = null, town22 = null;
		Set<Town> townSet = graph.vertexSet();
		Iterator itrTown = (Iterator) townSet.iterator();
		while (itrTown.hasNext())
		{   
			Town t = (Town)itrTown.next();
		//	System.out.println(t.getName());
			if(t.getName().equals(town1))
			{
				town11 = t;
			}
			if(t.getName().equals(town2))
			{
				town22 = t;
			}
		}
		return graph.shortestPath(town11, town22);
	}





@Override
public ArrayList<String> getPath(String town1, String town2) {
	String beginTown = town1, endTown = town2;
	Town beginIndex=null, endIndex=null;
	Set<Town> towns = graph.vertexSet();
	Iterator<Town> iterator = towns.iterator();
	while(iterator.hasNext())
	{    	
		  Town town = iterator.next();
		  if(town.getName().equals(beginTown))
			  beginIndex = town;
		  if(town.getName().equals(endTown))
			  endIndex = town;		
	}
	ArrayList<String> rl = null;
	if(beginIndex != null && endIndex != null)
	{
		rl = graph.shortestPath(beginIndex, endIndex);
	 }
	return rl;
	
	}
}

/**
 * inner class for comparator road 
 * @return compared value
 * @author hyejeongkim
 *
 */
class MyNameComp implements Comparator<Road>
   {
	@Override
	public int compare(Road a, Road b)
	{
		return a.getName().compareTo(b.getName());
	}
}
/**
 * inner class for comparator town 
 * @return compared value
 * @author hyejeongkim
 *
 */
class MyNameCompa implements Comparator<Town>
	{
	@Override
	public int compare(Town a, Town b) 
	{		
		return a.getName().compareTo(b.getName());
	}	
}

