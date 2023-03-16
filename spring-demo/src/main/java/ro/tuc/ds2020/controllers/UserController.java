package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.business.dto.EditUserDTO;
import ro.tuc.ds2020.business.dto.LoginDTO;
import ro.tuc.ds2020.business.dto.RegisterDTO;
import ro.tuc.ds2020.business.exception.InvalidUsernameException;
import ro.tuc.ds2020.business.exception.UsernameAlreadyExistsException;
import ro.tuc.ds2020.business.exception.UsernameNotFoundException;
import ro.tuc.ds2020.model.User;
import ro.tuc.ds2020.business.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) throws InvalidUsernameException, UsernameAlreadyExistsException
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(registerDTO));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) throws UsernameNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginDTO));
    }

    @GetMapping(value = "/get-users")
    public ResponseEntity<List<User>> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping(value = "/delete")
    public void delete(@RequestBody User userToDelete)
    {
        userService.delete(userToDelete);
        ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping(value = "/change-role")
    public void changeRole(@RequestBody User userToChange)
    {
        userService.changeRole(userToChange);
        ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping(value = "/edit")
    public void edit(@RequestBody EditUserDTO editUserDTO) throws InvalidUsernameException
    {
        userService.editUser(editUserDTO.getUserToEdit(), editUserDTO.getNewUsername());
        ResponseEntity.status(HttpStatus.OK);
    }
}
