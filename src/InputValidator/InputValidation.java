package InputValidator;

public class InputValidation implements IInputValidation {

    @Override
    public boolean onlyContainsNumbers(String... args) {
        for (String iterator : args) {
            if (!iterator.matches("[0-9]+") || iterator.length() <= 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onlyContainsLetters(String... args) {
        for (String iterator : args) {
            if (!iterator.matches("[a-zA-Z ]+") || iterator.length() <= 1) {
                return false;
            }
        }
        return true;
    }

}
