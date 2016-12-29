package com.official.android.androidofficialguidedemo.tools;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by user on 15/11/20.
 */
public class GetPathFromUriBefore4kitkat {

    private static String filename;
    private static Cursor cursor;

    public static String getPath(final Context context, final Uri uri) {
        if (uri.getScheme().toString().compareTo("content") == 0) {
            cursor = context.getContentResolver().query(uri,
                    new String[] {MediaStore.Audio.Media.DATA}, null, null, null);
            if (cursor.moveToFirst()) {
                filename = cursor.getString(0);
            }
        }else if (uri.getScheme().toString().compareTo("file") == 0)         //file:///开头的uri
        {
            filename = uri.toString();
            filename = uri.toString().replace("file://", "");
            //替换file://
            if(!filename.startsWith("/mnt")){
                //加上"/mnt"头
                filename += "/mnt";
            }
        }
        return filename;
    }

}
