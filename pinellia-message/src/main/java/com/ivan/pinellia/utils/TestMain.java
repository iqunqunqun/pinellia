package com.ivan.pinellia.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author ivan
 * @className TestMain
 * @since 2020/11/25 21:41
 */
public class TestMain {

    private static List<Node> childList = new ArrayList<>();

    static List<Node> allList = new ArrayList<>();

    static Node newNode = new Node();


    public static void main(String[] args) {
        Node gd = new Node("gd");
        Node sz = new Node("sz");
        Node gz = new Node("gz");
        Node ba = new Node("ba");
        Node ns = new Node("ns");
        Node xx = new Node("xx");

        List<Node> gdChildren = gd.getChildren();
        gdChildren.add(sz);
        gdChildren.add(gz);

        List<Node> szChildren = sz.getChildren();
        szChildren.add(ba);
        szChildren.add(ns);

        List<Node> baChildren = ba.getChildren();
        baChildren.add(xx);

        countSize(gd, "sz");

        System.out.println(childList.toString());

        System.out.println(childList.size());
    }

    private static void countSize(Node node, String name) {

        Node target = recurse(node, name);

        if (newNode != null) {
            count(childList, newNode);
        }
    }

    private static void count(List<Node> allList, Node target) {

        List<Node> targetChildren = target.getChildren();

        for (Node targetChild : targetChildren) {
            List<Node> children = targetChild.getChildren();
            if (children.size() > 0) {
                count(allList, targetChild);
            }

            allList.add(targetChild);

        }

    }

    private static Node recurse(Node node, String name) {

        List<Node> nodeChildren = node.getChildren();

        for (Node nodeChild : nodeChildren) {

            if (nodeChild.getName().equalsIgnoreCase(name)) {

                newNode = nodeChild;

                return nodeChild;
            }

            if (nodeChild.getChildren().size() > 0) {
                recurse(nodeChild, name);
            }

            allList.add(nodeChild);
        }

        return null;
    }
}
