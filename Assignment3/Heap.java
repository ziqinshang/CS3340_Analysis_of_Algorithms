import java.lang.Integer;
import java.lang.Math;

public class Heap {

	int[] keys;
	int[] id;
	int max;
	int current_size;

	public Heap(int[] ints) {
		int size=ints.length;
		current_size = size;
		keys=new int[size+1];
		id=new int[2*(size+1)-1];
		System.arraycopy(ints, 0, keys, 1, size);
		this.max =keys.length-1;
		for (int i = max; i<2* max -1; i++){
			id[i]=i- max +1;
		}
		for(int j = max -1; j>0; j--) {
			if(keys[id[2*j]]<keys[id[2*j+1]]) {
				id[j]=id[2*j];
			}
			else {
				id[j]=id[2*j+1];
			}
		}


	}
	public boolean in_heap(int id) {
		for (int i=1;i<this.id.length;i++) {
			if(this.id[i]==id) {
				return true;
			}
		}return false;
	}

	public int min_key() {
		return this.keys[this.id[1]];
	}

	public int min_id() {
		return this.id[1];
	}

	public int key(int id) {
		return this.keys[id];
	}

	public int delete_min() {
		keys[0]=Integer.MAX_VALUE;
		this.id[this.id[1]+ max -1]=0;
		int v=keys[id[1]];
		int i=(int) Math.floor((id[1]+ max -1)/2);
		while(i>=1) {
			if(keys[id[2*i]]<keys[id[2*i+1]]) {
				id[i]=id[2*i];
			}else {
				id[i]=id[2*i+1];
			}
			i=(int) Math.floor(i/2);
		}
		current_size--;
		return v;
	}
	public boolean isEmpty() {
		return current_size == 0;
	}
	public void decrease_key(int id, int new_key) {
		if(keys[id]>new_key) {
			update(id, new_key);
		}
	}

	private void update(int i, int new_key) {
		keys[i]=new_key;
		i=(int) Math.floor((i+ max -1)/2);
		while(i>=1) {
			if(keys[id[2*i]]<keys[id[2*i+1]]) {
				id[i]=id[2*i];

			}else {
				id[i]=id[2*i+1];
			}i=(int) Math.floor(i/2);
		}

	}
}



//public class Heap {
//
//	static class node {
//		int vertex;
//		int distance;
//	}
//
//	node keys[];
//	int id[];
//	int max;
//	int hMax;
//	public void heap_ini(node nodes[], int n){
//		// Set A to reference the array of edges
//		keys = nodes;
//		// Set the max to be n-1
//		max = n-1;
//		// Set H to be an array of 2*max elements
//		id = new int[2*max];
//		// hMax refers to 2n-1 --> 2max - 1
//		hMax = 2*max-1;
//		// Call heapify to create the heap
//		heapify();
//	}
//	public Heap(node nodes[],int n)
//	{
//		// Set A to reference the array of edges
//		keys = nodes;
//		// Set the max to be n-1
//		max = n-1;
//		// Set H to be an array of 2*max elements
//		id = new int[2*max];
//		// hMax refers to 2n-1 --> 2max - 1
//		hMax = 2*max-1;
//		for (int j=max;j<hMax;j++){
//			id[j]=j-n+1;
//		}
//		for(int j=n-1;j>0;j--) {
//			if(keys[id[2*j]].distance<keys[id[2*j+1]].distance) {
//				id[j]=id[2*j];
//			}
//			else {
//				id[j]=id[2*j+1];
//			}
//		}
//
//
//	}
//	public boolean in_heap(int id) {
//		for (int i=1;i<this.id.length;i++) {
//			if(this.id[i]==id) {
//				return true;
//			}
//		}return false;
//	}
//
//	public int min_key() {
//		return this.keys[this.id[1]];
//	}
//
//	public int min_id() {
//		return this.id[1];
//	}
//
//	public int key(int id) {
//		return this.keys[id];
//	}
//
//	public int delete_min() {
//		keys[0]=Integer.MAX_VALUE;
//		this.id[this.id[1]+ max -1]=0;
//		int v=keys[id[1]];
//		int i=(int) Math.floor((id[1]+ max -1)/2);
//		while(i>=1) {
//			if(keys[id[2*i]]<keys[id[2*i+1]]) {
//				id[i]=id[2*i];
//			}else {
//				id[i]=id[2*i+1];
//			}
//			i=(int) Math.floor(i/2);
//		}
//		return v;
//	}
//
//	public void decrease_key(int id, int new_key) {
//		if(keys[id]>new_key) {
//			update(id, new_key);
//		}
//	}
//
//	private void update(int i, int new_key) {
//		keys[i]=new_key;
//		i=(int) Math.floor((i+ max -1)/2);
//		while(i>=1) {
//			if(keys[id[2*i]]<keys[id[2*i+1]]) {
//				id[i]=id[2*i];
//
//			}else {
//				id[i]=id[2*i+1];
//			}i=(int) Math.floor(i/2);
//		}
//
//	}
//}
