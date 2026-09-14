package com.app.ecom.controller;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/users");
public class UserController {
//    else add a constructor or a lombok based annotation
//    RequredArgsConstructor and use a final as it will only pass it to the constructor
//    private final UserService userService
    @Autowired
    private UserService userService;
//    private List<User> userList=new ArrayList<>();
//    @RequestMapping(value="/api/users",method=RequestMethod.GET)


    @GetMapping("/")
    public String home() {
        return "Spring Boot is running!";
    }


    @GetMapping("api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
        //return new ResponseEntity<>(userService.fetchAllUsers(),HttpStatus.OK);
    }


    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
//        User user = userService.fetchUser(id);
//
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            return ResponseEntity.notFound().build();
//
//        }return ResponseEntity.ok(user);
//        return ResponseEntity.ok(userService.fetchUser(id));
    }


    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return ResponseEntity.ok("User Added successfully!");
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id ,@RequestBody UserRequest updatedUserRequest){
       boolean updated = userService.updateUser(id, updatedUserRequest);
       if(updated){
           return ResponseEntity.ok("User Updated successfully!");
       }
       else return ResponseEntity.notFound().build();
    }


}
