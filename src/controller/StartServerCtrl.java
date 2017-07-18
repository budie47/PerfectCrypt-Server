package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;

import rmitransfer.TestServer.ServerImpl;
import view.GuiStartServer;
import database.DbConn;
import modal.FileModal;
import modal.FileUser;
import modal.User;

public class StartServerCtrl extends UnicastRemoteObject implements StaticRI
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;

	public StartServerCtrl() throws RemoteException{
		super();
	}
	protected StartServerCtrl(String n) throws RemoteException {
		super();
		name = n;
		// TODO Auto-generated constructor stub
	}
	
	
	public void sayHello(){
		JOptionPane.showMessageDialog(null, "Hello", "Server Hello: ", JOptionPane.WARNING_MESSAGE);
	}
	public boolean loginUser(User user) throws RemoteException{
		boolean state =  false;
		LoginUser login = new LoginUser();
		try {
			int result = login.loginUser(user);
			
			if (result == 0){
				state = false;
			}else{
				state = true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return state;
	}

	
	public boolean registerUser(User user) throws RemoteException {
		boolean state = false;
		Registration register = new Registration();
		try {
			int result = register.registerUser(user);
			File userFolder = new File("file/"+user.getUsername());
			userFolder.mkdirs();
			if(result == 0){
				state = false;
			} else{
				state = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public Vector<User> searchFriend(String name){
		Vector<User> users = new Vector<User>();
		String sql = null;
		ResultSet rs;
		
		try {
			Connection conn = new DbConn().getConnection();
			sql = "SELECT username, full_name FROM pc_adm_users WHERE username LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setFname(rs.getString(2));
				users.addElement(user);
			}
			System.out.println(users.size());
			//System.out.println(users.get(0).getFname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	public boolean addFriend(Vector<User> friendUser) throws RemoteException{
		boolean status = false;
		AddFriend af = new AddFriend();
		try {
			int result = af.addFriend(friendUser);
			if(result == 0){
				status =false;
			}else{
				status = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public Vector<User> getCurrentFriend(String name) throws RemoteException {
		Vector<User> friend = new Vector<User>();
		AddFriend af = new AddFriend();
		friend = af.getCurrentFriend(name);
		return friend;
	}
	@Override
	public boolean sendCipher(byte[] cipherText, String filename, int sizeFile, String receiverName) throws RemoteException {
		
		System.out.println("dah smpai sini");
		boolean status = false;
		CipherController cc = new CipherController();
		status = true;
		//status = cc.saveCipher(file);
		return status;
	}
	@Override
	public boolean sendData(FileModal f) throws RemoteException {
		// TODO Auto-generated method stub
		boolean state = false;
		
		return state;
	}
	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public void openServer(String userFile,String rmiAdd) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			FileSendCS c = new FileSendCS(userFile);
			FileSendingCSInt sending = (FileSendingCSInt)Naming.lookup(rmiAdd);
			sending.fileSending(c);
			Scanner s = new Scanner(System.in);
			while(true){
				String line = s.nextLine();
				
			}
			
		}catch(Exception err){
			err.printStackTrace();
		}
		System.out.println("Success transfer");
		
	}
	@Override
	public void checkPath(String pathFile) throws RemoteException {
		// TODO Auto-generated method stub
		File f = new File(pathFile);
		if (!f.exists())
		{
		   
		   f.mkdir();
		}
	}
	
	public String getPublicKey(String userName) throws RemoteException{
		String publicKey = "";
		ResultSet rs;
		try {
			Connection conn = new DbConn().getConnection();
			String sql = "SELECT public_key FROM pc_adm_users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			while(rs.next()){
				publicKey = rs.getString(1);
			}
			System.out.println(publicKey);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return publicKey;
	}
	
	public String getSenderPublicKey(int user_id) throws RemoteException{
		String publicKey = "";
		ResultSet rs;
		try {
			Connection conn = new DbConn().getConnection();
			String sql = "SELECT public_key FROM pc_adm_users WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			while(rs.next()){
				publicKey = rs.getString(1);
			}
			System.out.println(publicKey);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return publicKey;
	}
	@Override
	public void saveData(String method, String receiveName, String receiverPath,String digitalSignture,String sender) {
		// TODO Auto-generated method stub
		int state = 0;
//		System.out.println(method);
//		System.out.println(receiveName);
//		System.out.println(receiverPath);
		DataController sd = new DataController();
		try {
			state = sd.saveData(method, receiveName, receiverPath,digitalSignture,sender);
			if(state == 0){
				System.out.println("data NOT inserted");
			}else{
				System.out.println("data inserted");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Vector<FileUser>getFileUser(String user)throws Exception{
		//Vector<FileUser> fu = new Vector<FileUser>();
		AddFriend af = new AddFriend();
		DataController dc = new DataController();
		String user_id = af.getUserId(user);
		int id =  Integer.parseInt(user_id);
		//System.out.println(user_id);
		Vector<FileUser> dataUser = new Vector<FileUser>();
		String sqlSyntax = "SELECT method, path FROM data_info WHERE user_id = ?";
		ResultSet rs;
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sqlSyntax);
		ps.setInt(1,id);
		//System.out.println(id);
		rs = ps.executeQuery();
		//System.out.println(rs.getFetchSize());
		//System.out.println(rs);
		while(rs.next()){
			FileUser fu = new FileUser();
			String filePath = rs.getString(2);
			String[] fileNameArry = filePath.split("/");
			//System.out.println(filePath);
			fu.setMethod(rs.getString(1));
			fu.setFilePath(fileNameArry[3]);
			dataUser.addElement(fu);
		}
		return dataUser;

	}
	
	
	
	@Override
	public String getFileNamePath(String fileName, String username) throws RemoteException {
		// TODO Auto-generated method stub
		String filePath = null;
		ResultSet rs ;

		try{
			AddFriend ad = new AddFriend();
			String user_id = ad.getUserId(username);
			Connection conn = new DbConn().getConnection();
			String sql = "SELECT path FROM `data_info` WHERE user_id = ? AND path LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, "%"+fileName+"%");
			rs = ps.executeQuery();
			while(rs.next()){
				filePath = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return filePath;
	}
	@Override
	public String getMethodCrypto(String filePath,String username) throws RemoteException {
		// TODO Auto-generated method stub
		String method = null;
		ResultSet rs;
		try{
			AddFriend ad = new AddFriend();
			String user_id = ad.getUserId(username);
			Connection conn = new DbConn().getConnection();
			String sql = "SELECT method FROM data_info WHERE user_id = ? AND path LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setString(2,"%"+filePath+"%");
			rs = ps.executeQuery();
			while(rs.next()){
				method = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return method;
	}
	
	public String getDigitalSignature(String filePath,String username) throws RemoteException {
		// TODO Auto-generated method stub
		String digitalSignature = null;
		ResultSet rs;
		try{
			AddFriend ad = new AddFriend();
			String user_id = ad.getUserId(username);
			Connection conn = new DbConn().getConnection();
			String sql = "SELECT digital_signature FROM data_info WHERE user_id = ? AND path LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setString(2,"%"+filePath+"%");
			rs = ps.executeQuery();
			while(rs.next()){
				digitalSignature = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return digitalSignature;
	}
	
	public String getSenderPublicKey(String filePath,String username) throws RemoteException {
		// TODO Auto-generated method stub
		String senderPK = null;
		String sender_id= "0";
		ResultSet rs;
		try{
			AddFriend ad = new AddFriend();
			String user_id = ad.getUserId(username);
			Connection conn = new DbConn().getConnection();
			String sql = "SELECT sender_id FROM data_info WHERE user_id = ? AND path LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setString(2,"%"+filePath+"%");
			rs = ps.executeQuery();
			while(rs.next()){
				sender_id = rs.getString(1);
			}
			System.out.println(sender_id);
			System.out.println(sql);
			System.out.println(filePath);
			System.out.println(username);
			senderPK = getSenderPublicKey(Integer.parseInt(sender_id));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return senderPK;
	}

	public String getUserEncryptedPrivateKey(String username) throws RemoteException {
		// TODO Auto-generated method stub
		UserController uc = new UserController();
		AddFriend ad = new AddFriend();
		
		
		String encryptedPrivatekey = null;
		try {
			 String userId = ad.getUserId(username);
			encryptedPrivatekey = uc.getEncryptedPrivateKey(Integer.parseInt(userId));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encryptedPrivatekey;
	}


  
	

	
}
