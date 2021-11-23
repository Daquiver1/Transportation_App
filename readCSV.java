package csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class readCSV {

    public static void main(String[] args) throws IOException, CsvException {
        String source = "School of Pharmacy";
        String destination = "Faculty of arts";

        String filename = "C:\\Users\\Anita Agyepong\\Documents\\Daquiver's Quivers\\Java\\JavatarDemo\\dummy_data.csv";

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
        double total_distance = 0;

        // Finding total distance
        for (String[] array: r){
            if (r.indexOf(array) == row_start_index){
                for (int i = 0; i < array.length; i++){
                    if (i == column_end_index) {
                        total_distance = Float.parseFloat(array[i]);
                    }
                }
            }
            else {continue;}
        }

        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Total Distance: " + total_distance);

        String[] destinationArray = r.get(0);    // the first row in the csv
        String[] startArray = r.get(row_start_index);

        // routes
        Map<String, Double> routes = new LinkedHashMap<String, Double>();
        List<String> exploredPathIndexes = new ArrayList<>();
        exploredPathIndexes.add(source);

//        for (int j = 0; j< 3; j++){
//            Double[] min_array = generatePath(startArray, total_distance, exploredPathIndexes);
//            int minIndex = min_array[1].intValue();
//            Double min = min_array[0];
//
////            if (destinationArray[minIndex] != "School of Pharmacy"){
//            routes.put(destinationArray[minIndex], min);
//            exploredPathIndexes.add(minIndex);
//            startArray = r.get(minIndex);
////            }
////            else {
////                break;
////            }
//        }
        Double[] min_array = generatePath(startArray, destinationArray,  total_distance, exploredPathIndexes);
        if (min_array != null) {
            int minIndex = min_array[1].intValue();
            Double min = min_array[0];

            routes.put(destinationArray[minIndex], min);
            exploredPathIndexes.add(destinationArray[minIndex]);
            startArray = r.get(minIndex);
        }

        min_array = generatePath(startArray, destinationArray,  total_distance, exploredPathIndexes);
        if (min_array != null) {
            int minIndex = min_array[1].intValue();
            Double min = min_array[0];

            routes.put(destinationArray[minIndex], min);
            exploredPathIndexes.add(destinationArray[minIndex]);
            startArray = r.get(minIndex);
        }

        min_array = generatePath(startArray, destinationArray,  total_distance, exploredPathIndexes);
        if (min_array != null) {
            int minIndex = min_array[1].intValue();
            Double min = min_array[0];

            routes.put(destinationArray[minIndex], min);
            exploredPathIndexes.add(destinationArray[minIndex]);
            startArray = r.get(minIndex);
        }


//        for (String name: routes.keySet()) {
//            String key = name.toString();
//            String value = routes.get(name).toString();
//            System.out.println(key + " : " + value);
//        }

        System.out.println(routes);

    }


    public static Double[] generatePath(String[] startArray, String[] destinationArray, Double total_distance, List<String> explored_paths){
        // iterating through routes
        Double min = 2.00;
        int minIndex = 0;
        Double[] minArray = new Double[2];

        for(int i = 1; i< startArray.length; i++){
            if (Double.parseDouble(startArray[i]) < min && Double.parseDouble(startArray[i]) != 0) {
                min = Double.parseDouble(startArray[i]);
                minIndex = i;

                for (String location: explored_paths){
                    if (destinationArray[minIndex].contains(location)){
                        min = 22.00;
                    }
                }

            }
        }
        minArray[0] = min;
        minArray[1] = Double.valueOf(minIndex);
        return minArray;
    }
}
