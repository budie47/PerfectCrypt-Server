package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIAdminDashboard {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAdminDashboard window = new GUIAdminDashboard();
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
	public GUIAdminDashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 486, 258);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 676, 70);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("Username :");
		label.setFont(new Font("Candara", Font.PLAIN, 16));
		label.setBounds(287, 43, 79, 27);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Perfect Crypt");
		label_1.setFont(new Font("Candara", Font.BOLD, 45));
		label_1.setBounds(22, 4, 287, 55);
		panel.add(label_1);
		
		JButton button = new JButton("Log Out");
		button.setBounds(371, 4, 90, 28);
		panel.add(button);
		
		JLabel lblAdministrator = new JLabel("administrator");
		lblAdministrator.setFont(new Font("Candara", Font.PLAIN, 16));
		lblAdministrator.setBounds(371, 43, 118, 27);
		panel.add(lblAdministrator);
		
		JLabel lblServerAdminstrator = new JLabel("Server Adminstrator");
		lblServerAdminstrator.setFont(new Font("Candara", Font.PLAIN, 16));
		lblServerAdminstrator.setBounds(32, 43, 178, 27);
		panel.add(lblServerAdminstrator);
		
		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.setFont(new Font("Candara", Font.PLAIN, 16));
		btnStartServer.setBounds(97, 81, 130, 51);
		frame.getContentPane().add(btnStartServer);
		
		JButton btnManageUser = new JButton("Manage User");
		btnManageUser.setFont(new Font("Candara", Font.PLAIN, 16));
		btnManageUser.setBounds(250, 81, 130, 51);
		frame.getContentPane().add(btnManageUser);
		
		JButton btnBranchUser = new JButton("Branch User");
		btnBranchUser.setFont(new Font("Candara", Font.PLAIN, 16));
		btnBranchUser.setBounds(250, 157, 130, 51);
		frame.getContentPane().add(btnBranchUser);
		
		JButton btnOpenDirectory = new JButton("Open Directory");
		btnOpenDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOpenDirectory.setFont(new Font("Candara", Font.PLAIN, 14));
		btnOpenDirectory.setBounds(97, 157, 130, 51);
		frame.getContentPane().add(btnOpenDirectory);
	}
}
