package ro.tuc.ds2020.business.exception;

public class UsernameNotFoundException extends Exception
{
    public UsernameNotFoundException(String errorMessage)
    {
        super(errorMessage);
    }
}
