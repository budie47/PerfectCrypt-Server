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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIManageUser {

	private JFrame frame;
	private JTable table;

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
		frame.setBounds(100, 100, 762, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 752, 70);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("Username :");
		label.setFont(new Font("Candara", Font.PLAIN, 16));
		label.setBounds(440, 11, 79, 27);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Perfect Crypt");
		label_1.setFont(new Font("Candara", Font.BOLD, 45));
		label_1.setBounds(22, 4, 287, 55);
		panel.add(label_1);
		
		JButton button = new JButton("Log Out");
		button.setBounds(652, 4, 90, 28);
		panel.add(button);
		
		JLabel label_2 = new JLabel("administrator");
		label_2.setFont(new Font("Candara", Font.PLAIN, 16));
		label_2.setBounds(524, 11, 118, 27);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Server Adminstrator");
		label_3.setFont(new Font("Candara", Font.PLAIN, 16));
		label_3.setBounds(32, 43, 178, 27);
		panel.add(label_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(588, 70, 164, 386);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnCancel.setFont(new Font("Candara", Font.PLAIN, 15));
		btnCancel.setBounds(10, 341, 131, 34);
		panel_1.add(btnCancel);
		
		JLabel lblUserMenu = new JLabel("User Menu");
		lblUserMenu.setFont(new Font("Candara", Font.BOLD, 16));
		lblUserMenu.setBounds(37, 0, 79, 27);
		panel_1.add(lblUserMenu);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 81, 570, 364);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 566, 364);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
