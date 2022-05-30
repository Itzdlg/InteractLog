package me.joshios.interactlog.util;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;

public class MaterialFormatter {

    public static String format(Material material) {
        String materialName = material.name().toLowerCase().replaceAll("_", " ");

        return WordUtils.capitalize(materialName);
    }
}