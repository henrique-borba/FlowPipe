package org.br.dataslack.flowpipe.wmn.controllers;

import org.br.dataslack.flowpipe.FlowManager;

public class FlowController {


    final public static String no_flow_msg = "<div class='alert alert-danger w-100'><strong>Your cluster has no running Flows.</strong></div>";

    final public static String widget_html = "<div class=\"card mb-4 box-shadow\">" +
            "            <div class=\"card-body\">" +
            "            </div>" +
            "        </div>";

    public static String buildWidgets(FlowManager flowmng) {
        if(flowmng.getFlows().size() == 0) {
            return no_flow_msg;
        } else {
            return widget_html;
        }
    }


}
