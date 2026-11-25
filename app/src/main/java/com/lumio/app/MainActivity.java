
package com.lumio.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    LinearLayout layout;
    ImageView imageView;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(30, 40, 30, 30);
        layout.setBackgroundColor(Color.rgb(7, 18, 35));

        TextView logo = new TextView(this);
        logo.setText("LUMIO");
        logo.setTextSize(38);
        logo.setTextColor(Color.rgb(50, 200, 255));
        logo.setGravity(Gravity.CENTER);

        TextView subtitle = new TextView(this);
        subtitle.setText("Открой мир вокруг себя");
        subtitle.setTextSize(18);
        subtitle.setTextColor(Color.WHITE);
        subtitle.setGravity(Gravity.CENTER);

        imageView = new ImageView(this);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxHeight(550);

        result = new TextView(this);
        result.setText("Сфотографируй предмет,\nчтобы узнать о нём больше.");
        result.setTextColor(Color.WHITE);
        result.setTextSize(17);
        result.setGravity(Gravity.CENTER);

        Button camera = new Button(this);
        camera.setText("📸 ОТКРЫТЬ КАМЕРУ");

        camera.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 100);
            }
        });

        layout.addView(logo);
        layout.addView(subtitle);
        layout.addView(imageView);
        layout.addView(result);
        layout.addView(camera);

        setContentView(layout);
    }

    @Override
    protected void onActivityResult(
        int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK
                && data != null) {

            Bitmap photo = (Bitmap)
                data.getExtras().get("data");

            if (photo != null) {
                imageView.setImageBitmap(photo);
                result.setText(
                    "Фото получено!\nСледующий этап — распознавание ИИ."
                );
            }
          
        }
    }
}
