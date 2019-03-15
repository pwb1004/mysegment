package cn.gjbigdata.mysegmentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.gjbigdata.mysegment.MySegmentListener;
import cn.gjbigdata.mysegment.MySegmentView;

public class MainActivity extends AppCompatActivity {

    private MySegmentView mySegmentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySegmentView = findViewById(R.id.my_segment);

        List<String> titles = new ArrayList<>();
        titles.add("第一个栏目");
        titles.add("第二个栏目");
        titles.add("第三个栏目");
        titles.add("第四个栏目");
        mySegmentView.addButtonWithTitles(titles);
        mySegmentView.setSelectedIndex(0);
        mySegmentView.setOnSegmentChangedListener(new MySegmentListener() {
            @Override
            public void onSegmentChanged(int index) {
                Log.i("SEG INDEX : ", index + "");
            }
        });
    }
}
