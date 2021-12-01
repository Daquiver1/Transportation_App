//package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Welcome extends JFrame
{
	private JLabel welcomeL, myLabel;
	private JButton welcomeB;
	private WelcomeButtonHandler wbHandler;
	private ImageIcon bgimg;

	public Welcome(){
      bgimg = new ImageIcon("images/leg1(mod).jpg");
      myLabel = new JLabel(bgimg);
      myLabel.setSize(350, 400);

		//Instantating label
		welcomeL = new JLabel("Welcome to our App", SwingConstants.CENTER);
		welcomeL.setFont(new Font("Serif", Font.BOLD, 16));
		//Instantating Button
		welcomeB = new JButton("Let's begin.");
		Color bg = new Color(0x87ceeb);
		welcomeB.setBackground(bg);
		wbHandler = new WelcomeButtonHandler();
		welcomeB.addActionListener(wbHandler);

		//Title of window
		setTitle("Welcome page");

		//Getting container
		Container pane = getContentPane();

		//Set the layout#
		pane.setLayout(null);
		//pane.setLayout(new GridLayout(2, 1));
		welcomeL.setLocation(80,100);
		welcomeL.setSize(150,35);
		welcomeB.setLocation(70,200);
		welcomeB.setSize(200,45);


		//Place componnets
		myLabel.add(welcomeL);
		myLabel.add(welcomeB);

		pane.add(myLabel);

		String workdr = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workdr);

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

