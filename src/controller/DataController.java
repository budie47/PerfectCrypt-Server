package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import database.DbConn;
import modal.FileUser;

public class DataController {
	
	public DataController(){
		super();
	}
	public int saveData(String method,String user, String path, String digitalSignture) throws Exception{
		int state = 0;
		AddFriend ad = new AddFriend();
		String user_id = ad.getUserId(user);
		String sqlSyntax = "INSERT INTO `data_info`(`method`, `path`, `user_id`,`digital_signature`) VALUES (?,?,?,?)";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sqlSyntax);
		ps.setString(1, method);
		ps.setString(2, path);
		ps.setString(3, user_id);
		ps.setString(4, digitalSignture);
		state = ps.executeUpdate();
		ps.close();
		return state;
	}
	
	
	public Vector<FileUser> getCurrentData(String user_id)throws Exception{
		Vector<FileUser> dataUser = new Vector<FileUser>();
		String sqlSyntax = "SELECT method, path FROM data_info WHERE user_id = ?";
		ResultSet rs;
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sqlSyntax);
		ps.setString(1, user_id);
		System.out.println(user_id);
		rs = ps.executeQuery();
		System.out.println(rs);
		while(rs.next()){
			FileUser fu = new FileUser();
			String filePath = rs.getString(2);
			String[] fileNameArry = filePath.split("/");
			System.out.println(filePath);
			fu.setMethod(rs.getString(1));
			fu.setFilePath(fileNameArry[3]);
			dataUser.addElement(fu);
		}
		return dataUser;
		
	}
	
	public static void main(String[] args){
		DataController dc = new DataController();
		Vector<FileUser> fileUser = null;
		try {
			fileUser = dc.getCurrentData("19");
			for(int i =0; i<fileUser.size(); i++){
				fileUser.get(i).getMethod();
				fileUser.get(i).getFilePath();
				System.out.println(fileUser.get(i).getMethod() + "  "+ fileUser.get(i).getFilePath());
			}
			System.out.println(fileUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
