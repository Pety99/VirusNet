import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private ArrayList<Node> nodeList = new ArrayList<>();

    public ArrayList<Node> scan() {
        Scanner sc = new Scanner(System.in);
        int numberOfNodes = sc.nextInt();

        for (int i = 0; i < numberOfNodes; i++) {
            scanNode(sc, i);
        }
        int numberOfEvidences = Integer.parseInt(sc.nextLine());
        setEvidences(nodeList, sc, numberOfEvidences);
        setTarget(nodeList, sc);
        return nodeList;
    }

    private void scanNode(Scanner sc, int index) {
        int numberOfValues = sc.nextInt();
        int numberOfParents = sc.nextInt();


        Node n = null;
        if (numberOfParents == 0) {
            n = scanNodesNoParents(sc, index, numberOfValues);
            if (n != null) {
                nodeList.add(n);
            }
        } else {
            n = scanNodesWithParents(sc, index, numberOfValues, numberOfParents);
            nodeList.add(n);
        }
    }

    private Node scanNodesNoParents(Scanner sc, int index, int numberOfValues) {
        ArrayList<Double> probabilities = new ArrayList<>();
        String line = sc.nextLine();
        for (String word : line.split(",")) {
            probabilities.add(Double.parseDouble(word));
        }
        Node n = new Node(index, numberOfValues, probabilities);
        return n;
    }

    private Node scanNodesWithParents(Scanner sc, int index, int numberOfValues, int numberOfParents) {
        ArrayList<Integer> parentsIndex = getParentsIndex(sc, numberOfParents);
        ArrayList<Node> parentNodes = getPaerntsList(parentsIndex);
        ArrayList<ConditionalProbability> conditionalProbabilities = getConditionalProbabilities(sc);

        Node n = new Node(index, numberOfValues, conditionalProbabilities, parentNodes);
        return n;
    }

    private ArrayList<ConditionalProbability> getConditionalProbabilities(Scanner sc) {
        ArrayList<ConditionalProbability> conditionalProbabilities = new ArrayList<>();
        for (String condProp : sc.nextLine().split("[ \t]")) {
            if (!condProp.equals("")) {
                String[] words = condProp.split(":");
                String parVals = words[0];
                String probVals = words[1];

                ArrayList<Integer> parentValues = new ArrayList<>();
                for (String parentVal : parVals.split(",")) {
                    parentValues.add(Integer.parseInt(parentVal));
                }

                ArrayList<Double> probabilityValues = new ArrayList<>();
                for (String probVal : probVals.split(",")) {
                    probabilityValues.add(Double.parseDouble(probVal));
                }

                ConditionalProbability c = new ConditionalProbability(probabilityValues, parentValues);
                conditionalProbabilities.add(c);
            }
        }
        return conditionalProbabilities;
    }

    private ArrayList<Node> getPaerntsList(ArrayList<Integer> parentsIndex) {
        ArrayList<Node> parentNodes = new ArrayList<>();
        for (int idx : parentsIndex) {
            parentNodes.add(nodeList.get(idx));
        }
        return parentNodes;
    }

    private ArrayList<Integer> getParentsIndex(Scanner sc, int numberOfParents) {
        ArrayList<Integer> parentsIndex = new ArrayList<>();
        for (int i = 0; i < numberOfParents; i++) {
            parentsIndex.add(sc.nextInt());
        }
        return parentsIndex;
    }

    private void setTarget(ArrayList<Node> nodeList, Scanner sc){
        int target = Integer.parseInt(sc.nextLine());
        Node n = nodeList.get(target);
        n.setTarget(true);
    }

    private void setEvidences(ArrayList<Node> nodeList, Scanner sc, int numberOfEvidences){
        for(int i = 0; i< numberOfEvidences; i++){
            String line = sc.nextLine();
            String[] values = line.split("[ \t]");
            Node n = nodeList.get(Integer.parseInt(values[0]));
            n.setEvidence(Integer.parseInt(values[1]));
        }
    }
}
