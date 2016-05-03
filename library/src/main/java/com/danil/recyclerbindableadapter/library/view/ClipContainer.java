package com.danil.recyclerbindableadapter.library.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

/**
 * Created by Danil on 08.10.2015.
 */
public class ClipContainer extends FrameLayout {

    private final boolean isClip;
    private final boolean isFooter;
    private int offset;

    public ClipContainer(Context context, boolean isClip, boolean isFooter) {
        super(context);
        this.isClip = isClip;
        this.isFooter = isFooter;
    }

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        if (isClip) {
            int top = isFooter ? -offset : 0;
            int bottom = isFooter ? getBottom() : getBottom() + offset;
            Rect rect = new Rect(getLeft(), top, getRight(), bottom);
            canvas.clipRect(rect);
        }
        super.dispatchDraw(canvas);
    }

    public void setClipY(int offset) {
        this.offset = offset;
        invalidate();
    }
}