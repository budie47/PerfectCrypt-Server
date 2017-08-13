package controller;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.hazelcast.util.Base64;

import database.DbConn;
import modal.User;

public class UserController {
	
	public Vector<User> retieveUser() throws Exception{
		Vector<User> users = new Vector<User>();
		String sql = "SELECT username,full_name,ic_new,phone_no,email,type FROM pc_adm_users";
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
		File userFolder = new File("file/"+user.getUsername());
		userFolder.mkdirs();
		return state;
	}
	public int registerUserAdm(User user)throws Exception{
		int state = 0;
		String sqlSyntax = "INSERT INTO `pc_adm_users`( `username`, `password`, `full_name`, "
				+ "`ic_new`, `phone_no`, `email`, `address`, "
				+ "`public_key`, `e_private_key`,  `type`,"
				+ " `branch_id`,`dh_public_key`, `dh_e_secret_key`) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		ps.setString(9, user.getEncryptedPrivateKey());
		ps.setString(10, user.getType());
		ps.setInt(11, user.getBranchId());
		ps.setString(12, user.getDhPublicKey());
		ps.setString(13, user.getDhPrivateKey());
		state = ps.executeUpdate();
		ps.close();conn.close();
		return state;
	}
	
	public String getEncryptedPrivateKey(int userId)throws Exception{
		String encryptedPrivateKey = null;
		String sql = "SELECT `e_private_key` FROM `pc_adm_users` WHERE user_id = ?";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			encryptedPrivateKey = rs.getString(1);
		}
		return encryptedPrivateKey;
	}
	public ArrayList<String> getEncryptedPrivateKeyNew(int userId)throws Exception{
		ArrayList<String> encryptedPrivateKey = new ArrayList<String>();
		String sql = "SELECT `e_private_key`,`password` FROM `pc_adm_users` WHERE user_id = ?";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			encryptedPrivateKey.add(rs.getString(1));
			encryptedPrivateKey.add(rs.getString(2));
		}
		return encryptedPrivateKey;
	}
	
	public ArrayList<String> getDHKey(int userId)throws Exception{
		
		String encryptedPrivateKey = null;
		ArrayList<String> dhKey = new ArrayList<String>();
		UserController uc = new UserController();
		
		 ArrayList<String> pkList = uc.getEncryptedPrivateKeyNew(userId);
		
		 String password = pkList.get(1);
		 String decryptedPrivateKey ;
		 String secondEncryption ;
		
		String sql = "SELECT `dh_public_key`, `dh_e_secret_key` FROM `pc_adm_users` WHERE user_id = ?";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			dhKey.add(rs.getString(1));
			decryptedPrivateKey = uc.decryptPrivateKeyNew(rs.getString(2), "-");
			secondEncryption = uc.encryptPrivateKey(decryptedPrivateKey, password);
			dhKey.add(secondEncryption);
		}
		
		ps.close();conn.close();
		return dhKey;
	}
	
	
	public ArrayList generateKey(String password){
		//boolean state = false;
		GenerateKeys gk;
		ArrayList keyPair = new ArrayList();
		try {
			gk = new GenerateKeys(2048);
			gk.createKeys();

			String publicKey = gk.getStringPublicKey(gk.getPublicKey());
			String privateKey = gk.getStringPrivateKey(gk.getPrivateKey());
			
			DiffieHellman dh = new DiffieHellman();
			String dhPublicKey = dh.getPublicKey().toString();
			String dhSecretKey = dh.getSecretKey().toString();
			
			
			String encryptedPrivateKey = encryptPrivateKeyNew(privateKey,password);
			String encryptedDHSecretKey = encryptPrivateKeyNew(dhSecretKey,password);
			//String decryptedPrivateKey = decryptPrivateKey(encryptedPrivateKey, password);
			
			keyPair.add(publicKey);
			keyPair.add(encryptedPrivateKey);
			keyPair.add(dhPublicKey);
			keyPair.add(encryptedDHSecretKey);
			keyPair.add(dhSecretKey);

			
			 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
		return keyPair;
	}
	
	public String encryptPrivateKeyNew(String privateKey,String Password) throws Exception{
		String encryptedPrivateKey = "";
		byte[] encryptedPrivateKeybyte ;
        String strIv = "18A5Z/IsHs6g8/65sBxkCQ==";
        String strKey = "";
        byte[] privateByte = privateKey.getBytes();
        GenerateMasterKey GMK = new GenerateMasterKey();
        
        String masterkey = GMK.getMasterKey();
        
		
		AES256Encryption aes = new AES256Encryption();
        SecretKey skey = aes.generateAESPasswordKey(masterkey,strIv);
			
	        byte[] raw = skey.getEncoded();
	        strKey = new String(Base64.encode(raw));
			//encryptedPrivateKey = aes.encrypt(strKey,strIv, privateKey);
	        encryptedPrivateKey = aes.encrypt(strKey, strIv, privateKey);
			
			//System.out.println("STRKey : " + strKey);

		return encryptedPrivateKey;
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
	        encryptedPrivateKey = aes.encrypt(strKey, strIv, privateKey);
			
			//System.out.println("STRKey : " + strKey);

		return encryptedPrivateKey;
	}
	
	public String decryptPrivateKeyNew(String encryptedKey,String Password) throws Exception{
		String decryptPrivateKey = "";
        String strIv = "18A5Z/IsHs6g8/65sBxkCQ==";
        String strKey = "";
        GenerateMasterKey GMK = new GenerateMasterKey();
        String masterkey = GMK.getMasterKey();
		AES256Encryption aes = new AES256Encryption();
        SecretKey skey;

			skey = aes.generateAESPasswordKey(masterkey,strIv);
	        byte[] raw = skey.getEncoded();
	        strKey = new String(Base64.encode(raw));
	        //System.out.println("STRKey : " +strKey);
	        decryptPrivateKey = aes.decrypt(strKey,strIv, encryptedKey.trim());

		return decryptPrivateKey;
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
	        //System.out.println("STRKey : " +strKey);
	        decryptPrivateKey = aes.decrypt(strKey,strIv, encryptedKey.trim());

		return decryptPrivateKey;
	}
	
	public static String encryptBlowfish(String to_encrypt, String strkey) {
		  try {
		    SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
		     Cipher cipher = Cipher.getInstance("Blowfish");
		     cipher.init(Cipher.ENCRYPT_MODE, key);
		     return new String(cipher.doFinal(to_encrypt.getBytes()));
		  } catch (Exception e) { return null; }
		}

		public static String decryptBlowfish(String to_decrypt, String strkey) {
		  try {
		     SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
		     Cipher cipher = Cipher.getInstance("Blowfish");
		     cipher.init(Cipher.DECRYPT_MODE, key);
		     byte[] decrypted = cipher.doFinal(to_decrypt.getBytes());
		     return new String(decrypted);
		  } catch (Exception e) { return null; }
		}
		
		public String getUserName(int userId) {
			// TODO Auto-generated method stub
			String username  = "" ;
			ResultSet rs;
			try {
				Connection conn = new DbConn().getConnection();
				String sql = "SELECT username FROM pc_adm_users WHERE user_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				rs = ps.executeQuery();
				while(rs.next()){
					username = rs.getString(1);
				}
				
				ps.close();conn.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return username;
		}

	
	public static void main(String[] args) {
		
		String publicKey;
		String privateKey;
		String decryptPrivateKey = null;
		UserController uc = new UserController();
		ArrayList keyPair = uc.generateKey("abc123");
		String encryptedKey = keyPair.get(3).toString();
		GenerateMasterKey gmk = new GenerateMasterKey();
		String masterKey = gmk.getMasterKey();
		try {
			decryptPrivateKey = uc.decryptPrivateKeyNew(encryptedKey,"abc123");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Private Key : " + keyPair.get(4));
		System.out.println("Encrypted Private Key : "+ encryptedKey);
		System.out.println("Decrypted Private key : " + decryptPrivateKey);
		
		

		
		if(keyPair.get(4).equals(decryptPrivateKey)){
			System.out.println("same.....");
		}else{
			System.out.println("not same.....");
		}


	}

}
