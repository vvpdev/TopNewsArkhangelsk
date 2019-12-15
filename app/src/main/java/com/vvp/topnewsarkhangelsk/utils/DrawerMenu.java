package com.vvp.topnewsarkhangelsk.utils;


        // item для NavigationDrawer

import com.vvp.topnewsarkhangelsk.R;

import java.util.ArrayList;

public class DrawerMenu {

    private int title;
    private int icon;


    private static ArrayList<DrawerMenu> arrayDrawerItems = new ArrayList<>();

    private DrawerMenu(int title, int icon) {
        this.title = title;
        this.icon = icon;
    }


    public static ArrayList<DrawerMenu> getArrayDrawerMenu(){

        if (arrayDrawerItems.isEmpty()){
            arrayDrawerItems.add(new DrawerMenu(R.string.settingAction, R.drawable.setting_icon));
            arrayDrawerItems.add(new DrawerMenu(R.string.aboutAppText, R.drawable.about_app_icon));
        }

        return arrayDrawerItems;
    }






}
