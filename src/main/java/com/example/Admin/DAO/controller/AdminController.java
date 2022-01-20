package com.example.Admin.DAO.controller;

import com.example.Admin.DAO.Model.Admin;
import com.example.Admin.DAO.repository.Adminrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    private Adminrepository adminRepository;

    @GetMapping("/Admins")
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/Admins/{id_Admin}")
    public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id_Admin") int adminId) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id :: " +adminId));
        return ResponseEntity.ok().body(admin);
    }

    @PostMapping("/Admins")
    public Admin createEmployee(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/Admins/{id_Admin}")
    public ResponseEntity<Admin>updateEmployee(@PathVariable(value = "id_Admin")int id_Admin, @Validated @RequestBody Admin adminDetails)throws ResourceNotFoundException
    {
        Admin admin = adminRepository.findById(id_Admin).orElseThrow(()->new ResourceNotFoundException("Admin not found for this id :: "+id_Admin));
        admin.setNom(adminDetails.getNom());
        admin.setPrenom(adminDetails.getPrenom());
        admin.setEmail(adminDetails.getEmail());
        admin.setPassword(admin.getEmail());
        final Admin updatedAdmin =adminRepository.save(admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/Admins/{id_Admin}")
    public Map<String, Boolean> deleteAdmin(@PathVariable(value="admin_Id")int id_Admin, @Validated @RequestBody Admin adminDetails)throws ResourceNotFoundException
    {
        Admin admin=adminRepository.findById(id_Admin).orElseThrow(()->new ResourceNotFoundException(("Admin not found for this id :: "+id_Admin)));
        adminRepository.delete(admin);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}