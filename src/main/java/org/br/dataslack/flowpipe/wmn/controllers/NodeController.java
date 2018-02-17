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
            "                                <h1 class=\"card-title pricing-card-title\">0 <small class=\"text-muted\">/ flows</small></h1>" +
            "                                <ul class=\"list-unstyled mt-3 mb-4\">" +
            "                                    <li class=\"mt-1\">Allocated Memory</br><div class=\"progress\"> <div class=\"progress-bar progress-bar-striped progress-bar-animated\" role=\"progressbar\" aria-valuenow=\"75\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 75%\"></div> </div></li>" +
            "                                    <li class=\"mt-1\">Maximum Memory</br><div class=\"progress\"> <div class=\"progress-bar progress-bar-striped progress-bar-animated\" role=\"progressbar\" aria-valuenow=\"75\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 75%\"></div> </div></li>" +
            "                                    <li class=\"mt-1\">Free Memory</br><div class=\"progress\"> <div class=\"progress-bar progress-bar-striped progress-bar-animated\" role=\"progressbar\" aria-valuenow=\"75\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 75%\"></div> </div></li>" +
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
