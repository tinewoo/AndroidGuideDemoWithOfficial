package com.official.android.androidofficialguidedemo.gettingstarted.interactingotherapps.detail;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.official.android.androidofficialguidedemo.R;

public class IntentFilterActivity extends Activity {

    private TextView tv_english_document, tv_cn_document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documenturl_nodemo);
        init();
    }

    private void init(){
        tv_english_document = (TextView)findViewById(R.id.tv_english_document);
        tv_cn_document = (TextView)findViewById(R.id.tv_cn_document);
        tv_english_document.setText(Html.fromHtml("英文文档地址：<a href=\"http://developer.android.com/training/basics/intents/filters.html\">http://developer.android.com/training/basics/intents/filters.html</a>"));
        tv_cn_document.setText(Html.fromHtml("英文文档地址：<br/><br/><a href=\"http://hukai.me/android-training-course-in-chinese/basics/intents/filters.html\">http://hukai.me/android-training-course-in-chinese/basics/intents/filters.html</a>"));
        tv_english_document.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
