package dev.akhileshaher.moviestation.repository;

import dev.akhileshaher.moviestation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
