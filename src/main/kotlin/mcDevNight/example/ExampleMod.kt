@file:Suppress("UNUSED")
package mcDevNight.example

import mcDevNight.example.common.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.*
import net.minecraftforge.fml.common.ModMetadata
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * @author C6H2Cl2
 */

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES, useMetadata = true, acceptedMinecraftVersions = "1.10.2")
class ExampleMod {

    companion object {
        @JvmStatic
        @Metadata
        lateinit var META: ModMetadata

        @JvmStatic
        @SidedProxy(serverSide = "mcDevNight.example.common.CommonProxy", clientSide = "mcDevNight.example.client.ClientProxy")
        lateinit var PROXY: CommonProxy

        @JvmStatic
        @Instance
        lateinit var INSTANCE: ExampleMod
    }

    @EventHandler
    fun preinit(event: FMLPreInitializationEvent) {
        register()
    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {

    }

    @EventHandler
    fun postinit(event: FMLPostInitializationEvent) {

    }

    private fun loadMeta() {
        META.modId = MOD_ID
        META.name = MOD_NAME
        META.version = VERSION
        META.authorList.add("YourName")
        META.url = "http://minecraft.curseforge.com/projects/$MOD_ID"
        META.autogenerated = false
    }
}