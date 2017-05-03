package test;

import java.rmi.Naming;
import java.util.Scanner;

import controller.FileSendCS;
import controller.FileSendingCSInt;

public class StartFileSend {

	public static void main(String[] args){

		try{
			FileSendCS c = new FileSendCS("imed");
			FileSendingCSInt sending = (FileSendingCSInt)Naming.lookup("rmi://192.168.0.157:1099/file");
			sending.fileSending(c);
			Scanner s = new Scanner(System.in);
			while (true){
				String line = s.nextLine();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
