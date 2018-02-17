package org.br.dataslack.flowpipe;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * NodeManager
 */
public class NodeManager {

    final private Logger LOGGER = LogManager.getLogger(NodeManager.class);
    private ArrayList<Node> nodes = new ArrayList<>();

    /**
     * Get current node first
     *
     * @param current_node
     */
    NodeManager(Node current_node) {
        LOGGER.debug("Registering current node...");
        this.nodes.add(current_node);
        this.nodes.add(current_node);
        this.nodes.add(current_node);
    }

    /**
     *
     * @return
     */
    public ArrayList<Node> getNodes() {
        return this.nodes;
    }

    /**
     *
     * @param node
     */
    public void insertNode(Node node) {
        LOGGER.debug("Inserting new Node to NodeManager");
        this.nodes.add(node);
    }

    /**
     *
     * @param node
     */
    public void removeNode(Node node) {
        LOGGER.debug("Removing Node <"+node.getName()+"> from NodeManager");
        this.nodes.remove(node);
    }


}
