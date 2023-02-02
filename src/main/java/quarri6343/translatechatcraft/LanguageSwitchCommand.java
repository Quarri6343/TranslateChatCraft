package quarri6343.translatechatcraft;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.argument.StringArgument;

public class LanguageSwitchCommand extends Command {
    
    public static CurrentLang currentLang = CurrentLang.JA;
    
    public LanguageSwitchCommand() {
        super("lang");
        
        argument(new StringArgument("langName(ja/cn/en/fi)"), (string, ctx) -> {
            if(string.equals("ja")){
                currentLang = CurrentLang.JA;
            }
            else if(string.equals("cn")){
                currentLang = CurrentLang.CN;
            }
            else if(string.equals("en")){
                currentLang = CurrentLang.EN;
            }
            else if(string.equals("fi")){
                currentLang = CurrentLang.FI;
            }
        });
    }
    
    public enum CurrentLang{
        JA(""),
        CN("&source=ja&target=zh"),
        EN("&source=ja&target=en"),
        FI("&source=ja&target=fi");
        
        public final String url;
        
        CurrentLang(String url){
            this.url = url;
        }
    }
}
