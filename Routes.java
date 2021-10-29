import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Routes extends JFrame{
	private JLabel route1, route2, route3;
	private JTextField routeTF1, routeTF2, routeTF3;
	private JButton againB, endB;
	private AgainButtonHandler abhandler;
	private EndButtonHandler ebhandler;

	public Routes()
	{
		//Instantiaitng Labels
		route1 = new JLabel("Route1: ");
		route2 = new JLabel("Route2: ");
		route3 = new JLabel("Route3: ");

		//Instantiating TextFields
		routeTF1 = new JTextField(10);
		routeTF2 = new JTextField(10);
		routeTF3 = new JTextField(10);

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
		route1.setSize(60,50);
		route2.setLocation(20, 110);
		route2.setSize(60,50);
		route3.setLocation(20, 170);
		route3.setSize(60,50);

		routeTF1.setLocation(80,40);
		routeTF1.setSize(150, 30);
		routeTF2.setLocation(80,110);
		routeTF2.setSize(150, 30);
		routeTF3.setLocation(80,170);
		routeTF3.setSize(150, 30);

		againB.setLocation(80, 250);
		againB.setSize(120,30);
		endB.setLocation(200, 250);
		endB.setSize(120,30);

		//placing components
		pane.add(route1);
		pane.add(route2);
		pane.add(route3);
		pane.add(routeTF1);
		pane.add(routeTF2);
		pane.add(routeTF3);
		pane.add(againB);
		pane.add(endB);

		//Size and display
		setSize(350, 330);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

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