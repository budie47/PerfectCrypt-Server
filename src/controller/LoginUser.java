package controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import database.DbConn;
import modal.User;

public class LoginUser {
	public LoginUser(){
		super();
	}
	
	public String checkSystemAdmin(User user)throws Exception{
		
		String userType = null;
		
		String sql = "SELECT type FROM user WHERE username = ?";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,user.getUsername());
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			userType = rs.getString(1);
			
		}
		
		return userType;
	}
public int loginUser(User user) throws Exception{
	int state = 0;
	String pass = null;
	HashPassword hash = new HashPassword();
	
	String sqlSyntax = "SELECT password FROM user WHERE username = ?";
	Connection conn = new DbConn().getConnection();
	
	PreparedStatement ps =  conn.prepareStatement(sqlSyntax);
	ps.setString(1, user.getUsername());

	ResultSet rs = ps.executeQuery();
	
	if(rs.next()){
		
		pass = rs.getString(1);
		boolean isTrue = hash.validatePassword(user.getPassword(), pass);
		if(isTrue){
			state = 1;
		}
		//JOptionPane.showMessageDialog(null, isTrue, "Server Hello: ", JOptionPane.WARNING_MESSAGE);
	}
	//JOptionPane.showMessageDialog(null, state, "Server Hello: ", JOptionPane.WARNING_MESSAGE);
	return state;
}

public int loginAdmin(User user) throws Exception{
	int state = 0;
	String pass = null;
	HashPassword hash = new HashPassword();
	
	String userType = checkSystemAdmin(user);
	
	if(userType.equals("SYSTEM_ADMINISTRATOR")){
		String sqlSyntax = "SELECT password FROM user WHERE username = ?";
		Connection conn = new DbConn().getConnection();
		
		PreparedStatement ps =  conn.prepareStatement(sqlSyntax);
		ps.setString(1, user.getUsername());

		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
			
			pass = rs.getString(1);
			boolean isTrue = hash.validatePassword(user.getPassword(), pass);
			if(isTrue){
				state = 1;
			}
			//JOptionPane.showMessageDialog(null, isTrue, "Server Hello: ", JOptionPane.WARNING_MESSAGE);
		}
	} else{
		JOptionPane.showMessageDialog(null,"Login Fail. You are not adminstrator", "Login", JOptionPane.WARNING_MESSAGE);
	}
	

	
	return state;
}

}
