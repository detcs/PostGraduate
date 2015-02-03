package com.pages.notes.camera;

import java.util.List;

import com.data.util.SysCall;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera.Size;
import android.util.DisplayMetrics;

public class CameraUtil {
	// private static final String TAG = "CameraUtil";

	public static Bitmap scaleToScreen(Activity activity, Bitmap bitmap) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int intScreenWidth = dm.widthPixels;
		int intScreenHeight = dm.heightPixels;
		// Log.i(TAG, "screen width: " + intScreenWidth);
		// Log.i(TAG, "screen height: " + intScreenHeight);
		return Bitmap.createScaledBitmap(bitmap, intScreenWidth,
				intScreenHeight, true);
	}

	// ******************************************************
	public static final int SIZE = 0, RATIO = 1, WIDTH = 2;

	public static Size getUsefulSize(List<Size> sizes, int w, int h, int type) {
		Size size;
		switch (type) {
		case SIZE:
			size = getSizeSIZE(sizes, w, h);
			break;
		case RATIO:
			size = getSizeRATIO(sizes, w, h);
			break;
		case WIDTH:
			size = getSizeWIDTH(sizes, w, h);
			break;
		default:
			size = getSizeSIZE(sizes, w, h);
			break;
		}
		return size;
	}

	// size 最接近要求的大小
	private static Size getSizeSIZE(List<Size> sizes, int w, int h) {
		Size re = null;
		int wantSize = w * h;
		int minGap = wantSize;
		for (Size size : sizes) {
			if (getSizeGap(size, wantSize) < minGap) {
				re = size;
				minGap = getSizeGap(size, wantSize);
			}
		}
		return re;
	}

	private static int getSizeGap(Size size, int wantSize) {
		return Math.abs(size.width * size.height - wantSize);
	}

	private static Size getSizeRATIO(List<Size> sizes, int w, int h) {
		Size re = null;
		double wantRatio = (double) w / (double) h;
		double minGap = wantRatio;
		for (Size size : sizes) {
			if (getRatioGap(size, wantRatio) < minGap) {
				minGap = getRatioGap(size, wantRatio);
				re = size;
			}
		}
		return re;
	}

	private static double getRatioGap(Size size, double wantRatio) {
		double sizeRatio = (double) size.height / (double) size.width;
		return Math.abs(sizeRatio - wantRatio);
	}

	private static Size getSizeWIDTH(List<Size> sizes, int w, int h) {
		int wantWidth = w;
		Size re = null;
		int minGap = w;
		for (Size size : sizes) {
			if (getWidthGap(size, wantWidth) < minGap) {
				minGap = getWidthGap(size, wantWidth);
				re = size;
			}
		}
		return re;
	}

	private static int getWidthGap(Size size, int wantWidth) {
		return Math.abs(size.height - wantWidth);
	}

	// ******************************************************

	public static Bitmap adjustPhotoRotation(Bitmap bm,
			final int orientationDegree) {
		Matrix m = new Matrix();
		m.setRotate(orientationDegree, (float) bm.getWidth() / 2,
				(float) bm.getHeight() / 2);
		try {
			Bitmap bm1 = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
					bm.getHeight(), m, true);
			checkSizeKeep(bm, bm1);
			return bm1;
		} catch (OutOfMemoryError ex) {
		}
		return null;
	}

	public static Bitmap adjustPhotoRotation1(Bitmap bm,
			final int orientationDegree) {
		Matrix m = new Matrix();
		m.setRotate(orientationDegree, (float) bm.getWidth() / 2,
				(float) bm.getHeight() / 2);
		float targetX, targetY;
		if (orientationDegree == 90) {
			targetX = bm.getHeight();
			targetY = 0;
		} else {
			targetX = bm.getHeight();
			targetY = bm.getWidth();
		}
		final float[] values = new float[9];
		m.getValues(values);
		float x1 = values[Matrix.MTRANS_X];
		float y1 = values[Matrix.MTRANS_Y];
		m.postTranslate(targetX - x1, targetY - y1);
		Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(),
				Bitmap.Config.ARGB_8888);
		Paint paint = new Paint();
		Canvas canvas = new Canvas(bm1);
		canvas.drawBitmap(bm, m, paint);
		checkSizeKeep(bm, bm1);
		return bm1;
	}

	private static void checkSizeKeep(Bitmap bitmap1, Bitmap bitmap2) {
		int orginWidth = bitmap1.getHeight();
		int orginHeight = bitmap1.getWidth();
		int resultWdith = bitmap2.getWidth();
		int resultHeight = bitmap2.getHeight();
		if (orginHeight == resultHeight && orginWidth == resultWdith) {
			return;
		}
		SysCall.error("bitmap size changed when scale");
	}
}
