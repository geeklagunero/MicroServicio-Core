package edu.tienda.core.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class ClienteRenderController {

    @GetMapping(value = "/cliente-html", produces = MediaType.TEXT_HTML_VALUE)
    public String getClienteAsHtml(){
        StringBuilder sb = new StringBuilder();
        sb.append("<hyml>");
        sb.append("body");
        sb.append("<p>Nombre rafael benedeti</p>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }
}
