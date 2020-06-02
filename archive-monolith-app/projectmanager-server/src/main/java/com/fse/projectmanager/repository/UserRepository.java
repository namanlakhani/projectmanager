package com.fse.projectmanager.repository;

import org.springframework.stereotype.Repository;

import com.fse.projectmanager.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

}
