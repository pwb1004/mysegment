package cn.gjbigdata.mysegment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MySegmentView extends LinearLayout {

    private Context c;

    private int textColorNormal;

    private int textColorSelected;

    private Drawable buttonBgImageNormal;

    private Drawable buttonBgImageSelected;

    private List<Button> buttons;

    private int selectedIndex;

    private MySegmentListener segmentListener;

    public MySegmentView(Context context) {
        super(context);
        customInit(context);
    }

    public MySegmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        customInit(context);
        initAttrs(context, attrs);
        if (buttonBgImageNormal == null) {
            buttonBgImageNormal = c.getResources().getDrawable(R.drawable.seg_nor);
        }
        if (buttonBgImageSelected == null) {
            buttonBgImageSelected = c.getResources().getDrawable(R.drawable.seg_sel);
        }
    }

    private void customInit(Context context) {
        this.c = context;
        this.setOrientation(HORIZONTAL);
    }

    public void addButtonWithTitles(List<String> titles){
        if (buttons != null) {
            this.removeAllViews();
        }
        buttons = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++){
            String title = titles.get(i);
            final Button button = new Button(c);
            LinearLayout.LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
            button.setText(title);
            button.setTextSize(16);
            button.setLayoutParams(layoutParams);
            this.addView(button, i);
            button.setBackground(buttonBgImageNormal);
            buttons.add(button);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClicked((Button)v);
                }
            });
        }
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int index) {
        selectedIndex = index;
        if (buttonBgImageNormal == null) {
            buttonBgImageNormal = c.getResources().getDrawable(R.drawable.seg_nor);
        }
        if (buttonBgImageSelected == null) {
            buttonBgImageSelected = c.getResources().getDrawable(R.drawable.seg_sel);
        }
        for (Button button:buttons) {

            button.setBackground(buttonBgImageNormal);
            button.setTextColor(textColorNormal);
        }
        Button button = buttons.get(index);
        button.setBackground(buttonBgImageSelected);
        button.setTextColor(textColorSelected);
    }

    public void setOnSegmentChangedListener(MySegmentListener listener) {
        this.segmentListener = listener;
    }

    /**
     * 按钮的点击事件
     * @param button
     */
    private void buttonClicked(Button button){
        int index = buttons.indexOf(button);
        if (selectedIndex != index) {
            setSelectedIndex(index);
            if (segmentListener != null) {
                segmentListener.onSegmentChanged(index);
            }
        }
    }

    /**
     * 初始化，引入相关属性
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySegmentView);
        textColorNormal = typedArray.getColor(R.styleable.MySegmentView_ms_text_color_nor, 0x327AE5);
        textColorSelected = typedArray.getColor(R.styleable.MySegmentView_ms_text_color_sel, 0x000000);
        buttonBgImageNormal = typedArray.getDrawable(R.styleable.MySegmentView_ms_button_bg_nor);
        buttonBgImageSelected = typedArray.getDrawable(R.styleable.MySegmentView_ms_button_bg_sel);
        typedArray.recycle();
    }

}
