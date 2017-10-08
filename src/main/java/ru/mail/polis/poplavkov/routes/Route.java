package ru.mail.polis.poplavkov.routes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.jetbrains.annotations.NotNull;
import ru.mail.polis.poplavkov.dao.DAO;

import java.io.IOException;

public interface Route {
    /**
     * Add current route to specified http server
     *
     * @param server server to bind with current route
     */
    void addRouteTo(HttpServer server, DAO dao);

    default void sendResponse(HttpExchange exchange, ResponseCode code, @NotNull byte[] data) throws IOException {
        exchange.sendResponseHeaders(code.getCode(), data.length);
        exchange.getResponseBody().write(data);
        exchange.close();
    }

    default void sendResponse(HttpExchange exchange, ResponseCode code) throws IOException {
        exchange.sendResponseHeaders(code.getCode(), 0);
        exchange.close();
    }
}
