package practice.compoundviewexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Oslo on 7/29/15.
 */
public class ColorOptionsView extends LinearLayout{

    private View mView;
    private ImageView mImageView;


    public ColorOptionsView(Context context) {
        super(context, null);
    }

    public ColorOptionsView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        //
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ColorOptionsView, 0, 0);
        String titleText = typedArray.getString(R.styleable.ColorOptionsView_titleText);
        int color = typedArray.getColor(R.styleable.ColorOptionsView_colorValue, android.R.color.holo_blue_light);
        typedArray.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compound_layout, this, true);

        TextView title = (TextView)getChildAt(0);
        title.setText(titleText);

        mView = getChildAt(1);
        mView.setBackgroundColor(color);

        mImageView = (ImageView)getChildAt(2);
    }

    public void setColor(int color){
        mView.setBackgroundColor(color);
    }

    public void setImageView(boolean visible){
        mImageView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
