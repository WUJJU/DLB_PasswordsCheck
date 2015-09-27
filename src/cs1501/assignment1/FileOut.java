package cs1501.assignment1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import sun.org.mozilla.javascript.internal.ast.CatchClause;

public class FileOut {
    public File file;
    public void createFile(String st,Queue<String> qu2){
        try{
            File file = new File(st);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                    file.createNewFile();
            }
           
            FileWriter fw=new FileWriter(file);
            BufferedWriter bw =new BufferedWriter(fw);
        
            for (String key : qu2) {
                bw.write(key);
                bw.newLine();
        
            }
     
            bw.close();
            }catch(IOException e){
                e.printStackTrace();
                
            }
    }
  
   
}
