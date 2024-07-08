package com.food.Osahaneat.service;

import com.food.Osahaneat.Repository.UserRepository;
import com.food.Osahaneat.dto.UserDTO;
import com.food.Osahaneat.entity.Roles;
import com.food.Osahaneat.entity.Users;
import com.food.Osahaneat.payLoad.SignUpRequest;
import com.food.Osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    UserRepository userRepository;


@Override
    public boolean checkLogin(String email, String password) {
    List<Users> listUser=userRepository.findByEmailAndPassword(email,password);

    return !listUser.isEmpty();
}

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        //Thêm User vào db
        Roles roles=new Roles();
        roles.setId(signUpRequest.getRole_id());

        Users user=new Users();
        user.setFullname(signUpRequest.getHo_ten());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getMat_khau());
        user.setRole(roles);
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
