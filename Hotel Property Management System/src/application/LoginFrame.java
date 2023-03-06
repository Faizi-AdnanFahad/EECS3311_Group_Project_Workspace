package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginFrame implements ActionListener {
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JButton registerButton;
	private JButton loginButton;
	JLabel loginFailedMsg;
	private JFrame frame  = new JFrame();
	
	public LoginFrame() {
		frame.setVisible(true);
		frame.setBounds(100, 100, 697, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		window();
	}
	
	private void window() {
		
		ImageIcon image = new ImageIcon("src/application/hotel.png");
		
		// labels
		JLabel lblImage = new JLabel();
		lblImage.setIcon(image);
		lblImage.setBounds(215, 20, 250, 250);
		frame.getContentPane().add(lblImage);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font(null, Font.BOLD, 14));
		lblUsername.setBounds(250, 240, 94, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font(null, Font.BOLD, 14));
		lblPassword.setBounds(250, 265, 94, 20);
		frame.getContentPane().add(lblPassword);
		
		// user input fields
		usernameInput = new JTextField();
		usernameInput.setBounds(325, 240, 108, 25);
		frame.getContentPane().add(usernameInput);
	    usernameInput.setColumns(10);
	    
	    passwordInput = new JPasswordField();
		passwordInput.setBounds(325, 265, 108, 25);
		frame.getContentPane().add(passwordInput);
	    usernameInput.setColumns(10);
	    
	    // buttons    
	    registerButton = new JButton("Register");
	    registerButton.setBounds(250, 295, 90, 25);
	    frame.getContentPane().add(registerButton);
	    registerButton.addActionListener(this);
	    
	    loginButton = new JButton("Login");
		loginButton.setBounds(342, 295, 90, 25);
		frame.getContentPane().add(loginButton);
		loginButton.addActionListener(this);
	   		
		loginFailedMsg = new JLabel();
		loginFailedMsg.setBounds(175, 320, 500, 30);
		loginFailedMsg.setFont(new Font(null, Font.ITALIC, 12));
		frame.getContentPane().add(loginFailedMsg);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
		if (e.getSource() == loginButton) {
			try {
				if (AccountsController.accountVerification(usernameInput.getText(), new String(passwordInput.getPassword()))) {
					CreateReservationFrame createFrame = new CreateReservationFrame();
					frame.setVisible(false);
				}
				else {
					loginFailedMsg.setText("The credentials you entered are either not registered or incorrect!");
					
				
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		if (e.getSource() == registerButton) {
			loginFailedMsg.setBounds(300, 320, 150, 30);
			try {
				loginFailedMsg.setText(AccountsController.registerAccount(usernameInput.getText(), new String(passwordInput.getPassword())));
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
				
		}
		
	}

}
