import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        File file = new File("test.txt");
        String str = "";

        int i = 1;

        int count = 0;
        int testIrisAmount = 45;
        int k;

        Scanner scan = new Scanner(System.in);

        System.out.println("Choose k: ");
        k = scan.nextInt();

        boolean changeSets;
        String trainSet = "", testSet = "";
        System.out.println("Would you like to change Train-set and test-set? [true or false]");
        changeSets = scan.nextBoolean();

        if (changeSets){
            System.out.println("Name file for Train-set: ");
            trainSet = scan.next();
            System.out.println("Name file for Test-set: ");
            testSet = scan.next();
            file = new File(testSet);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((str = br.readLine()) != null) {
                Iris iris = new Iris(str, k);
                if (!trainSet.equals("")) {
                    iris.setFile(new File(trainSet));
                }

                String typeName = iris.type();

                System.out.print(i + ". ");
                System.out.println("by program: " + typeName + ", accual: " + iris.getAccualName());
                i++;

                if (typeName.equals(iris.getAccualName())){
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("\nAccuracy: " + (double)count/testIrisAmount);

        System.out.println("Would you like to enter your flower parameters? [true or false]");
        boolean newFlower = scan.nextBoolean();
        if (newFlower){
            System.out.println("Enter your paramiters in form \"x,y,z,q\": ");
            String parameters = scan.next();
            String irisInput = parameters + ",none";
            System.out.println("You can also change k: ");
            k = scan.nextInt();

            Iris personalisedIris = new Iris(irisInput, k);

            System.out.println("This is the type of your flower: " + personalisedIris.type());
        }
    }
}
