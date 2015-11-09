package com.official.android.androidofficialguidedemo.interactingotherapps;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.official.android.androidofficialguidedemo.R;

import org.w3c.dom.Text;

public class IntentFilterActivity extends ActionBarActivity {

    private TextView tv_english_document, tv_cn_document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intent_filter, menu);
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

    private void init(){
        tv_english_document = (TextView)findViewById(R.id.tv_english_document);
        tv_cn_document = (TextView)findViewById(R.id.tv_cn_document);
        tv_english_document.setText(Html.fromHtml("英文文档地址：<a href=\"http://developer.android.com/training/basics/intents/filters.html\">http://developer.android.com/training/basics/intents/filters.html</a>"));
//        tv_cn_document.setText(Html.fromHtml("英文文档地址：<br/><br/><a href=\"http://developer.android.com/training/basics/intents/filters.html\">http://developer.android.com/training/basics/intents/filters.html</a>"));
        tv_english_document.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
