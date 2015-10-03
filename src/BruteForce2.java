

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BruteForce2 {
    int[] pw;
    char[] chars;
    Queue<String> queue = new Queue<String>();
    DLB dlb1 = new DLB();
    DLB dlb=new DLB();
    FileOut fout = new FileOut();

    public BruteForce2() {
        pw = new int[6];

        // chars=new
        chars=new char[]{' ','0','1','2', '3', '4', '8',
                '9','$', '%', '&', '*'};
      // chars=new char[]{' ','0','1','2', '3', '4', '8',
        //       '9', 'a', 'b', 'c','e','f','i','l','m', 'n', 'o', 'p','s','t','!','@','$', '%', '&', '*'};
       /**
        chars = new char[] { ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '!', '@', '$', '%', '&', '*' };
**/
        Arrays.fill(pw, 1, pw.length, 1);
        // System.out.println(pw[0]+"----"+pw[1]);

    }

    public void run() {
        while (pw[0] == 0) {
            print();
            enumerate();

        }
        if (queue.isEmpty()) {
            System.out.println("BruteForce return empty password");

        } else {
            fout.createFile("My_Dictionary.txt", queue);
            System.out.println("------------------");
       
            System.out.println("all possible passwords got");
        }
     
    }

    private void enumerate() {
        // TODO Auto-generated method stub
        for (int i = pw.length - 1; i >= 0; i--) {
            if (pw[i] < chars.length - 1) {
                pw[i]++;
                return;
            }
            pw[i] = 1;
        }

    }

    // store all possible passwords into queue, each is stringbuilder
    private void print() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        for (int k = 1; k < pw.length; k++) {

            sb.append(chars[pw[k]]);
            // System.out.print(chars[pw[k]]);
        }
        if(dlb1.check(sb)){
            System.out.println(sb+" insert into");
            queue.enqueue(sb.toString());
        }else{
            System.out.println(sb+"  not insert into");
        }
      
      
      System.out.println("Total Memory:"+Runtime.getRuntime().totalMemory()/(1024*1024)+"M");
     
    }

    public static void main(String[] args) {
   
    }

}
