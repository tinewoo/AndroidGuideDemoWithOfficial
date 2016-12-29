package com.official.android.androidofficialguidedemo.ServerClientDemoName;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.official.android.androidofficialguidedemo.R;
import com.official.android.androidofficialguidedemo.tools.Constants;

public class ServerClientDemoNameActivity extends AppCompatActivity {

    TextView tv_demoname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_client_demo_name);
        tv_demoname = (TextView)findViewById(R.id.tv_demoname);
        tv_demoname.setText(getIntent().getIntExtra(Constants.STRINGRESOURCEID, R.string.hello_world));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_server_client_demo_name, menu);
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
