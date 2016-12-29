package com.official.android.androidofficialguidedemo.contentsharing.appcontentsharing;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.official.android.androidofficialguidedemo.R;

public class ReceivingSimpleDataActivity extends Activity {

    private TextView tv_english_document, tv_cn_document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documenturl_nodemo);
    }

    private void init(){
        tv_english_document = (TextView)findViewById(R.id.tv_english_document);
        tv_cn_document = (TextView)findViewById(R.id.tv_cn_document);
        tv_english_document.setText(Html.fromHtml("英文文档地址：<a href=\"http://developer.android.com/training/sharing/receive.html\">http://developer.android.com/training/sharing/receive.html</a>"));
        tv_cn_document.setText(Html.fromHtml("中文文档地址：<br/><br/><a href=\"http://hukai.me/android-training-course-in-chinese/content-sharing/sharing/index.html\">http://hukai.me/android-training-course-in-chinese/content-sharing/sharing/index.html</a>"));
        tv_english_document.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
