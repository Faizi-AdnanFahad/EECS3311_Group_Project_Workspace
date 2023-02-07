package application;
import domain_objects.Reservation;
import domain_objects.*;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class guiBuilder {

	private JFrame frame;
	private JTextField firstInput;
	private JTextField lastInput;
	private JTextField phoneInput;
	private JTextField addressInput;
	private JTextField creditCardInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiBuilder window = new guiBuilder();
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
	public guiBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Customer Info
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(30, 33, 94, 14);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(30, 58, 94, 14);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblPhoneNum = new JLabel("Phone Num:");
		lblPhoneNum.setBounds(30, 83, 94, 14);
		frame.getContentPane().add(lblPhoneNum);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(30, 108, 94, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblCreditCard = new JLabel("Credit Card:");
		lblCreditCard.setBounds(30, 133, 94, 14);
		frame.getContentPane().add(lblCreditCard);
		
		//Customer Info Input
		firstInput = new JTextField();
		firstInput.setBounds(134, 30, 108, 20);
		frame.getContentPane().add(firstInput);
		firstInput.setColumns(10);
		
		lastInput = new JTextField();
		lastInput.setColumns(10);
		lastInput.setBounds(134, 55, 108, 20);
		frame.getContentPane().add(lastInput);
		
		phoneInput = new JTextField();
		phoneInput.setColumns(10);
		phoneInput.setBounds(134, 80, 108, 20);
		frame.getContentPane().add(phoneInput);
		
		addressInput = new JTextField();
		addressInput.setColumns(10);
		addressInput.setBounds(134, 105, 427, 20);
		frame.getContentPane().add(addressInput);
		
		creditCardInput = new JTextField();
		creditCardInput.setColumns(10);
		creditCardInput.setBounds(134, 130, 427, 20);
		frame.getContentPane().add(creditCardInput);
		
		//Reservation Date Chooser
		JLabel lblCheckinDate = new JLabel("Check-in Date:");
		lblCheckinDate.setBounds(335, 33, 108, 14);
		frame.getContentPane().add(lblCheckinDate);
		
		JLabel lblCheckoutDate = new JLabel("Check-out Date:");
		lblCheckoutDate.setBounds(335, 58, 108, 14);
		frame.getContentPane().add(lblCheckoutDate);
		
		JDateChooser checkInChooser = new JDateChooser();
		checkInChooser.setBounds(453, 33, 108, 20);
		frame.getContentPane().add(checkInChooser);
		
		JDateChooser checkOutChooser = new JDateChooser();
		checkOutChooser.setBounds(453, 58, 108, 20);
		frame.getContentPane().add(checkOutChooser);
		
		//Room Type
		JLabel lblRoomType = new JLabel("Room Type:");
		lblRoomType.setBounds(30, 179, 94, 14);
		frame.getContentPane().add(lblRoomType);
		
		JComboBox roomSelect = new JComboBox();
		roomSelect.setModel(new DefaultComboBoxModel(new String[] {"Standard", "Deluxe", "Suite", "Executive", "Presidential"}));
		roomSelect.setBounds(30, 204, 134, 22);
		frame.getContentPane().add(roomSelect);
		
		//Services
		JLabel lblServices = new JLabel("Services:");
		lblServices.setBounds(30, 255, 94, 14);
		frame.getContentPane().add(lblServices);
		
		JCheckBox parkCheck = new JCheckBox("Parking");
		parkCheck.setBounds(30, 276, 97, 23);
		frame.getContentPane().add(parkCheck);
		
		JCheckBox wifiCheck = new JCheckBox("Wifi");
		wifiCheck.setBounds(134, 276, 97, 23);
		frame.getContentPane().add(wifiCheck);
		
		JCheckBox petCheck = new JCheckBox("Pets");
		petCheck.setBounds(233, 276, 97, 23);
		frame.getContentPane().add(petCheck);
		
		//Output message
		JLabel lblOutput = new JLabel("");
		lblOutput.setForeground(new Color(0, 0, 0));
		lblOutput.setBounds(10, 376, 661, 124);
		frame.getContentPane().add(lblOutput);
		
		//Error message
		JLabel lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(10, 486, 661, 14);
		frame.getContentPane().add(lblError);
		
		//Create Reservation Button
		JButton createButton = new JButton("Create Reservation");
		
		
	//  resController = (ActionListener) new ReservationController(firstInput, lastInput, roomSelect,
	//	checkInChooser, checkOutChooser);
	//	createButton.addActionListener(resController);
		
		
		

		createButton.addMouseListener(new MouseAdapter() {
			@Override
			//Create Reservation clicked
			public void mouseClicked(MouseEvent e) { 
				Room room = RoomController.roomAvailable(roomSelect.getSelectedItem().toString());
				if (room != null) { //room of selected type is available
					Reservation reservation = ReservationController.createReservation(lastInput.getText(), firstInput.getText(), addressInput.getText(), phoneInput.getText(), creditCardInput.getText(), room, checkInChooser, checkOutChooser);
					displayReservation(lblOutput, reservation, roomSelect.getSelectedItem().toString());
				}
				else { //room NOT available
					lblError.setText("Error: Room not available");
				}
			}
		});
		createButton.setBounds(273, 342, 155, 23);
		frame.getContentPane().add(createButton);
	}
	
	//display reservation info on successful creation
	private void displayReservation(JLabel output, Reservation reservation, String roomType) {
		output.setText("<html>Reservation Created - " + "<br/>Name: " + firstInput.getText() + " " + lastInput.getText() + "<br/> Phone: " + phoneInput.getText() 
		+ "<br/> Address: " + addressInput.getText()+ "<br/> Room Type:" + roomType);
	}
}
