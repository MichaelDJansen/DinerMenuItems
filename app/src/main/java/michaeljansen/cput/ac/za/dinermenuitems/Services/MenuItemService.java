package michaeljansen.cput.ac.za.dinermenuitems.Services;

import java.util.List;

import michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem;

/**
 * Created by Michael on 25/09/2015.
 */
public interface MenuItemService {

    void addMenuItem(MenuItem menuItem);

    void updateMenuItem(MenuItem menuItem);

    List<MenuItem> getMenuItems();

    MenuItem getMenuItem(int id);

    void  deleteMenuItem(int id);
}
