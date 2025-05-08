import java.io.*;

public class Iris {
    private double x, y, z, q;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getQ() {
        return q;
    }

    private String name = "";

    public String getName() {
        return name;
    }

    public String accualName;

    public String getAccualName() {
        return accualName;
    }

    private int k = 10;

    public void setK(int k) {
        this.k = k;
    }

    public Iris(String string) {
        String[] arr = new String[5];
        arr = string.split(",");
        this.x = Double.parseDouble(arr[0]);
        this.y = Double.parseDouble(arr[1]);
        this.z = Double.parseDouble(arr[2]);
        this.q = Double.parseDouble(arr[3]);
        this.name = arr[4];
    }

    public Iris(String string, int k) {
        String[] arr = new String[5];
        arr = string.split(",");
        this.x = Double.parseDouble(arr[0]);
        this.y = Double.parseDouble(arr[1]);
        this.z = Double.parseDouble(arr[2]);
        this.q = Double.parseDouble(arr[3]);
        this.accualName = arr[4];
        this.k = k;
    }

    public Iris(double dimensionX, double dimensionY, double dimensionZ, double dimensionQ) {
        this.x = dimensionX;
        this.y = dimensionY;
        this.z = dimensionZ;
        this.q = dimensionQ;
    }

    public String whatType() {

        return name;
    }

    public File file = new File("iris.txt");

    public void setFile(File file) {
        this.file = file;
    }

    public BufferedReader br;

    public String type() {
        SimilarIris[] arr = new SimilarIris[k];

        String str = "";
        Iris iris;
        double diff;
        {
            try {
                br = new BufferedReader(new FileReader((file)));

                while ((str = br.readLine()) != null) {
                    iris = new Iris(str);
                    diff = compare(iris);

                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == null) {
                            arr[i] = new SimilarIris(diff, iris);
                            //break;
                        }
                    }

                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].getDiference() > diff) {
                            arr[i] = new SimilarIris(diff, iris);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /*for(SimilarIris s : arr){
            System.out.println(s.getIris());
        }*/

        String type = findTypeInClosests(arr);

        return type;
    }

    public String findTypeInClosests(SimilarIris arr[]) {
        int setosa = 0;
        int versicolor = 0;
        int virginica = 0;

        SimilarIris setosaArr[] = new SimilarIris[k];
        SimilarIris versicolorArr[] = new SimilarIris[k];
        SimilarIris virginicaArr[] = new SimilarIris[k];

        SimilarIris closest;

        String type = "";
        String irisType = "";


        for (SimilarIris x : arr) {
            type = x.getIris().getName();
            if (type.equals("Iris-setosa")) {
                setosaArr[setosa] = x;
                setosa++;
            } else if (type.equals("Iris-versicolor")) {
                versicolorArr[versicolor] = x;
                versicolor++;
            } else if (type.equals("Iris-virginica")) {
                virginicaArr[virginica] = x;
                virginica++;
            }
        }

        if (setosa > versicolor && setosa > virginica) {
            irisType = "Iris-setosa";
        } else if (versicolor > setosa && versicolor > virginica) {
            irisType = "Iris-versicolor";
        } else if (virginica > setosa && virginica > versicolor) {
            irisType = "Iris-virginica";
        } else if (setosa == versicolor && setosa != 0) {
            irisType = ifSame(setosa, versicolor, setosaArr, versicolorArr);

        } else if (versicolor == virginica && versicolor != 0) {
            irisType = ifSame(versicolor, virginica, versicolorArr, virginicaArr);

        } else if (setosa == virginica && virginica != 0) {
            irisType = ifSame(setosa, virginica, setosaArr, virginicaArr);

        }

        return irisType;
    }

    public double calc(double x, double y) {
        return java.lang.Math.pow(x - y, 2);
    }

    public double compare(Iris iris) {
        return calc(iris.getX(), x) + calc(iris.getY(), y) + calc(iris.getZ(), z) + calc(iris.getQ(), q);
    }

    public String ifSame(int t1, int t2, SimilarIris[] a1, SimilarIris[] a2) {
        SimilarIris closest;
        closest = a1[0];

        for (int i = 1; i < t1; i++) {
            if (a1[i].getDiference() < closest.getDiference()) {
                closest = a1[i];
            }
        }
        for (int i = 0; i < t2; i++) {
            if (a2[i].getDiference() < closest.getDiference()) {
                closest = a2[i];
            }
        }
        return closest.getIris().getName();
    }

    @Override
    public String toString() {
        return this.x + "," + this.y + "," + this.z + "," + this.q + "," + this.name;
    }
}
