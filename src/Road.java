import java.util.Set;
/**
 * The class Road that can represent the edges of a Graph of Towns. 
 * The class must implement Comparable. The class stores references to the two vertices(Town endpoints), 
 * the distance between vertices, and a name, and the traditional methods (constructors, getters/setters, toString, etc.), 
 * and a compareTo, which compares two Road objects. Since this is a undirected graph, an edge from A to B is equal to an edge from B to A.
 * @author hyejeongkim
 *
 */
public class Road implements Comparable<Road>{

	private String roadName;
	private Town source;
	private Town destination;
	private int weight;
	/**
	 * Constructor
	 * @param Town start
	 * @param Town finish
	 * @param int distance
	 * @param String name
	 */
	public Road(Town start, Town finish, int distance, String name)
	{
		roadName = name;
		source = start;
		destination = finish;
		weight = distance;
	}
	/**
	 * Constructor with weight preset at 1
	 * @param Town start
	 * @param Town finish
	 * @param String name
	 */
	public Road(Town start, Town finish, String name)
	{
		roadName = name;
		source = start;
		destination = finish;
		weight = 1;
	}
	/**
	 * Returns true only if the edge contains the given town
	 * @param town - a vertex of the graph
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town)
	{
		if (source.equals(town)|| destination.equals(town))
		    return true;
		else
			return false;	
	}
	/**
	 * To string method.
     * override toString in class java.lang.Object
	 */
	public String toString()
	{
		return source.getName()+" via "+ roadName+" to "+ destination.getName()+ " "+weight+ " mi";
	}
	/**
	 * Returns the first town on the road
	 * @return A town on the road
	 */
	public Town getSource()
	{
		return source;
	}
    /**
     * Returns the road name
     * @return The name of the road
     */
	public String getName() 
	{
		return roadName;
	}
    /**
     * Returns the second town on the road
     * @return A town on the road
     */
	public Town getDestination()
	{
		return destination;
	}
	/**
	 * Returns the distance of the road
	 * @return the distance of the road
	 */
	public int getWeight()
	{
		return weight;
	}
	/**
	 * compareTo in interface java.lang.Comparable Road
	 * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
	 */
	@Override
	public int compareTo(Road edge) {
	
		if (roadName.equals(edge.getName()))
		    return 0;
		else 
		    return 1;
	}
	/**
	 * Returns true if each of the ends of the road r is the same as the ends of this road. 
	 * Remember that a road that goes from point A to point B is the same as a road that goes from point B to point A.
	 * @return boolean true or false
	 */
	public boolean equals(Object obj) {
		Road edge = (Road) obj;
		
		if (source.equals(edge.destination) && destination.equals(edge.source))
		    return true;
		else if (source.equals(edge.source)&& destination.equals(edge.destination))
			return true;
		else
		    return false;
	}
}
