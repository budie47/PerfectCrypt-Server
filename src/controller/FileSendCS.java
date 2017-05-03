package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileSendCS extends UnicastRemoteObject implements FileSentCSInt{

	private String name;
	protected FileSendCS() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FileSendCS(String n) throws RemoteException{
		super();
		name = n;
	}
	
	public boolean sendData(String filename, byte[] data,int len) throws RemoteException{
		try{
			File f = new File(filename);
			f.createNewFile();
			//f.getParentFile().mkdirs();
			FileOutputStream out = new FileOutputStream(f,true);
			out.write(data, 0, len);
			out.flush();
			out.close();
			System.out.println("Done Writting Data");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
		
	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return name;
	}

}
