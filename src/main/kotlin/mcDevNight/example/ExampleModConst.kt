package mcDevNight.example

import c6h2cl2.YukariLib.Util.BlockUtil
import c6h2cl2.YukariLib.Util.ItemUtil
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side.*
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * @author C6H2Cl2
 */

const val MOD_ID = "example_mod"
const val MOD_NAME = "ExampleMod"
const val VERSION = "1.0.0"
const val DEPENDENCIES = "required-after:yukarilib"

val tabExampleMod = object : CreativeTabs("exampleMod") {
    override fun getTabIconItem(): Item {
        return Items.APPLE
    }
}

//Item  ===========================================================================================
val exampleItem = ItemUtil.CreateItem("exampleItem", "example_item", MOD_ID, tabExampleMod)

//Block ===========================================================================================
val exampleBlock = BlockUtil.CreateBlock("exampleBlock", "example_block", MOD_ID, Material.ROCK, 20f, 20f).setCreativeTab(tabExampleMod)



//Register
fun register() {
    register(exampleItem)
    register(exampleBlock.initItemBlock())
    register(exampleBlock)
}

fun texture() {
    setTexture(exampleItem)
    setTexture(exampleBlock)
}

private fun register(item: Item) {
    GameRegistry.register(item)
}

private fun register(block: Block) {
    GameRegistry.register(block)
}

private fun Block.getItemBlock(): Item {
    return Item.getItemFromBlock(this)!!
}

private fun Block.initItemBlock(registryName: ResourceLocation = this.registryName!!): Item {
    val name = if (!registryName.resourcePath.startsWith("block")) {
        ResourceLocation(registryName.resourceDomain, "block/${registryName.resourcePath}")
    } else {
        registryName
    }
    return ItemBlock(this).setRegistryName(name)
}

@SideOnly(CLIENT)
private fun getModelResourceLocation(item: Item): ModelResourceLocation {
    return ModelResourceLocation(item.registryName!!, "inventory")
}

@SideOnly(CLIENT)
private fun getModelResourceLocation(block: Block): ModelResourceLocation {
    return getModelResourceLocation(block.getItemBlock())
}
@SideOnly(CLIENT)
private fun setTexture(item: Item) {
    ModelLoader.setCustomModelResourceLocation(item, 0, getModelResourceLocation(item))
}

@SideOnly(CLIENT)
private fun setTexture(block: Block) {
    setTexture(block.getItemBlock())
}

@SideOnly(CLIENT)
private fun setTexture(item: Item, range: IntRange, otherTexture: Boolean, customFolder: String = "") {
    if (otherTexture) {
        range.forEach {
            ModelLoader.setCustomModelResourceLocation(item, it, ModelResourceLocation(ResourceLocation("${item.registryName?.resourceDomain}", "$customFolder/${item.registryName?.resourcePath}_$it"), "inventory"))
        }
    } else {
        range.forEach {
            ModelLoader.setCustomModelResourceLocation(item, it, getModelResourceLocation(item))
        }
    }
}