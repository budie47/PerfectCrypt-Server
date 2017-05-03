package controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import database.DbConn;
import modal.User;

public class LoginUser {
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
}
