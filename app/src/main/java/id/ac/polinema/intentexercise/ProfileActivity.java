package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import id.ac.polinema.intentexercise.model.User;

public class ProfileActivity extends AppCompatActivity {

    public static final String USER_KEY = "user";
    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;
    private ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullnameText = findViewById(R.id.label_fullname);
        emailText = findViewById(R.id.label_email);
        homepageText = findViewById(R.id.label_homepage);
        aboutText = findViewById(R.id.label_about);
        gambar = findViewById(R.id.image_profile_1);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            User data = getIntent().getParcelableExtra(USER_KEY);
            fullnameText.setText(data.getFullname());
            emailText.setText(data.getEmail());
            homepageText.setText(data.getHomepage());
            aboutText.setText(data.getAbout());
            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getImage());
                gambar.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleHomepage(View view) {
        String url = homepageText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://" +url));
        startActivity(intent);
    }
}
