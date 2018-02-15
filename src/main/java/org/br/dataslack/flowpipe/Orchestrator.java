package org.br.dataslack.flowpipe;


import org.br.dataslack.flowpipe.wmn.WebManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.br.dataslack.flowpipe.orchestrator.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static java.lang.Thread.sleep;

public class Orchestrator extends Thread {

    final static Logger LOGGER = LogManager.getLogger(Orchestrator.class);
    final private InputStream input;
    final private PrintStream output;
    final private Path home;
    final private String[] args;
    final private PrintStream error;
    private NodeManager nmngr;
    final private Node node;

    /**
     * FlowPipe Orchestrator
     *
     * @param home
     * @param args
     * @param output
     * @param error
     * @param input
     */
    Orchestrator (final Path home, final String[] args, final PrintStream output,
                  final PrintStream error, final InputStream input, final Node node) {
        LOGGER.debug("Starting Orchestrator...");
        this.input = input;
        this.output = output;
        this.home = home;
        this.args = args;
        this.error = error;
        this.node = node;
        this.nmngr = new NodeManager(node);
    }

    /**
     * Master Orchestrator Startup
     */
    public void master_() {
        LOGGER.debug("Started MASTER");
        try {
            WebManager wmn = new WebManager();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        new Server(this.home, this.args, this.output, this.error, this.input, this).start();
        this.master_runtime();
    }

    /**
     * Slave Orchestrator Startup
     */
    public void slave_() {
        LOGGER.debug("Started SLAVE");
        this.slave_runtime();
    }

    /**
     *  Master Orchestrator Agent
     */
    private void master_runtime() {
        while(true){
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.debug("CHECKPOINT ORCHESTRATOR");
        }
    }

    /**
     *  Slave Orchestrator Agent
     */
    private void slave_runtime() {
        while(true){
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.debug("CHECKPOINT ORCHESTRATOR");
        }
    }

    /**
     * Start Orchestrator Thread
     */
    public void run() {
        // Determines wich Orchestration methods to use
        if(this.node.getType() == NodeType.MASTER) {
            // Run MASTER
            this.master_();
        } else {
            // Run SLAVE
            this.slave_();
        }
    }

}
