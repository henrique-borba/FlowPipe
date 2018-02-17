package org.br.dataslack.flowpipe.wmn.controllers;

import org.br.dataslack.flowpipe.wmn.WebManager;

import javax.sound.sampled.Control;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DashboardController extends Controller {


    public DashboardController(WebManager webmngr) {
        this.webmngr = webmngr;
    }

    public String index() {
        // TODO: 17-Feb-18 Upgrade to StringBuilder
        String msg = this.getHeader();
        msg = msg + this.getIndex();
        msg = msg + this.closeHeader();
        msg = msg +  this.getScripts();
        return msg;
    }


    public String getIndex() {
        InputStream in = getClass().getResourceAsStream("/index.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String response = "";
        try {
            for (String line; (line = reader.readLine()) != null;) {
                response = response + line + "\n";
            }
        } catch (IOException e) {
            response = "500 ERROR";
        }
        return response;
    }

    public String getScripts() {
        InputStream inCss = getClass().getResourceAsStream("/scripts.js");
        BufferedReader readerCss = new BufferedReader(new InputStreamReader(inCss));
        String js = "<script>";
        try {
            for (String line; (line = readerCss.readLine()) != null;) {
                js = js + line + "\n";
            }
        } catch (IOException e) {
            js = "500 ERROR";
        }
        js = js + "</script>";
        return js;
    }

    public String getStyle() {
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
        return css;
    }


    public String getHeader() {
        return "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <meta name=\"description\" content=\"\">\n" +
                "    <meta name=\"author\" content=\"\">\n" +
                "    <title>FlowPipe WebManager</title>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/vue\"></script>" +
                "</head>\n" +
                "\n" +
                this.getStyle() +
                "<body><div id=\"app\">";
    }


    public String closeHeader() {
        return "</div></body></html>";
    }

}
