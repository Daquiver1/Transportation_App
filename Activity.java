import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.util.*;
import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Activity extends JFrame{
	private JLabel startL, stopL;
	private JButton searchB;
	private JComboBox<String> stop_locations, start_locations;
	private SearchButtonHandler sbHandler;
	public String SelectedStopLoc, SelectedStartLoc;

	public Activity(){

		
		//Initializing label
		startL = new JLabel("Start: ",SwingConstants.LEFT);
		stopL = new JLabel("Stop: ", SwingConstants.LEFT);

		//Initializing button
		searchB = new JButton("Search");
		sbHandler = new SearchButtonHandler();
		searchB.addActionListener(sbHandler);

		//Initializing combobox
		start_locations = new JComboBox<String>();
		start_locations.addItem("Legon Hall");
		start_locations.addItem("Commonwealth");
		start_locations.addItem("Volta Hall");
		start_locations.addItem("JQB");
		start_locations.addItem("Chemistry Department");
		start_locations.addItem("CS Department");
		

		stop_locations = new JComboBox<String>();
		stop_locations.addItem("Legon Hall");
		stop_locations.addItem("Commonwealth");
		stop_locations.addItem("Volta Hall");
		stop_locations.addItem("JQB");
		stop_locations.addItem("Chemistry Department");
		stop_locations.addItem("CS Department");
		


		//Title of window
		setTitle("Main page");

		//Getting container
		Container pane = getContentPane();
		pane.setLayout(null);
		startL.setLocation(90,20);
		startL.setSize(150,60);
		start_locations.setLocation(180,20);
		start_locations.setSize(120,60);
		stopL.setLocation(90,80);
		stopL.setSize(150,70);
		stop_locations.setLocation(180,90);
		stop_locations.setSize(120,60);
		searchB.setLocation(120, 200);
		searchB.setSize(100, 60);

		//placing components
		pane.add(startL);
		pane.add(start_locations);
		pane.add(stopL);
		pane.add(stop_locations);
		pane.add(searchB);

		//Size and display
		setSize(350, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private class SearchButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
			SelectedStartLoc = (String) start_locations.getSelectedItem();
			SelectedStopLoc = (String) stop_locations.getSelectedItem();
			try {
				FileWriter myWriter = new FileWriter("Location1.txt");
				myWriter.write(SelectedStartLoc);
				myWriter.write(SelectedStopLoc);
				myWriter.flush();
				myWriter.close();
			}	catch (IOException k){}

			new Routes();

		}
	}

	public static void main(String[] args){
		new Activity();
	}


}