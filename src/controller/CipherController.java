package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DbConn;
import modal.FileModal;

public class CipherController {
	ResultSet rs = null;
	
	public boolean saveCipher(FileModal cipherFile){
		boolean status = false;
		int state = 0;
		String sqlSyntax = "INSERT INTO `data_dump`( `name`, `data`, `date_stored`, `size_data`, `userid`) VALUES (?,?,?,?,?)";
		Connection conn;
		byte[] cipherData = cipherFile.getFileByte();
		int fileSize = cipherFile.getFileSize();

		try {
			conn = new DbConn().getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlSyntax);
			ps.setString(1, cipherFile.getFileName());
			ps.setBytes(2, cipherFile.getFileByte());
			ps.setString(3, "NOW()");
			ps.setInt(4, cipherFile.getFileSize());
			ps.setString(5,cipherFile.getReceiveName());
			state = ps.executeUpdate();
			if (state == 1){
				status = true;
			} else{
				status = false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return status;
	}

}
