package org.br.dataslack.flowpipe;


import org.br.dataslack.flowpipe.wmn.WebManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.br.dataslack.flowpipe.orchestrator.Server;

import java.io.IOException;

/**
 * FlowPipe Orchestrator
 */
public class Orchestrator extends Thread {

    final static Logger LOGGER = LogManager.getLogger(Orchestrator.class);
    private NodeManager nmngr;
    final private FlowPipe current_flowpipe;
    final private FlowManager flowmng;

    /**
     * FlowPipe Orchestrator
     *
     * @param flowpipe
     */
    Orchestrator (FlowPipe flowpipe) {
        LOGGER.debug("Starting Orchestrator...");
        this.current_flowpipe = flowpipe;
        this.nmngr = new NodeManager(this.current_flowpipe.getNode());
        this.flowmng = new FlowManager(this);
    }

    /**
     * Master Orchestrator Startup
     */
    public void master_() {
        LOGGER.debug("Started MASTER");
        try {
            WebManager wmn = new WebManager(this.current_flowpipe,this);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        new Server(this.current_flowpipe,
                this).start();
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
                sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.debug("CHECKPOINT ORCHESTRATOR");
        }
    }

    /**
     * Get NodeManagement Object
     * @return
     */
    public NodeManager getNodeManager() {
        return this.nmngr;
    }


    public FlowManager getFlowManager() {
        return this.flowmng;
    }

    /**
     *  Slave Orchestrator Agent
     */
    private void slave_runtime() {
        while(true){
            try {
                sleep(20000);
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
        if(this.current_flowpipe.getNode().getType() == NodeType.MASTER) {
            // Run MASTER
            this.master_();
        } else {
            // Run SLAVE
            this.slave_();
        }
    }

}
