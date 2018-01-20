package ru.otus;

import org.json.simple.JSONObject;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JsonSerializer {
    private JsonObjectBuilder jsonObject = Json.createObjectBuilder();

    public void objectToJson(Object someObject) {
        try {
            generateJsonTree(jsonObject, someObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateJsonTree(JsonObjectBuilder jsonObject, Object someObject) throws IllegalAccessException {
        if (someObject == null ) {
            jsonObject.addNull("");
        } else {
            Field[] fields = someObject.getClass().getDeclaredFields();

            if (fields.length  > 0) {
                for (Field field : fields) {
                    field.setAccessible(true);

                    if (field.getType().isPrimitive()) {
                        Object value = field.get(someObject);
                        createPrimitiveObject(field, value);
                    } else if (field.getType().getName().equals("java.lang.String")) {
                        Object value = field.get(someObject);
                        if (value != null) {
                            jsonObject.add(field.getName(), value.toString());
                        }
                    } else if (field.getType().isArray()) {
                        Class cls = field.getType().getComponentType();
                        Object value = field.get(someObject);

                        if (value != null) {
                            int size = Array.getLength(value);
                            Object[] arr = new Object[Array.getLength(value)];
                            JsonArrayBuilder builder = Json.createArrayBuilder();

                            for (int i = 0; i < size; i++) {
                                arr[i] = Array.get(value, i);
                                createArrayObject(builder, cls.toString(), Array.get(value, i));

                                //if (arr[i].getClass().isPrimitive()) {
                                    //createArrayObject(builder, cls.toString(), Array.get(value, i));
                                //} else {
                                    /*if (arr[i] != null) {
                                        JsonObjectBuilder classBuilder = Json.createObjectBuilder();
                                        generateJsonTree(classBuilder, arr[i]);
                                        jsonObject.add(field.getName(), classBuilder.build());
                                    }
                                //}*/
                            }
                            jsonObject.add(field.getName(), builder.build());
                        }
                    } else {
                        Object myClass = field.get(someObject);
                        if (myClass != null) {
                            JsonObjectBuilder classBuilder = Json.createObjectBuilder();
                            generateJsonTree(classBuilder, myClass);
                            jsonObject.add(field.getName(), classBuilder.build());
                        }
                    }

                }
            }
        }
    }

    private void createArrayObject(JsonArrayBuilder builder, String arrayType, Object value) {
        switch (arrayType) {
            case "boolean":
            case "class java.lang.Boolean":
                builder.add((boolean)value);
                break;
            case "byte":
            case "class java.lang.Byte":
                builder.add((byte) value);
                break;
            case "short":
            case "class java.lang.Short":
                builder.add((short) value);
                break;
            case "char":
            case "class java.lang.Character":
                builder.add((char) value);
                break;
            case "int":
            case "class java.lang.Integer":
                builder.add((int) value);
                break;
            case "long":
            case "class java.lang.Long":
                builder.add((long) value);
                break;
            case "float":
            case "class java.lang.Float":
                builder.add((float) value);
                break;
            case "double":
            case "class java.lang.Double":
                builder.add((double) value);
                break;
            case "class java.lang.String":
                builder.add(value.toString());
                break;
        }
    }

    private void createPrimitiveObject(Field field, Object value) {
        switch (field.getType().getName()) {
            case "boolean":
                jsonObject.add(field.getName(), (boolean) value);
                break;
            case "byte":
                jsonObject.add(field.getName(), (byte) value);
                break;
            case "short":
                jsonObject.add(field.getName(), (short) value);
                break;
            case "char":
                jsonObject.add(field.getName(), (char) value);
                break;
            case "int":
                jsonObject.add(field.getName(), (int) value);
                break;
            case "long":
                jsonObject.add(field.getName(), (long) value);
                break;
            case "float":
                jsonObject.add(field.getName(), (float) value);
                break;
            case "double":
                jsonObject.add(field.getName(), (double) value);
                break;
        }
    }

    public String getJsonString() {
        return jsonObject.build().toString();
    }

}
