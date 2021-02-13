package uz.zako.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.zako.springboot.domain.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserName(String userName);
    Boolean existsByUserName(String userName);
}
