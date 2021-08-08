package com.example.videoview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    Button pick;
    MediaController mc;
    ImageView imageView;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pick = findViewById(R.id.pick);
        videoView = findViewById(R.id.videoView);
        mc = new MediaController(MainActivity.this);
        videoView.setMediaController(mc);
        next= findViewById(R.id.next);


        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pickIntent = new Intent(Intent.ACTION_PICK);
                pickIntent.setType("video/*");
                startActivityForResult(pickIntent, 1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pickIntent = new Intent(Intent.ACTION_PICK);
                pickIntent.setType("image/*");
                startActivityForResult(pickIntent, 2);
            }
        });
        
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri videoUri = data.getData();
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(videoUri);
            videoView.start();
        }

    }
    public class ImageAdapter extends PagerAdapter {
        Context mContext;

        ImageAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        private int[] sliderImageId = new int[]{
                R.drawable.image1, R.drawable.image2, R.drawable.image3
        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(sliderImageId[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }

        @Override
        public int getCount() {
            return sliderImageId.length;
        }
    }

}
