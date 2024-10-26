package com.demo.demo.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll(){
       return this.repository.findAll();
    }

    public User create(User body){
        Optional<User> emailAlreadyInUse = this.repository.findUserByEmail(body.getEmail());

        if(emailAlreadyInUse.isPresent()){
            throw new IllegalStateException("Email is taken");
        }

        return this.repository.save(body);
    }


    public User delete(Long id){
        if(!this.repository.existsById(id)){
            throw new IllegalStateException("User não encontrado");
        }
        User user = this.repository.findById(id).get();

        this.repository.deleteById(id);
        return user;
    }


    private boolean checkIfChangeable(String att){
        return att != null && !att.isEmpty();
    }

    @Transactional
    public User update(Long id, String name, String email){
        User user = this.repository.findById(id).orElseThrow(()-> new IllegalStateException("User não encontrado"));

        if(name != null && !name.isEmpty()){
            user.setName(name);
        }
        if(email != null && !email.isEmpty()){
            user.setEmail(email);
        }

        return user;
    }


}
