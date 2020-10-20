import maow.pojobuilder.PojoWriter;
import maow.pojobuilder.pojo.PojoObject;

public class Test {
    public static void main(String[] args) {
//        PojoObject object = new PojoObject.Builder().addField("field", "test").addField("array", new String[]{"test1","test2"}).build();
//        object.to("json.json");
//
//        PojoObject object2 = PojoObject.from("json.json");
//        System.out.println(object2.getField("field"));
//        System.out.println(object2.getField("array"));
//
//        PojoWriter.toSource("maow.testpojo", "TestPojo", object, "./");

        PojoObject object = PojoObject.from("test.json");
        for (String key : object.getKeys()) {
            System.out.println("Key: " + key + "\nData: " + object.getField(key) + "\n");
        }
        PojoWriter.toSource("example.test", "TestJson", object, "./");
    }
}
