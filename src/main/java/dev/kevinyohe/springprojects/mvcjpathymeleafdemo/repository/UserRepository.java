package dev.kevinyohe.springprojects.mvcjpathymeleafdemo.repository;


import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {
}
