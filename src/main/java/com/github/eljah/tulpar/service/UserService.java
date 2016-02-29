package com.github.eljah.tulpar.service;

import com.github.eljah.tulpar.form.UserRegistrationForm;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void saveNewUser(UserRegistrationForm form);

    void securedMethod();

}
