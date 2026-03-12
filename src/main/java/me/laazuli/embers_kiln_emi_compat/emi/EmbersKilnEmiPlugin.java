package me.laazuli.embers_kiln_emi_compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import gay.quack.kiln.KilnMain;
import gay.quack.kiln.KilnRecipe;
import me.laazuli.embers_kiln_emi_compat.EmbersKilnEmiCompat;
import net.minecraft.block.Block;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class EmbersKilnEmiPlugin implements EmiPlugin {
    private static final Block KILN_BLOCK = Registries.BLOCK.get(Identifier.of(KilnMain.MOD_ID, "kiln"));

    private static final EmiStack EMBERS_KILN_BLOCK = EmiStack.of(KILN_BLOCK);

    public static final EmiRecipeCategory EMBERS_KILN_CATEGORY = new EmiRecipeCategory(
            Identifier.of(KilnMain.MOD_ID, "kiln"),
            EMBERS_KILN_BLOCK,
            EmbersKilnEmiCompat.SIMPLIFIED_KILN_TEXTURE
    );

    @Override
    public void register(EmiRegistry emiRegistry) {
        emiRegistry.addCategory(EMBERS_KILN_CATEGORY);

        emiRegistry.addWorkstation(EMBERS_KILN_CATEGORY, EMBERS_KILN_BLOCK);

        RecipeManager manager = emiRegistry.getRecipeManager();

        // add all kiln recipes
        for (RecipeEntry<KilnRecipe> recipeEntry : manager.listAllOfType(KilnMain.KILN_RECIPE_TYPE)) {
            emiRegistry.addRecipe(new EmbersKilnEmiRecipe(recipeEntry.id(), recipeEntry.value()));
        }
    }
}
