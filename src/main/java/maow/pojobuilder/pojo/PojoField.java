package maow.pojobuilder.pojo;

public class PojoField<T> {
    private final String key;
    private final T value;

    public PojoField(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
