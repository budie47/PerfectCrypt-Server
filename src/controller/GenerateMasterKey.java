package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import com.hazelcast.util.Base64;



public class GenerateMasterKey {
	
	final static String FILE_NAME_MKEY_1 = "C:\\Users\\Default\\AppData\\Local\\PerfectCrypt\\mkey1.txt";
	final static String FILE_NAME_MKEY_2 = "C:\\ProgramData\\PerfectCrypt\\mkey2.txt";
	
	public static void main(String[] args){
		GenerateMasterKey tmk = new GenerateMasterKey();
		
		ArrayList<String> mkey = new ArrayList<String>();
		mkey = tmk.generateMKey();
		tmk.writeMkeyToFile(FILE_NAME_MKEY_1, mkey.get(0));
		tmk.writeMkeyToFile(FILE_NAME_MKEY_2, mkey.get(1));
		
		System.out.println("THIS IS MASTER KEY : "+tmk.getMasterKey());

	}
	
	
	public String getMasterKey(){
		GenerateMasterKey GMK = new GenerateMasterKey();
		String readMkey1 = GMK.readMasterKey(FILE_NAME_MKEY_1);
		String readMkey2 = GMK.readMasterKey(FILE_NAME_MKEY_2);
		String masterKey = readMkey1+readMkey2;
		
		return masterKey;
		
	}
	
	
	

	public  String readMasterKey(String FILENAME){
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
