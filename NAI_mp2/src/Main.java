import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Podaj stałą uczenia: ");
        int a = scan.nextInt();

        System.out.println("Podaj nazwę pliku treningowego: ");
        String trainPath = scan.next();

        File trainSet = new File(trainPath);

        System.out.println("Podaj nazwę pliku testowego: ");
        String testPath = scan.next();

        File testSet = new File(testPath);

        boolean go = true;

        Perceptron perceptron;
        int wielkoscWektora = 4;



        try {

            BufferedReader brTrain = new BufferedReader(new FileReader(trainSet));

            double[] train = new double[wielkoscWektora];

            String trainName = "";

            String str;

            while ((str = brTrain.readLine()) != null) {

                String[] arr = new String[5];
                arr = str.split(",");

                train = toArray(arr, wielkoscWektora);

                System.out.println(arr[wielkoscWektora + 1]);

                perceptron = new Perceptron(0.5, )

            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static double[] toArray(String[] arr, int wielkoscWektora){
        double[] res = new double[wielkoscWektora];

        int i = 0;
        for ( ; i < wielkoscWektora; i++) {
            res[i] = Double.parseDouble(arr[i]);
            res[i] = Double.parseDouble(arr[i]);
            res[i] = Double.parseDouble(arr[i]);
            res[i] = Double.parseDouble(arr[i]);
        }

        return res;
    }
}