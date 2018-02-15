package org.br.dataslack.flowpipe.orchestrator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.br.dataslack.flowpipe.Orchestrator;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

/**
 *
 */
public class Server extends Thread  {

    final private static Logger LOGGER = LogManager.getLogger(Server.class);
    private final InputStream input;
    public ServerSocket server;

    /**
     * 
     * @param home
     * @param args
     * @param output
     * @param error
     * @param input
     */
    public Server(final Path home, final String[] args, final PrintStream output,
                  final PrintStream error, final InputStream input, Orchestrator orchestrator) {
        this.server = null;
        this.input = input;
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
     * Listen and wait for nodes join the cluster.
     */
    // TODO: 12-Feb-18 Atualizar mensagens de debug e error
    public void listen() {
        LOGGER.debug("Started server at localhost:3322");
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
     * Start Orchestrator Master Server for Cluster Management
     */
    // TODO: 12-Feb-18 Carregar porta e configuração de ip da configuração e atualizar mensagens
    public void run() {
        try {
            this.server = new ServerSocket(3322, 1, InetAddress.getByName("127.0.0.1"));
            this.listen();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

}
