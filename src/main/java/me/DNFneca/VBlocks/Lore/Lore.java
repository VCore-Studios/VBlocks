package me.DNFneca.VBlocks.Lore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class Lore {
    private List<Component> lore = new ArrayList<>(0);

    public Lore addLoreLine(Component component) {
        this.lore.add(component);
        return this;
    }

    public Lore addLoreLine(String text, TextColor color) {
        this.lore.add(Component.text(text).color(color).decorations(Map.of(
                TextDecoration.ITALIC, TextDecoration.State.FALSE,
                TextDecoration.BOLD, TextDecoration.State.FALSE,
                TextDecoration.UNDERLINED, TextDecoration.State.FALSE,
                TextDecoration.STRIKETHROUGH, TextDecoration.State.FALSE,
                TextDecoration.OBFUSCATED, TextDecoration.State.FALSE)));
        return this;
    }

    public Lore addLoreLines(List<Component> components) {
        this.lore.addAll(components);
        return this;
    }

    public Lore addEmptyLine() {
        this.lore.add(Component.empty());
        return this;
    }

    public Lore clearLore() {
        this.lore.clear();
        return this;
    }

    public Lore appendToLine(int lineNumber, Component component) {
        if (this.lore.get(lineNumber) == null) return this;
        this.lore.set(lineNumber, this.lore.get(lineNumber).append(component));
        return this;
    }

    public Lore appendToLine(int lineNumber, String text, TextColor color) {
        if (this.lore.get(lineNumber) == null) return this;
        this.lore.set(lineNumber, this.lore.get(lineNumber).append(Component.text(text).color(color).decorations(Map.of(
                TextDecoration.ITALIC, TextDecoration.State.FALSE,
                TextDecoration.BOLD, TextDecoration.State.FALSE,
                TextDecoration.UNDERLINED, TextDecoration.State.FALSE,
                TextDecoration.STRIKETHROUGH, TextDecoration.State.FALSE,
                TextDecoration.OBFUSCATED, TextDecoration.State.FALSE))));
        return this;
    }

    public List<Component> build() {
        return this.lore;
    }
}
