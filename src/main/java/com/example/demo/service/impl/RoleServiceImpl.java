package com.example.demo.service.impl;


import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleRepository.findRoleById(id);
    }


}
