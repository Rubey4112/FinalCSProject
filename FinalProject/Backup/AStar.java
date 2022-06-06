import java.util.ArrayList;
import java.util.LinkedList;

public class AStar implements Size {
    ArrayList<Node> open = new ArrayList<>();
    ArrayList<Node> close = new ArrayList<>();

    Node[][] nodeMap;

    public AStar(Tile[][] map) {
        nodeMap = new Node[TILES][TILES];
        for (int r = 0; r < TILES; r++) {
            for (int c = 0; c < TILES; c++) {
                nodeMap[r][c] = (map[r][c].getSolid()) ? null : new Node(c, r);
            }
        }
    }

    public Node getLowestF() {
        int tmpF = 0;
        for (int i = 0; i < open.size(); i++) {
            if (open.get(i).f < open.get(tmpF).f)
                tmpF = i;
        }
        return open.get(tmpF);
    }

    public int heuristic(Node node, int endX, int endY) {
        return Math.abs(node.x - endX) + Math.abs(node.y - endY);
    }

    public LinkedList<Node> findPath(int startX, int startY, int endX, int endY) {
        open.clear();
        close.clear();
        Node startNode = nodeMap[startY][startX];
        startNode.h = heuristic(startNode, endX, endY);
        open.add(startNode);
        while (!open.isEmpty()) {
            Node current = getLowestF();
            open.remove(current);
            close.add(current);

            if (current.x == endX && current.y == endY) {
                LinkedList<Node> path = new LinkedList<>();
                Node tmp = current;
                path.add(tmp);
                while (tmp.getParent() != null) {
                    path.add(0, tmp.getParent());
                    tmp = tmp.getParent();
                }
                return path;

            }

            // north
            if (current.x > 0) {
                Node neighbor = nodeMap[current.y][current.x - 1];
                if (neighbor != null && !close.contains(neighbor)) {
                    int tmpGScore = current.g + 1;
                    if (open.contains(neighbor)) {
                        if (tmpGScore < neighbor.g)
                            neighbor.g = tmpGScore;
                    } else {
                        neighbor.g = tmpGScore;
                        open.add(neighbor);
                    }
                    neighbor.h = heuristic(neighbor, endX, endY);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.setParent(current);
                }
            }

            if (current.x < TILES - 1) {
                Node neighbor = nodeMap[current.y][current.x + 1];
                if (neighbor != null && !close.contains(neighbor)) {
                    int tmpGScore = current.g + 1;
                    if (open.contains(neighbor)) {
                        if (tmpGScore < neighbor.g)
                            neighbor.g = tmpGScore;
                    } else {
                        neighbor.g = tmpGScore;
                        open.add(neighbor);
                    }
                    neighbor.h = heuristic(neighbor, endX, endY);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.setParent(current);
                }
            }
            if (current.y > 0) {
                Node neighbor = nodeMap[current.y - 1][current.x];
                if (neighbor != null && !close.contains(neighbor)) {
                    int tmpGScore = current.g + 1;
                    if (open.contains(neighbor)) {
                        if (tmpGScore < neighbor.g)
                            neighbor.g = tmpGScore;
                    } else {
                        neighbor.g = tmpGScore;
                        open.add(neighbor);
                    }
                    neighbor.h = heuristic(neighbor, endX, endY);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.setParent(current);
                }
            }
            if (current.y < TILES - 1) {
                Node neighbor = nodeMap[current.y + 1][current.x];
                if (neighbor != null && !close.contains(neighbor)) {
                    int tmpGScore = current.g + 1;
                    if (open.contains(neighbor)) {
                        if (tmpGScore < neighbor.g)
                            neighbor.g = tmpGScore;
                    } else {
                        neighbor.g = tmpGScore;
                        open.add(neighbor);
                    }
                    neighbor.h = heuristic(neighbor, endX, endY);
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.setParent(current);
                }
            }
        }
        return null;
    }
}
