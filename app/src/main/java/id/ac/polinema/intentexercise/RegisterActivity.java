package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import id.ac.polinema.intentexercise.model.User;

public class RegisterActivity extends AppCompatActivity {

    public static final String USER_KEY = "user";
    private EditText fullnameInput;
    private EditText emailInput;
    private EditText homepageInput;
    private EditText aboutInput;
    private EditText passInput;
    private EditText conf_passInput;
    private ImageView avatarImage;
    private Uri imageUri;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullnameInput = findViewById(R.id.text_fullname);
        emailInput = findViewById(R.id.text_email);
        homepageInput = findViewById(R.id.text_homepage);
        aboutInput = findViewById(R.id.text_about);
        passInput = findViewById(R.id.text_password);
        conf_passInput = findViewById(R.id.text_confirm_password);
        avatarImage = findViewById(R.id.image_profile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }
        if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null){
                try{
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarImage.setImageBitmap(bitmap);
                } catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleSubmit(View view) {
        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String homepage = homepageInput.getText().toString();
        String about = aboutInput.getText().toString();
        Uri image = imageUri.normalizeScheme();

        // Validate Pass

        String pass = passInput.getText().toString();
        String conf_pass = conf_passInput.getText().toString();

        if (TextUtils.isEmpty(fullnameInput.getText())){
            fullnameInput.setError("Silahkan diisi terlebih dahulu");
        } else if (TextUtils.isEmpty(emailInput.getText())){
            emailInput.setError("Silahkan diisi terlebih dahulu");
        } else if (TextUtils.isEmpty(passInput.getText())){
            passInput.setError("Silahkan diisi terlebih dahulu");
        } else if(TextUtils.isEmpty(homepageInput.getText())){
            homepageInput.setError("Silahkan diisi terlebih dahulu");
        } else if(TextUtils.isEmpty(aboutInput.getText())){
            aboutInput.setError("Silahkan diisi terlebih dahulu");
        } else if (!pass.equals(conf_pass)){
            passInput.setError("Password anda tidak cocok");
            conf_passInput.setError("Password anda tidak cocok");
        } else if(imageUri == null){
            Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
        }
        else{
            User user = new User(fullname, email, homepage, about, image);
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(USER_KEY, user);
            startActivity(intent);
        }

        //End Validate Pass

    }

    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }
}
