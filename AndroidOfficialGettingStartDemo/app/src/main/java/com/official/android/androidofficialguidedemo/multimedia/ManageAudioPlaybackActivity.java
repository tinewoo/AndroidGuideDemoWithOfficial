package com.official.android.androidofficialguidedemo.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.official.android.androidofficialguidedemo.R;
import com.official.android.androidofficialguidedemo.ServerClientDemoName.ServerClientDemoNameActivity;
import com.official.android.androidofficialguidedemo.multimedia.manageaudioplayback.ManageAudioActivity;
import com.official.android.androidofficialguidedemo.tools.Constants;

/**
 * Created by user on 15/11/26.
 */
public class ManageAudioPlaybackActivity extends Activity {

    private ListView lv_detail_catalogue;

//ManageAudioActivity待完成
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catalogue);
        lv_detail_catalogue = (ListView)findViewById(R.id.lv_detail_catalogue);
        lv_detail_catalogue.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.managing_audio_playback)));
        lv_detail_catalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(ManageAudioPlaybackActivity.this, ManageAudioActivity.class);
                        intent.putExtra(Constants.STRINGRESOURCEID, R.string.nfcsharingfilesname);
                        break;

                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
