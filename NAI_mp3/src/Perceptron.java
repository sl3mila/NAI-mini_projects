import java.io.*;
import java.util.Comparator;
import java.util.HashMap;

public class Perceptron {
    private HashMap<Character, Double> wejscia = new HashMap<>();

    private HashMap<Character, Double> wagi = new HashMap<>();

    private double stalaUczenia = 0.1;
    private double wyjscie;
    private double wagaPrim = Math.random();
    public double getWagaPrim(){
        return wagaPrim;
    }
    private String name;
    public String getName(){
        return name;
    }
    public double res;

    public Perceptron(String name){

        this.name = name;
        setMap(wagi);


    }
    
    private void setMap(HashMap map){

        for (char chr = 'a'; chr <= 'z' ; chr++){
            map.put(chr, Math.random());
        }
    }

    public void study(File file, double wyjscie) throws IOException {

        this.wyjscie = wyjscie;

        countLetters(file);

        policzWyjscie(true);
    }

    public double getRes(File file) throws IOException {

        countLetters(file);

        policzWyjscie(false);

        return res;
    }

    private void countLetters(File file) throws IOException {
        setMap(wejscia);

        int amoutOfLetters = 0;

        BufferedReader br = new BufferedReader(new FileReader(file));

        String str;
        while ((str = br.readLine()) != null){

            str = str.toLowerCase();
            for (int i = 0; i < str.length(); i++){

                Double amount;
                char c = str.charAt(i);

                if (Character.isLetter(c) && (amount = wejscia.get(c)) != null){

                    amount++;
                    wejscia.replace(str.charAt(i), amount);
                    amoutOfLetters++;
                }
            }
        }

        for (char c = 'a'; c <= 'z'; c++){

            double val = wejscia.get(c)/amoutOfLetters;
            wejscia.replace(c, val);
        }
    }



    public void policzWyjscie(boolean isStuding){
        double sum = 0;
        int wynik;

        for (char c = 'a'; c <= 'z'; c++){
            sum += wagi.get(c) * wejscia.get(c);
        }

        if (sum >= wagaPrim){
            wynik = 1;
        } else {
            wynik = 0;
        }
        res = sum - wagaPrim;

        if (isStuding) {
            if (wynik != wyjscie) {
                recount(wynik);
            }
        }
    }

    private void recount(double otrzymaneWyjscie){

        for (char c = 'a'; c <= 'z'; c++){
            //double res = wejscia.get(c) * (wagi.get(c) + (stalaUczenia * wejscia.get(c) * (wyjscie - otrzymaneWyjscie)));
            //double res = wejscia.get(c) + (wagi.get(c) + (stalaUczenia * (wyjscie - otrzymaneWyjscie)));

            double res = wagi.get(c) + wejscia.get(c) * (stalaUczenia * (wyjscie - otrzymaneWyjscie));
            wagi.replace(c, res);
        }

        wagaPrim = wagaPrim - (stalaUczenia * (wyjscie - otrzymaneWyjscie));
    }
}
