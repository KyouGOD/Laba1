import java.io.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US")); // без этого не видит числа типа double через точки типа 0.0
//        try(FileWriter writer = new FileWriter("data.txt")) { // генерирует новый набор случайных точек
//            for(int i = 0; i < 100; i++){
//                double a = Math.random() * 100;
//                writer.write(String.valueOf(a) + " ");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        File file = new File("data.txt");
        try {
            Scanner scanner = new Scanner(file);
            double [] X = new double[50];
            double [] Y = new double[50];
            double [] DIST = new double[50];
            double deltaX;
            double deltaY;
            double distance;
            double min = Integer.MAX_VALUE;
            int pointNUM = 0;
            int i = 0;
            while(scanner.hasNextDouble()){

                double value = scanner.nextDouble();
//                System.out.println(value);
                if(i%2 == 0)
                    X[i/2] = value;
                else
                    Y[i/2] = value;
                i++;
            }
            scanner.close();
            for(i = 0; i < X.length - 1 ;i++){
                System.out.println("X: "+ X[i] + " | Y: " + Y[i]);
                for(int j = 0; j < X.length - 1; j++){
                    deltaX = Math.abs(X[i] - X[j]);
                    deltaY = Math.abs(Y[i] - Y[j]);
                    distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
                    DIST[i] += distance;
                }
                if(DIST[i] < min){
                    min = DIST[i];
                    pointNUM = i;
                }
            }
            System.out.println("Point with min dist to other:\nX: " + X[pointNUM] +" | Y: "+ Y[pointNUM] + " | Sum dist to other points: " + DIST[pointNUM] );

        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
        }
    }
}