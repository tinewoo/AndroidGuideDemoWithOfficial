package com.official.android.androidofficialguidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainCatalogueActivity extends AppCompatActivity {

    private ListView lv_main_catalogue;
    public static final String ARRAYRESOURCES = "arrayResources";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_catalogue);
        lv_main_catalogue = (ListView)findViewById(R.id.lv_main_catalogue);
        lv_main_catalogue.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.main_catalogue)));
        lv_main_catalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainCatalogueActivity.this, DetailCatalogueActivity.class);
                Bundle bundle = new Bundle();
                switch (position){
                    case 0: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue1); break;
                    case 1: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue2); break;
                    case 2: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue3); break;
                    case 3: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue4); break;
                    case 4: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue5); break;
                    case 5: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue6); break;
                    case 6: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue7); break;
                    case 7: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue8); break;
                    case 8: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue9); break;
                    case 9: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue10); break;
                    case 10: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue11); break;
                    case 11: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue12); break;
                    case 12: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue13); break;
                    case 13: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue14); break;
                    case 14: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue15); break;
                    case 15: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue16); break;
                    case 16: bundle.putInt(ARRAYRESOURCES, R.array.detail_catalogue17); break;

                    default: break;
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_catalogue, menu);
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
