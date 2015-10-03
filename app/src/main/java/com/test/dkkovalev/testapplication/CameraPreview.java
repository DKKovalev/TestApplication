package com.test.dkkovalev.testapplication;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private Camera c;
    private SurfaceHolder surfaceHolder;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.c = camera;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        try {
            c.setPreviewDisplay(surfaceHolder);
            c.startPreview();
        } catch (IOException e) {
            e.getMessage();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        try {
            c.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            c.setPreviewDisplay(surfaceHolder);
            c.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
