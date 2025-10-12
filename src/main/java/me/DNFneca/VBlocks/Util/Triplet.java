package me.DNFneca.VBlocks.Util;

public record Triplet<K, V, C>(K firstValue, V secondValue, C thirdValue) {
    public static <K, V, C> Triplet<K, V, C> of(K firstValue, V secondValue, C thirdValue) {
        return new Triplet<>(firstValue, secondValue, thirdValue);
    }
}
