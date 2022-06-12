import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
public class Histogram {

        public static void main(String[] args)  throws FileNotFoundException, IOException{

            BufferedReader reader = new BufferedReader(new FileReader("0.txt"));
            String text = reader.readLine();

            String parts[] = text.split(" ");
            int amount = Integer.parseInt(parts[0]);
            int intervals = Integer.parseInt(parts[1]);

            LinkedList<String>[] lists = new LinkedList[intervals];
            for(int i = 0 ; i < intervals;i++) {
                lists[i] = new LinkedList<>();
            }
            String st = null;
            st = reader.readLine();

            int max=0;
            int min=0;

            if(st!= null){
                ArrayList<Integer> number = new ArrayList<>();
                String temp[] = st.split(" ");



                for(int i=0 ; i < temp.length ; i++){
                    if(i ==0){
                        max = min = Integer.parseInt(temp[i]);
                    }

                    if (Integer.parseInt(temp[i])> max)
                    {
                        max = Integer.parseInt(temp[i]);
                    }
                    if (Integer.parseInt(temp[i])< min)
                    {
                        min = Integer.parseInt(temp[i]);
                    }
                }

                ArrayList<Integer> cutoffs = new ArrayList<>();
                int middleValueOfCutOffs = (max-min) / intervals;
                for(int i  = 0 ; i <= intervals ; i++){
                    int cutOffValue = min + middleValueOfCutOffs*i;
                    cutoffs.add(cutOffValue);

                }


                for(int i = 0 ; i < temp.length; i++){
                    innerLoop:
                    for(int j = 0 ; j < intervals; j++){
                       if(Integer.parseInt(temp[i]) < cutoffs.get(j+1)){
                           lists[j].add(temp[i]);
                           break innerLoop;

                       }
                        if(Integer.parseInt(temp[i]) == max){
                            lists[intervals-1].add(temp[i]);

                            break innerLoop;
                        }
                    }
                }






                System.out.println("ANS: ");
                for(int i  = 0 ; i <= intervals ; i++){
                    System.out.print(cutoffs.get(i) + " ");

                }
                System.out.println();
                for(int i  = 0 ; i < intervals ; i++){
                    System.out.print(lists[i].size() + " ");

                }





            }
            else{
                return;
            }


    }

}
