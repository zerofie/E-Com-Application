package com.app.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

//    private List<User> userList=new ArrayList<>();
//    private Long nextId=1L;
    @Autowired
    private UserRepository userRepository;


    public List<User> fetchAllUsers()
    {
        return userRepository.findAll();
    }
    public void addUser(User user){
//        user.setId(nextId++);
        userRepository.save(user);
//        userList.add(user);
//        return userList;
    }

    public Optional<User> fetchUser(Long id) {
//        for(User user:userList){
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
//        return null;

//        return userList.stream()
//                .filter(user->user.getId().equals(id))
//                .findFirst();
        return userRepository.findById(id);
    }
    public boolean updateUser(Long id, User updatedUser){
//        return userList.stream()
//                .filter(user->user.getId().equals(id))
//                .findFirst()
//                .map(existingUser->{
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
//                    return true;
//                }).orElse(false);
//
//
        return userRepository.findById(id)
                .map(existingUser->{
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
            }


}
