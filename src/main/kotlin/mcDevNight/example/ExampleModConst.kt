package mcDevNight.example

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side.*
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * @author C6H2Cl2
 */

typealias MCItem = net.minecraft.init.Items
typealias MCBlock = net.minecraft.init.Blocks
typealias Registry = ExampleModRegistry

const val MOD_ID = "examplemod"
const val MOD_NAME = "ExampleModCore"
const val VERSION = "1.0.0"
const val DEPENDENCIES = "required:forgelin"

val tabExampleMod = object : CreativeTabs("exampleMod") {
    override fun getTabIconItem(): Item {
        return MCItem.APPLE
    }
}

//Item  ===========================================================================================
val exampleItem = createItem("exampleItem", "example_item")

//Block ===========================================================================================
val exampleBlock = createBlock("exampleBlock", "example_block", Material.ROCK, 20f, 20f).setCreativeTab(tabExampleMod)



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

fun recipe() {
    addRecipe(ItemStack(exampleItem), "DDD", "EEE", "GGG", 'D', MCBlock.DIAMOND_BLOCK, 'E', MCBlock.EMERALD_BLOCK, 'G', MCBlock.GOLD_BLOCK)
    addRecipe(ItemStack(exampleBlock), "EAE", "AEA", "EAE", 'E', exampleItem, 'A', MCItem.APPLE)
}

private fun register(item: Item) {
    GameRegistry.register(item)
}

private fun register(block: Block) {
    GameRegistry.register(block)
}

// 以下、コピペ =========================================================================================

private fun Block.getItemBlock(): Item {
    return Item.getItemFromBlock(this)!!
}

private fun addRecipe(output: ItemStack, vararg components: Any) {
    GameRegistry.addRecipe(output, *components)
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

private fun createItem(name: String, textureName: String = name, stackSize: Int = 64, /*hasSubType : Boolean = false,
                       maxMeta : Int = 0,*/isFull3D: Boolean = false, containerItem: Item? = null): Item {
    val item = Item()
    item.unlocalizedName = name
    item.registryName = ResourceLocation(MOD_ID, textureName)
    item.creativeTab = tabExampleMod
    item.setMaxStackSize(stackSize)
    if (isFull3D) item.setFull3D()
    if (containerItem != null) item.containerItem = containerItem
    return item
}

private fun createBlock(name: String, textureName: String = name, material: Material, hardness: Float = 1.5f, resistance: Float = 10f): Block {
    val block = Block(material)
    block.unlocalizedName = name
    block.registryName = ResourceLocation(MOD_ID, textureName)
    block.setHardness(hardness)
    block.setResistance(resistance)
    return block
}