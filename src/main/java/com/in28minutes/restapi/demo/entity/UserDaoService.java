package com.in28minutes.restapi.demo.entity;


import com.in28minutes.restapi.demo.dto.UserDto;
import com.in28minutes.restapi.demo.exception.UserNotFoundException;
import com.in28minutes.restapi.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {
    // JPA/Hibernate > Database
    // UserDaoService > Static List
    @Autowired
    UserRepository userRepository;
    private static List<User> users = new ArrayList<>();


//    static {
//        users.add(new User(1,"Adam",LocalDate.now().minusYears(30)));
//        users.add(new User(2,"Eve",LocalDate.now().minusYears(25)));
//        users.add(new User(3,"Jim",LocalDate.now().minusYears(20)));
//    }

    public List<User> findAll() {
//        {
//            return users;
//        }
        return userRepository.findAll();
    }

    //public User save(User user) {

    public User findOne(Integer id) {
//        Predicate<? super User> predicate = user -> user.getId().equals(id);
//        return users.stream().filter(predicate).findFirst().orElse(null);

//        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found."));
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new UserNotFoundException("User with id " + id + " is not found.");
        }
    }

    public User createUser(UserDto request){
        User user = new User();
        user.setName(request.getName());
        user.setBirthDate(request.getBirthDate());
        return user;
    }

    public void deleteUser(Integer id){
//        Predicate<? super User> predicate = user -> user.getId().equals(id);
//        users.removeIf(predicate);
        userRepository.deleteById(id);
    }
}
