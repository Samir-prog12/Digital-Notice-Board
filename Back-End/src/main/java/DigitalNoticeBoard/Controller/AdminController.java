package DigitalNoticeBoard.Controller;


import DigitalNoticeBoard.Dto.RegisterRequestDto;
import DigitalNoticeBoard.Entity.User;
import DigitalNoticeBoard.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDto registerRequestDto){
        return  ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDto));
    }

}
