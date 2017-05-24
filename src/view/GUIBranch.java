package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import controller.BranchController;
import modal.Branch;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GUIBranch {

	public JFrame frame;
	private JTextField tBranchName;
	private JTextField tTelephoneNo;
	private JTextArea tAddress;
	private String username;
	private JLabel lblAdminUser;
	private JButton btnAddBranch;

	private JButton btnCancel;
	private JButton btnEditBranch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBranch window = new GUIBranch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void clearGuiBranch(){
		tBranchName.setText("");
		tTelephoneNo.setText("");
		tAddress.setText("");
	}
	
	public void setAddBranch(){
		btnEditBranch.setVisible(false);
		btnAddBranch.setVisible(true);
	}
	
	public void setEditBranch(){
		btnEditBranch.setVisible(true);
		btnAddBranch.setVisible(false);
	}

	
	public void setUsername(String username){
		this.username = username;
		lblAdminUser.setText(this.username);
		
	}

	/**
	 * Create the application.
	 */
	public GUIBranch() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 486, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 472, 70);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("Username :");
		label.setFont(new Font("Candara", Font.PLAIN, 16));
		label.setBounds(385, 4, 79, 27);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Perfect Crypt");
		label_1.setFont(new Font("Candara", Font.BOLD, 45));
		label_1.setBounds(22, 4, 287, 55);
		panel.add(label_1);
		
		lblAdminUser = new JLabel("administrator");
		lblAdminUser.setFont(new Font("Candara", Font.PLAIN, 16));
		lblAdminUser.setBounds(371, 24, 118, 27);
		panel.add(lblAdminUser);
		
		JLabel label_3 = new JLabel("Server Adminstrator");
		label_3.setFont(new Font("Candara", Font.PLAIN, 16));
		label_3.setBounds(32, 43, 178, 27);
		panel.add(label_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 81, 450, 289);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBranchName = new JLabel("Branch Name");
		lblBranchName.setFont(new Font("Candara", Font.PLAIN, 14));
		lblBranchName.setBounds(19, 65, 128, 27);
		panel_1.add(lblBranchName);
		
		tBranchName = new JTextField();
		tBranchName.setColumns(10);
		tBranchName.setBounds(171, 64, 269, 27);
		panel_1.add(tBranchName);
		
		JLabel lblAddNewBranch = new JLabel("Add New Branch");
		lblAddNewBranch.setFont(new Font("Candara", Font.PLAIN, 16));
		lblAddNewBranch.setBounds(19, 11, 178, 27);
		panel_1.add(lblAddNewBranch);
		
		JLabel lblTelephoneNo = new JLabel("Telephone No");
		lblTelephoneNo.setFont(new Font("Candara", Font.PLAIN, 14));
		lblTelephoneNo.setBounds(19, 103, 128, 27);
		panel_1.add(lblTelephoneNo);
		
		tTelephoneNo = new JTextField();
		tTelephoneNo.setColumns(10);
		tTelephoneNo.setBounds(171, 102, 269, 27);
		panel_1.add(tTelephoneNo);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Candara", Font.PLAIN, 14));
		lblStatus.setBounds(19, 240, 128, 27);
		panel_1.add(lblStatus);
		
		JComboBox cStatus = new JComboBox();
		cStatus.setModel(new DefaultComboBoxModel(new String[] {"Active", "Inactive"}));
		cStatus.setBounds(171, 236, 269, 32);
		panel_1.add(cStatus);
		
		JLabel label_4 = new JLabel("Address");
		label_4.setFont(new Font("Candara", Font.PLAIN, 14));
		label_4.setBounds(19, 142, 128, 27);
		panel_1.add(label_4);
		
		tAddress = new JTextArea();
		tAddress.setBounds(171, 142, 269, 83);
		panel_1.add(tAddress);
		
		btnAddBranch = new JButton("Add Branch");
		btnAddBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result;
				Branch branch = new Branch();
				BranchController bc =new BranchController();
				
				branch.setBranch_name(tBranchName.getText());
				branch.setAddress(tAddress.getText());
				branch.setTel_no(tTelephoneNo.getText());
				branch.setStatus(cStatus.getSelectedItem().toString());
				branch.setCreated_by(username);
				try {
					result = bc.insertBranch(branch);
					if(result == 0){
						JOptionPane.showMessageDialog(null,"Insert Branch", "Error during Adding new Branch", JOptionPane.WARNING_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,"Branch Inserted", "Branch", JOptionPane.WARNING_MESSAGE);
						clearGuiBranch();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAddBranch.setFont(new Font("Candara", Font.BOLD, 18));
		btnAddBranch.setBounds(8, 381, 215, 36);
		frame.getContentPane().add(btnAddBranch);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIManageBranch gmb = new GUIManageBranch();
				gmb.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setFont(new Font("Candara", Font.BOLD, 18));
		btnCancel.setBounds(245, 381, 215, 36);
		frame.getContentPane().add(btnCancel);
		
		
		btnEditBranch = new JButton("Save");
		btnEditBranch.setFont(new Font("Candara", Font.BOLD, 18));
		btnEditBranch.setBounds(8, 381, 215, 36);
		frame.getContentPane().add(btnEditBranch);
	}
}
