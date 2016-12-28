package com.official.android.androidofficialguidedemo.gettingstarted.savingdata.detail;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.official.android.androidofficialguidedemo.R;

public class SavingPreferenceActivity extends Activity {

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

    protected void writePreference(View v){
//        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score), 150);//随便指定了值
        editor.apply();//commit()是同步方法，apply()是异步方法，如果不需要返回值的话，可以完全用apply()替代commit()
    }

    protected void readPreference(View v){
        long defaultValue = getResources().getInteger(R.string.saved_high_score_default);
        long highScore = sharedPref.getInt(getString(R.string.saved_high_score), (int)defaultValue);
    }
}
