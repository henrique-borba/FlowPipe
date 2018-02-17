package org.br.dataslack.flowpipe;

import java.util.ArrayList;
import java.util.List;

public class FlowManager {

    private List<Flow> flows = new ArrayList<>();
    final private Orchestrator orchestrator;

    FlowManager(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public void addFlow(Flow flow) {
        this.flows.add(flow);
    }

    public List<Flow> getFlows() {
        return this.flows;
    }

}
