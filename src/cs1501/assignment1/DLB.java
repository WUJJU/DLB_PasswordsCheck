package cs1501.assignment1;

import java.io.File;
import java.util.Iterator;

public class DLB {

    public Node root;
    boolean check_flag = false;

    public class Node {
        Node nextNode;
        Node childNode;
        char value;

        public Node() {

        }

        public Node(char c) {
            this(c, null, null);
        }

        public Node(char c, Node nextNode, Node childNode) {
            this.value = c;
            this.nextNode = nextNode;
            this.childNode = childNode;
        }

        public int size(Node cur) {
            int i = 1;

            while (cur.nextNode != null) {
                i++;
                cur = cur.nextNode;
            }

            return i;
        }

    }

    // constructor
    public DLB() {
        root = new Node();
    }

    /**
     * method used by addPW()
     * 
     * @param cur
     * @param c
     * @return
     */
    public Node addchildNode(Node cur, char c) {
        if (cur.childNode == null) {
            cur.childNode = new Node(c);
            return cur.childNode;
        } else
            return addpeerNode(cur.childNode, c);

    }

    /**
     * method used by addPw()
     * 
     * @param peerNode
     * @param c
     * @return
     */
    public Node addpeerNode(Node peerNode, char c) {
        if (peerNode.value == c)
            return peerNode;
        while (peerNode.nextNode != null) {
            peerNode = peerNode.nextNode;
            if (peerNode.value == c)
                return peerNode;
        }
        peerNode.nextNode = new Node(c);

        return peerNode.nextNode;

    }

    // add every password into DLB
    public void addPw(String pw) {
        pw = pw + "^";
        if (pw != null) {
            // root=new Node();
            Node curNode = root;
            for (int i = 0; i < pw.length(); i++) {
                // System.out.println(i+":"+pw.charAt(i));
                curNode = addchildNode(curNode, pw.charAt(i));

            }
        }

    }

    // search key
    // return true if find;
    public boolean search(String pw) {
        Node curNode = root;
        for (int i = 0; i < pw.length(); i++) {
            curNode = getChildNode(curNode, pw.charAt(i));
        }
        curNode = getChildNode(curNode, '^');
        if (curNode != null) {
            return true;
        } else

            return false;
    }

    public Node getChildNode(Node curNode, char c) {
        if (curNode == null)
            return null;

        return getPeerNode(curNode.childNode, c);
    }

    public Node getPeerNode(Node curNode, char c) {
        if (curNode.value == c)
            return curNode;
        while (curNode.nextNode != null) {
            curNode = curNode.nextNode;
            if (curNode.value == c)
                return curNode;
        }
        return null;
    }

    /**
     * find all keys in DLB
     * 
     * @return
     */
    public Iterable<String> keys() {
        return keywithPrefix("");
    }

    public Iterable<String> keywithPrefix(String prefix) {
        Queue<String> results = new Queue<String>();

        collect(root.childNode, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node curNode, StringBuilder prefix,
            Queue<String> results) {

        if (curNode == null)
            return;
        // if check_flag is true, check prefix statisfy the requirements of good
        // passwords
        if (check_flag = true) {
            if(prefix.length()>2){
              boolean flag2=check(prefix);
              //flag2 false pruning
              if(!flag2) return;
            }
        }
        if (curNode.value == '^') {
            boolean flag3=check2(prefix);
            if(flag3)   results.enqueue(prefix.toString());
        }
        // System.out.println(curNode.size(curNode));
        int N = curNode.size(curNode);
        for (int i = 0; i < N; i++) {

            if (i != 0) curNode = curNode.nextNode;

            prefix.append(curNode.value);
            collect(curNode.childNode, prefix, results);
            prefix = prefix.deleteCharAt(prefix.length() - 1);
        }

    }
    
    private boolean check2(StringBuilder prefix) {
        int n=0;//number 0-9
        int c=0;//character a-z
        int s=0;//specail "!", "@", "$", "%", "&", or "*"
       for(int i=0;i<prefix.length();i++){
           int j=prefix.charAt(i);
          
           if(j>=48&&j<=57) n++;
           else if (j>=97&&j<=122) c++;
           else s++;
           
           
       }
       if(n>=3||c>=4||s>=3) return false;
       else return true;
    }

    // check function check whether prefix statisfy good passwords
    private boolean check(StringBuilder prefix) {
        int n=0;//number 0-9
        int c=0;//character a-z
        int s=0;//specail "!", "@", "$", "%", "&", or "*"
       for(int i=0;i<prefix.length();i++){
           int j=prefix.charAt(i);
          
           if(j>=48&&j<=57) n++;
           else if (j>=97&&j<=122) c++;
           else s++;
           
           
       }
       if(n>=3||c>=4||s>=3) return false;
       else return true;
       

    }

 

    public Iterable<String> goodPws() {
        // TODO Auto-generated method stub
        check_flag = true;

        return keys();
    }

    public static void main(String[] args) {
        DLB dlb = new DLB();
        BruteForce2 bf = new BruteForce2();
        // all possible passwords
        Queue<StringBuilder> allpw = new Queue<StringBuilder>();
        allpw = bf.run();
        System.out.println("------------------");

        for (StringBuilder eachpw : allpw) {
            dlb.addPw(eachpw.toString());
        }
        System.out.println("already insert into DLB Data Structrue");

        System.out.println(dlb.search("99996"));
        Queue<String> q2 = new Queue<String>();
        q2 = (Queue<String>) dlb.keys();

        // create My_Dictionary.txt
        FileOut fout = new FileOut();
       // fout.createFile("My_Dictionary.txt", q2);
        /**
         * get all good passwords
         * 
         */
        Queue<StringBuilder> q3=new Queue<StringBuilder>();
        
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
          st.put(key, i);
        }
    
        
System.out.println("print all good passwords");
        Queue<String> goodpw = new Queue<String>();
        goodpw = (Queue<String>) dlb.goodPws();
        for(String key:goodpw){
            System.out.println(key);
        }
      fout.createFile("Good_Passwords.txt", goodpw);
        /**
         * for (String key : q2) { StdOut.println(key); } StdOut.println();
         **/
    }

}
