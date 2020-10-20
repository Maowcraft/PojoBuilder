package maow.pojobuilder.pojo;

import maow.pojobuilder.util.GsonUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PojoObject {
    private final Map<String, PojoField<?>> fields;

    private PojoObject(Map<String, PojoField<?>> fields) {
        this.fields = fields;
    }

    public Object getField(String key) {
        if (!fields.containsKey(key)) {
            throw new RuntimeException("PojoObject does not contain key " + key);
        }
        return this.fields.get(key).getValue();
    }

    public static PojoObject from(String path) {
        return GsonUtil.pojoFromJson(path);
    }

    public void to(String path) {
        GsonUtil.pojoToJson(this, path);
    }

    public Collection<String> getKeys() {
        return fields.keySet();
    }

    public static final class Builder {
        private final Map<String, PojoField<?>> fields = new HashMap<>();

        public <T> Builder addField(String key, T value) {
            fields.put(key, new PojoField<>(key, value));
            return this;
        }

        public PojoObject build() {
            return new PojoObject(fields);
        }
    }
}
