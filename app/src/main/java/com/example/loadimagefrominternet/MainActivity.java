package com.example.loadimagefrominternet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView iv;

    String catimg="https://www.thesprucepets.com/thmb/dCuBQtyCYUH9eoUQOkfEgToH95s=/1535x1151/smart/filters:no_upscale()/Stocksy_txp33a24e10lxw100_Medium_214761-5af9d6d7875db900360440a7.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.imageview);

        final Handler handler = new Handler(Looper.getMainLooper());


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL uri = new URL(catimg);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) uri.openConnection();
                    httpURLConnection.connect();
                    InputStream inputStream= httpURLConnection.getInputStream();
                    final Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                    //iv.setImageBitmap(bitmap);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bitmap);
                        }
                    });



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();




    }
}
