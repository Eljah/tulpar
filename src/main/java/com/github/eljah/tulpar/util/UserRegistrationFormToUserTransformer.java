package com.github.eljah.tulpar.util;


import com.github.eljah.tulpar.form.UserRegistrationForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.github.eljah.tulpar.model.User;
import com.github.eljah.tulpar.model.enums.UserRole;

public class UserRegistrationFormToUserTransformer {

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static User transform(UserRegistrationForm form) {
        if (form == null) {
            return null;
        }
        User user = new User();
        user.setUsername(form.getUsername());
        user.setRole(UserRole.ROLE_USER);
        user.setPassword(encoder.encode(form.getPassword()));
        return user;
    }
}
