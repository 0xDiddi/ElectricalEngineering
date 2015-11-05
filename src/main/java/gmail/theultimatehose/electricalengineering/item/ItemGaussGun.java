package gmail.theultimatehose.electricalengineering.item;

import cofh.api.energy.ItemEnergyContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gmail.theultimatehose.electricalengineering.ElectricalEngineering;
import gmail.theultimatehose.electricalengineering.config.values.CfgFloatValues;
import gmail.theultimatehose.electricalengineering.entity.EntityBolt;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import java.util.List;

public class ItemGaussGun extends ItemEnergyContainer {

    private float damageMultiplier = 0.256f;
    private int coilWindings = 2048;

    public ItemGaussGun() {
        super(5000000, 10000);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        ItemStack stackFull = new ItemStack(this);
        this.setEnergy(stackFull, this.getMaxEnergyStored(stackFull));
        list.add(stackFull);

        ItemStack stackEmpty = new ItemStack(this);
        this.setEnergy(stackEmpty, 0);
        list.add(stackEmpty);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        this.writeDefValuesToNBT(stack);
        this.setEnergy(stack, 0);
    }

    @Override
    public EnumRarity getRarity(ItemStack p_77613_1_) {
        return EnumRarity.epic;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int itemInUseCount) {
        int charge = this.getMaxItemUseDuration(stack) - itemInUseCount;

        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, charge);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return;
        }
        charge = event.charge;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        int energyStored = getEnergyStored(stack);
        int energyUsed = charge * 2560;

        if (energyUsed > energyStored) {
            energyUsed = energyStored;
            charge = energyUsed / 2560;
        }

        if (charge == 0) {
            return;
        }

        if (flag || player.inventory.hasItem(ElectricalEngineering.bolt)) {
            float f = charge/12;

            this.setEnergy(stack, energyStored - energyUsed);

            EntityBolt entityBolt = new EntityBolt(world, player, f);

            if (!stack.hasTagCompound()) {
                stack.stackTagCompound = new NBTTagCompound();
                writeDefValuesToNBT(stack);
            }

            NBTTagCompound compound = stack.getTagCompound();
            float cfg = CfgFloatValues.GAUSS_FUN_DAMAGE.getValue();
            double multiplier = compound.getDouble("multiplier") * cfg;

            entityBolt.setDamage(Math.min(1000, multiplier * charge) / 2);

            stack.damageItem(1, player);
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag) {
                entityBolt.canBePickedUp = 2;
            } else {
                player.inventory.consumeInventoryItem(ElectricalEngineering.bolt);
            }

            if (!world.isRemote) {
                world.spawnEntityInWorld(entityBolt);
            }
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 7200;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        ArrowNockEvent event = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return event.result;
        }

        if (player.capabilities.isCreativeMode || player.inventory.hasItem(ElectricalEngineering.bolt)) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return stack.getItemDamage() < 2049;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        double energyDif = getMaxEnergyStored(stack) - getEnergyStored(stack);
        double maxEnergy = getMaxEnergyStored(stack);
        return energyDif / maxEnergy;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        if (GuiScreen.isShiftKeyDown()) {
            if (!stack.hasTagCompound()) {
                stack.stackTagCompound = new NBTTagCompound();
                writeDefValuesToNBT(stack);
            }
            NBTTagCompound compound = stack.getTagCompound();
            float cfg = CfgFloatValues.GAUSS_FUN_DAMAGE.getValue();
            float multiplier = compound.getFloat("multiplier") * cfg;
            float dmg = (multiplier * 200.0f);
            String info = "";
            if (dmg >= 40) {
                info += EnumChatFormatting.DARK_RED;
                info += dmg;
            } else if (dmg >= 20) {
                info += EnumChatFormatting.YELLOW;
                info += dmg;
            } else if (dmg >= 10) {
                info += EnumChatFormatting.GREEN;
                info += dmg;
            } else {
                info += dmg;
            }

            list.add("Damage after 10s: " + info);

            int windings = compound.getInteger("windings");
            info = "";
            if (windings > 128) {
                info += EnumChatFormatting.OBFUSCATED;
                info += windings;
                info += EnumChatFormatting.DARK_GRAY;
                info += " (Too many to count...)";
            } else {
                info += windings;
            }

            list.add("Coil windings: " + info);

            int energy = compound.getInteger("Energy");

            list.add("Power: " + energy + "/" + "5000000 RF");
        } else {
            list.add(EnumChatFormatting.GRAY + "Press SHIFT for more info");
        }
    }

    private void writeDefValuesToNBT(ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        compound.setInteger("windings", coilWindings);
        compound.setFloat("multiplier", damageMultiplier);
        compound.setInteger("Energy", 0);
        stack.setTagCompound(compound);
    }

    public void setEnergy(ItemStack stack, int energy) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            writeDefValuesToNBT(stack);
        }
        compound = stack.getTagCompound();
        compound.setInteger("Energy", energy);
        stack.setTagCompound(compound);
    }

}
