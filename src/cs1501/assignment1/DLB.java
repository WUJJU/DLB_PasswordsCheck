package cs1501.assignment1;

public class DLB {
    
    public Node root;
    public class Node{
        Node nextNode;
        Node childNode;
        char value;
        public Node(){
            
        }
        public Node(char c){
            this(c,null,null);
        }
        public Node(char c,Node nextNode, Node childNode){
            this.value=c;
            this.nextNode=nextNode;
            this.childNode=childNode;
        }
        public int size(Node cur){
            int i=1;
           
            while(cur.nextNode!=null){
                i++;
                cur=cur.nextNode;
            }
            return i;
        }
        
    }
    //constructor
    public DLB(){
        root=new Node();
    }
    /**
     * method used by addPW()
     * @param cur
     * @param c
     * @return
     */
    public Node addchildNode(Node cur,char c){
        if(cur.childNode==null) {
            cur.childNode=new Node(c);
            return cur.childNode;
        }
        else return addpeerNode(cur.childNode,c);
        
    }
    /**
     * method used by addPw()
     * @param peerNode
     * @param c
     * @return
     */
    public Node addpeerNode(Node peerNode,char c){
        if(peerNode.value==c) return peerNode;
        while(peerNode.nextNode!=null){
            peerNode=peerNode.nextNode;
            if(peerNode.value==c) return peerNode; 
        }
        peerNode.nextNode=new Node(c);
       
        return peerNode.nextNode;
        
    }
   
    //add every password into DLB
    public void addPw(String pw){
        pw=pw+"^";
        if(pw!=null){
         //   root=new Node();
            Node curNode=root;
            for(int i=0;i<pw.length();i++){
                System.out.println(i+":"+pw.charAt(i));
                curNode=addchildNode(curNode,pw.charAt(i));
             
            }
        }
        
    }
    
    
    //search key
    //return true if find;
    public boolean search(String pw){
        Node curNode=root;
        for(int i=0;i<pw.length();i++){
                  curNode=getChildNode(curNode,pw.charAt(i));
        }
        curNode=getChildNode(curNode,'^');
        if(curNode!=null){
            return true;
        }
        else
        
        return false;
    }
   public  Node getChildNode(Node curNode,char c){
       if(curNode==null) return null;
       
        return getPeerNode(curNode.childNode, c) ;
    }
    public Node getPeerNode(Node curNode,char c){
        if(curNode.value==c) return curNode;
        while(curNode.nextNode!=null){
            curNode=curNode.nextNode;
           if(curNode.value==c) return curNode;
        }
        return null;
    }
    /**
     * find all keys in DLB 
     * @return
     */
    public Iterable<String> keys(){
        return keywithPrefix("");
    }
    
    public Iterable<String> keywithPrefix(String prefix){
        Queue<String>results=new Queue<String>();
        
        collect(root.childNode,new StringBuilder(prefix),results);
        return results;
    }
    
    
    private void collect(Node curNode, StringBuilder prefix,Queue<String>results) {
        // TODO Auto-generated method stub
        if(curNode==null) return;
        if(curNode.value=='^') results.enqueue(prefix.toString());
       // System.out.println(curNode.size(curNode));
        for(int i=0;i<curNode.size(curNode);i++){
            
            if(i!=0)  curNode=curNode.nextNode;
           
            prefix.append(curNode.value);
            collect(curNode.childNode,prefix,results);
            prefix=prefix.deleteCharAt(prefix.length()-1);
        }
       
        
  
          
           
        
      
    }
    
    public static void main(String[] args) {
      DLB dlb=new DLB();
        String [] a={"she","sells","sea","shells","by","the","sea","shore"};
        for(int i=0;i<a.length;i++){
         
            dlb.addPw(a[i]);
        }
        StdOut.println("keys(\"\"):");
        for (String key : dlb.keys()) {
            StdOut.println(key);
        }
        StdOut.println();
    }
}
