import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Welcome extends JFrame
{
	private JLabel welcomeL;
	private JButton welcomeB;
	private WelcomeButtonHandler wbHandler;

	public Welcome(){

		//Instantating label
		welcomeL = new JLabel("Welcome to our app",SwingConstants.CENTER);

		//Instantating Button
		welcomeB = new JButton("Let's begin.");
		wbHandler = new WelcomeButtonHandler();
		welcomeB.addActionListener(wbHandler);

		//Title of window
		setTitle("Welcome page");

		//Getting container
		Container pane = getContentPane();

		//Set the layout#
		pane.setLayout(null);
		//pane.setLayout(new GridLayout(2, 1));
		welcomeL.setLocation(90,100);
		welcomeL.setSize(150,35);
		welcomeB.setLocation(70,200);
		welcomeB.setSize(200,45);

		//Place componnets
		pane.add(welcomeL);
		pane.add(welcomeB);

		//Size and display
		setSize(350, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class WelcomeButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
			new Activity();
		}
	}

	public static void main(String[] args){
		new Welcome();
	}
}