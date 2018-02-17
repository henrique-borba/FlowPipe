package org.br.dataslack.flowpipe.wmn;
import fi.iki.elonen.NanoHTTPD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.br.dataslack.flowpipe.FlowPipe;

public class WebManager extends NanoHTTPD{

    final static Logger LOGGER = LogManager.getLogger(WebManager.class);


    public WebManager(FlowPipe flowpipe) throws IOException {
        super((int)flowpipe.getNode().getConfig().map().get("wmn.port"));
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nFlowPipe WebManager started at http://"+
                flowpipe.getNode().getConfig().map().get("wmn.host").toString()+":"+
                flowpipe.getNode().getConfig().map().get("wmn.port").toString()+"/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        InputStream in = getClass().getResourceAsStream("/index.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String msg = "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <meta name=\"description\" content=\"\">\n" +
                "    <meta name=\"author\" content=\"\">\n" +
                "    <link rel=\"icon\" href=\"../../../../favicon.ico\">\n" +
                "\n" +
                "    <title>FlowPipe WebManager</title>\n" +
                "\n" +
                "    <!-- Bootstrap core CSS -->\n" +
                "    <link href=\"../../../../dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- Custom styles for this template -->\n" +
                "    <link href=\"pricing.css\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "\n" +
                "<body>";

        InputStream inCss = getClass().getResourceAsStream("/style.css");
        BufferedReader readerCss = new BufferedReader(new InputStreamReader(inCss));
        String css = "<style>";
        try {
            for (String line; (line = readerCss.readLine()) != null;) {
                css = css + line;
            }
        } catch (IOException e) {
            css = "500 ERROR";
        }
        css = css + "</style>";
        msg = msg + css;

        try {
            for (String line; (line = reader.readLine()) != null;) {
                msg = msg + line;
            }
        } catch (IOException e) {
            msg = "500 ERROR";
        }
        Map<String, String> parms = session.getParms();
        /*if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }*/
        msg = msg + "</body></html>";
        return newFixedLengthResponse(msg);

    }



}
