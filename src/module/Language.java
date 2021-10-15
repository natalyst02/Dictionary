package module;

import java.util.Map;
import java.util.TreeMap;

public class Language {

    private static Language language;
    public Map<String, String> ListMapLanguage = new TreeMap<>();

    private Language() {

    }

    public static Language getLanguage() {
        if (language == null) {
            language = new Language();
            language.init();
        }
        return language;
    }

    private void init() {
        ListMapLanguage.put("af", "AFRIKAANS");
        ListMapLanguage.put("sq", "ALBANIAN");
        ListMapLanguage.put("ar", "ARABIC");
        ListMapLanguage.put("hy", "ARMENIAN");
        ListMapLanguage.put("az", "AZERBAIJANI");
        ListMapLanguage.put("eu", "BASQUE");
        ListMapLanguage.put("be", "BELARUSIAN");
        ListMapLanguage.put("bn", "BENGALI");
        ListMapLanguage.put("bg", "BULGARIAN");
        ListMapLanguage.put("ca", "CATALAN");
        ListMapLanguage.put("zh-CN", "CHINESE");
        ListMapLanguage.put("hr", "CROATIAN");
        ListMapLanguage.put("cs", "CZECH");
        ListMapLanguage.put("da", "DANISH");
        ListMapLanguage.put("nl", "DUTCH");
        ListMapLanguage.put("en", "ENGLISH");
        ListMapLanguage.put("et", "ESTONIAN");
        ListMapLanguage.put("tl", "FILIPINO");
        ListMapLanguage.put("fi", "FINNISH");
        ListMapLanguage.put("fr", "FRENCH");
        ListMapLanguage.put("gl", "GALICIAN");
        ListMapLanguage.put("ka", "GEORGIAN");
        ListMapLanguage.put("de", "GERMAN");
        ListMapLanguage.put("el", "GREEK");
        ListMapLanguage.put("gu", "GUJARATI");
        ListMapLanguage.put("ht", "HAITIAN_CREOLE");
        ListMapLanguage.put("iw", "HEBREW");
        ListMapLanguage.put("hi", "HINDI");
        ListMapLanguage.put("hu", "HUNGARIAN");
        ListMapLanguage.put("is", "ICELANDIC");
        ListMapLanguage.put("id", "INDONESIAN");
        ListMapLanguage.put("ga", "IRISH");
        ListMapLanguage.put("it", "ITALIAN");
        ListMapLanguage.put("ja", "JAPANESE");
        ListMapLanguage.put("kn", "KANNADA");
        ListMapLanguage.put("ko", "KOREAN");
        ListMapLanguage.put("la", "LATIN");
        ListMapLanguage.put("lv", "LATVIAN");
        ListMapLanguage.put("lt", "LITHUANIAN");
        ListMapLanguage.put("mk", "MACEDONIAN");
        ListMapLanguage.put("ms", "MALAY");
        ListMapLanguage.put("mt", "MALTESE");
        ListMapLanguage.put("no", "NORWEGIAN");
        ListMapLanguage.put("fa", "PERSIAN");
        ListMapLanguage.put("pl", "POLISH");
        ListMapLanguage.put("pt", "PORTUGUESE");
        ListMapLanguage.put("ro", "ROMANIAN");
        ListMapLanguage.put("ru", "RUSSIAN");
        ListMapLanguage.put("sr", "SERBIAN");
        ListMapLanguage.put("sk", "SLOVAK");
        ListMapLanguage.put("sl", "SLOVENIAN");
        ListMapLanguage.put("es", "SPANISH");
        ListMapLanguage.put("sw", "SWAHILI");
        ListMapLanguage.put("sv", "SWEDISH");
        ListMapLanguage.put("ta", "TAMIL");
        ListMapLanguage.put("te", "TELUGU");
        ListMapLanguage.put("th", "THAI");
        ListMapLanguage.put("tr", "TURKISH");
        ListMapLanguage.put("uk", "UKRAINIAN");
        ListMapLanguage.put("ur", "URDU");
        ListMapLanguage.put("vi", "VIETNAMESE");
        ListMapLanguage.put("cy", "WELSH");
        ListMapLanguage.put("yi", "YIDDISH");
    }
/*
https://www.it-swarm-vi.com/vi/java/map.entry-lam-nao-de-su-dung-no/940833969/
https://qastack.vn/programming/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
 */
    public String getWantLanguage(String targetLanguage) {
        for (Map.Entry<String, String> entry : ListMapLanguage.entrySet()) {
            if (entry.getValue().equals(targetLanguage)) {
                return entry.getKey();
            }
        }
        return null;
    }
}