package mcDevNight.example.item

import mcDevNight.example.MOD_ID
import mcDevNight.example.tabExampleMod
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Enchantments
import net.minecraft.init.Enchantments.*
import net.minecraft.item.Item
import net.minecraft.item.Item.ToolMaterial.*
import net.minecraft.item.ItemAxe
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class ItemExampleEnchantedAxe : ItemAxe(IRON, 6.0f, -2.0f) {
    init {
        unlocalizedName = "exampleEnchantedAxe"
        registryName = ResourceLocation(MOD_ID, "example_enchanted_axe")
        creativeTab = tabExampleMod
        hasSubtypes = true
    }

    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: MutableList<ItemStack>) {
        if (tab == tabExampleMod) {
            val itemStack = ItemStack(itemIn, 1, 0)
            itemStack.addEnchantment(MENDING, 1)
            itemStack.addEnchantment(Enchantments.EFFICIENCY, 2)
            subItems.add(itemStack)
        }
    }

    override fun onCreated(stack: ItemStack, worldIn: World?, playerIn: EntityPlayer?) {
        stack.addEnchantment(Enchantments.MENDING, 1)
        stack.addEnchantment(EFFICIENCY, 2)
    }

    fun getEnchanted(): ItemStack {
        val stack = ItemStack(this)
        stack.addEnchantment(MENDING, 1)
        stack.addEnchantment(EFFICIENCY, 2)
        return stack
    }
}