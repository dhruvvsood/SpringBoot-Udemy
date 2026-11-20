package in.dhruvsood.chaiauth.services;

import in.dhruvsood.chaiauth.dtos.LoginDto;
import in.dhruvsood.chaiauth.dtos.SignUpDto;
import in.dhruvsood.chaiauth.dtos.UserDto;
import in.dhruvsood.chaiauth.entities.User;
import in.dhruvsood.chaiauth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDto signUp(SignUpDto signUpDto) {
//        find user by email
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());

        if (user.isPresent()) {
            throw new BadCredentialsException("User with email :" + signUpDto.getEmail() + " already exists");
        }

        User toBeCreated = modelMapper.map(signUpDto, User.class);
        toBeCreated.setPassword(passwordEncoder.encode(toBeCreated.getPassword()));

        User savedUser = userRepository.save(toBeCreated);

        return modelMapper.map(savedUser, UserDto.class);

//        if user exist throw error
//        if not , change it to userClass
//        hash password
//            save user
//        return dto class


    }

    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

       String token = jwtService.generateToken(user);


       return token;
    }
}
