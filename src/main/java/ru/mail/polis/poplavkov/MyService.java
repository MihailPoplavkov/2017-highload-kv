package ru.mail.polis.poplavkov;

import com.sun.net.httpserver.HttpServer;
import ru.mail.polis.KVService;
import ru.mail.polis.poplavkov.routes.StatusRoute;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyService implements KVService {
    private final HttpServer server;

    public MyService(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        new StatusRoute().addRouteTo(server);
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
