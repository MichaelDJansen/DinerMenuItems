package michaeljansen.cput.ac.za.dinermenuitems.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import michaeljansen.cput.ac.za.dinermenuitems.R;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /*final Button btnCreate = (Button)findViewById(R.id.btnCreateItem);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreator();
            }
        });

        final Button btnView = (Button)findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v) {
            openList();
        }
    });*/
    }

    public void openCreator(View v) {
        Intent createItemIntent = new Intent(this,CreateItem.class);
        startActivity(createItemIntent);
        //v.findViewById()
    }

    public void openList(View v){
        Intent listItemsIntent = new Intent(this,ListMenuItems.class);
        startActivity(listItemsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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
