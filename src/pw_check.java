

import java.util.ArrayList;
import java.util.List;



public class pw_check {



    public static void main(String[] args) {
        DLB dlb = new DLB();
        BruteForce2 bf = new BruteForce2();
   
        if(args.length==1){
            FileOut fout = new FileOut();
            
        // all possible passwords
  
      Queue<String> allpw = new Queue<String>();
    bf.run();
       
          FileIn file=new FileIn();
          allpw=file.readDic("My_Dictionary.txt");
          for (String eachpw : allpw) {
              dlb.addPw(eachpw);
          }
          System.out.println("-----already read My_Dic.txt------");
        System.out.println("already insert into DLB Data Structrue");


        Queue<String> q2 = new Queue<String>();
        q2 = (Queue<String>) dlb.keys();
        for(String key:q2){
            System.out.println("X in DLB:"+key);
        }
   
        /**
         * get all good passwords
         * 
         */
    
       


        System.out.println("print all good passwords");
        Queue<String> goodpw = new Queue<String>();
    

        goodpw = (Queue<String>) dlb.goodPws("dictionary.txt");
        for (String key : goodpw) {
            System.out.println(key);
        }
        fout.createFile("Good_Passwords.txt", goodpw);
        System.out.println("Good_Passwords.txt is generated");
    }
        else{
            //add good passwords into another dlb
          

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
             List<String> top10=new ArrayList<String>();
                for(String s1:suggestq) top10.add(s1);
                
                for(int i=0;i<10;i++) System.out.println(i+":"+top10.get(i));
            }

        }
    }
}
