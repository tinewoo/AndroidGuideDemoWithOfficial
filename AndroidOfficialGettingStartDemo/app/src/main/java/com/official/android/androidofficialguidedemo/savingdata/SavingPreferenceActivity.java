package com.official.android.androidofficialguidedemo.savingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.official.android.androidofficialguidedemo.R;

public class SavingPreferenceActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_preference);

        //获取SharedPreference句柄
        Context context = this;
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //或者有下面的方法
//       sharedPref = getPreferences(Context.MODE_PRIVATE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saving_preference, menu);
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

    private void writePreference(View v){
//        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score), 150);//随便指定了值
        editor.apply();//commit()是同步方法，apply()是异步方法，如果不需要返回值的话，可以完全用apply()替代commit()
    }

    private void readPreference(View v){
        long defaultValue = getResources().getInteger(R.string.saved_high_score_default);
        long highScore = sharedPref.getInt(getString(R.string.saved_high_score), (int)defaultValue);
    }
}
