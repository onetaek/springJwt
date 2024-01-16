package study.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springjwt.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
