package org.gsonformat.intellij.entity;

import org.gsonformat.intellij.config.Config;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by dim on 16/11/7.
 */
public enum DataType {
    /**
     *
     */
    Data_Type_Boolean("boolean"), Data_Type_Int("int"), Data_Type_Double("double"),
    Data_Type_long("long"), Data_Type_String("String"), Data_type_Object("Object"), Data_Type_Array("array"),
    Data_type_Date("Date"), Data_type_LocalDateTime("LocalDateTime"), Data_type_LocalDate("LocalDate"), Data_type_LocalTime("LocalTime"),
    ;
    private String value;

    DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DataType typeOfObject(Object value) {
        if (value == null) {
            return Data_type_Object;
        }
        DataType type = null;
        if (value instanceof Boolean) {
            type = Data_Type_Boolean;
        } else if (value instanceof Integer) {
            type = Data_Type_Int;
        } else if (value instanceof Double) {
            type = Data_Type_Double;
        } else if (value instanceof Long) {
            type = Data_Type_long;
        } else if (value instanceof String) {
            type = checkDateDataType((String) value);
            if (type == null) {
                type = Data_Type_String;
            }
        } else if (value instanceof JSONObject) {
            type = Data_type_Object;
        } else if (value instanceof JSONArray) {
            type = Data_Type_Array;
        } else {
            type = Data_type_Object;
        }
        return type;
    }

    public static DataType checkDateDataType(String value) {
        Config config = Config.getInstant();
        try {
            if (config.isUseJava8LocalDateTime()) {
                if (config.getDateTimeFormatter() != null) {
                    config.getDateTimeFormatter().parse(value);
                    return Data_type_LocalDateTime;
                }
                if (config.getDateFormatter() != null) {
                    config.getDateFormatter().parse(value);
                    return Data_type_LocalDate;
                }
                if (config.getTimeFormatter() != null) {
                    config.getTimeFormatter().parse(value);
                    return Data_type_LocalTime;
                }
            } else {
                if (config.getSimpleDateFormat() != null) {
                    config.getSimpleDateFormat().parse(value);
                    return Data_type_Date;
                }
            }
        } catch (Exception ignore) {
        }
        return null;

    }

    public static DataType typeOfString(String type) {
        if ("boolean".equals(type) || "Boolean".equals(type)) {
            return Data_Type_Boolean;
        }
        if ("Integer".equals(type) || "int".equals(type)) {
            return Data_Type_Int;
        }
        if ("long".equals(type) || "Long".equals(type)) {
            return Data_Type_long;
        }
        if ("String".equals(type) || "String".equals(type)) {
            return Data_Type_String;
        }
        if ("object".equals(type)) {
            return Data_type_Object;
        }
        if ("array".equals(type)) {
            return Data_Type_Array;
        }

        return null;
    }

    public static boolean isSameDataType(String text, String text2) {
        return isSameDataType(typeOfString(text), typeOfString(text2));
    }

    public static boolean isSameDataType(DataType dataType, DataType dataType1) {
        if (dataType == null || dataType1 == null) {
            return false;
        }
        return dataType == dataType1;
    }

    public static String getWrapperTypeSimpleName(DataType type) {
        switch (type) {
            case Data_Type_Boolean:
                return "Boolean";
            case Data_Type_Int:
                return "Integer";
            case Data_Type_Double:
                return "Double";
            case Data_Type_long:
                return "Long";
            default:
                return type.getValue();
        }
    }

}
