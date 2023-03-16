package ro.tuc.ds2020.business.builder;

import ro.tuc.ds2020.business.dto.RegisterDTO;
import ro.tuc.ds2020.model.User;
import ro.tuc.ds2020.model.UserRole;

public class UserBuilder
{
    private static UserBuilder builder;

    private UserBuilder() {

    }

    public static UserBuilder getInstance()
    {
        if(builder == null)
            builder = new UserBuilder();
        return builder;
    }

    public User toUser(RegisterDTO registerDTO)
    {
        User user = new User();
        user.setName(registerDTO.getName());
        user.setAddress(registerDTO.getAddress());
        user.setAge(registerDTO.getAge());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setRole(UserRole.USER);

        return user;
    }
}
