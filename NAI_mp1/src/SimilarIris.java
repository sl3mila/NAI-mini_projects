public class SimilarIris {
    private double diference;
    public double getDiference(){
        return this.diference;
    }
    private Iris iris;
    public Iris getIris(){
        return this.iris;
    }

    public SimilarIris(double diference, Iris iris){
        this.diference = diference;
        this.iris = iris;
    }
}
