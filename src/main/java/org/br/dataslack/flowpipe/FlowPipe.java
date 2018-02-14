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
 *
 */
public class FlowPipe implements Runnable, AutoCloseable {


    final static Logger LOGGER = LogManager.getLogger(FlowPipe.class);
    private final PrintStream output;
    private final InputStream input;
    private final PrintStream error;
    private final String[] args;
    private final Path home;
    private final Node node = Node.getNode();

    /**
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
        LOGGER.info("FLOWPIPE 1.0");
        LOGGER.info("Inicializando em "+home+"...");
    }

    /**
     *
     * @param args
     */
    public static void main(String... args) {
        final String lsHome = System.getenv("FP_DIR");
        if (lsHome == null) {
            throw new IllegalStateException(
                    "FP_DIR environment variable must be set. This is likely a bug that should be reported."
            );
        }
        final Path home = Paths.get(lsHome).toAbsolutePath();
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
        LOGGER.info("Fechando...");
    }

    @Override
    public void run() {
        LOGGER.info("Inicializado...");
        new Orchestrator(this.home, this.args, this.output, this.error, this.input).start();
        while(true){
            try {
                sleep(10000);
                Socket socket = new Socket("127.0.0.1", 3322);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
