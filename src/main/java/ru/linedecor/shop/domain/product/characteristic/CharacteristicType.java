package ru.linedecor.shop.domain.product.characteristic;

public enum CharacteristicType {

    NUMERIC("Numeric"), STRING("String"), ARRAY("Array");

    private final String name;

    CharacteristicType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CharacteristicType getTypeFromName(String name) {
        switch (name) {
            case "Numeric":
                return NUMERIC;
            case "String":
                return STRING;
            case "Array":
                return ARRAY;
            default:
                throw new IllegalArgumentException("Unsupported type name: " + name);
        }
    }

}
