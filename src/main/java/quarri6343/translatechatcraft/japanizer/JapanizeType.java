package quarri6343.translatechatcraft.japanizer;

/**
 * 日本語変換タイプ
 * @author ucchy
 * Modified by Quarri6343
 */
public enum JapanizeType {

    /** 日本語変換をしない */
    NONE("none"),

    /** カナ変換のみする */
    KANA("kana"),

    /** カナ変換後、GoogleIMEで漢字変換 */
    GOOGLE_IME("googleime");

    private String id;

    /**
     * コンストラクタ
     * @param id
     */
    JapanizeType(String id) {
        this.id = id;
    }

    /**
     * 文字列表記を返す
     * @see Enum#toString()
     */
    public String toString() {
        return id;
    }

    /**
     * 文字列表記からJapanizeTypeを作成して返す
     * @param id ID
     * @param def デフォルト
     * @return
     */
    public static JapanizeType fromID(String id, JapanizeType def) {
        if ( id == null ) return def;
        for ( JapanizeType type : values() ) {
            if ( type.id.equalsIgnoreCase(id) ) {
                return type;
            }
        }
        return def;
    }
}
