package maow.pojobuilder;

import com.sun.codemodel.*;
import maow.pojobuilder.pojo.PojoObject;
import maow.pojobuilder.util.StringUtil;

import java.io.File;

public class PojoWriter {
    private static final int PRIVATE_CONSTANT = JMod.PRIVATE | JMod.FINAL;

    public static void toSource(String packageName, String className, PojoObject object, String path) {
        try {
            JCodeModel codeModel = new JCodeModel();

            JPackage jp = codeModel._package(packageName);
            JDefinedClass jc = jp._class(className);
            JMethod constructor = jc.constructor(JMod.PUBLIC);

            for (String key : object.getKeys()) {
                Object value = object.getField(key);

                JFieldVar field = jc.field(PRIVATE_CONSTANT, value.getClass(), key);

                JVar parameter = constructor.param(field.type(), key);
                constructor.body().assign(JExpr._this().ref(field.name()), JExpr.ref(parameter.name()));

                JMethod getter = jc.method(JMod.PUBLIC, field.type(), "get" + StringUtil.methodizeKey(key));
                getter.body()._return(field);
            }

            codeModel.build(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
