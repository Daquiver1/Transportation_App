import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.io.PrintWriter;  


public class ReadCSV {

    public static void main(String[] args) throws IOException, CsvException {
        int average_car_speed;

        String source = "School of Law Legon";
        String destination = "Commonwealth Hall Legon";

        String filename = "Scrapper/Addresses.csv";

        List<String[]> r;    // this is a list because reader.readAll returns a list.
        try (CSVReader reader = new CSVReader(new FileReader(filename))){
            r = reader.readAll();
        }

        int row_start_index = 0;
        int column_end_index = 0;

        for(String[] array: r){
            System.out.println(Arrays.toString(array));

            // get index of destination from first row
            if (r.indexOf(array) == 0){
                for (int i = 0; i < array.length; i++){
                    if (array[i].contains(destination)){
                        column_end_index = i;
                    }
                }
            }


            // get index of source from first column
            if (array[0].contains(source)) {
                int index = r.indexOf(array);
                row_start_index = index;
            }

        }

        System.out.println(row_start_index);
        System.out.println(column_end_index);

        // variables
        double actual_distance = 0;

        // Finding total distance
        actual_distance = Double.parseDouble(r.get(row_start_index)[column_end_index]);

        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Actual Distance: " + actual_distance);

        String[] destinationArray = r.get(0);    // the first row in the csv
        String[] startArray = r.get(row_start_index);

        // routes
        Map<String, Double> routes = new LinkedHashMap<String, Double>();

        // explored landmarks
        List<String> exploredPathIndexes = new ArrayList<>();
        exploredPathIndexes.add(source);

        double current_distance_to_destination = actual_distance;
        Double[] min_array = {};

        for (int j = 0; j< 5; j++){
            min_array = generatePath(r, startArray, destinationArray,  actual_distance, exploredPathIndexes, current_distance_to_destination);
            int minIndex = min_array[1].intValue();
            double min = min_array[0];

            routes.put(destinationArray[minIndex], min);
            exploredPathIndexes.add(destinationArray[minIndex]);
            if (j == 4) {routes.put(destination, Double.parseDouble(startArray[column_end_index]));} // after 3 landmarks
            startArray = r.get(minIndex);
            current_distance_to_destination = Double.parseDouble(startArray[column_end_index]);
        }


        System.out.println(routes);

        double total_distance = 0;

        System.out.print(source);
        for (String name: routes.keySet()) {
            String key = name.toString();
            total_distance += routes.get(name);
            System.out.print(" => " + key);
        }
        System.out.println();
        System.out.println("Total Path Distance: " + total_distance);

        average_car_speed = 50;

        float timeOfArrival = (float) total_distance / average_car_speed;
        System.out.println("Time taken to travel path: " + timeOfArrival + " hour(s)");

        PrintWriter writer = new PrintWriter("Random.txt", "UTF-8");
        //writer.println(timeofArrival);
        writer.println(total_distance);
        writer.print(source);
        for (String name: routes.keySet()) {
            String key = name.toString();
            total_distance += routes.get(name);
            writer.print(" => " + key);
        }
        writer.close();


    }


    public static Double[] generatePath(List<String[]> r, String[] startArray, String[] destinationArray, Double total_distance, List<String> explored_paths, Double previous_distance){
        // iterating through routes
        double min = 2.00;
        int minIndex = 0;
        Double[] minArray = new Double[2];

        for(int i = 1; i< startArray.length; i++){
            double i_ = Double.parseDouble(startArray[i]);
            if (i_ < min && i_ != 0) {
                // if the min, i < distance_of_previous_landmark from destination
                if (Double.parseDouble(r.get(i)[5]) < previous_distance){
                    min = Double.parseDouble(startArray[i]) + 0.1; //We can add some values to this to get different routes.// I added 0.1 so it will ignore routes which are very close to it. 
                    minIndex = i;
                }

                // ignoring already explored landmarks.
                for (String location: explored_paths){
                    if (destinationArray[minIndex].contains(location)) {
                        min = 22.00;
                        break;
                    }
                }

            }
        }
        minArray[0] = min;
        minArray[1] = (double) minIndex;
        return minArray;
    }
}