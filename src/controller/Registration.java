package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import database.DbConn;
import modal.User;

public class Registration {
	public int registerUser(User user)throws Exception{
		int state = 0;
		String sqlSyntax = "INSERT INTO user (username, password, full_name, ic, phone_no, email, address, public_key)"
				+ " VALUES (?, ?, ?,?, ?, ?, ?,?)";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sqlSyntax);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getFname());
		ps.setString(4, user.getIcNo());
		ps.setString(5, user.getPhoneNo());
		ps.setString(6, user.getEmail());
		ps.setString(7, user.getAddress1()+","+user.getAddress2()+","+user.getAddress3());
		ps.setString(8, user.getPublicKey());
		state = ps.executeUpdate();
		ps.close();conn.close();
		return state;
	}
}
