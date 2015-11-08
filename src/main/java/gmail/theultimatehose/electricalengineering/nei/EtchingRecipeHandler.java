package gmail.theultimatehose.electricalengineering.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.gui.GUIMAD;
import gmail.theultimatehose.electricalengineering.item.ItemManager;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class EtchingRecipeHandler extends TemplateRecipeHandler {

    public EtchingRecipeHandler() {
        RecipeInfo.setGuiOffset(this.getGuiClass(), 0, 0);
    }

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(83, 25, 16, 24), this.getName()));
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getName()) && getClass() == EtchingRecipeHandler.class) {
            arecipes.add(new CachedEtching(new ItemStack(ItemManager.pcbUnetched), new ItemStack(ItemManager.pcbEtched), new ItemStack(ItemManager.bucketAcid)));
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        if (result.getItem() == ItemManager.pcbEtched) {
            arecipes.add(new CachedEtching(new ItemStack(ItemManager.pcbUnetched), new ItemStack(ItemManager.pcbEtched), new ItemStack(ItemManager.bucketAcid)));
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient.getItem() == ItemManager.bucketAcid || ingredient.getItem() == ItemManager.pcbUnetched) {
            arecipes.add(new CachedEtching(new ItemStack(ItemManager.pcbUnetched), new ItemStack(ItemManager.pcbEtched), new ItemStack(ItemManager.bucketAcid)));
        }
    }

    @Override
    public String getGuiTexture() {
        return "electricalengineering:textures/gui/guiMAD.png";
    }

    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(76, 21, 196, 0, 16, 23, 1000, 1);
        drawProgressBar(144, 39, 240, 0, 16, 13, 1000, 7);
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GUIMAD.class;
    }

    @Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GuiDraw.changeTexture(this.getGuiTexture());
        GuiDraw.drawTexturedModalRect(-1, 0, 6, 4, 166, 77);
    }


    @Override
    public int recipiesPerPage() {
        return 1;
    }


    public String getName() {
        return Util.MOD_ID_LOWER + ".etching";
    }

    @Override
    public String getRecipeName() {
        return "Etching";
    }

    public class CachedEtching extends CachedRecipe {

        public PositionedStack input;
        public PositionedStack output;
        public PositionedStack acid;

        public CachedEtching(ItemStack in, ItemStack out, ItemStack ac) {
            this.input = new PositionedStack(in, 77, 1);
            this.output = new PositionedStack(out, 77, 51);
            this.acid = new PositionedStack(ac, 125, 2);
        }

        @Override
        public PositionedStack getResult() {
            return output;
        }

        @Override
        public PositionedStack getIngredient() {
            return input;
        }

        @Override
        public PositionedStack getOtherStack() {
            return acid;
        }
    }

}
