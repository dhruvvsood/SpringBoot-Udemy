package in.dhruvsood.chaiauth;

import in.dhruvsood.chaiauth.entities.User;
import in.dhruvsood.chaiauth.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChaiAuthApplicationTests {

    @Autowired
    private JwtService jwtService;

    @Test
    void testJwtToken() {
        User user = new User(1L, "dhruvsood1102@gmail.com", "Dhruv", "1231243");

        String token = jwtService.generateToken(user);

        System.out.println("Token ::" + token);

        Long userId = jwtService.getUserIdFromToken(token);

        System.out.println("User ID :: " + userId);
    }

}
