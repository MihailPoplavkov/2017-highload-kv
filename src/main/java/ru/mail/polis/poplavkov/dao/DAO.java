package ru.mail.polis.poplavkov.dao;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.NoSuchElementException;

public interface DAO {
    @NotNull
    byte[] getData(@NotNull String key) throws NoSuchElementException, IllegalArgumentException, IOException;

    void upsertData(@NotNull String key, @NotNull byte[] data) throws IllegalArgumentException, IOException;

    void deleteData(@NotNull String key) throws IllegalArgumentException, IOException;
}
