package com.dougfsilva.e_AGE.domain.user;

import com.dougfsilva.e_AGE.domain.exception.InvalidUserOrPasswordException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class Password {
    
    private String password;
    
    public Password(String password, PasswordEncoder encoder) {
        
        if (!password.matches("^(.*[0-9]).*$")) {
            throw new InvalidUserOrPasswordException("The password must contain at least one digit!");
        }
        if (!password.matches("^(?=.*[A-Z]).*$")) {
            throw new InvalidUserOrPasswordException("The password must contain at least one uppercase letter!");
        }
        if (!password.matches("^(?=.*[a-z]).*$")) {
            throw new InvalidUserOrPasswordException("The password must contain at least one lowercase letter!");
        }
        if (!password.matches("^(?=.*[@#$%^&-+=().]).*$")) {
            throw new InvalidUserOrPasswordException("The password must contain at least one special character!");
        }
        if (!password.matches("^(?=\\S+$).*$")) {
            throw new InvalidUserOrPasswordException("The password must not contain any white spaces!");
        }
        if (password.length() < 6 || password.length() > 20) {
            throw new InvalidUserOrPasswordException("The password must be between 6 and 20 characters long!");
        }
        this.password = encoder.encode(password);
    }

}
