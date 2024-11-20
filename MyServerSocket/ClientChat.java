import java.io.BufferedReader;
import java.io.InputStreamReader;
import util.*;
public class ClientChat {
    public static String usr_name;
    public static void main(String[] args){        
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Write your name: ");

        try{
            usr_name = input.readLine();
            System.out.println("Welcome " + usr_name);
        }catch(Exception e){
            System.out.println("Exception1");
        }

        MySocket s = new MySocket(usr_name);        
   
        new Thread() {
            public void run(){
                try{
                    String line;
                    while ((line = input.readLine()) != null){
                        s.write(line);                                        
                    }
                }catch (Exception e){
                    System.out.println("Exception2");
                }
            }
        }.start();




        new Thread() {
            public void run(){
                String line;
                while((line = s.read())!= null){ 
                    System.out.println(line);
                }
                s.close();
            }
        }.start();
    
    }
}
