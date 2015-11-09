package com.official.android.androidofficialguidedemo.interactingotherapps;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.official.android.androidofficialguidedemo.R;

import org.apache.http.protocol.HTTP;

import java.util.Calendar;
import java.util.List;

public class SendingUserAnotherAppActivity extends Activity {

    private Button bt_talkphone, bt_searchmap, bt_searchweb, bt_sendemail, bt_intentcalendar,
            bt_testexistintent, bt_startactivity, bt_sharechoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_user_another_app);
        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sending_user_another_app, menu);
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

    private void initView(){
        bt_talkphone = (Button)findViewById(R.id.bt_talkphone);
        bt_searchmap = (Button)findViewById(R.id.bt_searchmap);
        bt_searchweb = (Button)findViewById(R.id.bt_searchweb);
        bt_sendemail = (Button)findViewById(R.id.bt_sendemail);
        bt_intentcalendar = (Button)findViewById(R.id.bt_intentcalendar);
        bt_testexistintent = (Button)findViewById(R.id.bt_testexistintent);
        bt_startactivity = (Button)findViewById(R.id.bt_startactivity);
        bt_sharechoose = (Button)findViewById(R.id.bt_sharechoose);

        bt_talkphone.setOnClickListener(new BTClick());
        bt_searchmap.setOnClickListener(new BTClick());
        bt_searchweb.setOnClickListener(new BTClick());
        bt_sendemail.setOnClickListener(new BTClick());
        bt_intentcalendar.setOnClickListener(new BTClick());
        bt_testexistintent.setOnClickListener(new BTClick());
        bt_startactivity.setOnClickListener(new BTClick());
        bt_sharechoose.setOnClickListener(new BTClick());


    }

    private class BTClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_talkphone:
                    Uri number = Uri.parse("tel:5551234");
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(callIntent);
                    break;
                case R.id.bt_searchmap: {
                    // Map point based on address
                    Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
                    // Or map point based on latitude/longitude
                    // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                    startActivity(mapIntent);
                    break;
                }
                case R.id.bt_searchweb:
                    Uri webpage = Uri.parse("http://www.android.com");
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webIntent);
                    break;
                case R.id.bt_sendemail:
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    // The intent does not have a URI, so declare the "text/plain" MIME type
                    emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // recipients
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
                    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                    // You can also attach multiple items by passing an ArrayList of Uris
                    startActivity(emailIntent);
                    break;
                case R.id.bt_intentcalendar:
                    intentcalendar();
                    break;
                case R.id.bt_testexistintent: {
                    Intent intent = new Intent("com.example.user");
                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                    boolean isIntentSafe = activities.size() > 0;
                    Toast.makeText(SendingUserAnotherAppActivity.this, "" + isIntentSafe, Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.bt_startactivity: {
                    // Build the intent
                    Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                    // Verify it resolves
                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                    boolean isIntentSafe = activities.size() > 0;

                    // Start an activity if it's safe
                    if (isIntentSafe) {
                        startActivity(mapIntent);
                    }
                    break;
                }
                case R.id.bt_sharechoose:
                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    ...

                    // Always use string resources for UI text. This says something like "Share this photo with"
//                    String title = getResources().getText(R.string.chooser_title).toString();
//                    // Create and start the chooser；为了显示chooser, 需要使用createChooser()来创建Intent
//                    Intent chooser = Intent.createChooser(intent, title);
//                    startActivity(chooser);
                    intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                    intent.setType("text/plain");
                    startActivity(intent);
                    break;
                default:

                    break;
            }
        }
    }

    @TargetApi(14)      //>=API Level 14
    private void intentcalendar(){
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2015, 0, 19, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2015, 0, 19, 10, 30);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Ninja class");
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo");
        startActivity(calendarIntent);
    }

}
