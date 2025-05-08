import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Huffman {

    private File file;
    private int n;  //liczba liter
    private List<String[]> occurrings; //liczba wstąpień liter
    private List<String> pathOfLetters;
    private Map<Character, String> coding;

    public Huffman(String path){
        this.file = new File(path);
        this.occurrings = new ArrayList<>();
        this.pathOfLetters = new ArrayList<>();
        this.coding = new HashMap<>();

        readFile();

        createPathOfLetters();

        codeLetters();

        System.out.println(coding);

        /*for (String s : pathOfLetters){
            System.out.println(s);
        }*/
    }

    private void codeLetters(){
        for(int i = pathOfLetters.size() - 1; i >= 0; i = i - 3){
            char[] arr = pathOfLetters.get(i - 1).toCharArray();

            for (char c : arr){
                coding.put(c, coding.get(c) + "1");
            }

            arr = pathOfLetters.get(i - 2).toCharArray();

            for (char c : arr){
                coding.put(c, coding.get(c) + "0");
            }
        }
    }

    private void createPathOfLetters(){

        while (occurrings.size() > 1) {

            String[] res = addToPathOfLetters();

            pathOfLetters.add(res[0]);
            occurrings.add(res);

            ///System.out.println("adding: " + res[0] + res[1]);
        }

    }

    private String[] addToPathOfLetters(){

        int sumOfTwoLowest = 0;
        String twoLowest = "";

        for (int j = 0; j < 2; j++) {

            int lowestCount = Integer.parseInt(occurrings.get(0)[1]);
            String[] lowestCountLetter = occurrings.get(0);

            //System.out.println("fisrt smallest letter: " + occurrings.get(0)[0] + occurrings.get(0)[1]);

            for (int i = 0; i < occurrings.size(); i++) {

                if (Integer.parseInt(occurrings.get(i)[1]) < lowestCount) {
                    //System.out.println("smaller letter found: " + occurrings.get(i)[0] + occurrings.get(i)[1]);
                    lowestCount = Integer.parseInt(occurrings.get(i)[1]);
                    lowestCountLetter = occurrings.get(i);
                }
            }

            occurrings.remove(lowestCountLetter);
            pathOfLetters.add(lowestCountLetter[0]);

            sumOfTwoLowest += lowestCount;
            twoLowest += lowestCountLetter[0];
        }

        //System.out.println("finding lowest: " + twoLowest);

        return new String[]{twoLowest, String.valueOf(sumOfTwoLowest)};

    }

    private void readFile(){

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            this.n = Integer.parseInt(br.readLine());

            String line;
            String[] arr;
            while((line = br.readLine()) != null){
                arr = line.split(" ");

                occurrings.add(arr);
                coding.put(arr[0].charAt(0), "");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
