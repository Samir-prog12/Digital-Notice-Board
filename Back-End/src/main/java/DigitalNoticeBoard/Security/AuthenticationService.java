package DigitalNoticeBoard.Security;

import DigitalNoticeBoard.Dto.LoginRequestDto;
import DigitalNoticeBoard.Dto.LoginResponseDto;
import DigitalNoticeBoard.Dto.RegisterRequestDto;
import DigitalNoticeBoard.Entity.User;
import DigitalNoticeBoard.Repository.UserRepository;
import DigitalNoticeBoard.jwt.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

   public User registerNormalUser(RegisterRequestDto registerRequestDto){

       if(userRepository.findByUsername(registerRequestDto.getUsername()).isPresent()){
           throw new RuntimeException("User Already exists");
       }

       Set<String> roles  = new HashSet<>();
       roles.add("ROLE_USER");
       User user  = new User();
       user.setUsername(registerRequestDto.getUsername());
       user.setEmail(registerRequestDto.getEmail());
       user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
       user.setRoles(roles);
       user.setIsEnabled(true);
return  userRepository.save(user);
   }


   public User registerAdminUser(RegisterRequestDto registerRequestDto){
       if(userRepository.findByUsername(registerRequestDto.getUsername()).isPresent()){
           throw new RuntimeException("User not found");
       }

       Set<String> roles = new HashSet<>();
       roles.add("ROLE_USER");
       roles.add("ROLE_ADMIN");

       User user  = new User();
       user.setUsername(registerRequestDto.getUsername());
       user.setEmail(registerRequestDto.getEmail());
       user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
       user.setRoles(roles);
       user.setIsEnabled(true);
       return  userRepository.save(user);

   }

   public LoginResponseDto login(LoginRequestDto loginRequestDto){
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),
                       loginRequestDto.getPassword())
       );

       User user  = userRepository.findByUsername(loginRequestDto.getUsername())
               .orElseThrow(()-> new RuntimeException("User not found"));

       String token  = jwtService.generateToken(user);
       return  LoginResponseDto.builder().
               token(token)
               .username(user.getUsername())
               .roles(user.getRoles())
               .build();
   }
}




































