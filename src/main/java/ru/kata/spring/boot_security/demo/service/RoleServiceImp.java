package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Role> findByNameIn(List<String> roleNames) {
        return roleRepository.findByNameIn(roleNames);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
