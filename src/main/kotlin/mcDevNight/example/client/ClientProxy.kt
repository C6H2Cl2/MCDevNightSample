package mcDevNight.example.client

import mcDevNight.example.common.CommonProxy
import net.minecraft.client.Minecraft

/**
 * @author C6H2Cl2
 */
class ClientProxy: CommonProxy() {
    override fun getDir() = Minecraft.getMinecraft().mcDataDir!!
}