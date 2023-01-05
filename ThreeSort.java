package gr.duth.ee.euclid.datastructures.three_sort;

public class ThreeSort {

	public static void threeSort(IntegerArray a) {
		
		int key, n0=0, n2= a.length()-1;
		for(int n1=0; n1<=n2; n1++) {
			key = a.compare(n1, 1);
			if(key<0) {
				a.swap(n1, n0);
				n0+=1;
			}
			else if(key>0) {
				a.swap(n1, n2);
				n2-=1;
				k=k-1;
			}
		}
		
	}

}
