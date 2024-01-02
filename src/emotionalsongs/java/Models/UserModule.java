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

    public void registration(User u) throws RemoteException{
            si.registration(u);
    }

    public User login(String id, String psw) throws RemoteException{
        User u = si.access(id, psw);
        return u;
    }
}
