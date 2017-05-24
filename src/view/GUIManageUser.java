package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.UserController;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

import modal.User;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

public class GUIManageUser {

	public JFrame frame;
	private JTable table;
	private String username;
	private JLabel lblAdmUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIManageUser window = new GUIManageUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setUsername(String username){
		this.username = username;
		lblAdmUsername.setText(this.username);
		
	}
	
	public void loadUsers(){
		DefaultTableModel model1 = (DefaultTableModel) table.getModel();
		model1.setRowCount(0);
		Vector<User> users = new Vector<User>();
		UserController uc = new UserController();
		try{
			users = uc.retieveUser();
			System.out.println(users);
			for(User user : users){
				Vector<String> row = new Vector<String>();
				row.addElement(user.getUsername());
				row.addElement(user.getFname());
				row.addElement(user.getIcNo());
				row.addElement(user.getPhoneNo());
				row.addElement(user.getEmail());
				row.addElement(user.getType());
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(row);
				table.setRowHeight(30);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the application.
	 */
	public GUIManageUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1041, 697);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 1025, 70);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("Username :");
		label.setFont(new Font("Candara", Font.PLAIN, 16));
		label.setBounds(713, 11, 79, 27);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Perfect Crypt");
		label_1.setFont(new Font("Candara", Font.BOLD, 45));
		label_1.setBounds(22, 4, 287, 55);
		panel.add(label_1);
		
		JButton button = new JButton("Log Out");
		button.setBounds(925, 4, 90, 28);
		panel.add(button);
		
		lblAdmUsername = new JLabel("administrator");
		lblAdmUsername.setFont(new Font("Candara", Font.PLAIN, 16));
		lblAdmUsername.setBounds(797, 11, 118, 27);
		panel.add(lblAdmUsername);
		
		JLabel label_3 = new JLabel("Server Adminstrator");
		label_3.setFont(new Font("Candara", Font.PLAIN, 16));
		label_3.setBounds(32, 43, 178, 27);
		panel.add(label_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(861, 70, 164, 588);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIUser guiUser = new GUIUser();
				guiUser.frame.setVisible(true);
			
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Candara", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 27, 131, 34);
		panel_1.add(btnNewButton);
		
		JButton btnEditUser = new JButton("Edit");
		btnEditUser.setFont(new Font("Candara", Font.PLAIN, 15));
		btnEditUser.setBounds(10, 72, 131, 34);
		panel_1.add(btnEditUser);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Candara", Font.PLAIN, 15));
		btnDelete.setBounds(10, 117, 131, 34);
		panel_1.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setFont(new Font("Candara", Font.PLAIN, 15));
		btnCancel.setBounds(10, 543, 131, 34);
		panel_1.add(btnCancel);
		
		JLabel lblUserMenu = new JLabel("User Menu");
		lblUserMenu.setFont(new Font("Candara", Font.BOLD, 16));
		lblUserMenu.setBounds(37, 0, 79, 27);
		panel_1.add(lblUserMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 841, 566);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Username", "Full Name", "IC","Phone No", "Email", "Account Type"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(300);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(130);
			table.getColumnModel().getColumn(4).setPreferredWidth(150);
			table.getColumnModel().getColumn(5).setPreferredWidth(120);
			table.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		loadUsers();
	}
}
