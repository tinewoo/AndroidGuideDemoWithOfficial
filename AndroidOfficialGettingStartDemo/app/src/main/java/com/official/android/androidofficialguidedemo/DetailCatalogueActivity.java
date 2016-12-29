package com.official.android.androidofficialguidedemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.official.android.androidofficialguidedemo.R;

import java.util.List;

public class DetailCatalogueActivity extends AppCompatActivity {

    private ListView lv_detail_catalogue;
    private String[] intentActionArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catalogue);
        lv_detail_catalogue = (ListView)findViewById(R.id.lv_detail_catalogue);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.getInt(MainCatalogueActivity.ARRAY_INTENT_ACTION, 0) != 0){
                intentActionArray = getResources().getStringArray(bundle.getInt(MainCatalogueActivity.ARRAY_INTENT_ACTION));
            }

            lv_detail_catalogue.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(bundle.getInt(MainCatalogueActivity.ARRAYRESOURCES))));
            lv_detail_catalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(intentActionArray != null && intentActionArray.length > 0){
                    Intent intent = new Intent();
                    intent.setAction(intentActionArray[position]);

                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                    boolean isIntentSafe = activities.size() > 0;
                    if(isIntentSafe){
                        startActivity(intent);
                    }
                }


//                    switch (position) {
//                        case 0:
//                            break;
//                        case 1:
//                            break;
//                        case 2:
//                            break;
//                        case 3:
//                            break;
//                        case 4:
//                            break;
//                        case 5:
//                            intent.setClass(DetailCatalogueActivity.this, SavingDataActivity.class);
//                            break;
//                        case 6:
//                            break;
//                        case 7:
//                            break;
//                        case 8:
//                            break;
//                        case 9:
//                            break;
//                        case 10:
//                            break;
//                        case 11:
//                            break;
//                        case 12:
//                            break;
//                        case 13:
//                            break;
//                        case 14:
//                            break;
//                        case 15:
//                            break;
//                        case 16:
//                            break;
//
//                        default:
//                            break;
//                    }
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_catalogue, menu);
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
