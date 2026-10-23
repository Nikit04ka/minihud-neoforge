package team.cagayakegirls.bocchud;

import fi.dy.masa.malilib.network.IPluginClientPlayHandler;
import fi.dy.masa.minihud.MiniHUD;
import fi.dy.masa.minihud.compat.modmenu.ModMenuImpl;
import fi.dy.masa.minihud.network.ServuxEntitiesHandler;
import fi.dy.masa.minihud.network.ServuxEntitiesPacket;
import fi.dy.masa.minihud.network.ServuxHudHandler;
import fi.dy.masa.minihud.network.ServuxHudPacket;
import fi.dy.masa.minihud.network.ServuxStructuresHandler;
import fi.dy.masa.minihud.network.ServuxStructuresPacket;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = BoccHUD.MOD_ID, dist = Dist.CLIENT)
public class BoccHUD {
    public static final String MOD_ID = "bocchud";

    public BoccHUD(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, new ModMenuImpl().getModConfigScreenFactory());
        // NeoForge closes payload registration before MiniHUD's onGameInit(), so the Servux payloads
        // are registered here instead (the onGameInit() calls are commented out)
        ServuxStructuresHandler.<ServuxStructuresPacket.Payload>getInstance().registerPlayPayload(ServuxStructuresPacket.Payload.ID, ServuxStructuresPacket.Payload.CODEC, IPluginClientPlayHandler.BOTH_CLIENT);
        ServuxHudHandler.<ServuxHudPacket.Payload>getInstance().registerPlayPayload(ServuxHudPacket.Payload.ID, ServuxHudPacket.Payload.CODEC, IPluginClientPlayHandler.BOTH_CLIENT);
        ServuxEntitiesHandler.<ServuxEntitiesPacket.Payload>getInstance().registerPlayPayload(ServuxEntitiesPacket.Payload.ID, ServuxEntitiesPacket.Payload.CODEC, IPluginClientPlayHandler.BOTH_CLIENT);
        new MiniHUD().onInitialize();
    }
}
