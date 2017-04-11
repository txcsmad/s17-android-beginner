package ahn.springproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComposeActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    Button uploadButton;
    Button takePicButton;

    Uri file;
    String fileName;
    String caption;
    String timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        imageView = (ImageView) findViewById(R.id.upload_pic);
        editText = (EditText) findViewById(R.id.caption_edit_text);
        takePicButton = (Button) findViewById(R.id.take_pic_button);
        uploadButton = (Button) findViewById(R.id.upload_button);

        uploadButton.setEnabled(false);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePicButton.setEnabled(false);

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicButton.setEnabled(true);
            }
        }
    }

    public void takePic(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName()+".fileprovider", getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Picasso.with(this).load(file).rotate(90).into(imageView);
            uploadButton.setEnabled(true);
        }
    }

    public File getOutputMediaFile() {
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());

        if(!mediaStorageDir.exists()) {
            if(!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String picStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        fileName = "IMG_" + picStamp + ".jpg";
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }
}
