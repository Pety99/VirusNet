import java.util.ArrayList;

public class Node {
    int index;
    int numberOfValues;
    Integer evidence = null;
    boolean target = false;
    ArrayList<Node> parents = new ArrayList<>();
    ArrayList<Double> probabilitites = new ArrayList<>();
    ArrayList<ConditionalProbability> conditionalProbabilities = new ArrayList<>();
    //Constructor for nodes with no parents
    public Node(int index, int numberOfValues, ArrayList<Double> probabilitites) {
        this.index = index;
        this.numberOfValues = numberOfValues;
        this.probabilitites = probabilitites;
    }
    public Node(int index, int numberOfValues, ArrayList<ConditionalProbability> probabilities, ArrayList<Node> parents) {
        this.index = index;
        this.numberOfValues = numberOfValues;
        this.conditionalProbabilities = probabilities;
        this.parents = parents;
    }

    public void setEvidence(Integer evidence) {
        this.evidence = evidence;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    //Other Constructor
    //TODO

    public int getIndex() {
        return index;
    }

    public void printNode() {
        System.out.println("Index:");
        System.out.println(this.index);
        System.out.println("Nuber of Values:");
        System.out.println(numberOfValues);
        System.out.println("Probabilities:");
        for (double d : this.probabilitites) {
            System.out.println(d);
        }
        System.out.println("Parents:");
        for (Node n : parents) {
            System.out.println(n.getIndex());
        }
        System.out.println("Conditional Probabilities:");
        System.out.println(conditionalProbabilities.size());
        System.out.println();
    }

}
