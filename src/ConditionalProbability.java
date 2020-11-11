import java.util.ArrayList;

public class ConditionalProbability {
    private ArrayList<Double> probabilityValues;
    private ArrayList<Integer> parentValues;

    public ConditionalProbability(ArrayList<Double> prob, ArrayList<Integer> par){
        this.probabilityValues = prob;
        this.parentValues = par;
    }
}
