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
import org.br.dataslack.flowpipe.Orchestrator;
import org.br.dataslack.flowpipe.wmn.controllers.DashboardController;
import org.br.dataslack.flowpipe.wmn.controllers.FlowController;
import org.br.dataslack.flowpipe.wmn.controllers.NodeController;

public class WebManager extends NanoHTTPD{

    final static Logger LOGGER = LogManager.getLogger(WebManager.class);
    final private FlowPipe current_flowpipe;
    final private Orchestrator orchestrator;
    private IHTTPSession session = null;


    public WebManager(FlowPipe flowpipe, Orchestrator orchestrator) throws IOException {
        super((int)flowpipe.getNode().getConfig().map().get("wmn.port"));
        this.current_flowpipe = flowpipe;
        this.orchestrator = orchestrator;
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        LOGGER.info("\n\nFlowPipe WebManager started at http://"+
                flowpipe.getNode().getConfig().map().get("wmn.host").toString()+":"+
                flowpipe.getNode().getConfig().map().get("wmn.port").toString()+"/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        this.session = session;
        String response = "";
        if(String.valueOf(session.getUri()).equals("/")){
            DashboardController dashboard = new DashboardController(this);
            response = dashboard.index();
        }
        return newFixedLengthResponse(response);
    }





}
