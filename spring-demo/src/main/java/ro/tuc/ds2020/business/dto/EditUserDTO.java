package ro.tuc.ds2020.business.dto;

import ro.tuc.ds2020.model.User;

public class EditUserDTO
{
    private String newUsername;
    private User userToEdit;

    public String getNewUsername()
    {
        return newUsername;
    }

    public User getUserToEdit()
    {
        return userToEdit;
    }
}
