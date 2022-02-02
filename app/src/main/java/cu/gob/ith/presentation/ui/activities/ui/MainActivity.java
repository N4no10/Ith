package cu.gob.ith.presentation.ui.activities.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import cu.gob.ith.R;
import cu.gob.ith.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding uiBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBind = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setContentView(uiBind.getRoot());

        uiBind.setTitle(getString(R.string.menu_title));
    }
}