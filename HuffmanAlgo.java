/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmanalgo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Sofiane
 */
public class HuffmanAlgo {

    //  static LinkedHashMap<Character, Integer> freq;
    static Map<Character, Integer> freq;

//static LinkedHashMap<Character, Integer> freq;  
    public static void main(String[] args) throws IOException {
        HuffmanAlgo huffmanAlgo = new HuffmanAlgo();
        freq = new TreeMap<Character, Integer>();
        huffmanAlgo.readfile();
    }
    Node nd, secondNode;

    private void readfile() throws FileNotFoundException, IOException {
        String st;
        nd = new Node();
        secondNode = new Node('1', 1);
        freq = new TreeMap<>();
        File file = new File("C:\\test2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((st = br.readLine()) != null) {
            for (int i = 0; i < st.length(); i++) {
                add_to_list(nd, st.charAt(i));
            }

        }
        /*sort_freq(nd.next);
         printSecond_Tree(nd);*/
        calcnb(nd);
        printSecond_Tree(nd);
    }

    public void add_to_list(Node node, char occ) {
        if (node.chara == occ) {
            node.nbocc = node.nbocc + 1;
            // freq.put(occ,freq.get(occ)+1);
            return;

        }
        if (node.next != null) {
            add_to_list(node.next, occ);
        } else {
            if(node.nbocc==0)
            { node.nbocc=1;
            node.chara=occ;
                    }
            else{
                node.next = new Node(occ, 1);
            }
                // freq.put(occ,1);

        }

    }
    int total;

    private void create_empty_tree(Node secondNod) {
        if (secondNod.next != null) {
            create_empty_tree(secondNod.next);
        } else {
            Node d = secondNode;
            secondNod.next = new Node('1', total);
           // secondNod.next.left=
            //System.out.println("" + secondNod.nbocc + "\n");
            total--;
            if (total == 0) {
                return;
            } else {
                create_empty_tree(secondNod.next);
            }
        }
        /* for(int i=1;i<=totalnb;i++)
         {              
              
         Node d=secondNode;
         if(secondNode.nbocc==0)
         {
         secondNode=new Node('1',1);
         }
         else{
         while(secondNode.next!=null)
         secondNode=secondNode.next;
         secondNode.next=new Node('1',i);
         //secondNode=secondNode.next;
         }
         }*/

    }

    static class Node {

        Node left;
        Node bottom;
        Node top;
        Node next;
        char chara;
        int nbocc;

        public Node(char chara, int nbocc) {
            this.chara = chara;
            this.nbocc = nbocc;
        }

        public Node() {
        }

    }

    int grand = 1;
    int totalnb = 0;

