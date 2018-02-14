package org.br.dataslack.flowpipe;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.br.dataslack.flowpipe.orchestrator.Server;

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
                  final PrintStream error, final InputStream input) {
        LOGGER.debug("Inicializando Orquestrador...");
        this.input = input;
        this.output = output;
        this.home = home;
        this.args = args;
        this.error = error;
    }


    public void run() {
        LOGGER.debug("Orquestrador Inicializado");
        new Server(this.home, this.args, this.output, this.error, this.input).start();
        while(true){
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.debug("CHECKPOINT ORCHESTRATOR");
        }
    }

}
