package com.official.android.androidofficialguidedemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by user on 15/11/10.
 */
public class OtherListActivity extends Activity {

    private ListView lv_detail_catalogue;
    private String[] intentActionArray;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

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


                }
            });
        }
    }
}
