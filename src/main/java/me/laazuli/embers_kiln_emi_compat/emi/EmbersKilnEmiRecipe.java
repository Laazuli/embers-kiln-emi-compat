package me.laazuli.embers_kiln_emi_compat.emi;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import gay.quack.kiln.KilnRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class EmbersKilnEmiRecipe extends BasicEmiRecipe {
    private final KilnRecipe kilnRecipe;

    public EmbersKilnEmiRecipe(Identifier id, KilnRecipe kilnRecipe) {
        super(EmbersKilnEmiPlugin.EMBERS_KILN_CATEGORY,
                id,
                82,
                38
        );

        this.kilnRecipe = kilnRecipe;

        EmiIngredient input = EmiIngredient.of(kilnRecipe.getIngredients().getFirst()); // kiln recipes can only ever have one ingredient
        this.inputs.add(input);

        MinecraftClient client = MinecraftClient.getInstance();
        EmiStack output = EmiStack.of(kilnRecipe.getResult(client.world.getRegistryManager()));
        this.outputs.add(output);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addFillingArrow(24, 5, 50 * this.kilnRecipe.getCookingTime()).tooltip((mx, my) ->
            List.of(
                    TooltipComponent.of(
                            Text.translatable("emi.cooking.time", this.kilnRecipe.getCookingTime() /20f).asOrderedText()
                    )
            )
        );

        widgets.addTexture(EmiTexture.EMPTY_FLAME, 1, 24);
        widgets.addAnimatedTexture(EmiTexture.FULL_FLAME, 1, 24, 4000 / 2 /* fuelMultiplier */ , false, true, true);

        widgets.addText(
                Text.translatable("emi.cooking.experience", this.kilnRecipe.getExperience()).asOrderedText(),
                26,
                28,
                -1,
                true
        );

        widgets.addSlot(this.inputs.getFirst(), 0, 4);
        widgets.addSlot(this.outputs.getFirst(), 56, 0).large(true).recipeContext(this);
    }
}
