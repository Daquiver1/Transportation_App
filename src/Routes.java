///package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Routes extends JFrame{
	private JLabel route1, route2, route3;
	private JLabel time_dist1, time_dist2, time_dist3;
	//private JLabel routeTF1, routeTF2, routeTF3;
	private JButton againB, endB;
	private AgainButtonHandler abhandler;
	private EndButtonHandler ebhandler;

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
		route1 = new JLabel("Route1: " + source1);
		route2 = new JLabel("Route2: " + source2);
		route3 = new JLabel("Route3: " + source3);
		time_dist1 = new JLabel("The total distance is " + d1 + "km");

		//Instantiating TextFields
		// routeTF1 = new JLabel(source1);
		// routeTF2 = new JLabel(source2);
		// routeTF3 = new JLabel(source3);

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
		route1.setLocation(20, 40);
		route1.setSize(300,50);
		route2.setLocation(20, 110);
		route2.setSize(300,50);
		route3.setLocation(20, 170);
		route3.setSize(300,50);

		//routeTF1.setLocation(80,40);
		//routeTF1.setSize(150, 30);
		time_dist1.setLocation(80,60);
		time_dist1.setSize(250,50);
		// routeTF2.setLocation(80,110);
		// routeTF2.setSize(150, 30);
		// routeTF3.setLocation(80,170);
		// routeTF3.setSize(150, 30);

		againB.setLocation(80, 250);
		againB.setSize(120,30);
		endB.setLocation(200, 250);
		endB.setSize(120,30);

		//placing components
		pane.add(route1);
		pane.add(route2);
		pane.add(route3);
		//pane.add(routeTF1);
		pane.add(time_dist1);
		///pane.add(routeTF2);
		//pane.add(routeTF3);
		pane.add(againB);
		pane.add(endB);

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