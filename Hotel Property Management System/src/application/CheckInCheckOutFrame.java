package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class CheckInCheckOutFrame implements ActionListener {
	private JFrame frame = new JFrame();
	private JTable table;
	public static DefaultTableModel model;
	JTextField resNumInput;
    private JRadioButton rdbtnArr;
    private JRadioButton rdbtnDep;
    private JButton logOutButton;
    private JButton btnCheckIn;
    private JButton btnCheckOut;
    private JButton btnSearch;
    private JButton btnCancel;
    private JButton btnBilling;
    
	
	public CheckInCheckOutFrame() {
		window();
		queryPanel();
		reservationsDisplay();
		buttonsPanel();
	}
	
	/**
	 * Launch basic window
	 */
	private void window() {
		frame.setVisible(true);
		frame.setBounds(100, 100, 697, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHeader = new JLabel("Arrivals/Departures");
		lblHeader.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHeader.setBounds(25, 11, 120, 24);
		frame.getContentPane().add(lblHeader);
		
		logOutButton = new JButton("Logout");
		logOutButton.setFont(new Font("Dialog", Font.PLAIN, 10));
		logOutButton.setBounds(605, 11, 68, 18);
		logOutButton.setFocusable(false);
		logOutButton.addActionListener(this);
		frame.getContentPane().add(logOutButton);
	}
	
	/**
	 * Set search options
	 */
	private void queryPanel() {
		//create panel
		JPanel queryPanel = new JPanel();
		queryPanel.setBounds(10, 38, 560, 178);
		queryPanel.setLayout(null);
		queryPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		frame.getContentPane().add(queryPanel);
		
		//-----labels and textfields-----
		JLabel lblResNum = new JLabel("Reservation No.");
		lblResNum.setBounds(19, 25, 97, 20);
		queryPanel.add(lblResNum);
		lblResNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		resNumInput = new JTextField();
		resNumInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		resNumInput.setBounds(126, 25, 99, 20);
		queryPanel.add(resNumInput);
		resNumInput.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setBounds(78, 134, 38, 20);
		queryPanel.add(lblDate);
		
		JTextField dateField = new JTextField();
		dateField.setHorizontalAlignment(SwingConstants.CENTER);
		dateField.setEditable(false);
		dateField.setFont(new Font("Tahoma", Font.BOLD, 12));
		dateField.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		dateField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dateField.setColumns(10);
		dateField.setBounds(126, 134, 86, 20);
		queryPanel.add(dateField);
		//-----End of labels and texfields-----
		
		//-------radio buttons-------
		//selecting one or the other will disable certain features in window
		rdbtnArr = new JRadioButton("Arrivals");
		rdbtnArr.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					btnCheckIn.setEnabled(true);
					btnCheckOut.setEnabled(false);
					btnCancel.setEnabled(true);
				}
			}
		});
		rdbtnArr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnArr.setBounds(326, 25, 111, 23);
		rdbtnArr.setFocusable(false);
		queryPanel.add(rdbtnArr);
		
		rdbtnDep = new JRadioButton("Departures");
		rdbtnDep.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					btnCheckOut.setEnabled(true);
					btnCheckIn.setEnabled(false);
					btnCancel.setEnabled(false);
				}
			}
		});
		rdbtnDep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnDep.setBounds(326, 77, 111, 23);
		rdbtnDep.setFocusable(false);
		queryPanel.add(rdbtnDep);
		
		// place radio buttons in group so only one or the other can be selected;
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnArr);
		buttonGroup.add(rdbtnDep);
		//-------End of radio buttons-------
	}
	
	/**
	 * Set reservations display
	 */
	private void reservationsDisplay() {
		Object[] columnHeaders = {"Name", "Arrival Date", "Departure Date", "Room Type", "Room Number", "Reservation No."};
		model = new DefaultTableModel() { 
			@Override
			public boolean isCellEditable(int row, int column) {	
				return false;
			}
		};
		model.setColumnIdentifiers(columnHeaders);
		
		table = new JTable(model);
		table.setBounds(10, 288, 663, 264);
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10,218,560,334);
		frame.getContentPane().add(scroll);
		
		//center-align table cell values
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
	    renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		// manual row insert - delete later
		model.addRow(new Object[] {"Wayne, Bruce", "01-04-2023", "03-04-2023", "Standard", "100"} );
	}
	
	/**
	 * Set buttons panel
	 */
	private void buttonsPanel() {		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(572, 38, 101, 514);
		buttonsPanel.setLayout(null);
		buttonsPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		frame.getContentPane().add(buttonsPanel);
		
		btnCheckIn = new JButton("Check In");
		btnCheckIn.setBounds(4, 213, 95, 23);
		btnCheckIn.setFocusable(false);
		btnCheckIn.addActionListener(this);
		buttonsPanel.add(btnCheckIn);
		
		btnCheckOut = new JButton("Check Out");
		btnCheckOut.setBounds(4, 286, 95, 23);
		btnCheckOut.setFocusable(false);
		btnCheckOut.addActionListener(this);
		buttonsPanel.add(btnCheckOut);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(4, 21, 95, 23);
		btnSearch.setFocusable(false);
		btnSearch.addActionListener(this);
		buttonsPanel.add(btnSearch);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFocusable(false);
		btnCancel.setBounds(4, 332, 95, 23);
		btnCancel.addActionListener(this);
		buttonsPanel.add(btnCancel);
		
		btnBilling = new JButton("Billing");
		btnBilling.setBounds(4, 258, 95, 23);
		btnBilling.setFocusable(false);
		btnBilling.addActionListener(this);
		buttonsPanel.add(btnBilling);
	}
	
	// user alert method
	private void alertMsg(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logOutButton) {
			new LoginFrame();
			frame.dispose();
		}
		
		//search button clicked w/ empty fields and no selections alerts user
		if (e.getSource() == btnSearch) {	
			CheckInCheckOutController ctrl = new CheckInCheckOutController();
			boolean resNumIsEmpty = resNumInput.getText().isEmpty();
			
			//when user searches without any selections
			if ( !rdbtnArr.isSelected() && !rdbtnDep.isSelected() ) {
				this.alertMsg("Please select Arrivals or Departures!");
			}
			//when user searches for a specific arrival
			if (rdbtnArr.isSelected() && !resNumIsEmpty) {
				model.setRowCount(0);
				Object[] arr = ctrl.getResByNum(resNumInput.getText(), "Arrivals");
				resNumInput.setText("");
				if (arr != null) {
					model.addRow(arr);
				}
				else {
					this.alertMsg("The reservation number does not match today's arrivals");
				}
			}
			//when user searches for a specific departure
			if (rdbtnDep.isSelected() && !resNumIsEmpty) {
				// call controller to display the res details for specified room
				model.setRowCount(0);
				
			}
			//when user searches for all arrivals
			if (rdbtnArr.isSelected() && resNumIsEmpty) {
				// call controller to display all arrivals for this day
				
			}
			//when user searches for all departures
			if (rdbtnDep.isSelected() && resNumIsEmpty) {
				// call controller to display all departures for this day
				
			}
		}
		
		
	}
	
	// main method - delete later
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new CheckInCheckOutFrame();
			}
		});
	}
}
