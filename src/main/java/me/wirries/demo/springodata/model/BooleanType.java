package me.wirries.demo.springodata.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Boolean type reference to the database type (string).
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
public enum BooleanType {

    /**
     * False.
     */
    FALSE(false),
    /**
     * True.
     */
    TRUE(true);

    private final boolean value;

    /**
     * Create the type.
     *
     * @param value boolean value.
     */
    BooleanType(boolean value) {
        this.value = value;
    }

    /**
     * Compare the type with the given boolean value.
     *
     * @param b Boolean value
     * @return true if equals
     */
    public boolean equals(boolean b) {
        return value == b;
    }

    public boolean value() {
        return value;
    }

    /**
     * Compare the type with the given boolean value.
     *
     * @param type Type
     * @param b    Boolean value
     * @return true if equals
     */
    public static boolean equals(BooleanType type, boolean b) {
        if (type == null) {
            return false;
        }
        return type.equals(b);
    }

    /**
     * Return the BooleanType for the boolean value.
     *
     * @param b Boolean value
     * @return BooleanType
     */
    public static BooleanType get(boolean b) {
        if (b) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    /**
     * Return the BooleanType for the string value.
     *
     * @param s String value of true or false
     * @return BooleanType
     */
    public static BooleanType get(String s) {
        if (StringUtils.isBlank(s)) {
            return FALSE;
        } else if ("true".equalsIgnoreCase(s)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

}
