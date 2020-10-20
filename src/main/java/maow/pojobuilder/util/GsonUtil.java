package maow.pojobuilder.util;

import com.google.gson.*;
import maow.pojobuilder.pojo.PojoObject;

import java.io.*;
import java.util.HashMap;

public class GsonUtil {
    private static final JsonSerializer<PojoObject> POJO_OBJECT_JSON_SERIALIZER = (src, typeOfSrc, context) -> {
        JsonObject object = new JsonObject();
        for (String key : src.getKeys()) {
            object.add(key, new Gson().toJsonTree(src.getField(key)));
        }
        return object;
    };

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(PojoObject.class, POJO_OBJECT_JSON_SERIALIZER)
            .create();

    @SuppressWarnings("unchecked")
    public static PojoObject pojoFromJson(String path) {
        try {
            PojoObject.Builder builder = new PojoObject.Builder();
            HashMap<String, Object> map = GSON.fromJson(new FileReader(path), HashMap.class);
            for (String key : map.keySet()) {
                builder.addField(key, map.get(key));
            }
            return builder.build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void pojoToJson(PojoObject pojo, String path) {
        try {
            FileWriter writer = new FileWriter(new File(path));
            GSON.toJson(pojo, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
