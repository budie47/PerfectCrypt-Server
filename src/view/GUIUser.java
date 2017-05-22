package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class GUIUser {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIUser window = new GUIUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 488, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 676, 70);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("Username :");
		label.setFont(new Font("Candara", Font.PLAIN, 16));
		label.setBounds(385, 4, 79, 27);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Perfect Crypt");
		label_1.setFont(new Font("Candara", Font.BOLD, 45));
		label_1.setBounds(22, 4, 287, 55);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("administrator");
		label_2.setFont(new Font("Candara", Font.PLAIN, 16));
		label_2.setBounds(371, 24, 118, 27);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Server Adminstrator");
		label_3.setFont(new Font("Candara", Font.PLAIN, 16));
		label_3.setBounds(32, 43, 178, 27);
		panel.add(label_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 81, 452, 466);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Candara", Font.PLAIN, 14));
		lblUsername.setBounds(10, 22, 128, 27);
		panel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Candara", Font.PLAIN, 14));
		lblPassword.setBounds(10, 60, 128, 27);
		panel_1.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Candara", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(10, 98, 128, 27);
		panel_1.add(lblConfirmPassword);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Candara", Font.PLAIN, 14));
		lblFullName.setBounds(10, 135, 128, 27);
		panel_1.add(lblFullName);
		
		JLabel lblIc = new JLabel("IC");
		lblIc.setFont(new Font("Candara", Font.PLAIN, 14));
		lblIc.setBounds(10, 173, 128, 27);
		panel_1.add(lblIc);
		
		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setFont(new Font("Candara", Font.PLAIN, 14));
		lblPhoneNo.setBounds(10, 215, 128, 27);
		panel_1.add(lblPhoneNo);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Candara", Font.PLAIN, 14));
		lblEmail.setBounds(10, 253, 128, 27);
		panel_1.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Candara", Font.PLAIN, 14));
		lblAddress.setBounds(10, 332, 128, 27);
		panel_1.add(lblAddress);
		
		textField = new JTextField();
		textField.setBounds(162, 21, 269, 27);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(162, 62, 269, 27);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(162, 100, 269, 27);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(162, 137, 269, 27);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(162, 175, 269, 27);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(162, 217, 269, 27);
		panel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(162, 255, 269, 27);
		panel_1.add(textField_6);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(162, 331, 269, 27);
		panel_1.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(162, 369, 269, 27);
		panel_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(162, 407, 269, 27);
		panel_1.add(textField_10);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setFont(new Font("Candara", Font.PLAIN, 14));
		lblBranch.setBounds(10, 291, 128, 27);
		panel_1.add(lblBranch);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(162, 293, 269, 27);
		panel_1.add(comboBox);
		
		JButton btnNewButton = new JButton("Add User");
		btnNewButton.setFont(new Font("Candara", Font.BOLD, 18));
		btnNewButton.setBounds(10, 557, 215, 36);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Candara", Font.BOLD, 18));
		btnCancel.setBounds(247, 557, 215, 36);
		frame.getContentPane().add(btnCancel);
	}
}
