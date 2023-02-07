package quarri6343.translatechatcraft.event;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import quarri6343.translatechatcraft.TranslateChatCraft;
import quarri6343.translatechatcraft.japanizer.JapanizeType;
import quarri6343.translatechatcraft.japanizer.Japanizer;

import java.util.HashMap;

public class PlayerChatEvent implements Listener {

    public PlayerChatEvent(){
        TranslateChatCraft.getInstance().getServer().getPluginManager().registerEvents(this, TranslateChatCraft.getInstance());
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent e){
        String message = new PlainComponentSerializer().serialize(e.message());

        if (message.isEmpty() || message.startsWith("/") || message.startsWith("#")) {
            return;
        }

        String japanizedMessage = Japanizer.japanize(message, JapanizeType.GOOGLE_IME, new HashMap<String, String>(), false);

        if(!japanizedMessage.equals("")){
            Component component = Component.text(japanizedMessage).hoverEvent(e.message());
            e.message(component);
        }
    }
}
