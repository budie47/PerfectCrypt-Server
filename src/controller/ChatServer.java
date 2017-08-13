package controller;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class ChatServer  extends UnicastRemoteObject implements ChatServerInt{
	
	private Vector v=new Vector();	
	public ChatServer() throws RemoteException{
		System.setProperty("java.rmi.server.hostname", "10.73.32.144");
	}
		
	public boolean login(ChatClientInt a) throws RemoteException{	
		//System.setProperty("java.rmi.server.hostname", "10.73.32.144");
		System.setProperty("java.rmi.server.hostname", "192.168.0.186");
		System.out.println(a.getName() + "  got connected....");	
		a.tell("You have Connected successfully.");
		publish(a.getName()+ " has just connected.");
		v.add(a);
		return true;		
	}
	
	public void publish(String s) throws RemoteException{
		//System.setProperty("java.rmi.server.hostname", "10.73.32.144");
		System.setProperty("java.rmi.server.hostname", "192.168.0.186");
	    System.out.println(s);
		for(int i=0;i<v.size();i++){
		    try{
		    	ChatClientInt tmp=(ChatClientInt)v.get(i);
				tmp.tell(s);
		    }catch(Exception e){
		    	//problem with the client not connected.
		    	//Better to remove it
		    }
		}
	}
	
	public void sayHello(){
		System.out.println("Hello dasndkjasfhjkas");
	}

	public Vector getConnected() throws RemoteException{
		System.setProperty("java.rmi.server.hostname", "10.73.32.144");
		return v;
	}
}
