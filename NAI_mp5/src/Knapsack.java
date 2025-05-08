import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack {
    private String path;

    private int k;              //pojemność plecaka
    private int n;              //liczba przedmiotów
    private List<Integer> v;    //wartości przedmiotów
    private List<Integer> w;    //waga przedmiotów

    private List<int[]> bestKnapsack;
    private int bestResult;

    private int counter = 0;

    public Knapsack(String path) {

        this.path = path;
        this.v = new ArrayList<>();
        this.w = new ArrayList<>();

        readFile();

        this.bestKnapsack = new ArrayList<>();

    }

    private void generateCombinations(){

         
    }

    private void lookThrou(){

        List<int[]> knapsack = new ArrayList<>();

        int newRes = 0;
        int idx = 0;
        int[] obj = new int[2];

        while (newRes <= k){

            if (newRes + v.get(idx) <= k){

                obj[0] = v.get(idx);
                obj[1] = w.get(idx);

                newRes += v.get(idx);
                knapsack.add(obj);
            }

            idx++;
        }

        if(newRes > bestResult){

            bestResult = newRes;
            bestKnapsack = knapsack;
        }
    }

    private void readFile(){

        try {
            File file = new File(path);

            Scanner reader = new Scanner(file);

            String str = reader.nextLine();
            String[] contetnts = str.split(" ");

            k = Integer.parseInt(contetnts[0]);
            n = Integer.parseInt(contetnts[1]);

            str = reader.nextLine();
            contetnts = str.split(",");

            for (String c : contetnts){
                v.add(Integer.parseInt(c));
            }

            str = reader.nextLine();
            contetnts = str.split(",");

            for (String c : contetnts){
                w.add(Integer.parseInt(c));
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
