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
                exchange.sendResponseHeaders(ResponseCode.OK.getCode(), body.length());
                exchange.getResponseBody().write(body.getBytes());
            } else {
                exchange.getResponseHeaders().add("Allow", "GET");
                exchange.sendResponseHeaders(ResponseCode.NOT_ALLOWED.getCode(), 0);
            }
            exchange.close();
        });
    }
}
