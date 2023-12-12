package com.csm.ds.class06;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    public HashMap<Integer, GraphNode> nodes;//点集
    public HashSet<Edge> edges;//边集

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
