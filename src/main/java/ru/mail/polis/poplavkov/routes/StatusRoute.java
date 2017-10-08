package ru.mail.polis.poplavkov.routes;

import com.sun.net.httpserver.HttpServer;
import ru.mail.polis.poplavkov.dao.DAO;

public class StatusRoute implements Route {
    @Override
    public void addRouteTo(HttpServer server, DAO ignored) {
        server.createContext("/v0/status", exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                exchange.getResponseHeaders().add("content-type", "text");
                String body = "ONLINE";
                sendResponse(exchange, ResponseCode.OK, body.getBytes());
            } else {
                exchange.getResponseHeaders().add("Allow", "GET");
                sendResponse(exchange, ResponseCode.NOT_ALLOWED);
            }
            exchange.close();
        });
    }
}
