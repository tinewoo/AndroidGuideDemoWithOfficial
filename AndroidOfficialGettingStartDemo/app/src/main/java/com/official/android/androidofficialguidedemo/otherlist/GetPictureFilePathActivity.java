package com.official.android.androidofficialguidedemo.otherlist;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.official.android.androidofficialguidedemo.R;
import com.official.android.androidofficialguidedemo.tools.GetPathFromUri4kitkat;
import com.official.android.androidofficialguidedemo.tools.GetPathFromUriBefore4kitkat;

import java.io.File;
import java.lang.ref.WeakReference;

public class GetPictureFilePathActivity extends AppCompatActivity {

    private TextView tv_photopath;
    private ImageView iv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_picture_file_path);
        tv_photopath = (TextView)findViewById(R.id.tv_photopath);
        iv_photo = (ImageView)findViewById(R.id.iv_photo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_picture_file_path, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri uri = data.getData();
            String filePath;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                filePath = GetPathFromUri4kitkat.getPath(this, uri);
            }
            else{
                filePath = GetPathFromUriBefore4kitkat.getPath(this, uri);
            }
            tv_photopath.setText(filePath);
            Boolean isExist = fileIsExists(filePath);
            if(isExist){
                try{
                    Bitmap bitmap = convertToBitmap(filePath, 200, 100);
                    iv_photo.setImageBitmap(bitmap);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void openPhotos(View v){
        Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
        startActivityForResult(intent, 1);
    }

    //判断文件是否存在
    public boolean fileIsExists(String strFile)
    {
        try
        {
            File f=new File(strFile);
            if(!f.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    public Bitmap convertToBitmap(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int)scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }

}
