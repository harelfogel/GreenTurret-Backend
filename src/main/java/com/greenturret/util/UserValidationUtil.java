package com.greenturret.util;

import com.greenturret.dto.UserDTO;
import com.greenturret.exception.UsernameAlreadyExistsException;
import com.greenturret.exception.ValidationException;
import com.greenturret.response.ErrorConsts;

public class UserValidationUtil {

    public static void validateUser(UserDTO userDTO) throws ValidationException {

        if (!UserValidationUtil.isValidUsername(userDTO.getUsername())) {
            throw new UsernameAlreadyExistsException(ErrorConsts.INVALID_USERNAME);
        }
        if (!UserValidationUtil.isValidPassword(userDTO.getPassword())) {
            throw new UsernameAlreadyExistsException(ErrorConsts.INVALID_PASWWORD);
        }

    }

    public static boolean isValidUsername (String username){
        return username!=null && username.length()>=3;
    }

    public static boolean isValidPassword(String password){
        return password!=null && password.length()>=6;
    }


}
