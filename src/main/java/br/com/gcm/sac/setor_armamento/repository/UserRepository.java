package br.com.gcm.sac.setor_armamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.gcm.sac.setor_armamento.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    public Optional<User> findByLogin(String login);
}
