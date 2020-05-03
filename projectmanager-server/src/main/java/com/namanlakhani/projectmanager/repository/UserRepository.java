package com.namanlakhani.projectmanager.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.namanlakhani.projectmanager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

}
