package com.test.dkkovalev.testapplication;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Camera camera;
    private CameraPreview cameraPreview;
    private boolean isSurfaceCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (isCameraAvailable()) {
            camera = getCamera();
            camera.setDisplayOrientation(90);

            cameraPreview = new CameraPreview(MainActivity.this, camera);

            final FrameLayout previewLayout = (FrameLayout) findViewById(R.id.preview_layout);


            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isSurfaceCreated) {
                        previewLayout.addView(cameraPreview);
                        isSurfaceCreated = true;
                    } else {
                        previewLayout.removeAllViews();
                        isSurfaceCreated = false;
                    }
                }
            });
        } else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
        }
    }

    private Camera getCamera() {
        Camera camera = null;
        int camerasCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        camerasCount = Camera.getNumberOfCameras();
        for (int i = 0; i < camerasCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    camera = Camera.open(i);
                } catch (Exception e) {
                }
            }
        }
        return camera;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.camera == null) {
            this.camera = getCamera();
        }
    }

    private boolean isCameraAvailable() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

}
