package br.com.gcm.sac.setor_armamento.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.dto.UserDTO;
import br.com.gcm.sac.setor_armamento.model.UserModel;
import br.com.gcm.sac.setor_armamento.repository.UserRepository;
import br.com.gcm.sac.setor_armamento.service.TokenService;
import br.com.gcm.sac.setor_armamento.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;
    
    @GetMapping("/")
    public ResponseEntity<List<UserModel>> listAll(){
        return ResponseEntity.ok().body(userService.listAll());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserModel user){
        userService.save(user);
        return ResponseEntity.ok("Objeto Salvo");
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "Usuário desconectado!"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    //Autentica usuário no sistema
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
            new UsernamePasswordAuthenticationToken(userDTO.login(),
                    userDTO.password());

        Authentication authenticate = this.authenticationManager
            .authenticate(usernamePasswordAuthenticationToken);

        var usuario = (UserModel) authenticate.getPrincipal();

        return ResponseEntity.ok(tokenService.gerarToken(usuario));
    }
}
