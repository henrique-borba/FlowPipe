package org.br.dataslack.flowpipe.wmn.controllers;

import org.br.dataslack.flowpipe.Node;
import org.br.dataslack.flowpipe.NodeManager;

import java.util.ArrayList;
import java.util.List;

public final class NodeController {

    /**
     * Default Node Widget HTML
     */
    final static protected String WIDGET_HTML = "<div class=\"card mb-4 box-shadow\">" +
            "                           <div class=\"card-header\">" +
            "                                <h4 class=\"my-0 font-weight-normal\">{{$name}}</h4>" +
            "                            </div>" +
            "                            <div class=\"card-body\">" +
            "                                <h1 class=\"card-title pricing-card-title\">0 <small class=\"text-muted\">/ drops</small></h1>" +
            "                                <ul class=\"list-unstyled mt-3 mb-4\">" +
            "                                    <li>10 users included</li>" +
            "                                    <li>2 GB of storage</li>" +
            "                                    <li>Email support</li>" +
            "                                    <li>Help center access</li>" +
            "                                </ul>" +
            "                                <button type=\"button\" class=\"btn btn-lg btn-block btn-outline-primary\">Sign up for free</button>" +
            "                            </div>" +
            "                        </div>";




    /**
     * Build initital Nodes widgets boxes
     * @param ndm
     * @return
     */
    public static String buildWidgets(NodeManager ndm) {
        List<String> nodesHtml = new ArrayList<>();
        List<Node> nodes = ndm.getNodes();
        for(Node node : nodes){
            String temp_html = WIDGET_HTML.replace("{{$name}}",node.getName());
            nodesHtml.add(temp_html);
        }
        return String.join("", nodesHtml);
    }



}
