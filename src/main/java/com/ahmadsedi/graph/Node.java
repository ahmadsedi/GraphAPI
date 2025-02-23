package com.ahmadsedi.graph;

import java.util.*;

/**
 * @author Ahmad R. Seddighi (ahmadseddighi@yahoo.com)
 * Date: 17/02/2025
 * Time: 10:52
 */

public class Node {
    private String name;
    private Set<Node> neighbours = new HashSet<>();

    private Node(String name) {
        this.name = name;
    }

    public static Optional<Node> createNode(String name){
        return (name!=null&&name.length()>0)?Optional.of(new Node(name)):Optional.empty();
    }

    public String getName() {
        return name;
    }

    public Set<Node> getNeighbours() {
        return neighbours;
    }

    public boolean equals(Object obj){
        if(obj != null) {
            Node node = (Node) obj;
            if (this.getName().equals(node.getName())) {
                return true;
            }
        }else{
            System.out.println("Obj is null");
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
