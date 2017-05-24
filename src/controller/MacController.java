package controller;

import com.hazelcast.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MacController {
//	public static void main(String[] argv) throws Exception {
//	    KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA");
//	    SecretKey key = keyGen.generateKey();
//
//	    Mac mac = Mac.getInstance(key.getAlgorithm());
//	    mac.init(key);
//
//	    String str = "This message will be digested";
//
//	    byte[] utf8 = str.getBytes("UTF8");
//	    byte[] digest = mac.doFinal(utf8);
//
//	    //String digestB64 = new sun.misc.BASE64Encoder().encode(digest);
//	    String digestB64 = new String(Base64.encode(digest));
//	    System.out.println(digestB64);
//	  }
//	
	
	  public static void main(String[] args) throws Exception {
		    System.out.println(hmacDigest("The quick brown fox jumps over the lazy dog", "key", "HmacSHA256"));
		    System.out.println(hmacDigest("The quick brown fox jumps over the lazy dog", "key", "HmacSHA256"));
		  }

		  public static String hmacDigest(String msg, String keyString, String algo) {
		    String digest = null;
		    try {
		      SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
		      Mac mac = Mac.getInstance(algo);
		      mac.init(key);

		      byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

		      StringBuffer hash = new StringBuffer();
		      for (int i = 0; i < bytes.length; i++) {
		        String hex = Integer.toHexString(0xFF & bytes[i]);
		        if (hex.length() == 1) {
		          hash.append('0');
		        }
		        hash.append(hex);
		      }
		      digest = hash.toString();
		    } catch (UnsupportedEncodingException e) {
		    } catch (InvalidKeyException e) {
		    } catch (NoSuchAlgorithmException e) {
		    }
		    return digest;
		  }
	

}

