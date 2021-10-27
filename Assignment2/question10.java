import java.io.*;
import java.util.LinkedList;
public class question10{
    public static void main(String[] args) {
        try
        {
            //read in data
            File file=new File("girl.img");
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            StringBuilder stringBuilder=new StringBuilder();
            Disjointset set=new Disjointset();
            //some initial variables
            int col = 71;
            int row = 71;
            set.uandf(col*row);
            String tmpline;
            StringBuilder tmparray= new StringBuilder();
            int line_num=-1;
            int space_set = 0;
            boolean space=false;
            //read lines one by one
            while((tmpline=bufferedReader.readLine())!=null)
            {
                line_num++;
                //for every elements in the line
                for(int i=0;i<tmpline.length();i++)
                {
                    //if there appears to be a space, make it the first space ever
                    //which will be representing the entire space union
                    if(tmpline.charAt(i)==' '&& !space)
                    {
                        space_set=line_num*col+i;
                        space=true;
                    }
                    //
                    tmparray.append(tmpline.charAt(i));
                    set.make_set(line_num*col+i);

                    if(tmpline.charAt(i)==' '&& space) set.union_set(line_num*col+i, space_set);

                    if(line_num==1 && i==0)
                    {
                        //if the first element is +
                        if(tmparray.charAt(0)=='+')
                        {
                            //first case, set 0 and 1 as union
                            if(tmparray.charAt(1)=='+') set.union_set(0, 1);
                            //subsequently, link next +'s to 0
                            if(tmparray.charAt(line_num*col+i)=='+') set.union_set(0, line_num*col+i);
                        }
                    }
                    if(line_num>0 && i>0 &&tmpline.charAt(i)=='+' )
                    {
                        if(tmparray.charAt((line_num-1)*col+i)=='+')
                            //check horizontally
                        {
                            set.union_set(line_num*col+i, (line_num-1)*col+i);
                        }
                        if(tmparray.charAt(line_num*col+i-1)=='+')
                            //check vertically
                        {
                            set.union_set(line_num*col+i, line_num*col+i-1);
                        }
                    }
                }
                stringBuilder.append(tmpline);      //appends tmplin to string buffer
                stringBuilder.append("\n");     //tmplin fee
            }

//define the array of the characters which are needed to represent the certain parent by counting the index of the parent
            LinkedList<Integer> label = new LinkedList<Integer>();
            LinkedList<Integer> count = new LinkedList<Integer>();
            StringBuilder output= new StringBuilder();
            int current=0;
            //for each parent, traverse the entire subset represented by the parent
            for(int i=0;i<set.parent.length;i++)
            {
                //make the current node be the traversing ith element of subset
                current= set.parent[i];
                //initially make the isfound false, cause we havent found it yet
                boolean isfound=false;
                //as long as the current set is not the space set, we are good to go
                if(set.parent[space_set]!=current)
                {
                    //for every representing character of the subset
                    for(int j=0;j<label.size();j++)
                    {
                        //if the characters matches
                        if(label.get(j)==set.parent[i])
                        {
                            int num=count.get(j); //get the total number of elements
                            count.set(j, num+1);
                            output.append((char) ('a'+j));//append characters alphabetically
                            isfound=true; //we found the element
                        }
                    }
                    if(!isfound)
                    {
                        output.append((char) ('a'+label.size()));//append characters in a manner of last element
                        label.add(set.parent[i]);
                        count.add(1);
                    }
                }
                else output.append(' ');// else, the element is a space, append space
                if((i+1)%col==0||i==row) output.append('\n'); // if the rows are interchanging, append \n
            }
            System.out.println("first space element is the: "+set.parent[space_set]+"th element in the array");
            System.out.println("total number of first space element is: "+set.final_sets());
            fileReader.close();
            System.out.println("Original Image: ");
            System.out.println(stringBuilder.toString());
            System.out.println("Processed Image: \n"+output);
            for(int i=0;i<count.size();i++) {
                System.out.println("label # :"+(char)(i+'a'));
                System.out.println("count: "+count.get(i));
                System.out.println();
            }
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
