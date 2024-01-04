package Models;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import jars.ServerInterface;
import jars.User;

public class UserModule {
    private ServerInterface si;

    public UserModule() throws RemoteException, NotBoundException {
        si = ServerFinder.findServer();
    }

    public void registration(User u) throws RemoteException {
        si.registration(u);
    }

    public boolean alreadyExist(User u) throws RemoteException {
        try {
            if(si.findexExistingUsers().contains(u.getUserid()) || si.findexExistingUsers().contains(u.getMail()))
                return true;
            else 
                return false;
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
    }

    public User login(String id, String psw) throws RemoteException {
        User u = si.access(id, psw);
        return u;
    }
}
