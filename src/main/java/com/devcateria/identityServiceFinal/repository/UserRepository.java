package com.devcateria.identityServiceFinal.repository;

import com.devcateria.identityServiceFinal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
