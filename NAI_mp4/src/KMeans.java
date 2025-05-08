import java.io.*;
import java.util.ArrayList;


public class KMeans {

    private int k;
    private File file;
    private ArrayList<Double[]>[] groups;
    private double[][] centroids;
    private int n;
    private ArrayList<Double[]> vectorList = new ArrayList<>();

    public void getCentorids(){

        for (int i = 0; i < k; i++){
            System.out.print("grup " + (i+1) + " -> [");

            for(int j = 0; j < n - 1; j++){

                System.out.print(String.format("%.1f", centroids[i][j]) + ", ");
            }

            System.out.println(String.format("%.1f",centroids[i][n - 1]) + " ]");
        }
    }

    public void getGroup(int groupNum){

        groupNum -= 1;

        for (int i = 0; i < groups[groupNum].size(); i++){

            System.out.print("[ ");

            for(int j = 0; j < n - 1; j++){

                System.out.print(String.format("%.1f", groups[groupNum].get(i)[j]) + ", ");
            }
            System.out.println(String.format("%.1f", groups[groupNum].get(i)[n - 1]) + " ]");
        }
    }

    public KMeans(int amountOfGrups, String fileName, int howBigVector){

        this.k = amountOfGrups;
        this.file = new File(fileName);
        this.n = howBigVector;

        this.centroids = new double[k][n];

        this.groups = new ArrayList[k];

        for(int i = 0; i < k; i++){

            groups[i] = new ArrayList<>();
        }

        makeListOfVectors();
        start();
    }

    private void makeListOfVectors(){

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            while ((str = br.readLine()) != null) {

                Double[] vector = new Double[n];

                for (int i = 0; i < n; i++) {
                    vector[i] = Double.parseDouble(str.split(",")[i]);
                }

                vectorList.add(vector);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void resetGroups(){

        this.groups = new ArrayList[k];

        for(int i = 0; i < k; i++){

            groups[i] = new ArrayList<>();
        }
    }

    private void start(){

            for (int i = 0; i < k; i++){

                //set first centroids
                for (int j = 0; j < n; j++) {
                    centroids[i][j] = vectorList.get(i)[j];
                }
            }

            getCentorids();

            group();
    }

    public void group(){

        resetGroups();

        for (int idx = 0; idx < vectorList.size(); idx++) {

            int resIdx = -1;
            double lastRes = -1;


            for (int i = 0; i < k; i++) {
                double res = 0;

                for (int j = 0; j < n; j++) {
                    res += Math.pow(vectorList.get(idx)[j] - centroids[i][j], 2);
                }

                if (lastRes == -1) {
                    lastRes = res;
                    resIdx = i;
                } else if (lastRes > res) {

                    lastRes = res;
                    resIdx = i;
                }
            }

            if (resIdx < 0) {
                System.out.println("error on resIdx");
            } else {

                groups[resIdx].add(vectorList.get(idx));
            }
        }

    }

    public void countCentroid(){

        double[] newCentroid;

        for (int group = 0; group < k; group++){
            newCentroid = new double[n];

            for (int vector = 0; vector < groups[group].size(); vector++){

                for (int num = 0; num < n; num++){

                    newCentroid[num] = newCentroid[num] + groups[group].get(vector)[num];
                }
            }

            for (int num = 0; num < n; num++){

                double size = groups[group].size();

                if (groups[group].size() > 0) {
                    newCentroid[num] = newCentroid[num] / size;
                } else {
                    newCentroid[num] = newCentroid[num] / 1;
                }
            }

            centroids[group] = newCentroid;

        }
    }
}
