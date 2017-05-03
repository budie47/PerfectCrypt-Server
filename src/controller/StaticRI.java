package controller;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import com.healthmarketscience.rmiio.RemoteInputStream;

import modal.FileModal;
import modal.FileUser;
import modal.User;

public interface StaticRI extends Remote {
	public void sayHello()throws RemoteException;
	public boolean registerUser(User user)throws RemoteException;
	public boolean loginUser(User user) throws RemoteException;
	public Vector<User> searchFriend(String name) throws RemoteException;
	public boolean addFriend(Vector<User> friendUser) throws RemoteException;
	public Vector<User> getCurrentFriend(String name) throws RemoteException;
	public boolean sendCipher(byte[] cipherText, String filename, int sizeFile, String receiverName) throws RemoteException;
	public boolean sendData(FileModal f) throws RemoteException;
	public String getName() throws RemoteException;
	public void openServer(String userFile,String rmiAdd) throws RemoteException;
	public void checkPath(String pathFile) throws RemoteException;
	public String getPublicKey(String userName) throws RemoteException;
	public void saveData(String method, String receiveName, String receiverPath)throws RemoteException;
	public Vector<FileUser>getFileUser(String user)throws RemoteException, Exception;
	public String getFileNamePath(String fileName, String username) throws RemoteException;
	public String getMethodCrypto(String filePath,String username) throws RemoteException;


}