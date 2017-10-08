package ru.mail.polis.poplavkov;

import com.sun.net.httpserver.HttpServer;
import ru.mail.polis.KVService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyService implements KVService {
    private final HttpServer server;

    public MyService(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
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
