package ro.tuc.ds2020.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.business.builder.UserBuilder;
import ro.tuc.ds2020.business.dto.LoginDTO;
import ro.tuc.ds2020.business.dto.RegisterDTO;
import ro.tuc.ds2020.business.exception.InvalidUsernameException;
import ro.tuc.ds2020.business.exception.UsernameAlreadyExistsException;
import ro.tuc.ds2020.business.exception.UsernameNotFoundException;
import ro.tuc.ds2020.business.validators.RegisterValidator;
import ro.tuc.ds2020.model.Device;
import ro.tuc.ds2020.model.User;
import ro.tuc.ds2020.model.UserRole;
import ro.tuc.ds2020.repository.DeviceRepository;
import ro.tuc.ds2020.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceService deviceService;

    @Autowired
    public UserService(UserRepository userRepository, DeviceRepository deviceRepository, DeviceService deviceService) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.deviceService = deviceService;
    }

    @EventListener
    public void eventListener(ApplicationStartedEvent unused)
    {
        User superAdmin = new User("Administrator", "_", 1, UserRole.ADMIN, "admin", "admin");
        if (getByUsername(superAdmin.getUsername()) == null)
        {
            userRepository.save(superAdmin);
        }

        User normalUser1 = new User("Francesca Ditulescu", "_", 1, UserRole.USER, "franci", "franci");
        if(getByUsername(normalUser1.getUsername()) == null)
        {
            normalUser1 = userRepository.save(normalUser1);
        }
        User normalUser2 = new User("Andreea Ditulescu", "_", 1, UserRole.USER, "andreea", "andreea");
        if(getByUsername(normalUser2.getUsername()) == null)
        {
            normalUser2 = userRepository.save(normalUser2);
        }

        Device deviceUser1 = new Device("description device 1", "address user franci", 200.0, normalUser1);
        Device deviceUser2 = new Device("description device 2", "address user andreea", 200.0, normalUser2);

        if(!deviceRepository.existsById(1))
            deviceRepository.save(deviceUser1);

        if(!deviceRepository.existsById(2))
            deviceRepository.save(deviceUser2);
    }

    @Transactional
    public User getByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public User register(RegisterDTO registerDTO) throws InvalidUsernameException, UsernameAlreadyExistsException
    {
        RegisterValidator validator = RegisterValidator.getInstance();
        UserBuilder userBuilder = UserBuilder.getInstance();

        if (validator.checkNotNullField(registerDTO.getName()) && validator.checkNotNullField(registerDTO.getAddress()) &&
                validator.checkNotNullField(registerDTO.getAddress()) && validator.checkNotNullField(registerDTO.getPassword()))
        {
            if(validator.checkUsername(registerDTO.getUsername()))
            {
                User user = userRepository.findByUsername(registerDTO.getUsername());

                if(user == null)
                {
                    User newUser = userBuilder.toUser(registerDTO);
                    //user's data fields are completed with valid data, so it is safe to add him to the database
                    userRepository.save(newUser);

                    return newUser;
                }
                else
                {
                    System.out.println("CONSOLE - REGISTER SERVICE - REGISTER USER -> USERNAME ALREADY TAKEN!");
                    throw new UsernameAlreadyExistsException("Username is already taken! Can't add user to the db!");
                }
            }
            else
            {
                System.out.println("CONSOLE - REGISTER SERVICE - REGISTER USER -> INVALID USERNAME!");
                throw new InvalidUsernameException("Invalid username! Only alphabetical letters and numbers are allowed!");
            }
        }

        return null;
    }

    public User login(LoginDTO loginDTO) throws UsernameNotFoundException
    {
        User expectedUser = userRepository.findByUsername(loginDTO.getUsername());

        if(expectedUser != null)
        {
            String introducedPassword = loginDTO.getPassword();

            if(Objects.equals(introducedPassword, expectedUser.getPassword()))
            {
                //login credentials are valid
                return expectedUser;
            }
            else
            {
                LOGGER.error("Incorrect password!");
            }
        }
        else
        {
            throw new UsernameNotFoundException("There is no user with this username in the database!");
        }

        return null;
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    @Transactional
    public void delete(User userToDelete)
    {
        List<Device> userDevices = deviceService.filterDevicesByUser(userToDelete);

        for(Device device : userDevices)
        {
            deviceService.delete(device);
        }

        userRepository.delete(userToDelete);
        userRepository.flush();
    }

    public void editUser(User userToEdit, String newUsername) throws InvalidUsernameException
    {
        RegisterValidator validator = RegisterValidator.getInstance();

        if(validator.checkUsername(newUsername))
        {
            userToEdit.setUsername(newUsername);
            userRepository.save(userToEdit);
        }
        else
            throw new InvalidUsernameException("The new username is not valid!");
    }

    public void changeRole(User userToChange)
    {
        userToChange.setRole(UserRole.ADMIN);
        userRepository.save(userToChange);
    }
}
