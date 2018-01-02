package com.thorny.photoeasysample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.thorny.photoeasy.ExternalStoragePermission;
import com.thorny.photoeasy.OnPictureReady;
import com.thorny.photoeasy.PhotoEasy;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


  ImageView imageView;
  PhotoEasy photoEasy;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    imageView = findViewById(R.id.imageView);
  }

  public void cameraAction(View view) {
    photoEasy = PhotoEasy.builder()
        .setActivity(this)
        .setMimeType(PhotoEasy.MimeType.imagePng)
        .setStorageType(PhotoEasy.StorageType.media)
        .saveInCustomDirectory("dircus")
        .build();
    photoEasy.startActivityForResult(this);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    photoEasy.onActivityResult(requestCode, resultCode, new OnPictureReady() {
      @Override
      public void onFinish(Bitmap thumbnail) {
        imageView.setImageBitmap(thumbnail);
      }

      @Override
      public void onFailure() {

      }
    });
  }

  private static class ExtStoPer extends ExternalStoragePermission {

    public ExtStoPer(Activity activity) {
      super(activity,RequestMode.alwaysRequest);
    }

    @Override
    public void requestPermissionRationale() {

    }

    @Override
    public void requestPermission() {

    }
  }
}

