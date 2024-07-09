package org.example.framework.pages.home;

import org.example.framework.pages.BasePage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class HomePage extends BasePage {

    @Contract(" -> new")
    public static @NotNull HomePage getInstance(){
        return new HomePage();
    }

    public NavigateToAuthentication navigateToAuthentication(){
        return new NavigateToAuthentication();
    }

    public NavigateToProductDropdown navigateToProductDropdown(){
        return new NavigateToProductDropdown();
    }
}
