package org.br.dataslack.flowpipe;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;

public class Orchestrator implements Runnable {

    final static Logger LOGGER = LogManager.getLogger(Orchestrator.class);
    final private InputStream input;
    final private PrintStream output;
    final private Path home;
    final private String[] args;

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
    }

    @Override
    public void run() {
        LOGGER.debug("Orquestrador Inicializado");
    }
}
