package quarri6343.translatechatcraft;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.argument.StringArgument;
import org.bukkit.Bukkit;
import quarri6343.translatechatcraft.japanizer.JapanizeType;
import quarri6343.translatechatcraft.japanizer.Japanizer;

import java.util.HashMap;

public class LanguageSwitchCommand extends Command {

    public static CurrentLang currentLang = CurrentLang.JA;

    public LanguageSwitchCommand() {
        super("lang");

        argument(new StringArgument("langName(ja/cn/en/fi)"), (string, ctx) -> {
            if (string.equals("ja")) {
                currentLang = CurrentLang.JA;
            } else if (string.equals("cn")) {
                currentLang = CurrentLang.CN;
            } else if (string.equals("en")) {
                currentLang = CurrentLang.EN;
            } else if (string.equals("fi")) {
                currentLang = CurrentLang.FI;
            }
            Bukkit.getOnlinePlayers().forEach(
                    player -> {
                        String name = Japanizer.japanize(player.getName(), JapanizeType.GOOGLE_IME, new HashMap<String, String>(), true).replaceAll("\\s", "");
                        player.getServer().dispatchCommand(player.getServer().getConsoleSender(), "alias setname " + player.getName() + " " + "\"" + name + "\"");
                    }
            );
        });
    }

    public enum CurrentLang {
        JA("", "&source=en&target=ja"),
        CN("&source=ja&target=zh", "&source=en&target=zh"),
        EN("&source=ja&target=en", ""),
        FI("&source=ja&target=fi", "&source=en&target=fi");

        public final String url;
        public final String nickUrl;

        CurrentLang(String url, String nickUrl) {
            this.url = url;
            this.nickUrl = nickUrl;
        }
    }
}