    private void calcnb(Node nv) {
        while (nv.next != null) {
            totalnb++;
            nv = nv.next;
        }

        if (totalnb > 1) {
            total = totalnb;
            create_empty_tree(secondNode);
            //calcnb(secondNode);
        }
    }
    int j = 0;
    int calc = 1;
    Node third;
    Node node2;
    boolean exist=false;
    private void printSecond_Tree(Node node) {
        node2 = node;
        while (node != null) {   //j++; 
            while (node2 != null) {
                if (node.chara != node2.chara) {
                    if (node.nbocc < node2.nbocc) {
                        calc++;
                    }else{
                        if(node.nbocc==node2.nbocc)
                        {
                            if(!exist)
                                calc++;
                        }
                    }
                }
               else
                {
                   exist=true; 
                }
                node2 = node2.next;
            }
            /*third=secondNode;
            while(calc!=1)
            {    char k=third.chara;
                secondNode=third.next;
                calc--;
            }
            third.nbocc=node.nbocc;
            third.chara=node.chara;
            secondNode=third;*/
            remplir(secondNode,node.chara,node.nbocc, calc);
            calc=1;
            exist=false;
            node2=nd;
            node = node.next;
        }
        show(secondNode);
        
        /*third=node;     
         if(node.next!=null)
         {            printSecond_Tree(node.next);      
         j++;
         node2=node.next;    
         
        
         while(third.next!=null)
         {
         if(calc==j)   
         third=third.next;
         else{
         if(node2.nbocc > third.nbocc){
         grand++;
           
         }
         if(node2.nbocc==third.nbocc)
         {
         if((int)node2.chara > (int) third.chara)
         grand++;
         }
         third=third.next;

         }
         }
         }
         else{
         return;
         }
         for(int i=1;i<=grand+1;i++)
         {
         if(secondNode.next!=null)
         secondNode=secondNode.next;
                
            
         }
         secondNode.chara=node.chara;
         secondNode.nbocc=node.nbocc;*/
        /*while (node.next != null) {
         Node node2=node.next;
         j++;
         while (node2.next != null) 
         {
         if(node.nbocc>node2.nbocc)
         {
         grand++;
         node2=node2.next;
         }
         if(node.nbocc==node2.nbocc)
         {
         if((int)node.chara<(int)node2.chara)
         {
         grand++;
         node2=node2.next;
         }
         }
         }
         for(int i=1;i<=grand+1;i++)
         {
                
         secondNode=secondNode.next;
                
            
         }
         secondNode.chara=node.chara;
         secondNode.nbocc=node.nbocc;
         }*/
    }

    private void sort_freq(Node node) {
        //  System.out.println(""+node.next.left.chara+" "+node.next.left.nbocc);
        Node ndd = node;
        int nb_compar;
        Set set = freq.entrySet();
        Iterator i = set.iterator();

        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": " + me.getValue() + "\n");
        }
        secondNode = new Node(node.chara, node.nbocc);
        if (node.next != null) {
            nb_compar = node.nbocc;

            while (node.next != null) {
                node = node.next;
                if (node.nbocc > nb_compar) {
                    while (secondNode.left != null) {
                        if (secondNode.nbocc < node.nbocc) {
                            secondNode = secondNode.left;
                        }
                    }
                    if (secondNode.nbocc > node.nbocc) {
                        secondNode.next = new Node(node.chara, node.nbocc);
                    }
                    secondNode.left = new Node(node.chara, node.nbocc);

                } else {
                    while (secondNode.next != null) {
                        if (secondNode.nbocc > node.nbocc) {
                            secondNode = secondNode.next;
                        }
                        if (secondNode.nbocc == node.nbocc) {
                            while (secondNode.bottom != null) {
                                if ((int) node.chara < (int) secondNode.chara) {
                                    secondNode = secondNode.bottom;
                                }
                            }
                            secondNode.bottom = new Node(node.chara, node.nbocc);
                        }
                    }
                    if (secondNode.nbocc < node.nbocc) {
                        secondNode.left = new Node(node.chara, node.nbocc);
                    } else {
                        secondNode.next = new Node(node.chara, node.nbocc);
                    }
                }
            }

            /*while(node.next!=null)
             {
             node=node.next;
             if(node.nbocc>nb_compar)
             {
             while(secondNode.left!=null)
             {
             secondNode = secondNode.next;
             }
             secondNode=new Node();
             }
           
             else{
             secondNode.next=new Node(node.chara,node.nbocc);
             secondNode=secondNode.next;
             }
             }
             */
            // sort_freq(node.next);
        }

    }
    
    
      private void remplir(Node node,char c,int nb_occurance,int count) {
        if (count!=1) {
            count--;
            //  remplir(node.next,count);
            //secondNode=secondNode.next;
            remplir(node.next,c, nb_occurance, count);
        }else{
             node.nbocc=nb_occurance;
            node.chara=c;
            return;
            //secondNode=third;
        } 
      }
      
      private void show(Node node)
      {
          if(node!=null){
              System.out.println(" "+node.chara);
              show(node.next);
      
          }
      }
      
      private void huff(Node node)
      {
          
      }
}
