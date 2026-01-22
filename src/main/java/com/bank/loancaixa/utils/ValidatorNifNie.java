package com.bank.loancaixa.utils;

import java.util.Map;
import java.util.regex.Pattern;

public class ValidatorNifNie {

    private static final String CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";

    private static final Pattern NIF_PATTERN =
            Pattern.compile("\\d{8}[A-Z]");

    private static final Pattern NIE_PATTERN =
            Pattern.compile("[XYZ]\\d{7}[A-Z]");

    private static final Map<Character, Character> NIE_MAP = Map.of(
            'X', '0',
            'Y', '1',
            'Z', '2'
    );

    public static boolean isValid(String identifier) {
        if (identifier == null) {
            return false;
        }

        String id = identifier.trim().toUpperCase();

        if (NIF_PATTERN.matcher(id).matches()) {
            return validateNif(id);
        }

        if (NIE_PATTERN.matcher(id).matches()) {
            return validateNie(id);
        }

        return false;
    }

    private static boolean validateNif(String nif) {
        int number = Integer.parseInt(nif.substring(0, 8));
        char character = nif.charAt(8);
        return expectedLetter(number) == character;
    }

    private static boolean validateNie(String nie) {
        char prefix = nie.charAt(0);
        char equivalent = NIE_MAP.get(prefix);

        String completNumber = equivalent + nie.substring(1, 8);
        int number = Integer.parseInt(completNumber);
        char character = nie.charAt(8);

        return expectedLetter(number) == character;
    }

    private static char expectedLetter(int number) {
        return CONTROL.charAt(number % 23);
    }
}
