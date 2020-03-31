package org.gsonformat.intellij.config;

import com.intellij.ide.util.PropertiesComponent;
import org.gsonformat.intellij.common.StringUtils;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Created by dim on 15/5/31.
 */
public class Config {

    private static Config config;

    private boolean fieldPrivateMode = true;
    private boolean generateComments = true;
    private boolean useSerializedName = false;
    private boolean objectFromData = false;
    private boolean objectFromData1 = false;
    private boolean arrayFromData = false;
    private boolean arrayFromData1 = false;
    private boolean reuseEntity = false;
    private boolean virgoMode = true; //处女座模式
    private boolean useFieldNamePrefix = false;
    private boolean splitGenerate = false;


    private String objectFromDataStr;
    private String objectFromDataStr1;
    private String arrayFromDataStr;
    private String arrayFromData1Str;
    private String annotationStr; //注解语句
    private String filedNamePreFixStr; //字段前缀
    private String entityPackName;//创建实体类的包名.
    private String suffixStr;

    /**
     * 错误次数,前两次提醒哪里查看错误日志.
     */
    private int errorCount;

    /**
     * 是否使用包装类来替代基本类型
     */
    private boolean useWrapperClass;

    /**
     * 是否使用包装类来替代基本类型
     */
    private boolean useJava8LocalDateTime;

    private boolean dateFormatCheckBox;
    private String dateFormatTextField;
    private boolean dateTimeFormatCheckBox;
    private String dateTimeFormatField;
    private boolean timeFormatCheckBox;
    private String timeFormatTextField;

    private DateTimeFormatter dateTimeFormatter;
    private DateTimeFormatter dateFormatter;
    private DateTimeFormatter timeFormatter;
    private SimpleDateFormat simpleDateFormat;


    private Config() {

    }

    public void save() {

        PropertiesComponent.getInstance().setValue("fieldPrivateMode", "" + isFieldPrivateMode());
        PropertiesComponent.getInstance().setValue("useSerializedName", isUseSerializedName() + "");
        PropertiesComponent.getInstance().setValue("objectFromData", objectFromData + "");
        PropertiesComponent.getInstance().setValue("objectFromData1", objectFromData1 + "");
        PropertiesComponent.getInstance().setValue("arrayFromData", arrayFromData + "");
        PropertiesComponent.getInstance().setValue("arrayFromData1", arrayFromData1 + "");
        PropertiesComponent.getInstance().setValue("objectFromDataStr", objectFromDataStr + "");
        PropertiesComponent.getInstance().setValue("objectFromDataStr1", objectFromDataStr1 + "");
        PropertiesComponent.getInstance().setValue("arrayFromData1Str", arrayFromData1Str + "");
        PropertiesComponent.getInstance().setValue("suffixStr", suffixStr + "");
        PropertiesComponent.getInstance().setValue("reuseEntity", reuseEntity + "");
        PropertiesComponent.getInstance().setValue("virgoMode", virgoMode + "");
        PropertiesComponent.getInstance().setValue("filedNamePreFixStr", filedNamePreFixStr + "");
        PropertiesComponent.getInstance().setValue("annotationStr", annotationStr + "");
        PropertiesComponent.getInstance().setValue("errorCount", errorCount + "");
        PropertiesComponent.getInstance().setValue("entityPackName", entityPackName + "");
        PropertiesComponent.getInstance().setValue("useFieldNamePrefix", useFieldNamePrefix + "");
        PropertiesComponent.getInstance().setValue("generateComments", generateComments + "");
        PropertiesComponent.getInstance().setValue("splitGenerate", splitGenerate + "");
        PropertiesComponent.getInstance().setValue("useWrapperClass", useWrapperClass + "");
        PropertiesComponent.getInstance().setValue("useJava8LocalDateTime", useJava8LocalDateTime + "");
        PropertiesComponent.getInstance().setValue("dateFormatCheckBox", dateFormatCheckBox + "");
        PropertiesComponent.getInstance().setValue("dateFormatTextField", dateFormatTextField + "");
        PropertiesComponent.getInstance().setValue("dateTimeFormatCheckBox", dateTimeFormatCheckBox + "");
        PropertiesComponent.getInstance().setValue("dateTimeFormatField", dateTimeFormatField + "");
        PropertiesComponent.getInstance().setValue("timeFormatCheckBox", timeFormatCheckBox + "");
        PropertiesComponent.getInstance().setValue("timeFormatTextField", timeFormatTextField + "");


    }

