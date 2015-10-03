

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;



public class DLB {

    public Node root;
    boolean check_flag = false;
    Queue<String> dic_words = new Queue<String>();
    Queue<String> longest_gpw;
    Queue<StringBuilder> qq;
    int[] pre = new int[6];
    char[] il = { ' ', 'i', 'l' };
    Node cur1;

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
    
    public Iterable<String> keywithPrefix2(Node x,String prefix) {
        Queue<String> results = new Queue<String>();

        collect(x.childNode, new StringBuilder(prefix), results);
        return results;
    }
    private void collect(Node curNode, StringBuilder prefix,
            Queue<String> results) {

        if (curNode == null)
            return;
        // if check_flag is true, check prefix statisfy the requirements of good
        // passwords
        if (check_flag == true) {
            if (prefix.length() > 2) {
                boolean flag2 = check(prefix);

            
                
                // flag2 false pruning
                if (!flag2)
                    return;
            }
        }
        if (curNode.value == '^') {
            if (check_flag == true) {
                //boolean flag3 = check2(prefix);
                boolean flag4 = check3(prefix);
                if (flag4) {
                    System.out.println("good password+" + prefix.toString());
                    results.enqueue(prefix.toString());
                }
            } else
                results.enqueue(prefix.toString());

        }
        // System.out.println(curNode.size(curNode));
        int N = curNode.size(curNode);
        for (int i = 0; i < N; i++) {

            if (i != 0)
                curNode = curNode.nextNode;

            prefix.append(curNode.value);
            collect(curNode.childNode, prefix, results);
            prefix = prefix.deleteCharAt(prefix.length() - 1);
        }

    }

    private boolean check3(StringBuilder prefix) {

        pre[0] = 0;
        for (int i = 1; i < pre.length; i++)
            pre[i] = -1;
        qq = new Queue<StringBuilder>();
        StringBuilder prefix2 = new StringBuilder(prefix.toString());

        for (String dic : dic_words) {
            for (int i = 0; i < prefix2.length(); i++) {

                if (prefix2.charAt(i) == 55)
                    prefix2.replace(i, i + 1, "t");
                if (prefix2.charAt(i) == 52)
                    prefix2.replace(i, i + 1, "a");
                if (prefix2.charAt(i) == 48)
                    prefix2.replace(i, i + 1, "o");
                if (prefix2.charAt(i) == 51)
                    prefix2.replace(i, i + 1, "e");
                if (prefix2.charAt(i) == 53)
                    prefix2.replace(i, i + 1, "s");
                if (prefix2.charAt(i) == 49) {
                    prefix2.replace(i, i + 1, "i");
                    pre[i] = 1;
                }

            }
            qq.enqueue(prefix2);
            while (pre[0] == 0) {
                il_replace();
                for (int i = 0; i < pre.length; i++) {
                    if (pre[i] == 2)
                        prefix2.replace(i, i + 1, "l");
                    qq.enqueue(prefix2);
                }

            }
            for (StringBuilder pp : qq) {
                // System.out.println(pp.toString());
                if (pp.toString().contains(dic)
                        || prefix.toString().contains(dic))
                    return false;
            }

        }
        return true;
    }

    // emunerate i and l in prefix for 1;
    private void il_replace() {
        for (int i = pre.length - 1; i >= 0; i--) {
            if (pre[i] == 1 || pre[i] ==0) {
                if (pre[i] < il.length - 1) {
                    pre[i]++;

                    return;
                }else if(pre[i]!=-1){
                    pre[i] = 1;
                }
              
            }

        }

    }

    private boolean check2(StringBuilder prefix) {

        int n = 0;// number 0-9
        int c = 0;// character a-z
        int s = 0;// specail "!", "@", "$", "%", "&", or "*"
        for (int i = 0; i < prefix.length(); i++) {
            int j = prefix.charAt(i);

            if (j >= 48 && j <= 57)
                n++;
            else if (j >= 97 && j <= 122)
                c++;
            else
                s++;

        }
        if (n >= 3 || c >= 4 || s >= 3)
            return false;
        else
            return true;
    }

    // check function check whether prefix statisfy good passwords
    public boolean check(StringBuilder prefix) {
        int n = 0;// number 0-9
        int c = 0;// character a-z
        int s = 0;// specail "!", "@", "$", "%", "&", or "*"
        for (int i = 0; i < prefix.length(); i++) {
            int j = prefix.charAt(i);

            if (j >= 48 && j <= 57)
                n++;
            else if (j >= 97 && j <= 122)
                c++;
            else
                s++;

        }

        if (n >= 3 || c >= 4 || s >= 3)
            return false;

        else
            return true;

    }

    public Iterable<String> goodPws(String filename) {
        // TODO Auto-generated method stub
        check_flag = true;
        FileIn filein = new FileIn();

        dic_words = filein.readDic(filename);
        return keys();
    }
    /**
     * return 10 longest prefix of given passwords
     * @param pw
     * @return
     */
    public Queue<String> longestprefix(String pw){
        if(pw==null) return null;
       longest_gpw= new Queue<String>();
       //tansfer cur1 to keywithPrefix2
       cur1=new Node();
        int length=longestprefix(root,pw,0,-1);
        if(length==-1) return null;
        String goodpw_long=pw.substring(0, length);
        System.out.println("longest-prefix is:"+goodpw_long);
        longest_gpw=(Queue<String>) keywithPrefix2(cur1,goodpw_long);
        return longest_gpw;
    }
    public int longestprefix(Node x,String st,int d,int length){
   
        if(x==null) {
       
            return length;
        }
        if(x.value !='<') length=d;
        if(d==st.length()) {
      
            return length;
        }
        char c=st.charAt(d);
        cur1=x;
        return longestprefix(getChildNode(x,c),st,d+1,length);
    }

    public static void main(String[] args) {
        /**
        DLB dlb=new DLB();
        Queue<String> allgoodpw = new Queue<String>();
        FileIn file=new FileIn();
        allgoodpw=file.readDic("Good_Passwords.txt");
        for (String eachgoodpw : allgoodpw) {
            dlb.addPw(eachgoodpw.toString());
        }
        System.out.println("already insert good passwords into DLB Data Structrue");
        System.out.println("Please input your passwords:");
        String s = StdIn.readString();
        if(dlb.search(s)==true)
        System.out.println("Your password is good!");
        else{
            System.out.println("Your password doesn't meet requirements. Here are 10 recommendations");
            Queue<String> suggestq=new Queue<String>();
            suggestq=dlb.longestprefix(s);
            for(String s1:suggestq) System.out.println(s1);
        }
        **/

    }
        

}
