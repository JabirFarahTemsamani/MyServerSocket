package util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionTable {
    Map<String, MySocket> clients_map;
    Lock l;

    public ConnectionTable() {
        clients_map = new HashMap<>();
        l = new ReentrantLock();
    }

    public String addParticipant(String usr_orig, MySocket sc) {
        String usr = null;
        l.lock();
        try { 
            //Si en nombre de usuario esta cogido ponemos 3 valores aleatorios mas
            if(clients_map.containsKey(usr_orig)){
                do {
                    usr = usr_orig + "_" + (int) (Math.random() * 10) + (int) (Math.random() * 10) + (int) (Math.random() * 10);
                } while (clients_map.containsKey(usr));            
            }
            else{
                usr = usr_orig;
            }  
            clients_map.put(usr, sc);                      
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            l.unlock();
        }
        return usr;
    }

    public void broadcast(String msg, String nick) { 
        l.lock();
        try {
            for (String nickname : clients_map.keySet()) {
                if (!nickname.equals(nick)) {
                    MySocket sc = clients_map.get(nickname);
                    sc.write(nick + ": " + msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            l.unlock();
        }
    }

    public void removeParticipant(String my_usr) {
        l.lock();
        try {
            clients_map.remove(my_usr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            l.unlock();
        }
    }
}
