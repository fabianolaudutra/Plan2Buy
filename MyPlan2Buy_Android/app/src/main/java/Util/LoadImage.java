package Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by f.dutra on 01/06/2016.
 */
public abstract  class LoadImage extends AsyncTask<String, String, Bitmap> {
    Bitmap bitmap;
    public  Bitmap loadInBackground(String... args) {
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
