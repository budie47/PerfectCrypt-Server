package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.hazelcast.util.Base64;

import controller.AES256Encryption;

public class TestMasterKey {
	static AES256Encryption aes = new AES256Encryption();
	final static String FILE_NAME_MKEY_1 = "C:\\Users\\Default\\AppData\\Local\\PerfectCrypt\\mkey1.txt";
	final static String FILE_NAME_MKEY_2 = "C:\\ProgramData\\PerfectCrypt\\mkey2.txt";
	
	public static void main(String[] args){
		TestMasterKey tmk = new TestMasterKey();
		
		ArrayList<String> mkey = new ArrayList<String>();
		mkey = tmk.generateMKey();
		tmk.writeMkeyToFile(FILE_NAME_MKEY_1, mkey.get(0));
		tmk.writeMkeyToFile(FILE_NAME_MKEY_2, mkey.get(1));
		
		String readMkey1 = readMasterKey(FILE_NAME_MKEY_1);
		String readMkey2 = readMasterKey(FILE_NAME_MKEY_2);
//		
		System.out.println("READ MASTER KEY 1 : " + readMkey1);
		System.out.println("READ MASTER KEY 2 : " + readMkey2);
		
	}
	
	public static void  generateMasterKey(){
		try {
			SecretKey skey = aes.generateAES256Key();
			byte[] secretKey = skey.getEncoded();
			String strKey = new String(Base64.encode(secretKey));
			
			ArrayList<String> mkey = new ArrayList<String>();
			FileOutputStream fopMKEY1 = null;
			FileOutputStream fopMKEY2 = null;
			
			String message = strKey;
			for (int i = 0; i < message.length(); i += 22) {
				mkey.add(message.substring(i, Math.min(i + 22, message.length() )));

			}
			
			
			byte[] keySecret = Base64.decode(strKey.getBytes());
			SecretKeySpec keyspec = new SecretKeySpec(keySecret,"AES");
			
			File keyFile_1 = new File(FILE_NAME_MKEY_1);
			File keyFile_2 = new File(FILE_NAME_MKEY_2);
			
			if(keyFile_1.createNewFile()){
				

//				FileWriter fw = new FileWriter(FILE_NAME_MKEY_1);
//				BufferedWriter bw = new BufferedWriter(fw);
//				bw.write(mkey.get(0));
				
				//keyFile_1.setReadOnly();
				System.out.println("DONE Write Mkey 1");
				Runtime.getRuntime().exec("attrib +H "+FILE_NAME_MKEY_1);
			}
			fopMKEY1.write(mkey.get(0).getBytes());
			fopMKEY1.flush();
			fopMKEY1.close();
			if(keyFile_2.createNewFile()){
				
				fopMKEY2.write(mkey.get(1).getBytes());
				fopMKEY2.flush();
				fopMKEY2.close();
//				FileWriter fw = new FileWriter(FILE_NAME_MKEY_2);
//				BufferedWriter bw = new BufferedWriter(fw);
//				bw.write(mkey.get(1));
//				System.out.println("DONE Write Mkey 2");
				//keyFile_2.setReadOnly();
				Runtime.getRuntime().exec("attrib +H "+FILE_NAME_MKEY_2);
			}
			
			
			System.out.println("mkey 1 : " + mkey.get(0));
			System.out.println("meky 2 : " + mkey.get(1));
			System.out.println("secretKey form : "+secretKey);
			System.out.println("String form : "+strKey);
			System.out.println("secretKey form : "+secretKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String readMasterKey(String FILENAME){
		String mKey = null;
		
		
		BufferedReader br = null;
		FileReader fr = null;

		try {

		
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				mKey = sCurrentLine;
				//System.out.println(sCurrentLine);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
		return mKey;
	}
	
	public ArrayList<String> generateMKey(){
		ArrayList<String> mkey = new ArrayList<String>();
		AES256Encryption aes = new AES256Encryption();
		SecretKey skey;
		try {
			skey = aes.generateAES256Key();
			byte[] secretKey = skey.getEncoded();
			String strKey = new String(Base64.encode(secretKey));

			String message = strKey;
			for (int i = 0; i < message.length(); i += 22) {
				mkey.add(message.substring(i, Math.min(i + 22, message.length() )));

			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mkey;
	}
	
	public void writeMkeyToFile(String FILE, String mkey){
		FileOutputStream fop = null;
		File file;
		String content = mkey;

		try {

			file = new File(FILE);
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			file.setReadOnly();
			Runtime.getRuntime().exec("attrib +H "+FILE);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
