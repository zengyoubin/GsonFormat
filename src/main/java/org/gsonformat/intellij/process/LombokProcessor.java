package org.gsonformat.intellij.process;

import com.intellij.psi.*;
import com.intellij.psi.util.PsiUtil;
import org.apache.http.util.TextUtils;
import org.gsonformat.intellij.common.FieldHelper;
import org.gsonformat.intellij.common.Try;
import org.gsonformat.intellij.config.Config;
import org.gsonformat.intellij.config.Constant;
import org.gsonformat.intellij.entity.ClassEntity;
import org.gsonformat.intellij.entity.FieldEntity;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Created by dim on 16/11/7.
 */
public class LombokProcessor extends Processor {
    private Pattern pattern = Pattern.compile("@.*?NoArgsConstructor");
    private Pattern pattern2 = Pattern.compile("@.*?Data");

    @Override
    protected void onStarProcess(ClassEntity classEntity, PsiElementFactory factory, PsiClass cls, IProcessor visitor) {
        super.onStarProcess(classEntity, factory, cls, visitor);
        injectAnnotation(factory, cls);
    }

    private void injectAnnotation(PsiElementFactory factory, PsiClass generateClass) {
        if (factory == null || generateClass == null) {
            return;
        }
        // PsiImportStatement importStatement = factory.createImportStatement(new PsiUtil.NullPsiClass() {
        //     @Nullable
        //     @Override
        //     public String getQualifiedName() {
        //         return LocalDateTime.class.getSimpleName();
        //     }
        //
        //     @Override
        //     public PsiElement getParent() {
        //         return null;
        //     }
        // });

        PsiModifierList modifierList = generateClass.getModifierList();
        if (modifierList != null) {
            PsiElement[] children = modifierList.getChildren();
            boolean noArgsConstructorAnnotationExist = false;
            boolean lombokAnnotationExist = false;
            for (PsiElement child : children) {
                if (child != null) {
                    if (pattern.matcher(child.getText()).find()) {
                        noArgsConstructorAnnotationExist = true;

                    }
                    if (pattern2.matcher(child.getText()).find()) {
                        lombokAnnotationExist = true;
                    }
                }
            }
            if (!noArgsConstructorAnnotationExist) {
                modifierList.addBefore(factory.createAnnotationFromText("@lombok.NoArgsConstructor", generateClass), modifierList.getFirstChild());
            }
            if (!lombokAnnotationExist) {
                modifierList.addBefore(factory.createAnnotationFromText("@lombok.Data", generateClass), modifierList.getFirstChild());
            }
            // modifierList.addBefore(importStatement, modifierList.getFirstChild());
        }
    }

    @Override
    protected void generateField(PsiElementFactory factory, FieldEntity fieldEntity, PsiClass cls, ClassEntity classEntity) {
        if (fieldEntity.isGenerate()) {
            Try.run(new Try.TryListener() {
                @Override
                public void run() {
                    cls.add(factory.createFieldFromText(generateLombokFieldText(classEntity, fieldEntity, null), cls));
                }

                @Override
                public void runAgain() {
                    fieldEntity.setFieldName(FieldHelper.generateLuckyFieldName(fieldEntity.getFieldName()));
                    cls.add(factory.createFieldFromText(generateLombokFieldText(classEntity, fieldEntity, Constant.FIXME), cls));
                }

                @Override
                public void error() {
                    cls.addBefore(factory.createCommentFromText("// FIXME generate failure  field " + fieldEntity.getFieldName(), cls), cls.getChildren()[0]);
                }
            });
        }
    }

    @Override
    protected void createGetAndSetMethod(PsiElementFactory factory, PsiClass cls, FieldEntity field) {
    }

    @Override
    protected void onEndGenerateClass(PsiElementFactory factory, ClassEntity classEntity, PsiClass parentClass, PsiClass generateClass, IProcessor visitor) {
        super.onEndGenerateClass(factory, classEntity, parentClass, generateClass, visitor);
        injectAnnotation(factory, generateClass);
    }

    private String generateLombokFieldText(ClassEntity classEntity, FieldEntity fieldEntity, String fixme) {
        fixme = fixme == null ? "" : fixme;

        StringBuilder fieldSb = new StringBuilder();
        String filedName = fieldEntity.getGenerateFieldName();
        if (!TextUtils.isEmpty(classEntity.getExtra())) {
            fieldSb.append(classEntity.getExtra()).append("\n");
            classEntity.setExtra(null);
        }
        if (fieldEntity.getTargetClass() != null) {
            fieldEntity.getTargetClass().setGenerate(true);
        }

        if (Config.getInstant().isFieldPrivateMode()) {
            fieldSb.append("private  ").append(fieldEntity.getFullNameType()).append(" ").append(filedName).append(" ; ");
        } else {
            fieldSb.append("public  ").append(fieldEntity.getFullNameType()).append(" ").append(filedName).append(" ; ");
        }
        return fieldSb.append(fixme).toString();
    }
}
