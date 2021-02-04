package rs.telnet.StudentService.service;

import rs.telnet.StudentService.dto.UserDTO;
import rs.telnet.StudentService.model.User;
import rs.telnet.StudentService.repository.UserRepository;
import rs.telnet.StudentService.security.AuthenticationTokenService;
import rs.telnet.StudentService.security.TokenService;
import rs.telnet.StudentService.security.exception.UserNotFoundHttpException;
import rs.telnet.StudentService.security.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import rs.telnet.StudentService.service.RoleService;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;
    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper,
                       RoleService roleService,
                       AuthenticationTokenService authenticationTokenService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authenticationTokenService = authenticationTokenService;
        this.roleService = roleService;
    }


    public User findByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + username + " not found"));
    }

    public UserDTO getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundHttpException("User with id: " + id + " not found", HttpStatus.NOT_FOUND)
        );

        return modelMapper.map(existingUser, UserDTO.class);
    }
}