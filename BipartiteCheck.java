package gr.duth.ee.euclid.datastructures.bipartitecheck;

import gr.james.simplegraph.Graph;

import java.io.Serializable;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BipartiteCheck implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Graph g;
	private boolean isBipartite;
	private int bigGroupSize;
	private int smallGroupSize;

	public BipartiteCheck(Graph g) {
		if (g == null) {
			throw new NullPointerException();
		}
		if (g.size() < 2) {
			throw new IllegalArgumentException();
		}
		this.g = g;
		this.isBipartite = true;
		this.bigGroupSize = -1;
		this.smallGroupSize = -1;
		bipartiteCheck();
	}

	/**
	 * Perform the computation to figure out if {@link #g} is bipartite and
	 * compute and store the values {@link #isBipartite}, {@link #bigGroupSize}
	 * and {@link #smallGroupSize}.
	 * <p>
	 * The value {@link #isBipartite} must be {@code true} is the graph is
	 * bipartite, otherwise {@code false}.
	 * <p>
	 * The value {@link #bigGroupSize} must be equal to the number of vertices
	 * in the bigger of the two groups of the bipartite graph, or {@code -1} if
	 * the graph is not bipartite.
	 * <p>
	 * The value {@link #smallGroupSize} must be equal to the number of vertices
	 * in the smaller of the two groups of the bipartite graph, or {@code -1} if
	 * the graph is not bipartite.
	 */
	private void bipartiteCheck() {
		final Deque<Integer> queue = new LinkedList<Integer>();
        final Set<Integer> visited = new HashSet<Integer>();
        queue.offer(0);
        visited.add(0);
        int []B=new int[g.size()];
        B[0]=1;
        int max=1;
        int min=0;
        while (!queue.isEmpty() && isBipartite ) {
            final int next = queue.poll();         
            for (int adj : g.getEdges(next)) {  
            	if(B[next]==B[adj]) {
    				isBipartite=false;	 
    				max=-1;
    				min=-1;
    				
    				break;
            }
               if (visited.add(adj)) {
           	    queue.offer(adj);         			        				
          			 B[adj]=(-1*B[next]);
          				if(B[adj]==1) {
          					max++;          					
          					}else if(B[adj]==-1){          			
          					min++;
          				}     			
          			
                }
                	
             
            }
 	
                	
            }       if(max>min) {
                		bigGroupSize=max;
                		smallGroupSize=min;
                	}else {
                		bigGroupSize=min;
                		smallGroupSize=max;
                	}	}

	public boolean isBipartite() {
		return isBipartite;
	}

	public int bigGroupSize() {
		return bigGroupSize;
	}

	public int smallGroupSize() {
		return smallGroupSize;
	}
}
