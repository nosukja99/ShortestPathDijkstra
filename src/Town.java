import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * Represents an town as a node of a graph. 
 * The Town class holds the name of the town and a list of adjacent towns, 
 * and other fields as desired, and the traditional methods (constructors, getters/setters, toString, etc.). 
 * It will implement the Comparable interface These are the minimum methods that are needed. Please feel free to add as many methods as you need.
 * @author hyejeongkim
 *
 */
public class Town implements Comparable<Town>{

	private String townName;
	private String[] townsList;
	int townId;
	/**
	 * Constructor. Requires town's name.
	 * @param String name
	 */
	public Town (String name)
	{
		townName = name;
	}
	/**
	 * Copy constructor.
	 * @param Town t
	 */
	public Town (Town t)
	{
		townName = t.getName();
	}
	/**
	 * Returns the town's name
	 * @return town's name
	 */
	public String getName()
	{
		return townName;
	}
	/**
	 * give unique town Id to store in Graph
	 * @param int n2
	 */
	public void setTownId(int n2) {
		townId =n2;
	}
    /**
     * return town ID
     * @return townId
     */
	public int getTownId()
	{
		return townId;
	}
	/**
	 * To string method
	 * override
	 * @return the town name
	 */
	public String toString()
	{
		return townName;	
	}
    /**
     * Compare to method
     * @return 0 if names are equal, a positive or negative number if the names are not equal
     */
	@Override
	public int compareTo(Town town)  throws ClassCastException{

		if(townName.equals(town.townName))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	/**
	 * equals in class java.lang.Object
	 * @return true if the town names are equal, false if not
	 */
	public boolean  equals(Object obj) {
		Town town = (Town) obj;

		if(townName.equals(town.townName))
		{

			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * hashCode in class java.lang.Object
	 * @return the hashcode for the name of the town
	 */
	public int hashCode(){
		return townName.hashCode();
	}
}
