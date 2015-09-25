package michaeljansen.cput.ac.za.dinermenuitems.Services.Impl;

import java.util.List;

import michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem;
import michaeljansen.cput.ac.za.dinermenuitems.Repositories.RestAPIMenuItem;
import michaeljansen.cput.ac.za.dinermenuitems.Repositories.rest.RestMenuItemAPI;

/**
 * Created by Michael on 25/09/2015.
 */
public class MenuItemServiceImpl {

    RestAPIMenuItem rest = new RestMenuItemAPI();

    public void addMenuItem(MenuItem menuItem){
        rest.addMenuItem(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem){
        rest.updateMenuItem(menuItem);
    }

    public List<MenuItem> getMenuItems(){
        return rest.getMenuItems();
    }

    public MenuItem getMenuItem(int id){
        return rest.getMenuItem(id);
    }

    public void  deleteMenuItem(int id){rest.deleteMenuItem(id); }

}
