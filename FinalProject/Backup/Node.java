public class Node {
    int x;
    int y;
    int f;
    int g;
    int h;
    private Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        f = 0;
        g = 0;
        h = 0;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node node) {
        parent = node;
    }
}
