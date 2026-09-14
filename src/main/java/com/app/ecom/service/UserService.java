package com.app.ecom.service;
import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.ecom.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService  {

//    private List<User> userList=new ArrayList<>();
//    private Long nextId=1L;
    @Autowired
    private UserRepository userRepository;


    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        if(user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResponse.setAddress(addressDTO);
        }
        return userResponse;
    }

    private void updateUserFromRequest(User user,UserRequest userRequest)
    {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }
    }


    public List<UserResponse> fetchAllUsers()
    {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }


    public void addUser(UserRequest userRequest){
//        user.setId(nextId++);
        User user = new User();
        updateUserFromRequest(user,userRequest);

        userRepository.save(user);
//        userList.add(user);
//        return userList;
    }




    public Optional<UserResponse> fetchUser(Long id) {
//        for(User user:userList){
//            if(user.getId().equals(id)){
//                return user;
//            }
//        }
//        return null;

//        return userList.stream()
//                .filter(user->user.getId().equals(id))
//                .findFirst();
        return userRepository.findById(id)
                .map(this::mapToUserResponse);

    }



    public boolean updateUser(Long id, UserRequest updatedUserRequest){
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
                    updateUserFromRequest(existingUser,updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
            }


}
