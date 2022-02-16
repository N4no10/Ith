package cu.gob.ith.presentation.activities.main.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.data.preferences.UserAppPreferences;
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

    @Inject
    UserAppPreferences userAppPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiBind = DataBindingUtil.setContentView(this, R.layout.activity_main);
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
        uiBind.contentNavViewLayout.userHeaderTV.setText(userAppPreferences.getNamePreferences("not Found"));
        uiBind.contentNavViewLayout.emailHeaderTV.setText(userAppPreferences.getEmailPreferences("not Found"));
        uiBind.contentNavViewLayout.setAdapter(new ItemMenuAdapter(mainActivityViewModel.getItemMenuNavViewList()));
        uiBind.contentNavViewLayout.logoutItemLayout.getRoot().setOnClickListener(
                v -> logout()
        );
    }

    private void initTransformMotionLayout() {
        MotionLayout ml = uiBind.motionLayoutDrawerMainActivity;
        ConstraintSet constraintSetEnd = ml.getConstraintSet(R.id.end);
        ConstraintSet.Transform transformContentMainActiv = constraintSetEnd
                .getConstraint(R.id.contentMainActivityLayout).transform;
        transformContentMainActiv.elevation = 40;

        transformContentMainActiv.translationX = ((int) Util.widthDp(ml.getContext())) -
                (constraintSetEnd.getConstraint(R.id.guideline2).layout.guideEnd);

        uiBind.motionLayoutDrawerMainActivity.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                mainActivityViewModel.setColapsedMainContent(motionLayout.getProgress() != 0);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
    }

    private void logout() {
        userAppPreferences.clearPreferences();
        onBackPressed();
    }
}