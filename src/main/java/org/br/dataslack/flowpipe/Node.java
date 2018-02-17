package org.br.dataslack.flowpipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *  Node Class
 */
public class Node {

    final static Logger LOGGER = LogManager.getLogger(Node.class);
    private NodeConfig config;
    private String name;
    final private NodeType type;

    /**
     * Construct a new node object
     *
     * @param type
     * @param name
     */
    Node(NodeType type, String name, NodeConfig config) {
        LOGGER.debug("Registering Node " + name);
        this.name = name;
        this.config = config;
        this.type = type;
    }

    /**
     * Retrieve current running node object
     *
     * @return
     */
    public static Node current() {
        LOGGER.debug("Loading current Node configuration");
        // TODO: 15-Feb-18 Get correct saved configuration from flowpipe.yml
        return new Node(NodeType.MASTER,"master", NodeConfig.load_current());
    }

    /**
     * Get node type as NodeType Object
     *
     * @return NodeType this node type
     */
    public NodeType getType() {
        return this.type;
    }

    /**
     * Get Node Name
     *
     * @return String Node Name
     */
    public String getName() {
        return this.name;
    }


}
