package org.br.dataslack.flowpipe.orchestrator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.br.dataslack.flowpipe.FlowPipe;
import org.br.dataslack.flowpipe.Orchestrator;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Orchestrator Server
 */
public class Server extends Thread  {

    final private static Logger LOGGER = LogManager.getLogger(Server.class);
    final private FlowPipe current_flowpipe;
    final private Orchestrator orchestrator;
    private ServerSocket server = null;
    final private String host;
    final private int port;

    /**
     * 
     * @param current_flowpipe Current FlowPipe
     * @param orchestrator Current Orchestrator
     */
    public Server(FlowPipe current_flowpipe, Orchestrator orchestrator) {
        this.current_flowpipe = current_flowpipe;
        this.orchestrator = orchestrator;
        this.host = this.current_flowpipe.getNode().getConfig().map().get("orchestrator.host").toString();
        this.port = (int) this.current_flowpipe.getNode().getConfig().map().get("orchestrator.port");
        this.server = this.createServer(this.host, this.port);
    }

    /**
     * Register a new node
     */
    public void register() {

    }

    /**
     * Unregister a node
     */
    public void unregister() {

    }

    /**
     * Join an existing node
     */
    public void node_join() {

    }

    /**
     * Create ServerSocket for Orchestrator COM
     *
     * @param host
     * @param port
     */
    public ServerSocket createServer(String host, int port) {
        try {
            return new ServerSocket(port, 1, InetAddress.getByName(host));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    /**
     * Listen and wait for nodes join the cluster.
     */
    // TODO: 12-Feb-18 Atualizar mensagens de debug e error
    public void listen() {
        LOGGER.debug("Started server at "+this.host+":"+this.port);
        String data = null;
        Socket client = null;
        try {
            client = this.server.accept();
            String clientAddress = client.getInetAddress().getHostAddress();
            LOGGER.info("Node Connected: " + clientAddress);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            while ( (data = in.readLine()) != null ) {
                LOGGER.debug("DATA_SIGNAL: " + clientAddress + ": " + data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start Orchestrator Master Server for ClusterManagement
     */
    // TODO: 12-Feb-18 Carregar porta e configuração de ip da configuração e atualizar mensagens
    public void run() {
        this.listen();
    }

}
