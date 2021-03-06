package org.br.dataslack.flowpipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;

/**
 *  Node Class
 */
public class Node {

    final static Logger LOGGER = LogManager.getLogger(Node.class);
    private NodeConfig config;
    private String name;
    final private NodeType type;
    private NodeStatus status;


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
        this.status = NodeStatus.LOADING;
    }

    /**
     * Retrieve current running node object
     *
     * @return
     */
    public static Node current(Path home) {
        LOGGER.debug("Loading current Node configuration");
        // TODO: 15-Feb-18 Get correct saved configuration from flowpipe.yml
        return new Node(NodeType.MASTER,"master", NodeConfig.load_current(home));
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

    public NodeConfig getConfig() {
        return this.config;
    }

    public void update() {
        this.status = this.updateNodeStatus();
    }

    private NodeStatus updateNodeStatus() {
        return NodeStatus.GREEN;
    }



}
