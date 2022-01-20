package com.example.Admin.DAO.repository;

import com.example.Admin.DAO.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Adminrepository extends JpaRepository<Admin, Integer> {
}
