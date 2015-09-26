package michaeljansen.cput.ac.za.dinermenuitems.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import michaeljansen.cput.ac.za.dinermenuitems.R;
import michaeljansen.cput.ac.za.dinermenuitems.Services.Impl.MenuItemServiceImpl;

public class CreateItem extends AppCompatActivity {
    michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem menuItem;

    Boolean duplicate;
    int id;
    String itemName;
    String type;
    String description;
    float price;
    String extras;
    List<michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem> menuItemArrayList;
    CheckBox chkUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);


        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    MenuItemServiceImpl service = new MenuItemServiceImpl();
                    menuItemArrayList = service.getMenuItems();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }


        chkUpdate = (CheckBox) findViewById(R.id.chkUpdate);
        final EditText numItemID =(EditText) findViewById(R.id.numItemID);
        final EditText txtItemName = (EditText) findViewById(R.id.txtItemName);
        final Spinner spinner = (Spinner) findViewById(R.id.spinType);
        final EditText txtDescription = (EditText) findViewById(R.id.txtDescription);
        final EditText numPrice = (EditText) findViewById(R.id.numPrice);
        final EditText txtExtras = (EditText) findViewById(R.id.txtExtras);

        final Button btnCreate = (Button)findViewById(R.id.btnCreateItem);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numItemID.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(),
                            "Please enter a item ID", Toast.LENGTH_LONG)
                            .show();
                }
                else if(txtItemName.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter a item name", Toast.LENGTH_LONG)
                            .show();
                }
                else if(spinner.getSelectedItem().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),
                            "Please select a spinner value", Toast.LENGTH_LONG)
                            .show();
                }else if(txtDescription.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter a description", Toast.LENGTH_LONG)
                            .show();
                }else if(txtItemName.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter a item name", Toast.LENGTH_LONG)
                            .show();
                }else if(numPrice.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter a price", Toast.LENGTH_LONG)
                            .show();
                }
                else if(numItemID.getText().toString().length() != 3){
                    Toast.makeText(getApplicationContext(),
                            "Please enter a 3 digit Id", Toast.LENGTH_LONG)
                            .show();
                }
                else {
                    id = Integer.parseInt(numItemID.getText().toString());
                    itemName = txtItemName.getText().toString();
                    type = spinner.getSelectedItem().toString();
                    description = txtDescription.getText().toString();
                    price = Float.parseFloat(numPrice.getText().toString());
                    extras = txtExtras.getText().toString();
                    duplicate = false;

                    for (int z = 0; z < menuItemArrayList.size(); z++) {
                        if (menuItemArrayList.get(z).getMenuItemId() == id) {
                            duplicate = true;
                            if(!chkUpdate.isChecked())
                            {

                                Toast.makeText(getApplicationContext(),
                                    "Duplicate Item ID \n\n Please enter a new ID", Toast.LENGTH_LONG)
                                    .show();
                            }
                            break;
                        }
                        else if (menuItemArrayList.get(z).getItemName().equalsIgnoreCase(txtItemName.getText().toString())) {
                            duplicate = true;
                            if(!chkUpdate.isChecked())
                            {
                                Toast.makeText(getApplicationContext(),
                                        "Duplicate Item Name \n\n Please enter a new Item Name", Toast.LENGTH_LONG)
                                        .show();
                            }
                            break;
                        }
                    }

                    menuItem = new michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem.Builder(id, itemName, type, description, price).extras(extras).build();

                    if (!duplicate) {

                        if(chkUpdate.isChecked()){
                            Toast.makeText(getApplicationContext(),
                                    "No duplicate Item Id found to update", Toast.LENGTH_LONG)
                                    .show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Id :" + id + "\n" +
                                            "Item Name :" + itemName + "\n" +
                                            "Type :" + type + "\n" +
                                            "Description :" + description + "\n" +
                                            "Price :" + price + "\n" +
                                            "Extras:" + extras, Toast.LENGTH_LONG)
                                    .show();


                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        MenuItemServiceImpl service = new MenuItemServiceImpl();
                                        service.addMenuItem(menuItem);
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
                        }
                        finish();
                    }
                    else
                    {
                        if(chkUpdate.isChecked()){
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        MenuItemServiceImpl service = new MenuItemServiceImpl();
                                        service.updateMenuItem(menuItem);
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

                            finish();
                        }
                    }
                }
            }
        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {

                String workRequestType = arg0.getItemAtPosition(pos)
                        .toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        List<String> types = new ArrayList<String>();
        types.add("Alcoholic Beverage");
        types.add("Burgers");
        types.add("Breakfast");
        types.add("Chili Dogs");
        types.add("Milkshakes");
        types.add("Warm Beverages");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_item, menu);
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
