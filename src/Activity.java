package src;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Activity extends JFrame{
	private JLabel startL, stopL;
	private JButton searchB;
	private JComboBox<String> stop_locations, start_locations;
	private SearchButtonHandler sbHandler;
	public String SelectedStopLoc, SelectedStartLoc;

	public Activity(){

		String[] all_locations = {};
		try (CSVReader reader = new CSVReader(new FileReader("Scrapper/Addresses.csv"))){
			List<String[]> adjacencyMatrix = reader.readAll();
			all_locations = adjacencyMatrix.get(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}

		//Initializing label
		startL = new JLabel("Start: ",SwingConstants.LEFT);
		stopL = new JLabel("Stop: ", SwingConstants.LEFT);

		//Initializing button
		searchB = new JButton("Search");
		sbHandler = new SearchButtonHandler();
		searchB.addActionListener(sbHandler);

		//Initializing combobox
		start_locations = new JComboBox<String>();

		for(int i = 1; i < all_locations.length; i++){
			start_locations.addItem(all_locations[i]);
		}

		

		stop_locations = new JComboBox<String>();

		for(int i = 1; i < all_locations.length; i++){
			stop_locations.addItem(all_locations[i]);
		}


		//Title of window
		setTitle("Main page");

		//Getting container
		Container pane = getContentPane();
		pane.setLayout(null);
		startL.setLocation(90,20);
		startL.setSize(300,60);
		start_locations.setLocation(180,20);
		start_locations.setSize(300,60);
		stopL.setLocation(90,80);
		stopL.setSize(300,70);
		stop_locations.setLocation(180,90);
		stop_locations.setSize(300,60);
		searchB.setLocation(250, 200);
		searchB.setSize(100, 60);

		//placing components
		pane.add(startL);
		pane.add(start_locations);
		pane.add(stopL);
		pane.add(stop_locations);
		pane.add(searchB);

		//Size and display
		setSize(600, 400);
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
				FileWriter myWriter = new FileWriter("data/LocationQuery.txt");
				myWriter.write(SelectedStartLoc + "\n");
				myWriter.write(SelectedStopLoc);
				myWriter.flush();
				myWriter.close();
			}	catch (IOException k){}

			String[] n = {""};
			try {
				ReadCSV.main(n);
			} catch (IOException | CsvException ioException) {
				ioException.printStackTrace();
			}

			new Routes();

		}
	}

	public static void main(String[] args){
		new Activity();
	}


}