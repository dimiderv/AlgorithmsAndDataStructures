package gr.duth.ee.euclid.datastructures.bfs;

import gr.james.simplegraph.DirectedGraph;

import java.io.Serializable;
import java.util.*;

public class BreadthFirstSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final DirectedGraph g;
	private final int source;
	private final int[] distances;
	private final int[] through;

	public BreadthFirstSearch(DirectedGraph g, int source) {
		if (g == null) {
			throw new NullPointerException();
		}
		if (source < 0 || source >= g.size()) {
			throw new IndexOutOfBoundsException();
		}
		this.g = g;
		this.source = source;
		this.distances = new int[g.size()];
		this.through = new int[g.size()];
		for (int i = 0; i < g.size(); i++) {
			this.distances[i] = -1;
		}
		for (int i = 0; i < g.size(); i++) {
			this.through[i] = -1;
		}
		breadthFirstSearch();
	}

	/**
	 * Perform BFS and fill the arrays {@link #distances} and {@link #through}.
	 * <p>
	 * Any ties arising from the BFS algorithm should be resolved using the
	 * natural ordering of the elements.
	 * <p>
	 * The {@code distances} array holds the path lengths of the vertices from
	 * the source vertex. More specifically, the element {@code distances[i]}
	 * holds the shortest path length from {@code source} to {@code i}.
	 * <p>
	 * The {@code through} array holds the ancestors of the vertices. More
	 * specifically, the element {@code through[i]} holds the index of the
	 * vertex via which {@code i} was explored.
	 * <p>
	 * The arrays {@code distances} and {@code through} are initialized with
	 * {@code -1}.
	 */
	private void breadthFirstSearch() {
		// STUDENT CODE
		
		final Deque<Integer> queue = new LinkedList<Integer>();
        final Set<Integer> visited = new HashSet<Integer>();
        queue.offer(source);
        visited.add(source);
        
      
      
        distances[source]=0;
        through[source]=source;
        while (!queue.isEmpty()) {
            final int next = queue.poll();
            int A[]=new int[g.getOutEdges(next).size()];
              int i=0;  
            for (int adj : g.getOutEdges(next)) {
               if (visited.add(adj)) {
                	A[i]=adj;
                	i++;
                } 
            }
                for(int j=0;j<i;j++) {
                	for(int k=1;k<i-j;k++) {
                		if(A[k-1]>A[k]) {
                			int temp=A[k-1];
                			A[k-1]=A[k];
                			A[k]=temp;
                		}	
                	}
                }
                
                
                	for(int k=0;k<i;k++) {
                		//if (visited.add(A[k])) {
                			queue.offer(A[k]);
      
                		through[A[k]]=next;
                	distances[A[k]]=distances[through[A[k]]]+1;
                		//}
                	}
                	
                	
            } 
            		
        
        }
        
		
	

	public int[] distances() {
		return distances;
	}

	public int[] through() {
		return through;
	}
}