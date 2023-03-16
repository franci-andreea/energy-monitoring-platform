package ro.tuc.ds2020.business.exception;

public class InvalidUsernameException extends Exception
{
    public InvalidUsernameException(String errorMessage)
    {
        super(errorMessage);
    }
}
