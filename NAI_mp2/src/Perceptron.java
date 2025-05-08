public class Perceptron {

    private static double wagaPrim = 1;
    private double stalaUczenia;
    private double[] wagi, wejscia;
    private int wyjscie;

    public static double getWagaPrim() {
        return wagaPrim;
    }

    public Perceptron(double stalaUczenia, double[] wagi, double[] wejscia, int oczekiwaneWyjscie) throws Exception {
        this.stalaUczenia = stalaUczenia;
        this.wagi = wagi;
        this.wejscia = wejscia;
        this.wyjscie = oczekiwaneWyjscie;

        if (wejscia.length != wagi.length){
            System.out.println("Złe dane");
            throw new Exception(){};
        }
    }

    public Perceptron(double[] wagi, double[] wejscia) throws Exception {
        this.wagi = wagi;
        this.wejscia = wejscia;

        if (wejscia.length != wagi.length){
            System.out.println("Złe dane");
            throw new Exception(){};
        }
    }

    public void policzWyjscie(){
        double sum = 0;
        int wynik;

        for (int i = 0; i < wagi.length; i++){
            sum += wagi[i] * wejscia[i];
        }

        if (sum >= wagaPrim){
            wynik = 1;
        } else {
            wynik = 0;
        }

        if (wynik != wyjscie){
            pszelicz(wynik);
        }
    }

    private void pszelicz(double otrzymaneWyjscie){
        double sum = 0;

        for (int i = 0; i < wagi.length; i++){
            sum += wejscia[i] * (wagi[i] + (stalaUczenia * wejscia[i] * (wyjscie - otrzymaneWyjscie)));
        }

        wagaPrim = wagaPrim - stalaUczenia; //TODO to może być źle lol
    }
}
