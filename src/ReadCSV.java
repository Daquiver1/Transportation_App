package src;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;


public class ReadCSV {

    public static void main(String[] args) throws IOException, CsvException {

        String filename = "Scrapper/Addresses.csv";                 // represents our adjacency matrix
        String start_location = "";
        String destination = "";

        List<String[]> adjacencyMatrix;                             // data from csv (adjacency matrix)

        // Reading start location and destination
        try(Scanner scanner = new Scanner(new File("data/LocationQuery.txt"))) {
            start_location = scanner.nextLine();
            destination = scanner.nextLine();
        } catch (IOException e) {}

        // reading csv file into adjacencyMatrix.
        try (CSVReader reader = new CSVReader(new FileReader(filename))){
            adjacencyMatrix = reader.readAll();
        }


        int row_start_index = 0;            // index of the row on which the start location is.
        int column_end_index = 0;           // index of the column on which the stop location exist.

        for(String[] array: adjacencyMatrix){
//            System.out.println(Arrays.toString(array));

            // get index of destination from first row
            if (adjacencyMatrix.indexOf(array) == 0){
                for (int i = 0; i < array.length; i++){
                    if (array[i].contains(destination)){
                        column_end_index = i;
                    }
                }
            }


            // get index of source from first column
            if (array[0].contains(start_location)) {
                int index = adjacencyMatrix.indexOf(array);
                row_start_index = index;
            }

        }


        // Finding total distance
        double actual_distance = Double.parseDouble(adjacencyMatrix.get(row_start_index)[column_end_index]);

        System.out.println("Start Location: " + start_location);
        System.out.println("Destination: " + destination);
        System.out.println("Actual Distance between start and stop locations: " + actual_distance);
        System.out.println();


        generateRoute(adjacencyMatrix, start_location, destination, row_start_index, column_end_index, actual_distance);

    }

    // generates a single best next location to the current location
    public static Double[] generateLandmark(List<String[]> r, String[] startArray, String[] destinationArray, Double total_distance, List<String> explored_paths, Double previous_distance){
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

    // generates an entire route
    public static void generateRoute(List<String[]> adjacencyMatrix, String start_location, String destination, int row_start_index, int column_end_index, double actual_distance) throws FileNotFoundException, UnsupportedEncodingException {
        double total_distance = 0;
        double timeTaken = 0;
        Map<String, Double> routes = new LinkedHashMap<String, Double>();        // routes
        List<String> exploredPathIndexes = new ArrayList<>();       // explored landmarks



        String[] destinationArray = adjacencyMatrix.get(0);    // the first row in the csv
        String[] startArray = adjacencyMatrix.get(row_start_index);

        // update explored paths
        exploredPathIndexes.add(start_location);

        double current_distance_to_destination = actual_distance;
        Double[] min_array = {};

        for (int j = 0; j< 5; j++){
            min_array = generateLandmark(adjacencyMatrix, startArray, destinationArray,  actual_distance, exploredPathIndexes, current_distance_to_destination);
            int minIndex = min_array[1].intValue();
            double min = min_array[0];


            routes.put(destinationArray[minIndex], min);                        // update routes
            exploredPathIndexes.add(destinationArray[minIndex]);                // update explored_paths
            if (j == 4) {routes.put(destination, Double.parseDouble(startArray[column_end_index]));} // after 3 landmarks
            startArray = adjacencyMatrix.get(minIndex);
            current_distance_to_destination = Double.parseDouble(startArray[column_end_index]);
        }

        // print route
        System.out.print(start_location);
        for (String name: routes.keySet()) {
            String key = name.toString();
            System.out.print(" => " + key);
        }

        // calculating total distance and time taken
        double[] routeDetails = distance_time(routes);
        total_distance = routeDetails[0];
        timeTaken = routeDetails[1];

        // write generated route and details in random.txt
        PrintWriter writer = new PrintWriter("data/GeneratedRoutes.txt", "UTF-8");
        writeGUI(routes, total_distance, timeTaken, start_location, writer);
    }

    // distance and time of a given route
    public static double[] distance_time(Map<String, Double> routes){
        double distance = 0.0;
        double average_car_speed = 50.0;

        // distance
        for (String name: routes.keySet()) {
            distance += routes.get(name);
        }

        // time
        double timeTaken =  distance / average_car_speed;

        // print out the details
        System.out.println();
        System.out.println();
        System.out.println("Total Path Distance: " + distance);
        System.out.println("Time taken to travel path: " + timeTaken + " hour(s)");     // Time taken to travel a route.

        double[] route_details = {distance, timeTaken};

        // return route details
        return route_details;
    }

    // writes routes data for GUI
    public static void writeGUI (Map<String, Double> routes, double total_distance, double time, String start_location, PrintWriter writer){
        //writer.println(timeofArrival);
        writer.println(total_distance);
        writer.print(start_location);
        for (String name: routes.keySet()) {
            String key = name.toString();
            total_distance += routes.get(name);
            writer.print(" => " + key);
        }
        writer.close();
    }
}