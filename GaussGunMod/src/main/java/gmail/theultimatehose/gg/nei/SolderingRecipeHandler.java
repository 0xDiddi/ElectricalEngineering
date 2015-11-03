package gmail.theultimatehose.gg.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gmail.theultimatehose.gg.Main;
import gmail.theultimatehose.gg.Util;
import gmail.theultimatehose.gg.gui.GUISolder;
import gmail.theultimatehose.gg.recipe.SolderRecipe;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SolderingRecipeHandler extends TemplateRecipeHandler {

    public SolderingRecipeHandler() {
        RecipeInfo.setGuiOffset(this.getGuiClass(), 0, 0);
    }

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(108, 15, 22, 15), this.getName()));
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getName()) && getClass() == SolderingRecipeHandler.class) {
            for (SolderRecipe recipe : SolderRecipe.recipes) {
                arecipes.add(new CachedSolder(recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (SolderRecipe recipe : SolderRecipe.recipes) {
            if (result.getItem() == recipe.getRecipeOutput().getItem()) {
                arecipes.add(new CachedSolder(recipe));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {

        for (SolderRecipe recipe : SolderRecipe.recipes) {
            CachedSolder cachedSolder = new CachedSolder(recipe);
            if (cachedSolder.contains(cachedSolder.ingredients, ingredient)) {
                arecipes.add(cachedSolder);
            }
        }

    }

    @Override
    public String getGuiTexture() {
        return "gg:textures/gui/guiSolderingTable.png";
    }

    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(101, 11, 177, 15, 23, 15, 200, 0);
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
        return Util.MOD_ID_LOWER + ".soldering";
    }

    @Override
    public String getRecipeName() {
        return "Soldering";
    }

    public class CachedSolder extends CachedRecipe {

        public ArrayList<PositionedStack> ingredients;
        public PositionedStack result;

        public CachedSolder(Object[] items, ItemStack out) {
            result = new PositionedStack(out, 132, 11);
            ingredients = new ArrayList<PositionedStack>();
            setIngredients(3, 3, items);
        }

        public CachedSolder(ShapedOreRecipe recipe) {
            this(recipe.getInput(), recipe.getRecipeOutput());
        }

        /**
         * @param width
         * @param height
         * @param items  an ItemStack[] or ItemStack[][]
         */
        public void setIngredients(int width, int height, Object[] items) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (items[y * width + x] == null)
                        continue;

                    PositionedStack stack = new PositionedStack(items[y * width + x], 44 + x * 18, 13 + y * 18, false);
                    stack.setMaxSize(1);
                    ingredients.add(stack);
                }
            }
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 20, ingredients);
        }

        public PositionedStack getResult() {
            return result;
        }

    }

}
