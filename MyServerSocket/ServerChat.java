import util.ConnectionTable;
import util.MyServerSocket;
import util.MySocket;

public class ServerChat {
    
    public static void main(String[] args){
        MyServerSocket ss = new MyServerSocket();
        ConnectionTable table = new ConnectionTable();
        System.out.println("Waiting for client...");

        while(true){
            MySocket sc = ss.accept();

            new Thread() {
                public void run(){

                    String usr_name=sc.read();                
                    String usr = table.addParticipant(usr_name, sc);
                    System.out.println(usr + " is online");
                    String content;
                    while((content=sc.read())!=null){
                        table.broadcast(content, usr);
                        System.out.println(usr_name +": "+ content);             
                    }
                    System.out.println(usr + " has left");
                    table.removeParticipant(usr);
                    sc.close();
                }
            }.start();
            

        }
    }
}
