package ru.linedecor.shop.domain.product;

public enum CharacteristicType {

    NUMERIC("Numeric"), STRING("String");

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
            default:
                throw new IllegalArgumentException("Unsupported type name: " + name);
        }
    }

}
