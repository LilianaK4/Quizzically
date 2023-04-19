package pl.lilianakrol.quizzically.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.lilianakrol.quizzically.models.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

     @PostMapping() User createUser(@RequestBody User user)
     {
         return user;
     }

     @GetMapping("/{idUser}") User getUserById(@PathVariable long idUser)
     {
         return null;
     }

     @PutMapping("/{idUser}") User updateUserById(@RequestBody User user, @PathVariable long idUser)
     {
         return user;
     }

     @DeleteMapping("/{idUser}") void deleteUser(@PathVariable long idUser)
     {
         return;
     }


}