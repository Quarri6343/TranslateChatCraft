package quarri6343.translatechatcraft.japanizer;
/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2020
 */

import com.google.common.io.CharStreams;
import quarri6343.translatechatcraft.LanguageSwitchCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * ひらがなのみの文章を、IMEを使用して変換します。
 * 使用される変換候補は全て第1候補のため、正しくない結果が含まれることもよくあります。
 * @author ucchy
 */
public class IMEConverter {


    /**
     * URLはここから -> https://qiita.com/satto_sann/items/be4177360a0bc3691fdf
     */
    private static final String GOOGLE_IME_URL_1 =
            "https://script.google.com/macros/s/AKfycbzZtvOvf14TaMdRIYzocRcf3mktzGgXvlFvyczo/exec?text=";

    /**
     * GoogleIMEを使って変換する
     * @param org 変換元
     * @return 変換後
     */
    public static String convByGoogleIME(String org) {
        return conv(org);
    }

    // 変換の実行
    private static String conv(String org) {

        if ( org.length() == 0 ) {
            return "";
        }
        
        if(LanguageSwitchCommand.currentLang == LanguageSwitchCommand.CurrentLang.JA)
            return org;

        HttpURLConnection urlconn = null;
        BufferedReader reader = null;
        try {
            String baseurl;
            String encode;
            baseurl = GOOGLE_IME_URL_1 + URLEncoder.encode(org , "UTF-8") + LanguageSwitchCommand.currentLang.url;
            encode = "UTF-8";
            URL url = new URL(baseurl);

            urlconn = (HttpURLConnection)url.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(true);
            urlconn.connect();

            reader = new BufferedReader(
                    new InputStreamReader(urlconn.getInputStream(), encode));

            String json = CharStreams.toString(reader);
            String parsed = GoogleIME.parseJson(json);
//            if ( !Utility.isCB19orLater() ) {
//                parsed = YukiKanaConverter.fixBrackets(parsed);
//            }

            return parsed;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( urlconn != null ) {
                urlconn.disconnect();
            }
            if ( reader != null ) {
                try {
                    reader.close();
                } catch (IOException e) { // do nothing.
                }
            }
        }

        return "";
    }

    // デバッグ用エントリ
    public static void main(String[] args) {
        String testee = "sonnnakotohanak(ry)";
        System.out.println("original : " + testee);
        System.out.println("kana : " + YukiKanaConverter.conv(testee));
        System.out.println("GoogleIME : " + convByGoogleIME(YukiKanaConverter.conv(testee)));
    }
}