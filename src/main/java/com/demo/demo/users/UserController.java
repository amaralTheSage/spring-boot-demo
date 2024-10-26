package com.demo.demo.users;

import jakarta.websocket.server.PathParam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAll(){
        return this.service.getAll();
    }

    @PostMapping("/create")
    public User create(@RequestBody @NotNull User body){
       body.setDob(LocalDate.parse(body.getDob()));

       return this.service.create(body);
    }

    @DeleteMapping("/delete/{id}")
    public User delete(@PathVariable("id") Long id){
        return this.service.delete(id);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable("id") Long id, @RequestParam(required = false) String name,
                       @RequestParam(required = false) String email ){
        return this.service.update(id, name, email );
    }
}
