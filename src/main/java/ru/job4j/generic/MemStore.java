package ru.job4j.generic;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (storage.get(id) != null) {
            storage.replace(id, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (storage.get(id) != null) {
            storage.remove(id);
            result = true;
        }
        return result;    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}