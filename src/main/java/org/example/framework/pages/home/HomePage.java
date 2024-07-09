package org.example.framework.pages.home;

import org.example.framework.pages.BasePage;
import org.example.framework.pages.authentication.NavigateToAuthentication;

public class HomePage extends BasePage {

    public static HomePage getInstance(){
        return new HomePage();
    }

    public NavigateToAuthentication navigateToAuthentication(){
        return new NavigateToAuthentication();
    }
}