    public static Config getInstant() {

        if (config == null) {
            config = new Config();
            config.setFieldPrivateMode(PropertiesComponent.getInstance().getBoolean("fieldPrivateMode", true));
            config.setUseSerializedName(PropertiesComponent.getInstance().getBoolean("useSerializedName", false));
            config.setObjectFromData(PropertiesComponent.getInstance().getBoolean("objectFromData", false));
            config.setObjectFromData1(PropertiesComponent.getInstance().getBoolean("objectFromData1", false));
            config.setArrayFromData(PropertiesComponent.getInstance().getBoolean("arrayFromData", false));
            config.setArrayFromData1(PropertiesComponent.getInstance().getBoolean("arrayFromData1", false));
            config.setSuffixStr(PropertiesComponent.getInstance().getValue("suffixStr", "Bean"));
            config.setReuseEntity(PropertiesComponent.getInstance().getBoolean("reuseEntity", false));
            config.setObjectFromDataStr(PropertiesComponent.getInstance().getValue("objectFromDataStr", Constant.objectFromObject));
            config.setObjectFromDataStr1(PropertiesComponent.getInstance().getValue("objectFromDataStr1", Constant.objectFromObject1));
            config.setArrayFromDataStr(PropertiesComponent.getInstance().getValue("arrayFromDataStr", Constant.arrayFromData));
            config.setArrayFromData1Str(PropertiesComponent.getInstance().getValue("arrayFromData1Str", Constant.arrayFromData1));
            config.setAnnotationStr(PropertiesComponent.getInstance().getValue("annotationStr", Constant.gsonAnnotation));
            config.setEntityPackName(PropertiesComponent.getInstance().getValue("entityPackName"));
            config.setFiledNamePreFixStr(PropertiesComponent.getInstance().getValue("filedNamePreFixStr"));
            config.setErrorCount(PropertiesComponent.getInstance().getInt("errorCount", 0));
            config.setVirgoMode(PropertiesComponent.getInstance().getBoolean("virgoMode", true));
            config.setUseFieldNamePrefix(PropertiesComponent.getInstance().getBoolean("useFieldNamePrefix", false));
            config.setGenerateComments(PropertiesComponent.getInstance().getBoolean("generateComments", true));
            config.setSplitGenerate(PropertiesComponent.getInstance().getBoolean("splitGenerate", false));
            config.setUseWrapperClass(PropertiesComponent.getInstance().getBoolean("useWrapperClass", true));
            config.setUseJava8LocalDateTime(PropertiesComponent.getInstance().getBoolean("useJava8LocalDateTime", true));
            config.setDateFormatCheckBox(PropertiesComponent.getInstance().getBoolean("dateFormatCheckBox", false));
            config.setDateFormatTextField(PropertiesComponent.getInstance().getValue("dateFormatTextField", "yyyy-MM-dd"));
            config.setDateTimeFormatCheckBox(PropertiesComponent.getInstance().getBoolean("dateTimeFormatCheckBox", true));
            config.setDateTimeFormatField(PropertiesComponent.getInstance().getValue("dateTimeFormatField", "yyyy-MM-dd hh:mm:ss"));
            config.setTimeFormatCheckBox(PropertiesComponent.getInstance().getBoolean("timeFormatCheckBox", false));
            config.setTimeFormatTextField(PropertiesComponent.getInstance().getValue("timeFormatTextField", "hh:mm:ss"));
        }
        if (config.useJava8LocalDateTime) {
            if (config.isDateTimeFormatCheckBox() && StringUtils.hasText(config.dateTimeFormatField)) {
                config.dateTimeFormatter = DateTimeFormatter.ofPattern(config.dateTimeFormatField);
            } else {
                config.dateTimeFormatter = null;
            }
            if (config.isDateFormatCheckBox() && StringUtils.hasText(config.dateFormatTextField)) {
                config.dateFormatter = DateTimeFormatter.ofPattern(config.dateFormatTextField);
            } else {
                config.dateFormatter = null;
            }
            if (config.isTimeFormatCheckBox() && StringUtils.hasText(config.timeFormatTextField)) {
                config.timeFormatter = DateTimeFormatter.ofPattern(config.timeFormatTextField);
            } else {
                config.timeFormatter = null;

            }

        } else {
            if (config.isDateTimeFormatCheckBox() && StringUtils.hasText(config.dateTimeFormatField)) {
                config.simpleDateFormat = new SimpleDateFormat(config.dateTimeFormatField);
            } else {
                config.simpleDateFormat = null;
            }
        }
        return config;
    }

