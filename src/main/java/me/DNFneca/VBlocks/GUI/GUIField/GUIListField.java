package me.DNFneca.VBlocks.GUI.GUIField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GUIListField<T> {
    private final List<T> field = new ArrayList<>(0);

    public void addField(T t) {
        field.add(t);
    }

    public void addFields(List<T> fields) {
        this.field.addAll(fields);
    }

    public List<T> getFields() {
        return Collections.unmodifiableList(field);
    }

    public T getField(int i) {
        return field.get(i);
    }

    public boolean contains(T t) {
        return field.contains(t);
    }

    public void removeField(T t) {
        field.remove(t);
    }

    public void removeField(int t) {
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

    public List<T> all() {
        return List.copyOf(field);
    }

    public void clear() {
        field.clear();
    }
}
