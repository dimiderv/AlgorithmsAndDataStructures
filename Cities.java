package gr.duth.ee.euclid.datastructures.cities;

import gr.james.simplegraph.Graph;

import java.io.Serializable;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Cities implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Graph roads;
	private final int roadCost;
	private final int libraryCost;
	private int minimumCost;

	public Cities(Graph roads, int roadCost, int libraryCost) {
		if (roads == null) {
			throw new NullPointerException();
		}
		if (roads.size() < 1) {
			throw new IllegalArgumentException();
		}
		if (roadCost < 1) {
			throw new IllegalArgumentException();
		}
		if (libraryCost < 1) {
			throw new IllegalArgumentException();
		}
		this.roads = roads;
		this.roadCost = roadCost;
		this.libraryCost = libraryCost;
		this.minimumCost = -1;
		cities();
	}

	/**
	 * Use the finals {@link #roads}, {@link #roadCost} and {@link #libraryCost}
	 * to compute and store the value {@link #minimumCost}.
	 */
	private void cities() {
		// STUDENT CODE
		if(libraryCost<=roadCost)
		{
			minimumCost= roads.size()*libraryCost;
		}
		else
		{
			LinkedList<Integer> fifo = new LinkedList<Integer>();
			LinkedList<Integer> helper = new LinkedList<Integer>();
			int group[] = new int[roads.size()];
			for(int i : group)
			{
				group[i]=0;
			}
			int numgroup=0;
			for(int i=0;i<group.length;i++)
			{
				
				if(group[i]==0)
				{
					
					fifo.add(i);
					group[i]=++numgroup;
					while(!fifo.isEmpty()) 
					{
						int k = fifo.pollFirst();
						for(int adj : roads.getEdges(k))
						{
							if(group[adj]==0)
							{
								helper.add(adj);
								group[adj]=numgroup;
							}
						}
						Collections.sort(helper);
						if(!helper.isEmpty())   
						{
							fifo.addAll(helper);
							helper.clear();
						}
					}
					
				}
			}
			int numoflib = numgroup;
			int numofrods = group.length-numgroup;
			minimumCost = numoflib*libraryCost + numofrods*roadCost;
			
		}	
	}

	public int minimumCost() {
		return minimumCost;
	}
}
