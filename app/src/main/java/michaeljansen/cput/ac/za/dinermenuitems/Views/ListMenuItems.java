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
import android.widget.ListView;
import android.widget.Toast;

import michaeljansen.cput.ac.za.dinermenuitems.R;

public class ListMenuItems extends AppCompatActivity {

    ListView listView ;
    michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_items);

        listView = (ListView) findViewById(R.id.lstViewItems);

        final michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem[] menuItems =
                new michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem[]{
                new michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem.
                        Builder(305,"Western Chili Dog","Chili Dog","Chili dog with beans",30.00f).extras("Hot sauce").build(),
                        new michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem.
                        Builder(306,"Harold's hot hamburger","Burger","Burger with Harold's special hot sauce blend",45.00f).extras("extra hot sauce").build(),
                        new michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem.
                        Builder(307,"Travis Terrific hamburger","Burger","Burger with relish and bacon",60.00f).build()
                };

        String[] values = new String[menuItems.length];
        for(int x = 0 ;x < menuItems.length ; x++)
        {
         values[x] = menuItems[x].getItemName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                menuItem = menuItems[position];
                Toast.makeText(getApplicationContext(),
                        "Position :" + position + "\n" + "List Item :" + menuItem.getItemName() + "\n" +
                        "Description :" + menuItem.getDescription() + "\n" +
                        "Type :" + menuItem.getType() + "\n" +
                        "Price :" + menuItem.getPrice(), Toast.LENGTH_LONG)
                        .show();

                /*AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                alertDialog.setTitle(itemValue);
                alertDialog.setMessage("Position :" + position + "\n" + "List Item :"  + itemValue);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });
                alertDialog.show();
                */
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
