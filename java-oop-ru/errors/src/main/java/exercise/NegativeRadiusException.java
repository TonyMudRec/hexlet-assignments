package exercise;

import java.math.BigDecimal;

// BEGIN
class NegativeRadiusException extends Exception {
    String message;

    public NegativeRadiusException(String message) {
        this.message = message;
    }
}
// END
