package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.form.UserRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import com.github.eljah.tulpar.model.User;
import com.github.eljah.tulpar.repository.UserRepository;
import com.github.eljah.tulpar.service.UserService;
import com.github.eljah.tulpar.util.UserRegistrationFormToUserTransformer;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveNewUser(UserRegistrationForm form) {
        User user = UserRegistrationFormToUserTransformer.transform(form);
        userRepository.save(user);
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @Override
    public void securedMethod() {
        //FIXME
    }


}
