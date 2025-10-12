package me.DNFneca.VBlocks.Util;

public record Pair<K, V>(K firstValue, V secondValue) {
    public static <K, V> Pair<K, V> of(K firstValue, V secondValue) {
        return new Pair<>(firstValue, secondValue);
    }
}
