package michaeljansen.cput.ac.za.dinermenuitems.Repositories;

import java.util.List;

import michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem;

/**
 * Created by Michael on 25/09/2015.
 */
public interface RestAPIMenuItem {

    String addMenuItem(MenuItem menuItem);

    String updateMenuItem(MenuItem menuItem);

    List<MenuItem> getMenuItems();

    MenuItem getMenuItem(int id);

    void  deleteMenuItem(int id);

}
