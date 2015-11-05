package com.official.android.androidofficialguidedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.official.android.androidofficialguidedemo.savingdata.SavingDatabaseActivity;
import com.official.android.androidofficialguidedemo.savingdata.SavingFileActivity;
import com.official.android.androidofficialguidedemo.savingdata.SavingPreferenceActivity;

/**
 * Created by user on 15/11/5.
 */
public class SavingDataActivity extends Activity {

    private ListView lv_detail_catalogue;
    private final static String[] array = {"保存到Preference", "保存到文件", "保存到数据库"};


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
            lv_detail_catalogue.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.savingdata)));
            lv_detail_catalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    switch (position) {
                        case 0:
                            intent.setClass(SavingDataActivity.this, SavingPreferenceActivity.class);
                            break;
                        case 1:
                            intent.setClass(SavingDataActivity.this, SavingFileActivity.class);

                            break;
                        case 2:
                            intent.setClass(SavingDataActivity.this, SavingDatabaseActivity.class);

                            break;

                        default:
                            break;
                    }
                    startActivity(intent);
                }
            });
    }

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
}
