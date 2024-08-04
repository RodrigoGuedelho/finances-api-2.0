package com.guedelho.finances_api_20.repositories;

import com.guedelho.finances_api_20.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.login = :login and u.status = 'ACTIVE'")
    public User findByLogin(String login);
}
