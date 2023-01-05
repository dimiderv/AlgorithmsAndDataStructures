package gr.duth.ee.euclid.datastructures.dfs;

import gr.james.simplegraph.DirectedGraph;

import java.io.Serializable;
import java.util.*;

public class DepthFirstSearch implements Serializable {
	private static final long serialVersionUID = 1L;

	private final DirectedGraph g;
	private final int source;
	private final List<Integer> order;

	public DepthFirstSearch(DirectedGraph g, int source) {
		if (g == null) {
			throw new NullPointerException();
		}
		if (source < 0 || source >= g.size()) {
			throw new IndexOutOfBoundsException();
		}
		this.g = g;
		this.source = source;
		this.order = new ArrayList<Integer>();
		depthFirstSearch();
	}

	/**
	 * Perform DFS and fill the list {@link #order}.
	 * <p>
	 * Any ties arising from the DFS algorithm should be resolved using the
	 * natural ordering of the elements.
	 * <p>
	 * The {@code order} list must contain all the vertices that can be
	 * discovered using DFS at the order at which they are discovered. The first
	 * element of the list must be the source vertex. The list must contain
	 * exactly the vertices that can be discovered, not more or less.
	 * <p>
	 * The list {@code order} is initialized empty.
	 * <p>
	 * You can add elements at the end of the list using the code
	 * {@code order.add(v)}.
	 */
	private void depthFirstSearch() {
		// STUDENT CODE
	
	final Deque<Integer> stack = new LinkedList<Integer>();
    final Set<Integer> visited = new HashSet<Integer>();
    stack.push(source);
	
	
    while (!stack.isEmpty()) {
        final int next = stack.pop();
        int temp;
         int []A= new int[g.getOutEdges(next).size()];
        if (visited.add(next)) {
        	int i=0;
        		for (int adj : g.getOutEdges(next)) {
        			A[i]=adj;
        			i++;
        			
        		}
        		 for(int j=0;j<i;j++) {
                 	for(int k=1;k<i-j;k++) {
                 		if(A[k-1]<A[k]) {
                 			 temp=A[k-1];
                 			A[k-1]=A[k];
                 			A[k]=temp;
                 		}	
                 	}
                 }
        			for(int k=0;k<i;k++) {
                		
                		stack.push(A[k]);
                	}
        		 
        		order.add(next);
            }
        }
    }
    
	
		
	
		
	

	public int[] order() {
		final int[] a = new int[order.size()];
		for (int i = 0; i < order.size(); i++) {
			a[i] = order.get(i);
		}
		return a;
	}
}