    public boolean isUseFieldNamePrefix() {
        return useFieldNamePrefix;
    }

    public void setUseFieldNamePrefix(boolean useFieldNamePrefix) {
        this.useFieldNamePrefix = useFieldNamePrefix;
    }

    public boolean isObjectFromData() {
        return objectFromData;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String getEntityPackName() {
        return entityPackName;
    }

    public String geFullNameAnnotation() {

        if (annotationStr.equals(Constant.gsonAnnotation)) {
            return Constant.gsonFullNameAnnotation;
        }
        if (annotationStr.equals(Constant.jackAnnotation)) {
            return Constant.jackFullNameAnnotation;
        }
        if (annotationStr.equals(Constant.fastAnnotation)) {
            return Constant.fastFullNameAnnotation;
        }
        if (annotationStr.equals(Constant.loganSquareAnnotation)) {
            return Constant.loganSquareFullNameAnnotation;
        }
        return annotationStr.replaceAll("\\(", "(").replaceAll("\\)", ")").replaceAll("\\s\\*", "");
    }


    public boolean isGenerateComments() {
        return generateComments;
    }

    public void setGenerateComments(boolean generateComments) {
        this.generateComments = generateComments;
    }

    public void setEntityPackName(String entityPackName) {
        this.entityPackName = entityPackName;
    }

    public boolean isVirgoMode() {
        return virgoMode;
    }

    public void setVirgoMode(boolean virgoMode) {
        this.virgoMode = virgoMode;
    }

    public String getFiledNamePreFixStr() {
        return filedNamePreFixStr;
    }

    public void setFiledNamePreFixStr(String filedNamePreFixStr) {
        this.filedNamePreFixStr = filedNamePreFixStr;
    }

    public String getAnnotationStr() {
        return annotationStr;
    }

    public void setAnnotationStr(String annotationStr) {
        this.annotationStr = annotationStr;
    }

    public void setObjectFromData(boolean objectFromData) {
        this.objectFromData = objectFromData;
    }

    public boolean isObjectFromData1() {
        return objectFromData1;
    }

    public void setObjectFromData1(boolean objectFromData2) {
        this.objectFromData1 = objectFromData2;
    }

    public boolean isArrayFromData() {
        return arrayFromData;
    }

    public void setArrayFromData(boolean arrayFromData) {
        this.arrayFromData = arrayFromData;
    }

    public boolean isArrayFromData1() {
        return arrayFromData1;
    }

    public void setArrayFromData1(boolean arrayFromData1) {
        this.arrayFromData1 = arrayFromData1;
    }


    public void setObjectFromDataStr(String objectFromDataStr) {
        this.objectFromDataStr = objectFromDataStr;
    }

    public void setObjectFromDataStr1(String objectFromDataStr1) {
        this.objectFromDataStr1 = objectFromDataStr1;
    }

    public void setArrayFromDataStr(String arrayFromDataStr) {
        this.arrayFromDataStr = arrayFromDataStr;
    }

    public void setArrayFromData1Str(String arrayFromData1Str) {
        this.arrayFromData1Str = arrayFromData1Str;
    }

    public String getObjectFromDataStr() {
        return objectFromDataStr;
    }

    public String getObjectFromDataStr1() {
        return objectFromDataStr1;
    }

    public String getArrayFromDataStr() {
        return arrayFromDataStr;
    }

    public String getArrayFromData1Str() {
        return arrayFromData1Str;
    }

    public String getSuffixStr() {
        return suffixStr;
    }

    public void setSuffixStr(String suffixStr) {
        this.suffixStr = suffixStr;
    }

    public boolean isReuseEntity() {
        return reuseEntity;
    }

    public void setReuseEntity(boolean reuseEntity) {
        this.reuseEntity = reuseEntity;
    }

    public boolean isUseSerializedName() {
        return useSerializedName;
    }

    public void setUseSerializedName(boolean useSerializedName) {
        this.useSerializedName = useSerializedName;
    }

    public boolean isFieldPrivateMode() {
        return fieldPrivateMode;
    }

    public void setFieldPrivateMode(boolean fieldPrivateMode) {
        this.fieldPrivateMode = fieldPrivateMode;
    }


    public void saveObjectFromDataStr(String objectFromDataStr) {
        this.objectFromDataStr = objectFromDataStr;
        PropertiesComponent.getInstance().setValue("objectFromDataStr", objectFromDataStr + "");
    }

    public void saveObjectFromDataStr1(String objectFromDataStr1) {
        this.objectFromDataStr1 = objectFromDataStr1;
        PropertiesComponent.getInstance().setValue("objectFromDataStr1", objectFromDataStr1 + "");
    }

    public void saveArrayFromDataStr(String arrayFromDataStr) {
        this.arrayFromDataStr = arrayFromDataStr;
        PropertiesComponent.getInstance().setValue("arrayFromDataStr", arrayFromDataStr + "");
    }

    public void saveArrayFromData1Str(String arrayFromData1Str) {
        this.arrayFromData1Str = arrayFromData1Str;
        PropertiesComponent.getInstance().setValue("arrayFromData1Str", arrayFromData1Str + "");
    }

    public boolean isToastError() {
        if (Config.getInstant().getErrorCount() < 3) {
            Config.getInstant().setErrorCount(Config.getInstant().getErrorCount() + 1);
            Config.getInstant().save();
            return true;
        }
        return false;
    }

    public boolean isSplitGenerate() {
        return splitGenerate;
    }

    public void setSplitGenerate(boolean splitGenerate) {
        this.splitGenerate = splitGenerate;
    }


    public void saveCurrentPackPath(String entityPackName) {
        if (entityPackName == null) {
            return;
        }
        setEntityPackName(entityPackName + ".");
        save();
    }

    public boolean isUseWrapperClass() {
        return useWrapperClass;
    }

    public void setUseWrapperClass(boolean useWrapperClass) {
        this.useWrapperClass = useWrapperClass;
    }

    public boolean isUseJava8LocalDateTime() {
        return useJava8LocalDateTime;
    }

    public void setUseJava8LocalDateTime(boolean useJava8LocalDateTime) {
        this.useJava8LocalDateTime = useJava8LocalDateTime;
    }

    public boolean isDateFormatCheckBox() {
        return dateFormatCheckBox;
    }

    public void setDateFormatCheckBox(boolean dateFormatCheckBox) {
        this.dateFormatCheckBox = dateFormatCheckBox;
    }

    public String getDateFormatTextField() {
        return dateFormatTextField;
    }

    public void setDateFormatTextField(String dateFormatTextField) {
        this.dateFormatTextField = dateFormatTextField;
    }

    public boolean isDateTimeFormatCheckBox() {
        return dateTimeFormatCheckBox;
    }

    public void setDateTimeFormatCheckBox(boolean dateTimeFormatCheckBox) {
        this.dateTimeFormatCheckBox = dateTimeFormatCheckBox;
    }

    public String getDateTimeFormatField() {
        return dateTimeFormatField;
    }

    public void setDateTimeFormatField(String dateTimeFormatField) {
        this.dateTimeFormatField = dateTimeFormatField;
    }

    public Boolean getTimeFormatCheckBox() {
        return timeFormatCheckBox;
    }

    public void setTimeFormatCheckBox(Boolean timeFormatCheckBox) {
        this.timeFormatCheckBox = timeFormatCheckBox;
    }

    public String getTimeFormatTextField() {
        return timeFormatTextField;
    }

    public void setTimeFormatTextField(String timeFormatTextField) {
        this.timeFormatTextField = timeFormatTextField;
    }

    public boolean isTimeFormatCheckBox() {
        return timeFormatCheckBox;
    }

    public void setTimeFormatCheckBox(boolean timeFormatCheckBox) {
        this.timeFormatCheckBox = timeFormatCheckBox;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }

    public void setDateFormatter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }

    public void setTimeFormatter(DateTimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }
}
