package com.app.ecom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
@RestController
public class UserController {

//    else add a constructor or a lombok based annotation RequredArgsConstructor and use a final as it will only pass it to the constructor

    @Autowired
    private UserService userService;
//    private List<User> userList=new ArrayList<>();
//    @RequestMapping(value="/api/users",method=RequestMethod.GET)
    @GetMapping("/")
    public String home() {
        return "Spring Boot is running!";
    }
    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
        //return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
    }


    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
//        User user = userService.fetchUser(id);
//
//        if(user == null){
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            return ResponseEntity.notFound().build();
//
//        }return ResponseEntity.ok(user);
////        return ResponseEntity.ok(userService.fetchUser(id));
    }
    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User Added successfully!");
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id ,@RequestBody User updatedUser){
       boolean updated = userService.updateUser(id, updatedUser);
       if(updated){
           return ResponseEntity.ok("User Updated successfully!");
       }
       else return ResponseEntity.notFound().build();
    }


}
