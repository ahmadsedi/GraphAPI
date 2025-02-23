package com.ahmadsedi.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

/**
 * @author Ahmad R. Seddighi (ahmadseddighi@yahoo.com)
 * Date: 17/02/2025
 * Time: 10:56
 */

public class GraphTest {

    @Test
    void addNode_givenNode_addedExpected(){
        Graph graph = new Graph();
        Optional<Node> node  = Node.createNode("TestNode");
        graph = graph.addNode(node.get());
        Assertions.assertTrue(graph.getNodes().contains(node.get()));
    }

    @Test
    void addNode_givenExistedNode_notAddedExpected(){
        Graph graph = new Graph();
        Node node  = Node.createNode("TestNode").get();
        graph.addNode(node);

        //expect an exception
        Node node2  = Node.createNode("TestNode").get();
        graph.addNode(node2);
        Assertions.assertEquals(1, graph.getNodes().size());
    }

    @Test
    void addEdge(){
        Graph graph = new Graph();
        Node testNode1 = Node.createNode("TestNode1").get();
        Node testNode2 = Node.createNode("TestNode2").get();
        graph.addNode(testNode1);
        graph.addNode(testNode2);
        graph.addEdge(testNode1, testNode2);
        Assertions.assertTrue(graph.areNeighbours(testNode1, testNode2));
    }

    @Test
    void areConnected(){
        Graph graph = new Graph();
        Node node1  = Node.createNode("TestNode1").get();
        Node node2  = Node.createNode("TestNode2").get();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(node1, node2);
        Assertions.assertTrue(graph.areConnected(node1, node2));
    }

    @Test
    void getNeighbours(){
        Graph graph = new Graph();
        Node node1  = Node.createNode("TestNode1").get();
        Node node2  = Node.createNode("TestNode2").get();
        Node node3  = Node.createNode("TestNode3").get();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node3);
        graph.addEdge(node1, node3);
        Assertions.assertFalse(node1.getNeighbours().contains(node3));
    }

    @Test
    void traverse_givenReachablePath_returnAsExpected(){
        Graph graph = new Graph();
        Node node1  = Node.createNode("TestNode1").get();
        Node node2  = Node.createNode("TestNode2").get();
        Node node3  = Node.createNode("TestNode3").get();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node3);
        List<Node> path = graph.traverse(node1, node3);
        Assertions.assertEquals(3, path.size());
    }

    @Test
    void traverse_givenNonReachablePath_returnEmptyList(){
        Graph graph = new Graph();
        Node node1  = Node.createNode("TestNode1").get();
        Node node2  = Node.createNode("TestNode2").get();
        Node node3  = Node.createNode("TestNode3").get();
        Node node4  = Node.createNode("TestNode4").get();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addEdge(node1, node2);
        graph.addEdge(node3, node4);
        List<Node> path = graph.traverse(node1, node4);
        Assertions.assertEquals(0, path.size());
    }



}
