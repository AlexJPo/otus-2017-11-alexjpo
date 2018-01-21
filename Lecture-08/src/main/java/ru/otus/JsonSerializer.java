package ru.otus;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

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
                        Object value = field.get(someObject);
                        readArray(jsonObject, field, value);
                    } else if (Collection.class.isAssignableFrom(field.getType())) {
                        Object value = field.get(someObject);
                        readCollection(jsonObject, field, value);
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

    private void readCollection(JsonObjectBuilder jsonObject, Field field, Object value) throws IllegalAccessException {
        if (value != null) {
            ArrayList<Object> arr = (ArrayList)value;
            Type genericParameterTypes = field.getGenericType();
            ParameterizedType pType = (ParameterizedType) genericParameterTypes;
            Class cls = (Class)pType.getActualTypeArguments()[0];

            int size = arr.size();
            JsonArrayBuilder builder = Json.createArrayBuilder();

            for (int i = 0; i < size; i++) {
                if (cls.toString().contains("class java.lang")) {
                    createArrayObject(builder, arr.get(i).getClass().getTypeName(), arr.get(i));
                } else {
                    if (arr.get(i).getClass().isPrimitive() || cls.isPrimitive()) {
                        createArrayObject(builder, cls.toString(), arr.get(i));
                    } else {
                        JsonObjectBuilder classBuilder = Json.createObjectBuilder();
                        generateJsonTree(classBuilder, arr.get(i));
                        builder.add(classBuilder.build());
                    }
                }
            }
            arr.clear();
            jsonObject.add(field.getName(), builder.build());
        }
    }

    private void readArray(JsonObjectBuilder jsonObject, Field field, Object value) throws IllegalAccessException {
        if (value != null) {
            int size = Array.getLength(value);

            Class cls = field.getType().getComponentType();
            JsonArrayBuilder builder = Json.createArrayBuilder();

            for (int i = 0; i < size; i++) {
                if (cls.toString().contains("class java.lang")) {
                    createArrayObject(builder, Array.get(value, i).getClass().getTypeName(), Array.get(value, i));
                } else {
                    if (Array.get(value, i).getClass().isPrimitive() || cls.isPrimitive()) {
                        createArrayObject(builder, cls.toString(), Array.get(value, i));
                    } else {
                        JsonObjectBuilder classBuilder = Json.createObjectBuilder();
                        generateJsonTree(classBuilder, Array.get(value, i));
                        builder.add(classBuilder.build());
                    }
                }
            }
            jsonObject.add(field.getName(), builder.build());
        }
    }

    private void createArrayObject(JsonArrayBuilder builder, String arrayType, Object value) {
        switch (arrayType) {
            case "boolean":
            case "java.lang.Boolean":
            case "class java.lang.Boolean":
                builder.add((boolean)value);
                break;
            case "byte":
            case "java.lang.Byte":
            case "class java.lang.Byte":
                builder.add((byte) value);
                break;
            case "short":
            case "java.lang.Short":
            case "class java.lang.Short":
                builder.add((short) value);
                break;
            case "int":
            case "java.lang.Integer":
            case "class java.lang.Integer":
                builder.add((int) value);
                break;
            case "long":
            case "java.lang.Long":
            case "class java.lang.Long":
                builder.add((long) value);
                break;
            case "float":
            case "java.lang.Float":
            case "class java.lang.Float":
                builder.add((float) value);
                break;
            case "double":
            case "java.lang.Double":
            case "class java.lang.Double":
                builder.add((double) value);
                break;
            case "char":
            case "java.lang.Character":
            case "class java.lang.Character":
            case "class java.lang.String":
                builder.add(value.toString());
                break;
            default:
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
