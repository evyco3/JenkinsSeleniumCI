package org.example.framework.pages.home;

import org.example.framework.pages.BasePage;

public class HomePage extends BasePage {

    public static HomePage getInstance(){
        return new HomePage();
    }

    public NavigateToAuthentication navigateToAuthentication(){
        return new NavigateToAuthentication();
    }
}
