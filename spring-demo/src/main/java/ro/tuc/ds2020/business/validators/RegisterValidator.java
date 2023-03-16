package ro.tuc.ds2020.business.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidator
{
    private static RegisterValidator validator;

    private RegisterValidator() {}

    public static RegisterValidator getInstance()
    {
        if(validator == null)
            validator = new RegisterValidator();
        return validator;
    }

    public boolean checkNotNullField(String field)
    {
        return field != null && !field.isEmpty();
    }

    public boolean checkIntNumberField(String age)
    {
        String regex = "^[1-9]\\d*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(age);

        return !age.isEmpty() && matcher.matches();
    }

    public boolean checkHourlyConsumptionField(String number)
    {
        //integer or double value
        String regex = "^-?[0-9]+([.,][0-9]+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);

        return !number.isEmpty() && matcher.matches();
    }

    /**
     * RULES FOR USERNAME
     * Non-empty field
     * Only allow alphabet and numbers
     * @param username - username inserted by the user
     * @return false - at least one rule is not respected
     *         true - all rules are respected and validation is successful
     */
    public boolean checkUsername(String username)
    {
        String regex = "^[A-Za-z][A-Za-z0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        return !username.isEmpty() && matcher.matches();
    }
}
