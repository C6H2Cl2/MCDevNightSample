package mcDevNight.example.item

import mcDevNight.example.MOD_ID
import mcDevNight.example.tabExampleMod
import net.minecraft.item.Item.ToolMaterial.*
import net.minecraft.item.ItemAxe
import net.minecraft.util.ResourceLocation

/**
 * @author C6H2Cl2
 */
class ItemExampleAxe: ItemAxe(IRON, 6.0f, -2.0f) {
    init {
        unlocalizedName = "exampleAxe"
        registryName = ResourceLocation(MOD_ID, "example_axe")
        creativeTab = tabExampleMod
    }
}