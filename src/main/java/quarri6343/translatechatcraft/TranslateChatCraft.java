package quarri6343.translatechatcraft;

import net.kunmc.lab.commandlib.CommandLib;
import org.bukkit.plugin.java.JavaPlugin;
import quarri6343.translatechatcraft.event.PlayerChatEvent;

public final class TranslateChatCraft extends JavaPlugin {

    private static TranslateChatCraft instance;

    public TranslateChatCraft() {
        instance = this;
    }

    public static TranslateChatCraft getInstance() {
        return instance;
    }
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        new PlayerChatEvent();
        CommandLib.register(this, new LanguageSwitchCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
