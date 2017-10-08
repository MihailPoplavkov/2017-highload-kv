package ru.mail.polis.poplavkov;

import com.sun.net.httpserver.HttpServer;
import ru.mail.polis.KVService;
import ru.mail.polis.poplavkov.dao.DAO;
import ru.mail.polis.poplavkov.routes.StatusRoute;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyService implements KVService {
    private final HttpServer server;
    private final DAO dao;

    public MyService(int port, DAO dao) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        this.dao = dao;
        new StatusRoute().addRouteTo(server, dao);
    }

    @Override
    public void start() {
        server.start();
    }

    @Override
    public void stop() {
        server.stop(0);
    }
}
