package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmitransfer.RMIInputStream;
import rmitransfer.RMIInputStreamImpl;
import rmitransfer.RMIOutputStream;
import rmitransfer.RMIOutputStreamImpl;
import rmitransfer.Server;

public class RmiTransferServer {


    public static class ServerImpl extends UnicastRemoteObject implements Server {
        Registry rmiRegistry;
        public ServerImpl() throws RemoteException {
            super();
        }
        public void start() throws Exception {
            rmiRegistry = LocateRegistry.createRegistry(1100);
            rmiRegistry.bind("server", this);
            System.out.println("Server started");
        }
        public void stop() throws Exception {
            rmiRegistry.unbind("server");
            unexportObject(this, true);
            unexportObject(rmiRegistry, true);
            System.out.println("Server stopped");
        }
        
        public String sayHello() {
            return "Hello world";
        }
        public OutputStream getOutputStream(File f) throws IOException {
            return new RMIOutputStream(new RMIOutputStreamImpl(new FileOutputStream(f)));
        }

        public InputStream getInputStream(File f) throws IOException {
            return new RMIInputStream(new RMIInputStreamImpl(new 
            FileInputStream(f)));
        }
        
    }
}
