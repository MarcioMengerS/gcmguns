package br.com.gcm.sac.setor_armamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.gcm.sac.setor_armamento.model.UserModel;
import br.com.gcm.sac.setor_armamento.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    UserRepository userRepository;
    
    public UserModel save(UserModel user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<UserModel> listAll(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
