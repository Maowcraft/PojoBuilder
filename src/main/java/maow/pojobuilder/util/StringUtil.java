package maow.pojobuilder.util;

public class StringUtil {
    public static String methodizeKey(String key) {
        StringBuilder builder = new StringBuilder();
        String[] separateWords = key.split("_+");
        for (String word : separateWords) {
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            builder.append(word);
        }
        return builder.toString();
    }
}
