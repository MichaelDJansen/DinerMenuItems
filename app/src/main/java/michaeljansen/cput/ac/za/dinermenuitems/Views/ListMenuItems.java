package michaeljansen.cput.ac.za.dinermenuitems.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import michaeljansen.cput.ac.za.dinermenuitems.R;
import michaeljansen.cput.ac.za.dinermenuitems.Services.Impl.MenuItemServiceImpl;
import michaeljansen.cput.ac.za.dinermenuitems.Services.MenuItemService;

public class ListMenuItems extends AppCompatActivity {

    ListView listView ;
    michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem menuItem;
    List<michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem> menuItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_items);

        listView = (ListView) findViewById(R.id.lstViewItems);


        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    MenuItemServiceImpl service = new MenuItemServiceImpl();
                    menuItems = service.getMenuItems();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            thread.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        ArrayList<String> values = new ArrayList<String>();
        for(int x = 0 ;x < menuItems.size() ; x++)
        {
            values.add(menuItems.get(x).getItemName());
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                menuItem = menuItems.get(pos);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            MenuItemServiceImpl service = new MenuItemServiceImpl();
                            service.deleteMenuItem(menuItem.getMenuItemId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();

                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(),
                        menuItem.getItemName() + " Deleted", Toast.LENGTH_LONG)
                        .show();
                menuItems.remove(menuItem);
                adapter.notifyDataSetChanged();

                return true;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                menuItem = menuItems.get(position);

                Toast.makeText(getApplicationContext(),
                        "List Item :" + menuItem.getItemName() + "\n" +
                                "Description :" + menuItem.getDescription() + "\n" +
                                "Type :" + menuItem.getType() + "\n" +
                                "Price :" + menuItem.getPrice(), Toast.LENGTH_LONG)
                        .show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
