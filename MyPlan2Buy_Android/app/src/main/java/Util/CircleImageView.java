package Util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends ImageView {

    private Bitmap bitmap;
    private Canvas canvas;
    private Drawable drawable;

    private Paint paintBorder;
    private Integer borderColor;
    private Integer borderWidth = 1;


    public CircleImageView(Context context) {
        super(context);
        setup();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup() {
        drawable = getDrawable();

        if (!(drawable instanceof BitmapDrawable)) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
        }

        if (borderColor != null) {
            paintBorder = new Paint();
            paintBorder.setColor(borderColor);
            paintBorder.setAntiAlias(true);
        }
    }

    public void setBorderColor(final int borderColor) {
        this.borderColor = borderColor;
    }

    public void setBorderWidth(final int borderWidth) {
        this.borderWidth = borderWidth;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        Bitmap b;
        if (drawable instanceof BitmapDrawable) {
            b = ((BitmapDrawable) drawable).getBitmap();
        } else {
            drawable.setBounds(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
            drawable.draw(this.canvas);
            b = bitmap;
        }

        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth();
        int radius = w / 2;

        Bitmap roundBitmap = getCroppedBitmap(bitmap, w - borderWidth);


        canvas.drawCircle(radius, radius, radius, paintBorder);


        canvas.drawBitmap(roundBitmap, borderWidth / (float) 2, borderWidth / (float) 2, null);

    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;

        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bmp,
                    (int) (bmp.getWidth() / factor),
                    (int) (bmp.getHeight() / factor), false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(radius / (float) 2 + 0.7f, radius / (float) 2 + 0.7f, radius / (float) 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }


}
