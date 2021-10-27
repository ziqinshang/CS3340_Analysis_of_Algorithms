
import java.util.LinkedList;
class Disjointset {

    private int[] rank;
    int[] parent;
    private boolean valid=true;

    void uandf(int n) {
        parent =new int[n];
        rank = new int[n];
    }
    void make_set(int i) {
        if(valid) {
            parent[i]=i;
            rank[i]=0;
        }
    }

    private int find_set(int i) {
        if (parent[i] != i)
            parent[i]=find_set(parent[i]);
        return(parent[i]);
    }

    void union_set(int xroot, int yroot) {
        if(valid)
            link(find_set(xroot),find_set(yroot));
    }

    private void link(int x, int y) {
        if(rank[x]<rank[y])
            parent[x]=y;
        else if(rank[x]>rank[y])
            parent[y]=x;
        else if(x!=y) {
            parent[y]=x;
            rank[x]=rank[x]+1;
        }

    }


    int final_sets() {
        valid=false;
        int total = 0;
        int cur=-1;
        LinkedList<Integer> label = new LinkedList<Integer>();
        for (int value : parent) {
            boolean found = false;
            cur = value;
            for (Integer integer : label) {
                if (cur == (int) integer) {
                    found = true;
                    break;
                }
            }
            if (!found)
                label.add(cur);
        }
        total = label.size()-1;
        System.out.println(total);
        return total;
    }
}
