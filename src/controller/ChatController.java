package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import database.DbConn;
import modal.Branch;
import modal.Message;

public class ChatController {
	
	UserController uc = new UserController();
	
	public int insertMessage(Message message)throws Exception{
		int state = 0;
		
		String sql =  "INSERT INTO `pc_message` (`sender_id`, `receiver_id`, `message`, `date`, `digital_signature`,`show_status`,`status_1`,`status_2`,`e_key`) VALUES ( ?, ?, ?, NOW(), ?,?,?,?,?);";
		Connection conn = new DbConn().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, message.getSender_id());
		ps.setInt(2, message.getReceiver_id());
		ps.setString(3, message.getMessage());
		ps.setString(4, message.getDigital_signature());
		ps.setString(5, "0");
		ps.setString(6, "0");
		ps.setString(7, "0");
		ps.setString(8, message.getKey());
		state = ps.executeUpdate();
		ps.close();conn.close();
		return state;

	}
	
	//										1				4
	public Vector<Message> getMessage(int sender_id, int receiver_id)throws Exception{
		 Vector<Message> messages = new Vector<Message>();
		 String sql = "SELECT message, sender_id, receiver_id,date,digital_signature,message_id,show_status FROM pc_message WHERE (receiver_id = ? AND sender_id = ?) OR ( receiver_id = ? AND sender_id = ?) ORDER BY date ASC";
		 Connection conn = new DbConn().getConnection();
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, receiver_id);
		 ps.setInt(2, sender_id);
		 ps.setInt(3, sender_id);
		 ps.setInt(4, receiver_id);
		 
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Message msg = new Message();
			 String sender_name = uc.getUserName(rs.getInt(2));
			 String receiver_name = uc.getUserName(rs.getInt(3));
			 msg.setSender_id(rs.getInt(2));
			 msg.setReceiver_id(rs.getInt(3));
			 msg.setSender_name(sender_name);
			 msg.setReceiver_name(receiver_name);
			 msg.setMessage(rs.getString(1));
			 msg.setDigital_signature(rs.getString(5));
			 msg.setDate_time(rs.getTimestamp("date").toString());
			 changeShowStatus(rs.getInt(6), rs.getInt(7)+1);
			 messages.add(msg);
			 
		 }
		ps.close();
		conn.close();

		return messages;
	}
	
	public Vector<Message> getMessageNewMessage(int sender_id, int receiver_id)throws Exception{
		 Vector<Message> messages = new Vector<Message>();
		 String sql = "SELECT message, sender_id, receiver_id,date,digital_signature,message_id,show_status,e_key "
		 		+ "FROM pc_message "
		 		+ "WHERE ((receiver_id = ? AND sender_id = ?) OR ( receiver_id = ? AND sender_id = ?))"
		 		+ " AND status_1 = 0 ORDER BY date ASC";
		 Connection conn = new DbConn().getConnection();
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, receiver_id);
		 ps.setInt(2, sender_id);
		 ps.setInt(3, sender_id);
		 ps.setInt(4, receiver_id);
		 
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Message msg = new Message();
			 String sender_name = uc.getUserName(rs.getInt(2));
			 String receiver_name = uc.getUserName(rs.getInt(3));
			 msg.setSender_id(rs.getInt(2));
			 msg.setReceiver_id(rs.getInt(3));
			 msg.setSender_name(sender_name);
			 msg.setReceiver_name(receiver_name);
			 msg.setMessage(rs.getString(1));
			 msg.setDigital_signature(rs.getString(5));
			 msg.setDate_time(rs.getTimestamp("date").toString());
			 msg.setMessage_id(rs.getInt(6));
			 msg.setKey(rs.getString(7));
			 //changeShowStatus(rs.getInt(6), 2);
			 messages.add(msg);
			 
		 }
		ps.close();
		conn.close();

		return messages;
	}
	
	public Vector<Message> getMessageThread(int sender_id, int receiver_id)throws Exception{
		 Vector<Message> messages = new Vector<Message>();
		 String sql = "SELECT message, sender_id, receiver_id,date,digital_signature,message_id,show_status FROM pc_message WHERE (receiver_id = ? AND sender_id = ?) OR ( receiver_id = ? AND sender_id = ?) ORDER BY date ASC";
		 Connection conn = new DbConn().getConnection();
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, receiver_id);
		 ps.setInt(2, sender_id);
		 ps.setInt(3, sender_id);
		 ps.setInt(4, receiver_id);
		 
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Message msg = new Message();
			 String sender_name = uc.getUserName(rs.getInt(2));
			 String receiver_name = uc.getUserName(rs.getInt(3));
			 msg.setSender_id(rs.getInt(2));
			 msg.setReceiver_id(rs.getInt(3));
			 msg.setSender_name(sender_name);
			 msg.setReceiver_name(receiver_name);
			 msg.setMessage(rs.getString(1));
			 msg.setDigital_signature(rs.getString(5));
			 msg.setDate_time(rs.getTimestamp("date").toString());
			 changeShowStatus(rs.getInt(6), 2);
			 messages.add(msg);
			 
		 }
		ps.close();
		conn.close();

		return messages;
	}
	
	public void changeShowStatus(int message_id, int status){
		String sql = "UPDATE pc_message SET show_status = ? WHERE message_id = ?";
		Connection conn;
		try {
			conn = new DbConn().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, message_id);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}
