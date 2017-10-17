package mcDevNight.example

import mcDevNight.example.item.ItemExampleAxe
import mcDevNight.example.item.ItemExampleEnchantedAxe
import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side.*
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * @author C6H2Cl2
 */
object ExampleModRegistry {
    // Items ======================================================================================
    val exampleAxe = ItemExampleAxe()
    val enchantedAxe = ItemExampleEnchantedAxe()

    // Blocks =====================================================================================


    // Registers ==================================================================================

    fun preinit(event: FMLPreInitializationEvent) {
        register(exampleAxe)
        register(enchantedAxe)

        if (event.side.isClient) {
            setTexture(exampleAxe)
            setTexture(enchantedAxe)
        }
    }

    fun init(event: FMLInitializationEvent) {

    }

    //この部分のコードは、ExampleModConstに記述してあるものと同一ですが、あえて重複して書いています。
    //詳細は講義中に話します。
    //まぁ要するに、複数パターンあるから好きな方を選んでね、ってだけです

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
}