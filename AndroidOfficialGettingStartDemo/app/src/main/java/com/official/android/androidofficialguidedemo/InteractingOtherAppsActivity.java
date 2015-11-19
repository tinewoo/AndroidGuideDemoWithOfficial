package com.official.android.androidofficialguidedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.official.android.androidofficialguidedemo.interactingotherapps.IntentFilterActivity;
import com.official.android.androidofficialguidedemo.interactingotherapps.IntentResultActivity;
import com.official.android.androidofficialguidedemo.interactingotherapps.SendingUserAnotherAppActivity;
import com.official.android.androidofficialguidedemo.savingdata.SavingDatabaseActivity;
import com.official.android.androidofficialguidedemo.savingdata.SavingFileActivity;
import com.official.android.androidofficialguidedemo.savingdata.SavingPreferenceActivity;


public class InteractingOtherAppsActivity extends Activity {

    private ListView lv_detail_catalogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catalogue);

        lv_detail_catalogue = (ListView)findViewById(R.id.lv_detail_catalogue);
        lv_detail_catalogue.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.interactingwithotherapps)));
        lv_detail_catalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(InteractingOtherAppsActivity.this, SendingUserAnotherAppActivity.class);
                        break;
                    case 1:
                        intent.setClass(InteractingOtherAppsActivity.this, IntentResultActivity.class);

                        break;
                    case 2:
                        intent.setClass(InteractingOtherAppsActivity.this, IntentFilterActivity.class);

                        break;

                    default:
                        break;
                }
                startActivity(intent);
            }
        });

    }

}
