package org.br.dataslack.flowpipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Thread.sleep;


/**
 * FlowPipe
 */
public class FlowPipe implements Runnable, AutoCloseable {


    final static Logger LOGGER = LogManager.getLogger(FlowPipe.class);
    private final PrintStream output;
    private final InputStream input;
    private final PrintStream error;
    private final String[] args;
    private final Path home;
    private final Node node = Node.current();

    /**
     * FlowPipe
     *
     * @param home
     * @param args
     * @param output
     * @param error
     * @param input
     */
    FlowPipe(final Path home, final String[] args, final PrintStream output,
             final PrintStream error, final InputStream input) {
        this.home = home;
        this.args = args;
        this.output = output;
        this.error = error;
        this.input = input;
        LOGGER.info("FlowPipe 0.1");
        LOGGER.info("Starting at "+home+"...");
    }

    public PrintStream getErrorStream() {
        return this.error;
    }

    public Logger getMainLogger() {
        return this.LOGGER;
    }

    public InputStream getStreamInput() {
        return this.input;
    }

    public PrintStream getStreamOutput() {
        return this.output;
    }


    /**
     * Get current FlowPipe Args
     * @return
     */
    public String[] getArgs() {
        return this.args;
    }

    /**
     * Get current FlowPipe Node object
     * @return
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * Return loaded FlowPipe Home Path
     * @return
     */
    public Path getHome() {
        return this.home;
    }

    /**
     * @param args
     */
    public static void main(String... args) {
        final String fpHome = System.getenv("FP_DIR");
        if (fpHome == null) {
            throw new IllegalStateException(
                    "FP_DIR environment variable must be set."
            );
        }
        final Path home = Paths.get(fpHome).toAbsolutePath();
        try (
                final FlowPipe flowpipe = new FlowPipe(home, args, System.out, System.err, System.in)
        ) {
            flowpipe.run();
        } catch (final Throwable t) {
            LOGGER.error(t.toString());
            System.exit(1);
        }
        System.exit(0);
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Closing...");
    }

    @Override
    public void run() {
        LOGGER.info("Starting Flowpipe...");
        new Orchestrator(this).start();
        while(true){
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
