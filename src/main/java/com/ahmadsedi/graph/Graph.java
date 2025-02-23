package com.ahmadsedi.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ahmad R. Seddighi (ahmadseddighi@yahoo.com)
 * Date: 17/02/2025
 * Time: 10:52
 */

public class Graph {

    private Map<String, Node> nodes = new HashMap<>();

    public Graph addNode(Node node) {
        if(!nodes.containsKey(node.getName())) {
//            nodes.put(node.getName(), node);
            Node.createNode(node.getName()).ifPresent(value -> nodes.put(node.getName(), value));
        }
        return this;
    }

    public Graph addEdge(Node node1, Node node2) {
        if (!areConnected(node1, node2)) {
            node1 = nodes.get(node1.getName());
            node2 = nodes.get(node2.getName());
            addNeighbour(node1, node2);
            addNeighbour(node2, node1);
        }
        return this;
    }

    public boolean areNeighbours(Node node1, Node node2) {
        node1 = nodes.get(node1.getName());
        node2 = nodes.get(node2.getName());
        return node1.getNeighbours().contains(node2);
    }


    public boolean areConnected(Node node1, Node node2) {
        node1 = nodes.get(node1.getName());
        node2 = nodes.get(node2.getName());
        return areConnected(node1, node2, new HashSet<>());
    }

    private boolean areConnected(Node node1, Node node2, Set<Node> scanned) {
        scanned.add(node1);
        if (node1.getNeighbours().contains(node2)) {
            return true;
        } else {
            return node1.getNeighbours().stream().filter(n -> !scanned.contains(n)).anyMatch(n -> areConnected(n, node2, scanned));
        }
    }

    public List<Node> traverse(Node node1, Node node2){
        node1 = nodes.get(node1.getName());
        node2 = nodes.get(node2.getName());
        List<Node> path = new ArrayList<>();
        path.add(node1);
        return traverse(node1, node2, new HashSet<>(), path);
    }

    private List<Node> traverse(Node node1, Node node2, Set<Node> scanned, List<Node> path){
        scanned.add(node1);
        if (node1.getNeighbours().contains(node2)) {
            path.add(node2);
        } else {
            return node1.getNeighbours().stream().filter(n -> !scanned.contains(n)).map(n->{
                List<Node> subList = new ArrayList<>();
                subList.addAll(path);
                subList.add(n);
                scanned.add(n);
                return traverse(n, node2, scanned, subList);
            }).findAny().orElseGet(ArrayList::new);
        }
        return path;
    }

    public Set<Node> getNeighbours(Node node) {
        return node.getNeighbours().stream().map(n->Node.createNode(n.getName()).get()).collect(Collectors.toUnmodifiableSet());
    }

    /**
     * a->b->c
     * a->b->c->a
     *
     *
     *
     */
    public boolean addNeighbour(Node node1, Node node2){
        if(node2!=null&&!node2.equals(node1)) {
            return node1.getNeighbours().add(nodes.get(node2.getName()));
        }
        return false;
    }

    public Set<Node> getNodes() {
        return nodes.values().stream().map(n -> Node.createNode(n.getName()).get()).collect(Collectors.toUnmodifiableSet());
    }
}
