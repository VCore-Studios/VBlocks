package me.DNFneca.VBlocks.CustomPlayer.Option;

import org.bukkit.*;
import me.DNFneca.VBlocks.Registry.CustomRegistry;
import me.DNFneca.VBlocks.Registry.Registry;
import me.DNFneca.VBlocks.Registry.RegistryReference;
import me.DNFneca.VBlocks.Util.Triplet;

import java.lang.reflect.Type;
import java.util.function.Function;

public class PlayerOptions {
    public static final Registry<Triplet<Type, Function<String, Object>, Function<Object, String>>> PlayerOptions = new CustomRegistry<>("PlayerOptions");

    public static final RegistryReference<Triplet<Type, Function<String, Object>, Function<Object, String>>> MATERIAL =
            PlayerOptions.register("material",
                    Triplet.of(
                            Material.class,
                            Material::valueOf,
                            Object::toString
                    ));
    public static final RegistryReference<Triplet<Type, Function<String, Object>, Function<Object, String>>> SOUND =
            PlayerOptions.register("sound",
                    Triplet.of(
                            NamespacedKey.class,
                            NamespacedKey::fromString,
                            Object::toString
                    ));
}
