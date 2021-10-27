/**
 * The Login class is designed to validate user's credential before calling the main window.
 * 
 * @author Yifei Zhang 251109144
 *
 */


package project_UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * The login window that implements UserWindows interface.
 *
 */

public class Login extends JFrame implements UserWindows, ActionListener {
	
	/**
	 * Variables used in Login class.
	 */
	private boolean validation;
	private String[] input;
	private static final long serialVersionUID = 1L;
	JLabel UNlb, PWlb, pic;	// Some text labels that contains appropriate text.
	JTextField username, showPW; // showPW is used to display unmasked password by interchanging visibility.
	JPasswordField hidePW; // The masked password.
	JButton submit;
	JCheckBox mask;	// A check box that masks and unmasks password.
	JPanel mainFunc;
	

	public void display() {								// Initializing components and displays the login window.
		pic = new JLabel(new ImageIcon("statistics.png"));
		username = new JTextField(20);
		showPW = new JTextField(20);
		hidePW = new JPasswordField(20);
		mainFunc = new JPanel();
		UNlb = new JLabel("Username:");
		PWlb = new JLabel("Password:");
		mask = new JCheckBox("Show Password");
		submit = new JButton("Submit");
		
		
		UNlb.setFont(new Font("Helvetica", Font.BOLD, 17));
		PWlb.setFont(new Font("Helvetica", Font.BOLD, 17));
		mask.setFont(new Font("Helvetica", Font.PLAIN, 15));
		username.setFont(new Font("Helvetica", Font.PLAIN, 15));
		showPW.setFont(new Font("Helvetica", Font.PLAIN, 15));
		hidePW.setFont(new Font("Helvetica", Font.PLAIN, 15));
		submit.setFont(new Font("Helvetica", Font.BOLD, 15));
		mainFunc.setLayout(null);
		mainFunc.setBackground(Color.lightGray);
		mask.setBackground(Color.lightGray);
		
		
		username.setBounds(200, 10, 300, 37);
		hidePW.setBounds(200, 60, 300, 37);
		showPW.setBounds(200, 60, 300, 37);
		mask.setBounds(510, 60, 150, 37);
		UNlb.setBounds(100, 10, 100, 37);
		PWlb.setBounds(100, 60, 100, 37);
		submit.setBounds(300, 110, 100, 42);
		

		mainFunc.add(username);
		mainFunc.add(hidePW);
		mainFunc.add(showPW);
		mainFunc.add(UNlb);
		mainFunc.add(PWlb);
		mainFunc.add(mask);
		mainFunc.add(submit);
		
		this.add(pic,BorderLayout.NORTH);	
		this.add(mainFunc,BorderLayout.CENTER);
		
		setSize(700,600);
		setTitle("Login");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showPW.setVisible(false);
		mask.addActionListener(this);
		submit.addActionListener(this);
		this.getRootPane().setDefaultButton(submit);
	}
	
	public void actionPerformed(ActionEvent e) {			// Collects user input and react to user's actions.
		if(e.getSource() == submit) {						// After clicking the submit button, it checks user's input from the credential pairs in DataBase.
			input = new String[]{username.getText(), String.valueOf(hidePW.getPassword())};
			validation = validate(input[0], input[1]);
			if(!validation) {								// If wrong then displays a notification window.
				Notification note = new Notification();
				this.dispose();
				note.display();
			}
			else {											// If correct displays the main window.
				MainWindow main = new MainWindow();
				this.dispose();
				main.display();
			}
		}
		else if(e.getSource() == mask) {					// Mask and unmask password input.
			if(mask.isSelected()) {
				showPW.setText(String.valueOf(hidePW.getPassword()));
				showPW.setVisible(true);
				hidePW.setVisible(false);
			}
			else {
				hidePW.setText(showPW.getText());
				hidePW.setVisible(true);
				showPW.setVisible(false);
			}
		}
	}
	
	private boolean validate(String username, String password) {	// The validation method that compares user input with credential pairs in Database.
		File credDB = new File("CredentialsDB.txt");
		boolean result = false;
		try {
			Scanner myReader = new Scanner(credDB);
			while(myReader.hasNextLine()) {
				String pair[] = myReader.nextLine().split(" ");
				if(pair[0].equals(username) && pair[1].equals(password)) result = true;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
