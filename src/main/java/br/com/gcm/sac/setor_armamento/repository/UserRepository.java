package br.com.gcm.sac.setor_armamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gcm.sac.setor_armamento.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{
    // @Query("select i from User i where i.username = :username")
    // List<User> findByUsername(String username);

    // @Query("select j from User j where j.username = :username and j.password = :password")
    // public UserModel findByUser(String username, String password);

    UserModel findByUsername(String login);
}