package org.album.com.maybefinal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public ArrayList<Uri> galleryUri = new ArrayList<>();
    Gallery gallery;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Gallery g = (Gallery) findViewById(R.id.gallery1);
        g.setAdapter(new galleryAdapter(this));
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });*/
    }
    public void doTakeAlbumAction(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent,1);//앨범에서 뽑음 1
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode != RESULT_OK){
            return;
        }
        if(requestCode == 1){
            ArrayList<Parcelable> list = data.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
            if(list != null){
                for(Parcelable parcelable : list){
                    Uri uri = (Uri) parcelable;
                    galleryUri.add(uri);
                    Toast.makeText(this,"실행됨",Toast.LENGTH_SHORT).show();
                }
            }
            for(Uri uri : galleryUri){
                Toast.makeText(this,uri.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onClick(View v) {
        int id_view = v.getId();
        if(id_view == R.id.seed_picture){

        } else if(id_view == R.id.add_picture){
            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    doTakeAlbumAction();
                }
            };
            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
            new AlertDialog.Builder(this).setTitle("업로드할 이미지 선택").setNeutralButton("앨범선택",albumListener).setNegativeButton("취소",cancelListener).show();
        }
    }

    class galleryAdapter extends BaseAdapter {
        private final Context mContext;
        LayoutInflater inflater;

        public galleryAdapter(Context c) {
            mContext = c;
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return galleryUri.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = inflater.inflate(R.layout.gallery_item,parent,false);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.iamgeView1);
            Bitmap photo = null;
            try{
              photo = MediaStore.Images.Media.getBitmap(getContentResolver(),galleryUri.get(position));
            } catch (Exception e){

                e.printStackTrace();
            }
            imageView.setImageBitmap(photo);
            return convertView;
        }
    }/*
    public class ImageAdapter extends BaseAdapter{
        private Context mContext;
        private Gallery.LayoutParams params;

        public ImageAdapter(Context c){
            mContext = c;

            WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            Display display = wm.getDefaultDisplay();
            display.getMetrics(metrics);

            params = new Gallery.LayoutParams(metrics.widthPixels/3,Gallery.LayoutParams.MATCH_PARENT);

        }
        public int getCount(){
            return count;
        }
        public Object getItem(int position){
            return position;
        }
        public long getItemId(int position){
            return  position;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            ImageView iv = null;
            if(convertView == null){
                iv = new ImageView(mContext);
            } else {
                iv = (ImageView) convertView;
            }
            imageCursor.moveToPosition(position);
            int id = imageCursor.getInt(imageColumnIndex);

            iv.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,String.valueOf(id)));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setLayoutParams(params);

            return  iv;
        }
    }*/
}
