package com.example.functional;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.product.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public  class Img {
    Activity activity;
    public Img(Activity activity)
    {
        this.activity=activity;
    }
    public  String result(Intent intent, ImageView picView)
    {
        Bundle extras = intent.getExtras();
        // Получим кадрированное изображение
        Bitmap thePic = extras.getParcelable("data");
        // передаём его в ImageView

        picView.setImageBitmap(thePic);

        File file=saveJPGE_After(thePic);
        return file.getPath();
    }
    public  File saveJPGE_After(Bitmap bitmap) {
        File file = new File(activity.getFilesDir(), System.currentTimeMillis()+"_img.jpg");

        try {/*from w  w  w.  j a v  a 2 s  .c  om*/


            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            Toast toast = Toast.makeText(activity,e.toString(),Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            Toast toast = Toast.makeText(activity,e.toString(),Toast.LENGTH_LONG);
            toast.show();
        }
        return file;
    }
}
