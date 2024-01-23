package org.example.validator.property;

import de.cronn.reflection.util.PropertyUtils;
import de.cronn.reflection.util.TypedPropertyGetter;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public interface RefinedPropertyGetter<T, V> extends TypedPropertyGetter<T, V>, Serializable {

    default SerializedLambda serialized() {
        try {
            Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default String getMethodName() {
        try {
            String className = serialized()
                    .getInstantiatedMethodType().substring(2, serialized().getInstantiatedMethodType().indexOf(";"))
                    .replaceAll("/", ".");

            Class<T> clazz =(Class<T>) Class.forName(className);
            return PropertyUtils.getMethod(clazz, this).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
