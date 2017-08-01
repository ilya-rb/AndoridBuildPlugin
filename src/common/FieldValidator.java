package common;

public final class FieldValidator {

    private FieldValidator() {

    }

    public static boolean isValid(String text) {
        return !text.trim().isEmpty();
    }

}
