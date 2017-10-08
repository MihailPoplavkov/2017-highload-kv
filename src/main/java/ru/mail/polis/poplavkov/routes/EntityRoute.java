package ru.mail.polis.poplavkov.routes;

import com.sun.net.httpserver.HttpServer;
import ru.mail.polis.poplavkov.dao.DAO;

import java.io.IOException;
import java.util.NoSuchElementException;

public class EntityRoute implements Route {
    private static final String PREFIX = "id=";

    private static String extractId(String query) {
        if (!query.startsWith(PREFIX)) {
            throw new IllegalArgumentException();
        }
        String id = query.substring(PREFIX.length());
        if (id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return id;
    }

    @Override
    public void addRouteTo(HttpServer server, DAO dao) {
        server.createContext("/v0/entity", exchange -> {
            String id;
            try {
                id = extractId(exchange.getRequestURI().getQuery());
            } catch (Exception e) {
                sendResponse(exchange, ResponseCode.BAD_REQUEST);
                return;
            }
            byte[] bytes;
            switch (exchange.getRequestMethod()) {
                case "GET":
                    try {
                        bytes = dao.getData(id);
                    } catch (NoSuchElementException nse) {
                        sendResponse(exchange, ResponseCode.NOT_FOUND);
                        return;
                    }
                    sendResponse(exchange, ResponseCode.OK, bytes);
                    return;
                case "PUT":
                    int size = Integer.parseInt(exchange.getRequestHeaders().getFirst("Content-Length"));
                    bytes = new byte[size];
                    //noinspection ResultOfMethodCallIgnored
                    exchange.getRequestBody().read(bytes);
                    dao.upsertData(id, bytes);
                    sendResponse(exchange, ResponseCode.CREATED);
                    return;
                case "DELETE":
                    dao.deleteData(id);
                    sendResponse(exchange, ResponseCode.ACCEPTED);
                    return;
                default:
                    sendResponse(exchange, ResponseCode.SERVICE_UNAVAILABLE);
            }
        });
    }
}
