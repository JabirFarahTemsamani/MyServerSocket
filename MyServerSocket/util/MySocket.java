package util;
import java.io.*;
import java.net.Socket;

public class MySocket{
    Socket s;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    BufferedReader buff;
    PrintWriter pw;
    String usr_name;
    
    
    public MySocket(String nickname){ 
        try{
            this.s = new Socket("127.0.0.1", 12345);   // loopback         
            this.buff   = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.pw = new PrintWriter(s.getOutputStream(), true);
            this.usr_name  = nickname;
            write(usr_name);
    
        }catch(Exception e){
            System.out.println("Error creating MySocket");
        }
    }

    public MySocket(Socket s){
        this.s = s;
        try{
            this.buff   = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.pw = new PrintWriter(s.getOutputStream(), true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        
    
    public String read(){
        try{
            return buff.readLine();
        }catch(Exception e){
            e.printStackTrace();
            return null; 
        }
    }   
    
    public void write(String message){
        pw.println(message);
    }

    public void close(){
        try{
            pw.close();
            buff.close();
            s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getNickname(){
        return usr_name;
    }
    public void setNickname(String nickname){
        this.usr_name=nickname;
    }
}
