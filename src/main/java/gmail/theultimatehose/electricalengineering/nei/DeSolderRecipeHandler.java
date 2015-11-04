package gmail.theultimatehose.electricalengineering.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gmail.theultimatehose.electricalengineering.ElectricalEngineering;
import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.gui.GUISolder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class DeSolderRecipeHandler extends TemplateRecipeHandler {

    public DeSolderRecipeHandler() {
        RecipeInfo.setGuiOffset(this.getGuiClass(), 0, 0);
    }

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(14, 27, 15, 22), this.getName()));
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getName()) && getClass() == DeSolderRecipeHandler.class) {
            arecipes.add(new CachedDeSolder(new ItemStack(ElectricalEngineering.pcbLVBurnt), new ItemStack(ElectricalEngineering.pcbScrapLV)));
            arecipes.add(new CachedDeSolder(new ItemStack(ElectricalEngineering.pcbHVBurnt), new ItemStack(ElectricalEngineering.pcbScrapHV)));
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        Item item = result.getItem();
        if (item == ElectricalEngineering.pcbScrapLV) {
            arecipes.add(new CachedDeSolder(new ItemStack(ElectricalEngineering.pcbLVBurnt), new ItemStack(ElectricalEngineering.pcbScrapLV)));
        } else if (item == ElectricalEngineering.pcbScrapHV) {
            arecipes.add(new CachedDeSolder(new ItemStack(ElectricalEngineering.pcbHVBurnt), new ItemStack(ElectricalEngineering.pcbScrapHV)));
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        Item item = ingredient.getItem();
        if (item == ElectricalEngineering.pcbLVBurnt) {
            arecipes.add(new CachedDeSolder(new ItemStack(ElectricalEngineering.pcbLVBurnt), new ItemStack(ElectricalEngineering.pcbScrapLV)));
        } else if (item == ElectricalEngineering.pcbHVBurnt) {
            arecipes.add(new CachedDeSolder(new ItemStack(ElectricalEngineering.pcbHVBurnt), new ItemStack(ElectricalEngineering.pcbScrapHV)));
        }
    }

    @Override
    public String getGuiTexture() {
        return "electricalengineering:textures/gui/guiSolderingTable.png";
    }

    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(5, 23, 200, 0, 24, 22, 100, 1);
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GUISolder.class;
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
        return Util.MOD_ID_LOWER + ".desoldering";
    }

    @Override
    public String getRecipeName() {
        return "Desoldering";
    }

    public class CachedDeSolder extends CachedRecipe {

        public PositionedStack input;
        public PositionedStack output;

        public CachedDeSolder(ItemStack in, ItemStack out) {
            this.input = new PositionedStack(in, 6, 4);
            this.output = new PositionedStack(out, 6, 55);
        }

        @Override
        public PositionedStack getResult() {
            return null;
        }

        @Override
        public PositionedStack getIngredient() {
            return input;
        }

        @Override
        public PositionedStack getOtherStack() {
            return output;
        }
    }

}
