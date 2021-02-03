package com.nexters.winepick.user.repository;

import com.nexters.winepick.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}