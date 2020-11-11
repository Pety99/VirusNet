import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Parser p = new Parser();
        ArrayList<Node> nodeList = p.scan();
        for(Node n: nodeList){
            n.printNode();
        }
    }
}
