package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;

import database.DbConn;
import modal.User;

public class AddFriend {

	public int addFriend(Vector<User> users) throws Exception{
		int status = 0;
		ResultSet rs = null;
		String sqlSyntax = "INSERT INTO user_friends(user_id, friend_id, date_friend) VALUES (?,?,NOW())";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sqlSyntax);
		
		System.out.println(users.get(0).getUsername());
		System.out.println(users.get(1).getUsername());

		
		String me = getUserId(users.get(0).getUsername());
		String friend = getUserId(users.get(1).getUsername());
		
		ps.setString(1, me);
		ps.setString(2,friend);
	
		status = ps.executeUpdate();
		System.out.println(status);
		ps.close();conn.close();
		return status;
		
	}
	
	public String getUserId(String user){
		String Id = null;
		String sqlSyntax = "SELECT user_id FROM pc_adm_users WHERE username = ?";
		ResultSet rs;
		try{
			Connection conn = new DbConn().getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlSyntax);
			ps.setString(1, user);
			
			rs = ps.executeQuery();
			rs.next();
			Id = rs.getString(1);
			
			
			
			ps.close();
			conn.close();
		}catch(Exception err){
			//JOptionPane.showMessageDialog(null,err, "Connection with Server Error :", JOptionPane.WARNING_MESSAGE);
			System.out.println(err);
		}
		
		
		return Id;
	}
	
	public Vector<User> getCurrentFriend(String name){
		Vector<User> friends = new Vector<User>();
		String idUser = getUserId(name);
		String sqlSyntax = "SELECT pc_adm_users.username,pc_adm_users.full_name FROM user_friends INNER JOIN pc_adm_users ON user_friends.friend_id = pc_adm_users.user_id WHERE user_friends.user_id = ?";
		ResultSet rs ;
		try {
			Connection conn = new DbConn().getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlSyntax);
			ps.setString(1, idUser);
			rs = ps.executeQuery();
			while(rs.next()){
				User friend = new User();
				friend.setUsername(rs.getString(1));
				friend.setFname(rs.getString(2));
				friends.addElement(friend);
			}
		}catch (Exception err){
			err.printStackTrace();
		}
		return friends;
	}
}
