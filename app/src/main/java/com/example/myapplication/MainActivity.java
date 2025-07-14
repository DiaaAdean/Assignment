package com.example.myapplication;
import com.example.myapplication.Fragment2;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity
        implements Fragment2.OnFragment2Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // أول ما يشتغل التطبيق، يعرض Fragment1
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Fragment1())
                    .commit();
        }
    }

    // هذه تستقبل البيانات من Fragment2 وتنتقل إلى Fragment3
    @Override
    public void onDataFromFragment2(String name, String email, String gender, String university) {
        Fragment3 fragment3 = Fragment3.newInstance(name);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();
    }
}
