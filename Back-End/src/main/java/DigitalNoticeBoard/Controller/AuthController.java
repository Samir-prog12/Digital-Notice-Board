package DigitalNoticeBoard.Controller;

import DigitalNoticeBoard.Dto.LoginRequestDto;
import DigitalNoticeBoard.Dto.LoginResponseDto;
import DigitalNoticeBoard.Dto.RegisterRequestDto;
import DigitalNoticeBoard.Entity.User;
import DigitalNoticeBoard.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
   private AuthenticationService authenticationService;

    @PostMapping("/register-normal-user")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDto request){
        return  ResponseEntity.ok(authenticationService.registerNormalUser(request));
    }
    @PostMapping("/login")

    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return  ResponseEntity.ok(authenticationService.login(loginRequestDto));
    }
}
