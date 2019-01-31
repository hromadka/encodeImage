package com.iai.encodeimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    String m_base64encoded = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnE = (Button)findViewById(R.id.btnEncode);
        btnE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                encodeImage();
            }
        });

        Button btnD = (Button)findViewById(R.id.btnDecode);
        btnD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                decodeImage();
            }
        });

    }

    private void encodeImage() {
        m_base64encoded = "";

        ImageView iv =(ImageView)findViewById(R.id.imageView);
        iv.setImageDrawable(null);
        //iv.setImageResource(android.R.color.transparent);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p4);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        m_base64encoded = Base64.encodeToString(bytes, Base64.DEFAULT);

        Toast.makeText(MainActivity.this, "image encoded", Toast.LENGTH_SHORT).show();
    }

    private void decodeImage() {
        if (TextUtils.isEmpty(m_base64encoded)) {
            Toast.makeText(MainActivity.this, "no encoded image data", Toast.LENGTH_SHORT).show();
            return;
        }

        byte[] bytes = Base64.decode(m_base64encoded, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ImageView iv =(ImageView)findViewById(R.id.imageView);
        iv.setImageBitmap(bitmap);

    }
}
