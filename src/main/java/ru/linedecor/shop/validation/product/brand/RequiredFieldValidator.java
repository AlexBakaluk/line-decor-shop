package ru.linedecor.shop.validation.product.brand;

import org.springframework.stereotype.Component;
import ru.linedecor.shop.validation.annotations.validator.RequiredField;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class RequiredFieldValidator extends AbstractAnnotationValidator {

    private static final String NULL_VALIDATION_ERROR_MESSAGE = "Must not be null";
    private static final String BLANK_VALIDATION_ERROR_MESSAGE = "Must not be blank";

    @Override
    protected boolean checkIsObjectContainsServicedAnnotation(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        return checkIsAtLeastOneFieldContainsAnnotation(fields);
    }

    private boolean checkIsAtLeastOneFieldContainsAnnotation(Field[] fields) {
        for (Field field : fields) {
            boolean isPresent = field.isAnnotationPresent(RequiredField.class);
            if (isPresent) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void validateObject(Object object, Map<String, Set<String>> fieldsValidationErrors) {
        Class<?> clazz = object.getClass();
        Set<Field> annotatedFields = getAnnotatedFields(clazz);
        if (!annotatedFields.isEmpty()) {
            validateFields(annotatedFields, object, fieldsValidationErrors);
        }
    }

    private void validateFields(Set<Field> annotatedFields, Object obj, Map<String, Set<String>> fieldsValidationErrors) {
        for (Field field : annotatedFields) {
            validateFieldNotNull(field, obj, fieldsValidationErrors);
            ifFieldIsStringTypeCheckIsNotBlank(field, obj, fieldsValidationErrors);
        }
    }

    private void ifFieldIsStringTypeCheckIsNotBlank(Field field, Object obj, Map<String, Set<String>> fieldsValidationErrors) {
        boolean isString = checkFieldIsString(field);
        if (isString) {
            validateFieldNotBlank(field, obj, fieldsValidationErrors);
        }
    }

    private boolean checkFieldIsString(Field field) {
        return field.getType() == String.class;
    }

    private void validateFieldNotBlank(Field field, Object obj, Map<String, Set<String>> fieldsValidationErrors) {
        String fieldName = field.getName();
        String fieldValue = String.valueOf(getValueFromField(field, obj));
        if (fieldValue.isBlank() || fieldValue.isEmpty()) {
            Set<String> messages = fieldsValidationErrors.getOrDefault(fieldName, new HashSet<>());
            messages.add(BLANK_VALIDATION_ERROR_MESSAGE);
            fieldsValidationErrors.put(fieldName, messages);
        }
    }

    private void validateFieldNotNull(Field field, Object obj, Map<String, Set<String>> fieldsValidationErrors) {
        String fieldName = field.getName();
        Object fieldValue = getValueFromField(field, obj);
        if (fieldValue == null) {
            Set<String> messages = fieldsValidationErrors.getOrDefault(fieldName, new HashSet<>());
            messages.add(NULL_VALIDATION_ERROR_MESSAGE);
            fieldsValidationErrors.put(fieldName, messages);
        }
    }

    private Object getValueFromField(Field field, Object obj) {
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Can't get value from field: " + field.getName());
        }
    }

    private Set<Field> getAnnotatedFields(Class<?> clazz) {
        Set<Field> annotatedFields = new HashSet<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(RequiredField.class)) {
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }
}
