package me.laazuli.embers_kiln_emi_compat;

import dev.emi.emi.api.render.EmiTexture;
import net.minecraft.util.Identifier;

public class EmbersKilnEmiCompat {
    public static final String MOD_ID = "embers_kiln_emi_compat";

    private static final Identifier SIMPLIFIED_KILN_ID = Identifier.of(MOD_ID, "textures/gui/simplified_kiln.png");

    public static final EmiTexture SIMPLIFIED_KILN_TEXTURE = new EmiTexture(SIMPLIFIED_KILN_ID, 0, 0, 16, 16, 16, 16, 16, 16);

}
