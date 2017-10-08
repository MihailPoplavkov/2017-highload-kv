package ru.mail.polis.poplavkov.dao;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;

public class MyDAO implements DAO {
    private final File dir;

    public MyDAO(File dir) {
        this.dir = dir;
    }

    private File getFile(String key) {
        return new File(dir, key);
    }

    @NotNull
    @Override
    public byte[] getData(@NotNull String key) throws NoSuchElementException, IllegalArgumentException, IOException {
        File file = getFile(key);
        if (!file.exists()) {
            throw new NoSuchElementException();
        }
        byte[] bytes = new byte[(int) file.length()];
        try (InputStream inputStream = new FileInputStream(file)) {
            if (inputStream.read(bytes) != bytes.length) {
                throw new IOException("Can't read file in one go");
            }
        }
        return bytes;
    }

    @Override
    public void upsertData(@NotNull String key, @NotNull byte[] data) throws IllegalArgumentException, IOException {
        try (OutputStream outputStream = new FileOutputStream(getFile(key))) {
            outputStream.write(data);
        }
    }

    @Override
    public void deleteData(@NotNull String key) throws IllegalArgumentException, IOException {
        //noinspection ResultOfMethodCallIgnored
        getFile(key).delete();
    }
}
