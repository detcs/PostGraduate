package com.pages.notes.camera;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private static final String TAG = "MySurfaceView";
	private SurfaceHolder holder;
	private Camera camera;
	private MyCallBack call;

	public interface MyCallBack {
		public void afterTake(Bitmap bitmap);
	}

	private PictureCallback pictureCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			camera.stopPreview();
			camera = null;
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			// size just after take
			Log.i(TAG, "width: " + bitmap.getHeight());
			Log.i(TAG, "height: " + bitmap.getWidth());
			call.afterTake(CameraUtil.adjustPhotoRotation(bitmap, 90));
		}
	};

	public void autoFocus() {
		if (camera != null) {
			camera.autoFocus(null);
		}
	}

	public void takePicture(MyCallBack call) {
		if (camera != null) {
			this.call = call;
			camera.takePicture(null, null, pictureCallback);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera = Camera.open();
		camera.setDisplayOrientation(90);
		Camera.Parameters parameters = camera.getParameters();
		parameters.setPictureFormat(PixelFormat.JPEG);
		List<Size> sizes = parameters.getSupportedPictureSizes();
		Size optimalSize = CameraUtil.getUsefulSize(sizes, 1080, 1920,
				CameraUtil.SIZE);
		parameters.setPictureSize(optimalSize.width, optimalSize.height);
		camera.setParameters(parameters);
		try {
			camera.setPreviewDisplay(holder);
			// camera.startPreview();
		} catch (IOException e) {
			e.printStackTrace();
			camera.release();
			camera = null;
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		try {
			camera.startPreview();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera.release();
	}

	// ***********init************

	@SuppressWarnings("deprecation")
	private void initHolder() {
		holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	// **************************
	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initHolder();
	}

	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initHolder();
	}

	public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initHolder();
	}
}
