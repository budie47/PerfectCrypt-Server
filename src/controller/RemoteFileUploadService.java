package controller;

import java.io.IOException;
import java.io.InputStream;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;

public class RemoteFileUploadService {
	public void uploadFile(String fileName, RemoteInputStream remoteFileData){
		try {
			InputStream fileData = RemoteInputStreamClient.wrap(remoteFileData);
			System.out.println(fileData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
