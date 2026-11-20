package in.dhruvsood.chaiauth.controllers;

import in.dhruvsood.chaiauth.dtos.LoginDto;
import in.dhruvsood.chaiauth.dtos.SignUpDto;
import in.dhruvsood.chaiauth.dtos.UserDto;
import in.dhruvsood.chaiauth.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = authService.signUp(signUpDto);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto,

                                        HttpServletResponse response
    ) {
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
//       when deployed website use this to for https -> cookie.setSecure(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
