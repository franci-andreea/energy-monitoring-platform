package ro.tuc.ds2020.business.exception;

public class UsernameAlreadyExistsException extends Exception
{
    public UsernameAlreadyExistsException(String errorMessage)
    {
        super(errorMessage);
    }
}
