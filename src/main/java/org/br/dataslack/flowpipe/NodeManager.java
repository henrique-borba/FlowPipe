package org.br.dataslack.flowpipe;

import java.util.ArrayList;

public class NodeManager {

    private ArrayList<Node> nodes = new ArrayList<Node>();

    /**
     * Get current node first
     *
     * @param current_node
     */
    NodeManager(Node current_node) {
        this.nodes.add(current_node);
    }


}
