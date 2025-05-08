import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Perceptron> perceptronki = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        File langsFolder = new File("langs");

        for (File folder : langsFolder.listFiles()){
            perceptronki.add(new Perceptron(folder.getName()));

        }

        for (int i = 0; i < 200; i++) {

            for (int j = 0; j < perceptronki.size(); j++){
                study(perceptronki.get(j), perceptronki);
            }
        }

        int nr = 1;

        for (File folder : langsFolder.listFiles()){

            for(File file : folder.listFiles()){
                System.out.print(nr + ": " + file + " -> ");
                compLangs(file);
                nr++;
            }
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("podaj swój tekst: ");
        String text = scan.nextLine();

        System.out.println("enter file name");
        String fileName = scan.nextLine();

        BufferedWriter w = new BufferedWriter(new FileWriter(fileName));
        w.write(text);

        w.close();

        compLangs(new File(fileName));

    }

    public static void compLangs(File file) throws IOException {

        double[] res = new double[perceptronki.size()];

        for (int i = 0; i < perceptronki.size(); i++){

            res[i] = perceptronki.get(i).getRes(file);
        }

        int idx = 0;
        for (int i = 1; i < perceptronki.size(); i++){

            if (res[i] > res[idx]){

                idx = i;
            }
        }

        System.out.println(perceptronki.get(idx).getName());
    }

    public static void study(Perceptron p , ArrayList<Perceptron> perceptronki) throws IOException {

        //przemieszać języki co każdą epokę
        int[] wyniki = new int[perceptronki.size()];
        for (int i = 0; i < perceptronki.size(); i++){
            wyniki[i] = 0;
        }

        int idxPerceptronu = perceptronki.indexOf(p);

        wyniki[idxPerceptronu] = 1;

        ArrayList<String[]> fileNames = new ArrayList<>();

        File langsFolder = new File("langs");

        for (File folder : langsFolder.listFiles()){

            for(File file : folder.listFiles()){

                fileNames.add(new String[]{String.valueOf(file), String.valueOf(folder)});
            }
        }

        Collections.shuffle(fileNames);


            for(int j = 0; j < fileNames.size(); j ++){

                int idx = -1;

                for (int i = 0; i < perceptronki.size(); i++) {

                    if (fileNames.get(j)[1].equals("langs\\" + perceptronki.get(i).getName())){

                            idx = i;
                    }
                }

                p.study(new File(fileNames.get(j)[0]), wyniki[idx]);
            }
        //}

    }
}