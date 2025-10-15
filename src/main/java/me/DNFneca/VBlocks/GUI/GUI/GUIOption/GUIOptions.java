package me.DNFneca.VBlocks.GUI.GUI.GUIOption;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

@Getter
public class GUIOptions {
    @Setter
    boolean shouldSetBackground = true;
    int padding = 0;
    @Setter
    int padding_left = 0;
    @Setter
    int padding_right = 0;
    @Setter
    int padding_top = 0;
    @Setter
    int padding_bottom = 0;
    @Setter
    Material paddingMaterial = Material.TWISTING_VINES;


    public void setPadding(int padding) {
        this.padding = padding;
        this.padding_left = padding;
        this.padding_right = padding;
        this.padding_top = padding;
        this.padding_bottom = padding;
    }
}

