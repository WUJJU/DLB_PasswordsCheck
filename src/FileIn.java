

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileIn {
    
    public Queue<String> readDic(String filename){
        
  
    BufferedReader br;
    Queue<String>   queue=new Queue<String>();
    Queue<String>q0=new Queue<String>();
    DLB dlb = new DLB();
    try {
        br = new BufferedReader(new FileReader(filename));
       
        String line = br.readLine();
        dlb = new DLB();
        while (line != null) {
            queue.enqueue(line);
            System.out.println("read:"+line+" from my dic"+". Total Memory:"+Runtime.getRuntime().totalMemory()/(1024*1024)+"M");
            //System.out.println("Total Memory:"+Runtime.getRuntime().totalMemory()/(1024*1024)+"M");
      
           
           line=br.readLine();
         
        }
    
        br.close();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
/**
    for (String eachpw : queue) {
        dlb.addPw(eachpw);
    }
  **/   
      return queue;
    }
    public Queue<String> readDic(String filename,DLB dlb){
        
        
        BufferedReader br;
        Queue<String>   queue=new Queue<String>();
        Queue<String>q0=new Queue<String>();
      
        try {
            br = new BufferedReader(new FileReader(filename));
           
            String line = br.readLine();
            dlb = new DLB();
            while (line != null) {
                queue.enqueue(line);
                System.out.println("read:"+line+" from my dic"+". Total Memory:"+Runtime.getRuntime().totalMemory()/(1024*1024)+"M");
                //System.out.println("Total Memory:"+Runtime.getRuntime().totalMemory()/(1024*1024)+"M");
                if(queue.size()>100000){
                for (String eachpw : queue) {
                    dlb.addPw(eachpw);
                }
                queue.clear();
                }
               
               line=br.readLine();
             
            }
        
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        for (String eachpw : queue) {
            dlb.addPw(eachpw);
        }
        queue.clear();
          return queue;
        }
}
