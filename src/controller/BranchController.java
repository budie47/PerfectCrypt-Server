package controller;

import modal.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import database.DbConn;

public class BranchController {
	
	public int insertBranch(Branch branch)throws Exception{
		int state = 0;
		String sql =  "INSERT INTO branch (branch_name,tel_no,address,status,create_by) VALUES (?,?,?,?,?)";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, branch.getBranch_name());
		ps.setString(2, branch.getTel_no());
		ps.setString(3, branch.getAddress());
		ps.setString(4, branch.getStatus());
		ps.setString(5, branch.getCreated_by());
		state = ps.executeUpdate();
		ps.close();conn.close();
		return state;
	}
	
	public Vector<Branch> retrieveBranch()throws Exception{
		Vector<Branch> branches = new Vector<Branch>();
		String sql = "SELECT branch_name,tel_no,address,status FROM branch";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Branch branch = new Branch();
			branch.setBranch_name(rs.getString(1));
			branch.setTel_no(rs.getString(2));
			branch.setAddress(rs.getString(3));
			branch.setStatus(rs.getString(4));
			
			branches.add(branch);
		}
		rs.close();ps.close();conn.close();
		return branches;
	} 
	
	public int getBranchId(String branchName)throws Exception{
		int branchId = 0;
		String sql = "SELECT branch_id FROM `branch` WHERE branch_name LIKE ?";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "'%"+branchName+"%'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			branchId = rs.getInt(1);
		}
		
		return branchId;
	}

}
