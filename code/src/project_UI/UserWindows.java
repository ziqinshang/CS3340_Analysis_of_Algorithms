/**
 * An interface to be implemented by the classes in Project_UI,
 * 
 * @author Yifei Zhang 251109144
 *
 */


package project_UI;

import java.awt.event.ActionEvent;

/**
 * The interface to be implemented by all classes in project_UI package.
 *
 */
public interface UserWindows{
	
	/**
	 * Display appropriate content to user.
	 */
	public void display();
	
	/**
	 * Reacts to user's actions.
	 * @param e An action performed.
	 */
	public void actionPerformed(ActionEvent e);
	
}
