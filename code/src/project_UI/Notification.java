/**
 * The notification window that displays if user has submitted wrong matching username or password.
 * 
 * @author Yifei Zhang 251109144
 *
 */

package project_UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A notification window that display appropriate message to user when invalid username or password is submitted.
 *
 */
public class Notification extends JFrame implements UserWindows, ActionListener{

	private static final long serialVersionUID = 1L;
	JButton retry, terminate;
	JLabel text;

	public void display() {		// Display a window that notifies user that incorrect username or password is submitted.

		retry = new JButton("Retry");
		terminate = new JButton("Terminate");
		text = new JLabel("Invalid username or password!");
		
		retry.setFont(new Font("Helvetica", Font.BOLD, 15));
		terminate.setFont(new Font("Helvetica", Font.BOLD, 15));
		text.setFont(new Font("Helvetica", Font.PLAIN, 21));
		
		text.setBounds(50, 30, 300, 37);
		retry.setBounds(50, 80, 120, 37);
		terminate.setBounds(220, 80, 120, 37);
		
		add(text); add(retry); add(terminate);
		
		setLayout(null);
		setSize(400,200);
		setTitle("Login failed");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		retry.addActionListener(this);
		terminate.addActionListener(this);
		this.getRootPane().setDefaultButton(retry);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == retry) {			// Open the login window to retry.
			Login l = new Login();
			this.dispose();
			l.display();
		}
		else if(e.getSource() == terminate) {	// Terminates the program.
			this.dispose();
		}
	}
}
