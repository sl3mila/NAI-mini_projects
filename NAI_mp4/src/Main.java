public class Main {
    public static void main(String[] args) {

        KMeans kMeans = new KMeans(3, "iris.txt", 4);

        for (int i = 0; i < 10; i++){

            kMeans.group();

            kMeans.countCentroid();

        }

        System.out.println("group 1");
        kMeans.getGroup(1);

        System.out.println("group 2");
        kMeans.getGroup(2);

        System.out.println("group 3");
        kMeans.getGroup(3);

        kMeans.getCentorids();
    }
}