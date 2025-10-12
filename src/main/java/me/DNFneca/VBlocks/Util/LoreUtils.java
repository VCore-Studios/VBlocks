package me.DNFneca.VBlocks.Util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.List;

public class LoreUtils {

    public static List<Component> createDescriptionLoreLine(String description) {
        if (description.length() < 20) return List.of(Component.text(description).style(Style.style(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        List<Component> components = new ArrayList<>(0);
        components.addAll(splitLore(description));
        return components;
    }

    public static List<Component> splitLore(String text) {
        List<Component> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : text.split(" ")) {
            // If the word itself is longer than the max, split the word anyway
            if (word.length() > 30) {
                if (!currentLine.isEmpty()) {
                    result.add(Component.text(currentLine.toString().trim()));
                    currentLine.setLength(0);
                }
                for (int i = 0; i < word.length(); i += 30) {
                    int end = Math.min(i + 30, word.length());
                    result.add(createLoreLine(word.substring(i, end)));
                }
            } else {
                if (currentLine.length() + word.length() + 1 > 30) {
                    result.add(createLoreLine(currentLine.toString().trim()));
                    currentLine.setLength(0);
                }
                currentLine.append(word).append(" ");
            }
        }

        if (!currentLine.isEmpty()) {
            result.add(createLoreLine(currentLine.toString().trim()));
        }

        return result;
    }

    public static Component createLoreLine(String text) {
        return Component.text(text).style(Style.style(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
    }

    public static Component createLoreLine(String text, TextColor color) {
        return Component.text(text).style(Style.style(color).decoration(TextDecoration.ITALIC, false));
    }

}
