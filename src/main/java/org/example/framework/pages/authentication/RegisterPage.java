package org.example.framework.pages.authentication;

import org.example.framework.enums.LogType;
import org.example.framework.pages.BasePage;
import org.example.framework.utils.LoggerUtils;

public class RegisterPage extends BasePage {

    public  <T>T register(String firstName,String lastName,String email,String password,String confirmation,Class<T>nextPageClass){
        try{

            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to complete registration process "+e);
            throw new RuntimeException("Error during registration",e);
        }
    }
}
