package ru.mail.polis.poplavkov.routes;

import com.sun.net.httpserver.HttpServer;
import ru.mail.polis.poplavkov.dao.DAO;

public interface Route {
    /**
     * Add current route to specified http server
     *
     * @param server server to bind with current route
     */
    void addRouteTo(HttpServer server, DAO dao);
}
