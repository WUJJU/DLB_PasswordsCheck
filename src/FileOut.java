

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileOut {
    public File file;
    public void createFile(String st,Queue<String> qu2){
        try{
            File file = new File(st);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                    file.createNewFile();
            }
          
            FileWriter fw=new FileWriter(file,true);
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
