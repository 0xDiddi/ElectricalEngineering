package theultimatehose.electricalengineering.item;

import cofh.api.energy.ItemEnergyContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import theultimatehose.electricalengineering.config.values.CfgFloatValues;
import theultimatehose.electricalengineering.entity.EntityBolt;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
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

import java.util.List;

public class ItemGaussGun extends ItemEnergyContainer {

    private float damageMultiplier = 0.768f;
    public static int maxCharge;
    private float cfgMultiplier;
    private int maxCoilWindings = 2048;

    public ItemGaussGun() {
        super(5000000, 10000);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.cfgMultiplier = CfgFloatValues.GAUSS_GUN_DAMAGE.getValue();
        this.maxCharge = (int) (5000000 / (damageMultiplier * 1000));
    }

    @Override
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
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par3, boolean par4) {
        if (!stack.hasTagCompound()) {
            writeDefValuesToNBT(stack);
        }

        NBTTagCompound compound = stack.getTagCompound();
        boolean charging = compound.getBoolean("charging");
        int chargeTicks = compound.getInteger("chargeTicks");

        if (charging && chargeTicks < maxCharge) {
            chargeTicks++;
            compound.setInteger("chargeTicks", chargeTicks);
            stack.setTagCompound(compound);
        }
    }


    public void fire(ItemStack stack, World world, EntityPlayer player, int charge) {

        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, charge);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return;
        }
        charge = event.charge;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        int energyStored = getEnergyStored(stack);
        int energyUsed = (int) (charge * (damageMultiplier*1000));

        if (energyUsed > energyStored) {
            energyUsed = energyStored;
            charge = (int) (energyUsed / (damageMultiplier * 1000));
        }

        if (charge == 0) {
            return;
        }

        if (flag || player.inventory.hasItem(ItemManager.bolt)) {
            float f = charge/12;

            this.setEnergy(stack, energyStored - energyUsed);

            EntityBolt entityBolt = new EntityBolt(world, player, f);

            if (!stack.hasTagCompound()) {
                writeDefValuesToNBT(stack);
            }

            NBTTagCompound compound = stack.getTagCompound();
            double multiplier = compound.getDouble("multiplier") * this.cfgMultiplier;

            entityBolt.setDamage(Math.min(1000, (multiplier * charge) / 5 / 2));

            stack.damageItem(1, player);
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag) {
                entityBolt.canBePickedUp = 2;
            } else {
                player.inventory.consumeInventoryItem(ItemManager.bolt);
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
        return EnumAction.none;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            writeDefValuesToNBT(stack);
        }

        NBTTagCompound compound = stack.getTagCompound();
        boolean charging = compound.getBoolean("charging");

        if (charging) {
            int chargeTicks = compound.getInteger("chargeTicks");
            this.fire(stack, world, player, chargeTicks);
            compound.setInteger("chargeTicks", 0);
        }

        compound.setBoolean("charging", !charging);
        stack.setTagCompound(compound);

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
            float multiplier = compound.getFloat("multiplier") * this.cfgMultiplier;
            float dmg = Math.round((multiplier * maxCharge) / 5);
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
                info += EnumChatFormatting.AQUA;
                info += dmg;
            }

            list.add("Damage when fully charged: " + info);

            int windings = compound.getInteger("windings");
            info = "";
            if (windings > 512) {
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

            int charge = compound.getInteger("chargeTicks");

            list.add("Charge: " + charge + "/" + maxCharge);
        } else {
            list.add(EnumChatFormatting.GRAY + "Press SHIFT for more info");
        }
    }

    private void writeDefValuesToNBT(ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        compound.setInteger("windings", maxCoilWindings);
        compound.setFloat("multiplier", damageMultiplier);
        compound.setInteger("Energy", 0);
        compound.setBoolean("charging", false);
        compound.setInteger("chargeTicks", 0);
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
