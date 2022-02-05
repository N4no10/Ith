package cu.gob.ith.presentation.activities.main.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import cu.gob.ith.R;
import cu.gob.ith.databinding.ActivityMainBinding;
import cu.gob.ith.presentation.activities.main.recyclerview.ItemMenuAdapter;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import cu.gob.ith.presentation.model.ItemMenuNavView;
import cu.gob.ith.presentation.util.Util;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding uiBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiBind = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setContentView(uiBind.getRoot());

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        initView();

    }

    private void initView() {
        uiBind.setTitle(getString(R.string.menu_title));
        initNavView();
        initTransformMotionLayout();
    }

    private void initNavView() {
        uiBind.contentNavViewLayout.setAdapter(new ItemMenuAdapter(mainActivityViewModel.getItemMenuNavViewList()));
    }

    private void initTransformMotionLayout(){
        MotionLayout ml = uiBind.motionLayoutDrawerMainActivity;
        ConstraintSet constraintSetEnd = ml.getConstraintSet(R.id.end);
        ConstraintSet.Transform transformContentMainActiv = constraintSetEnd
                .getConstraint(R.id.contentMainActivityLayout).transform;
        transformContentMainActiv.elevation = 40;

        int translationX = ((int)Util.widthDp(ml.getContext())) - (constraintSetEnd.getConstraint(R.id.guideline2).layout.guideEnd);
        transformContentMainActiv.translationX = translationX;
    }
}