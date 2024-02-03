package ru.gb.hw3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.hw3.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam Integer age) {
        User user = userService.createUser(name, email, age);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        boolean deletedUser = userService.deleteUser(id);
        if (deletedUser) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<User>> sortUsers(@RequestParam(name = "field", defaultValue = "name") String field) {
        List<User> users = userService.getSortedUsers(field);
        if (users != null) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<User>> filterByAge(@RequestParam Integer age) {
        List<User> users = userService.filterByAge(age);
        if (users != null) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.notFound().build();
    }
}
