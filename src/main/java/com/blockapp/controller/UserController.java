package com.blockapp.controller;

import com.blockapp.entity.User;
import com.blockapp.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404", description ="Trabajador no encontrado")
    })
    public ResponseEntity<List<User>> findAll() {
        try {
            List<User> users = userService.getAll();
            if (users.size() > 0)
                return new ResponseEntity<>(users, HttpStatus.OK);
            else
                return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/{userId}"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404", description ="Trabajador no encontrado")
    })
    public ResponseEntity<User> findById(@PathVariable("userId") Long userId) {
        try {
            Optional<User> user = userService.getById(userId);
            if (!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404", description ="Trabajador no encontrado")
    })
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        try {
            User userNew = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userNew);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping({"/{userId}"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404", description ="Trabajador no encontrado")
    })
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody User user) {
        try {
            Optional<User> userUp = userService.getById(userId);
            if(!userUp.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            user.setId(userId);
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping({"/{userId}"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404", description ="Trabajador no encontrado")
    })
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) {
        try {
            Optional<User> userDelete = userService.getById(userId);
            if(!userDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}