package ru.linedecor.shop.validation.product.brand;

import org.springframework.stereotype.Component;
import ru.linedecor.shop.validation.annotations.UniqueConstraints;
import ru.linedecor.shop.validation.annotations.validator.TargetClass;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UniqueConstraintsValidator extends AbstractAnnotationValidator {

    @PersistenceContext
    private EntityManager entityManager;

    public void validateObject(Object object, Map<String, Set<String>> fieldsValidationErrors) {
        Class<?> clazz = object.getClass();
        Class<?> targetClass = getTargetClass(clazz);
        Set<UniqueConstraint> uniqueConstraints = getUniqueConstraintsFromTargetClass(targetClass);
        if (!uniqueConstraints.isEmpty()) {
            List<Set<String>> constraintsFields = getConstraintsFields(uniqueConstraints);
            String tableName = getTableNameFromTargetClass(targetClass);
            try {
                checkIsEntityUniqueByConstraintsFields(tableName, constraintsFields, object);
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException("Can't validate object. Check is fields names equals target class fields");
            }
        }
    }

    @Override
    protected boolean checkIsObjectContainsServicedAnnotation(Object object) {
        return object.getClass().isAnnotationPresent(UniqueConstraints.class);
    }

    private Class<?> getTargetClass(Class<?> tClass) {
        UniqueConstraints uniqueConstraintsAnnotation = tClass.getAnnotation(UniqueConstraints.class);
        TargetClass annotation = uniqueConstraintsAnnotation.target();
        return annotation.targetClass();
    }

    private Set<UniqueConstraint> getUniqueConstraintsFromTargetClass(Class<?> targetClass) {
        Set<UniqueConstraint> constraints = new HashSet<>();
        boolean isTableAnnotationPresent = checkIsClassHasTableAnnotation(targetClass);
        if (!isTableAnnotationPresent) {
            return constraints;
        }
        Table table = targetClass.getAnnotation(Table.class);
        UniqueConstraint[] uq = table.uniqueConstraints();
        constraints = Arrays.stream(uq).collect(Collectors.toSet());
        return constraints;
    }

    private boolean checkIsClassHasTableAnnotation(Class<?> clazz) {
        return clazz.isAnnotationPresent(Table.class);
    }

    private List<Set<String>> getConstraintsFields(Set<UniqueConstraint> uniqueConstraints) {
        List<Set<String>> constraintsFields = new ArrayList<>(uniqueConstraints.size());
        for (UniqueConstraint constraint : uniqueConstraints) {
            Set<String> fields = Arrays.stream(constraint.columnNames()).collect(Collectors.toSet());
            constraintsFields.add(fields);
        }
        return constraintsFields;
    }

    private String getTableNameFromTargetClass(Class<?> targetClass) {
        boolean isAnnotationPresent = targetClass.isAnnotationPresent(Table.class);
        if (!isAnnotationPresent) {
            throw new IllegalStateException("Target class not a table!");
        }
        Table table = targetClass.getAnnotation(Table.class);
        return table.name();
    }

    private void checkIsEntityUniqueByConstraintsFields(String tableName, List<Set<String>> constraintsFields, Object obj) throws NoSuchFieldException {
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT (*) FROM " + tableName + " e WHERE ");
        for (Set<String> constraint : constraintsFields) {
            queryBuilder.append("(");
            appendConstraintsToQuery(queryBuilder, constraint, obj);
            queryBuilder.append(")").append(" AND ");
        }
        queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
        BigInteger count = (BigInteger) entityManager.createNativeQuery(queryBuilder.toString()).getResultList().get(0);
        if (!count.equals(BigInteger.ZERO)) {
            throw new IllegalStateException("Not unique value");
        }
    }

    private void appendConstraintsToQuery(StringBuilder queryBuilder, Set<String> constraint, Object obj) throws NoSuchFieldException {
        boolean isFirst = true;
        for (String fieldName : constraint) {
            if (!isFirst) {
                queryBuilder.append(" OR ");
            }
            String fieldValue = getFieldValueByName(fieldName, obj);
            queryBuilder.append("UPPER(CAST(e.").append(fieldName).append(" as TEXT)) = UPPER('").append(fieldValue).append("')");
            isFirst = false;
        }
    }

    private String getFieldValueByName(String fieldName, Object obj) throws NoSuchFieldException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        String fieldValue = null;
        try {
            fieldValue = String.valueOf(field.get(obj));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValue;
    }
}
