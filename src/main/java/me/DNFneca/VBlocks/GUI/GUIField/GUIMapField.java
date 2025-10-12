package me.DNFneca.VBlocks.GUI.GUIField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GUIMapField<T, U> {
    private final Map<T, U> field = new HashMap<>(0);

    public void putField(T t, U u) {
        field.put(t, u);
    }

    public void putFields(Map<T, U> fields) {
        this.field.putAll(fields);
    }

    public U getField(T t) {
        return field.get(t);
    }

    public boolean containsKey(T t) {
        return field.containsKey(t);
    }

    public boolean containsValue(U u) {
        return field.containsValue(u);
    }

    public void removeField(T t) {
        field.remove(t);
    }

    public void removeFields(List<T> ts) {
        for (T t : ts) {
            removeField(t);
        }
    }

    public boolean isEmpty() {
        return field.isEmpty();
    }

    public Map<T, U> all() {
        return Map.copyOf(field);
    }

    public void clear() {
        field.clear();
    }
}
