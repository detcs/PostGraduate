package com.pages.notes.camera;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private SurfaceHolder holder; 
	private Camera camera; 
	private MyCallBack call;
	
	public interface MyCallBack{
		public void afterTake(Bitmap bitmap);
	}
	
	// ����һ��PictureCallback���󣬲�ʵ�����е�onPictureTaken���� 
    private PictureCallback pictureCallback = new PictureCallback() { 
 
        // �÷������ڴ�����������Ƭ��� 
        @Override 
        public void onPictureTaken(byte[] data, Camera camera) { 
            // ֹͣ��Ƭ���� 
            camera.stopPreview(); 
            camera = null; 
            Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0,
					data.length);
            call.afterTake(RotateBitmap.adjustPhotoRotation(bitmap, 90));
        }
    }; 
    
    public void autoFocus(){
    	if(camera!=null){
    		camera.autoFocus(null);
    	}
    }
    
    public void takePicture(MyCallBack call){
    	if(camera!=null){
    		this.call=call;
    		camera.takePicture(null, null, pictureCallback);
    	}
    }
    
 // ֹͣ���գ������������Ƭ����PictureCallback�ӿڵ�onPictureTaken���� 
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// ���Camera���� 
        camera = Camera.open(); 
        camera.setDisplayOrientation(90);
        try { 
            // ����������ʾ���������SurfaceHolder���� 
            camera.setPreviewDisplay(holder); 
//            camera.startPreview();
        } catch (IOException e) { 
            e.printStackTrace(); 
            // �ͷ��ֻ�����ͷ 
            camera.release(); 
            camera = null; 
        } 
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		try { 
            // ��ȡ�������� 
//            Camera.Parameters parameters = camera.getParameters(); 
//            // ������Ƭ��ʽ 
//            parameters.setPictureFormat(PixelFormat.JPEG); 
//            // ����Ԥ䯳ߴ� 
//            parameters.setPreviewSize(width, height); 
//            // ������Ƭ�ֱ��� 
//            parameters.setPictureSize(width, height); 
//            // ������������ 
//            camera.setParameters(parameters); 
            // ��ʼ���� 
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
//	***********init************
	
	private void initHolder(){
		 // ���SurfaceHolder���� 
        holder = getHolder(); 
        // ָ�����ڲ�׽�����¼���SurfaceHolder.Callback���� 
        holder.addCallback(this); 
        // ����SurfaceHolder��������� 
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
	}

//	**************************
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
