///package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Routes extends JFrame{ 
	private JLabel route1, route2, route3, myLabel;
	private JTextField dist1, dist2, dist3;
	private JTextField time1, time2, time3;
	private JTextField routeTF1, routeTF2, routeTF3;
	private JButton againB, endB;
	private AgainButtonHandler abhandler;
	private EndButtonHandler ebhandler;
	private ImageIcon bgimg;

	public Routes()
	
	{
	
		try(Scanner scanner = new Scanner(new File("data/GeneratedRoutes.txt"))) {
			String source1 = scanner.nextLine();
			String d1 = scanner.nextLine();
			String t1 = scanner.nextLine();
			String source2 = scanner.nextLine();
			String d2 = scanner.nextLine();
			String t2 = scanner.nextLine();
			String source3 = scanner.nextLine();
			String d3 = scanner.nextLine();
			String t3 = scanner.nextLine();

		
			
		//Instantiaitng Labels
		route1 = new JLabel("Route1: " );
		route2 = new JLabel("Route2: " );
		//route3 = new JLabel("Route3: " );
		dist1 = new JTextField("The total distance is " + d1);
		dist2 = new JTextField("The total distance is " + d2);
		//dist3 = new JLabel("The total distance is " + d3);
		time1 = new JTextField("The total time taken is " + t1);
		time2 = new JTextField("The total time taken is " + t2);
		//time3 = new JLabel("The total time taken is " + t3);
		route1.setFont(new Font("Serif", Font.BOLD, 20));
		route2.setFont(new Font("Serif", Font.BOLD, 20));


		route1.setForeground(Color.black);
		route2.setForeground(Color.black);
		//route3.setForeground(Color.black);
		dist1.setForeground(Color.black);
		dist2.setForeground(Color.black);
		//dist3.setForeground(Color.black);
		time1.setForeground(Color.black);
		time2.setForeground(Color.black);
		//time3.setForeground(Color.black);

		bgimg = new ImageIcon("images/leg2(mod).jpg");
      	myLabel = new JLabel(bgimg);
      	myLabel.setSize(600, 400);

		//Instantiating TextFields
		routeTF1 = new JTextField(source1);
		routeTF2 = new JTextField(source2);
		//routeTF3 = new JTextField(source3);

		//Instantiating buttons
		againB = new JButton("Try Again");
		abhandler = new AgainButtonHandler();
		againB.addActionListener(abhandler);

		endB = new JButton("Exit");
		ebhandler = new EndButtonHandler();
		endB.addActionListener(ebhandler);

		//Title of window 
		setTitle("Routes page");

		//Getting container
		Container pane = getContentPane();
		pane.setLayout(null);
		route1.setLocation(15, 80);
		route1.setSize(300,50);
		route2.setLocation(15, 190);
		route2.setSize(300,50);
		//route3.setLocation(20, 210);
		//route3.setSize(300,50);

		routeTF1.setLocation(80,80);
		routeTF1.setSize(450, 30);
		routeTF2.setLocation(80,190);
		routeTF2.setSize(450, 30);
		//routeTF3.setLocation(80,210);
		//routeTF3.setSize(450, 30);

		dist1.setLocation(80,110);
		dist1.setSize(350,15);
		time1.setLocation(80,125);
		time1.setSize(350,15);
		dist2.setLocation(80,220);
		dist2.setSize(350,15);
		time2.setLocation(80,235);
		time2.setSize(350,15);
		//dist3.setLocation(80,240);
		//dist3.setSize(350,10);
		//time3.setLocation(80,260);
		//time3.setSize(350,10);


		againB.setLocation(80, 300);
		againB.setSize(120,30);
		endB.setLocation(200, 300);
		endB.setSize(120,30);

		//placing components
		myLabel.add(route1);
		myLabel.add(route2);
		//myLabel.add(route3);
		myLabel.add(routeTF1);
		myLabel.add(dist1);
		myLabel.add(time1);
		myLabel.add(routeTF2);
		myLabel.add(dist2);
		myLabel.add(time2);
		//myLabel.add(routeTF3);
		//myLabel.add(dist3);
		//myLabel.add(time3);
		myLabel.add(againB);
		myLabel.add(endB);

		pane.add(myLabel);

		//Size and display
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	catch (FileNotFoundException e) {

	}}

	public class AgainButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			dispose();
			new Activity();
		}
	}

	public class EndButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}

	public static void main(String[] args)
	{
		new Routes();
	}

}