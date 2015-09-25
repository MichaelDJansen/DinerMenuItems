package michaeljansen.cput.ac.za.dinermenuitems.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import michaeljansen.cput.ac.za.dinermenuitems.R;
import michaeljansen.cput.ac.za.dinermenuitems.Services.Impl.MenuItemServiceImpl;

public class CreateItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        MenuItemServiceImpl service = new MenuItemServiceImpl();

        List<michaeljansen.cput.ac.za.dinermenuitems.Model.MenuItem> menuItemArrayList = service.getMenuItems();

        final Button btnCreate = (Button)findViewById(R.id.btnCreateItem);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinType);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {

                String workRequestType = arg0.getItemAtPosition(pos)
                        .toString();
/*
                if (pos != 0)
                    Toast.makeText(WorkOrderOpen.this, workRequestType,
                            Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        /*List<String> types = new ArrayList<String>();
        types.add("Burgers");
        types.add("Milkshakes");
        types.add("Chilli Dogs");
        */
        List<String> types = new ArrayList<String>();

        for (int y = 0;y < menuItemArrayList.size();y++){
            if(!(types.contains(menuItemArrayList.get(y).getType()))){
              types.add(menuItemArrayList.get(y).getType());
            }
        }

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
