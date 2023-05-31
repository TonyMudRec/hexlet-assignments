package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
class Validator {

    public static List<String> validate(Address address) {
        return Arrays.stream(address.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(NotNull.class))
                .peek(f -> f.setAccessible(true))
                .filter(f -> {
                    try {
                        return f.get(address) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(Field::getName)
                .toList();
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        List<String> nullFields = validate(address);
        List<String> errors = new ArrayList<>();
        errors.add("can not be null");
        Map<String, List<String>> result = new HashMap<>();
        String currentValue;
        String key;
        int currentMinLength;
        int currentLength;
        for (String field : nullFields) {
            result.put(field, List.copyOf(errors));
        }
        errors.clear();
        try {
            for (Field field : address.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(MinLength.class) && !nullFields.contains(field.getName())) {
                    currentValue = (String) field.get(address);
                    key = field.getName();
                    currentMinLength = field.getAnnotation(MinLength.class).minLength();
                    currentLength = currentValue.length();
                    if (currentMinLength > currentLength) {
                        errors.add("length less than " + currentMinLength);
                        result.put(key, List.copyOf(errors));
                        errors.clear();
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
// END
