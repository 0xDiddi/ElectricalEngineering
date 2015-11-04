package gmail.theultimatehose.ee.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gmail.theultimatehose.ee.Main;
import gmail.theultimatehose.ee.Util;
import gmail.theultimatehose.ee.gui.GUICoilWinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class CoilWinderRecipeHandler extends TemplateRecipeHandler {

    public CoilWinderRecipeHandler() {
        RecipeInfo.setGuiOffset(this.getGuiClass(), 0, 0);
    }

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(76, 32, 15, 22), this.getName()));
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getName()) && getClass() == CoilWinderRecipeHandler.class) {
            arecipes.add(new CachedWinding(new ItemStack(Main.coil, 1, 2048), new ItemStack(Main.coil, 1, 0), new ItemStack(Main.wireCopper)));
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        Item item = result.getItem();
        if (item == Main.coil) {
            arecipes.add(new CachedWinding(new ItemStack(Main.coil, 1, 2048), new ItemStack(Main.coil, 1, 0), new ItemStack(Main.wireCopper)));
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        Item item = ingredient.getItem();
        if (item == Main.wireCopper || item == Main.coil) {
            arecipes.add(new CachedWinding(new ItemStack(Main.coil, 1, 2048), new ItemStack(Main.coil, 1, 0), new ItemStack(Main.wireCopper)));
        }
    }

    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(68, 28, 196, 0, 16, 22, 2000, 1);
        drawProgressBar(63, 1, 176, 43, 26, 26, 10, 0);
        drawProgressBar(39, 2, 234, 0, 16, 72, 2000, 7);
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GUICoilWinder.class;
    }

    @Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GuiDraw.changeTexture(this.getGuiTexture());
        GuiDraw.drawTexturedModalRect(-1, 0, 6, 4, 166, 77);
    }

    @Override
    public String getGuiTexture() {
        return "ee:textures/gui/guiCoilWinder.png";
    }

    public String getName() {
        return Util.MOD_ID_LOWER + ".coilwinding";
    }

    @Override
    public String getRecipeName() {
        return "Coil Winding";
    }

    public class CachedWinding extends CachedRecipe {

        public PositionedStack input;
        public PositionedStack output;
        public PositionedStack wire;

        public CachedWinding(ItemStack in, ItemStack out, ItemStack wire) {
            this.input = new PositionedStack(in, 68, 6);
            this.output = new PositionedStack(out, 68, 56);
            this.wire = new PositionedStack(wire, 5, 4);
        }

        @Override
        public PositionedStack getResult() {
            return output;
        }

        @Override
        public PositionedStack getOtherStack() {
            return wire;
        }

        @Override
        public PositionedStack getIngredient() {
            return input;
        }
    }

}
