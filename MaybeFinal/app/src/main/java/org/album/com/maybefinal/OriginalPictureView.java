package org.album.com.maybefinal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by 521ab on 2017-04-20.
 */

public class OriginalPictureView extends Activity{
    private ImageView imgView;
    private String filename;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_item);

        imgView = (ImageView)findViewById(R.id.iamgeView1);
        processIntent();
    }

    private void processIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        filename = extras.getString("filename");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        Bitmap bitmap = BitmapFactory.decodeFile(filename,options);

        imgView.setImageBitmap(bitmap);
    }
}
