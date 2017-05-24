package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.crypto.SecretKey;

import com.hazelcast.util.Base64;

import database.DbConn;
import modal.User;

public class UserController {
	
	public Vector<User> retieveUser() throws Exception{
		Vector<User> users = new Vector<User>();
		String sql = "SELECT username,full_name,ic,phone_no,email,type FROM user";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			User user = new User();
			user.setUsername(rs.getString(1));
			user.setFname(rs.getString(2));
			user.setIcNo(rs.getString(3));
			user.setPhoneNo(rs.getString(4));
			user.setEmail(rs.getString(5));
			user.setType(rs.getString(6));
			
			users.add(user);
			
		}
		rs.close();ps.close();conn.close();
		return users;
	}
	
	public int registerUser(User user)throws Exception{
		int state = 0;
		String sqlSyntax = "INSERT INTO user (username, password, full_name, ic, phone_no, email, address, public_key)"
				+ " VALUES (?, ?, ?,?, ?, ?, ?,?)";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sqlSyntax);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getFname());
		ps.setString(4, user.getIcNo());
		ps.setString(5, user.getPhoneNo());
		ps.setString(6, user.getEmail());
		ps.setString(7, user.getAddress1());
		ps.setString(8, user.getPublicKey());
		state = ps.executeUpdate();
		ps.close();conn.close();
		return state;
	}
	
	public ArrayList generateKey(String password){
		//boolean state = false;
		GenerateKeys gk;
		ArrayList keyPair = new ArrayList();
		try {
			gk = new GenerateKeys(1024);
			gk.createKeys();
			//gk.writeToFile("key/"+user.getUsername()+"/KeyPair/publicKey", gk.getPublicKey().getEncoded());
			//gk.writeToFile("key/"+user.getUsername()+"/KeyPair/privateKey", gk.getPrivateKey().getEncoded());
			//publicKey = gk.getPublicKey().toString();	
			
			String publicKey = gk.getStringPublicKey(gk.getPublicKey());
			String privateKey = gk.getStringPrivateKey(gk.getPrivateKey());


			
			String encryptedPrivateKey = encryptPrivateKey(privateKey,password);
			keyPair.add(publicKey);
			keyPair.add(privateKey);
			keyPair.add(encryptedPrivateKey);
			 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
		return keyPair;
	}
	
	public String encryptPrivateKey(String privateKey,String Password) throws Exception{
		String encryptedPrivateKey = "";
		byte[] encryptedPrivateKeybyte ;
        String strIv = "18A5Z/IsHs6g8/65sBxkCQ==";
        String strKey = "";
        byte[] privateByte = privateKey.getBytes();
		
		AES256Encryption aes = new AES256Encryption();
        SecretKey skey = aes.generateAESPasswordKey(Password,strIv);
			
	        byte[] raw = skey.getEncoded();
	        strKey = new String(Base64.encode(raw));
			//encryptedPrivateKey = aes.encrypt(strKey,strIv, privateKey);
	        encryptedPrivateKeybyte = aes.encryptAES256(strKey, strIv, privateByte);
			
			System.out.println("STRKey : " + strKey);

		return encryptedPrivateKey;
	}
	
	public String decryptPrivateKey(String encryptedKey,String Password) throws Exception{
		String decryptPrivateKey = "";
        String strIv = "18A5Z/IsHs6g8/65sBxkCQ==";
        String strKey = "";
		
		AES256Encryption aes = new AES256Encryption();
        SecretKey skey;

			skey = aes.generateAESPasswordKey(Password,strIv);
	        byte[] raw = skey.getEncoded();
	        strKey = new String(Base64.encode(raw));
	        System.out.println("STRKey : " +strKey);
	        decryptPrivateKey = aes.decrypt(strKey,strIv, encryptedKey.trim());

		return decryptPrivateKey;
	}
	
	public static void main(String[] args) {
		
		String publicKey;
		String privateKey;
		String decryptPrivateKey = null;
		UserController uc = new UserController();
		ArrayList keyPair = uc.generateKey("Pass");
		String encryptedKey = keyPair.get(1).toString();
		try {
			decryptPrivateKey = uc.decryptPrivateKey(encryptedKey,"Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(keyPair.get(0));
		System.out.println(keyPair.get(1));
		System.out.println(encryptedKey);
		System.out.println(decryptPrivateKey);
		


	}

}
