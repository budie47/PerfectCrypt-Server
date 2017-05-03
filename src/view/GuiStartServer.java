package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;

import controller.RmiTransferServer;
import controller.StartServerCtrl;
import controller.StaticRI;
import rmitransfer.TestServer.ServerImpl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;
import java.rmi.server.UnicastRemoteObject;

import java.io.*;


//import com.healthmarketscience.rmiio.*;

public class GuiStartServer {

	private JFrame frame;
	public JTextArea serverTextArea;
	Registry reg,rmiRegistry;
	StaticRI objServer ;
	ServerImpl fileServer ;
	JButton btnStartServer;
	JButton btnStopServer;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiStartServer window = new GuiStartServer();
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
	public GuiStartServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		System.setProperty("hostname", "192.168.0.157");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Perfect Crypt Server");
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 27));
		lblNewLabel.setBounds(21, 11, 311, 34);
		frame.getContentPane().add(lblNewLabel);
		
		serverTextArea = new JTextArea();
		serverTextArea.setBounds(21, 60, 389, 142);
		frame.getContentPane().add(serverTextArea);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					startServer();
					btnStartServer.setEnabled(false);
					btnStopServer.setEnabled(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStartServer.setBounds(21, 213, 188, 37);
		frame.getContentPane().add(btnStartServer);
		
		btnStopServer = new JButton("Stop");
		btnStopServer.setBounds(219, 213, 191, 37);
		
		btnStopServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					stop();
					btnStartServer.setEnabled(true);
					btnStopServer.setEnabled(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStopServer.setEnabled(false);
		frame.getContentPane().add(btnStopServer);
	}
	
	
    public void startServer() throws Exception {
    	objServer = new StartServerCtrl();
    	reg = LocateRegistry.createRegistry(1099);
    	reg.bind("PerfectCrypt", objServer);
        System.out.println("Server started");
		serverTextArea.setText("PerfectCrypt server Starting at ");
		serverTextArea.append("\r\n Listening...");
		
//		fileServer = new ServerImpl();
//        rmiRegistry = LocateRegistry.createRegistry(1100);
//        rmiRegistry.bind("server", fileServer);
        
        System.out.println("Server started at " + rmiRegistry.REGISTRY_PORT);
        System.out.println("Server started at ");
        
		fileServer = new ServerImpl();
		fileServer.start();
		serverTextArea.append("\r\n File Server Start...");
		
		
    }
    
    public void stop() throws Exception {
    	reg.unbind("PerfectCrypt");
    	UnicastRemoteObject.unexportObject(objServer, true);
    	UnicastRemoteObject.unexportObject(reg, true);
		serverTextArea.append("\r\n PerfectCrypt server Stopping");
		serverTextArea.append("\r\n Server stop from listening");
		fileServer.stop();
    }
    
    public void startFileServer() throws RemoteException {
		// TODO Auto-generated method stub
		ServerImpl server = new ServerImpl();
		try {
			server.start();
			System.out.println(server.getClientHost());
			Thread.sleep(5*60*1000);
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//	public void startServer(){
//		try{
//			StaticRI objServer = new StartServerCtrl();
//			Registry sreg = LocateRegistry.getRegistry();
//			
//			sreg.bind("PerfectCrypt",objServer);
//			serverTextArea.setText("PerfectCrypt server Starting");
//			serverTextArea.append("\r\n Listening...");
//			
//			
//		}catch(Exception err){
//			//JOptionPane.showMessageDialog(null, err, "Server Error: ", JOptionPane.WARNING_MESSAGE);
//			System.out.println(err);
//		}
//	}
}
