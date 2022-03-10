package cu.gob.ith.presentation.activities.main.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import java.util.Objects;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.data.preferences.UserAppPreferences;
import cu.gob.ith.databinding.ActivityMainBinding;
import cu.gob.ith.presentation.activities.main.recyclerview.ClickItemMenuInterface;
import cu.gob.ith.presentation.activities.main.recyclerview.ItemMenuAdapter;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import cu.gob.ith.presentation.model.ItemMenuNavView;
import cu.gob.ith.presentation.util.Util;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements ClickItemMenuInterface {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding uiBind;
    private ItemMenuAdapter itemMenuAdapter;

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
        initNavView();
        initTransformMotionLayout();
        observerChangeToolBar();
        initShopCar();
    }

    private void initShopCar() {
        uiBind.contentMainActivityLayout.toolbarLayout.shopCarIV.setOnClickListener(v ->
                initNavigateWithoutPopUpTo(R.id.to_pedidoListFragment, "itemMenuNavView.getTitle()"));
    }

    private void initNavView() {
        uiBind.contentNavViewLayout.userHeaderTV.setText(userAppPreferences.getNamePreferences("not Found"));
        uiBind.contentNavViewLayout.emailHeaderTV.setText(userAppPreferences.getEmailPreferences("not Found"));
        initAdapterNavMenu();
    }

    private void initAdapterNavMenu() {
        itemMenuAdapter = new ItemMenuAdapter(mainActivityViewModel.getItemMenuNavViewList(),
                false);
        itemMenuAdapter.setClickItemMenuInterface(this);
        uiBind.contentNavViewLayout.setAdapter(itemMenuAdapter);
        uiBind.contentNavViewLayout.logoutItemLayout.getRoot().setOnClickListener(v -> logout());

    }

    private boolean goToStartTransitionNavView() {
        Log.e("aaa", "aaaa " + uiBind.motionLayoutDrawerMainActivity.getProgress());
        if (uiBind.motionLayoutDrawerMainActivity.getProgress() > 0.0) {
            uiBind.motionLayoutDrawerMainActivity.transitionToStart();
            return true;
        }
        return false;
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
                mainActivityViewModel.setColapsedMainContent(motionLayout.getProgress() != 0.0);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });


    }

    private void observerChangeToolBar() {
        mainActivityViewModel.getTitleToolBar().observe(this,
                title -> {
                    uiBind.contentMainActivityLayout.setTitle(title);
                    if (title.equals(this.getString(R.string.menu_nuevo_pedido)))
                        uiBind.contentMainActivityLayout.toolbarLayout.shopCarIV.setVisibility(View.VISIBLE);
                    else
                        uiBind.contentMainActivityLayout.toolbarLayout.shopCarIV.setVisibility(View.GONE);
                });

        mainActivityViewModel.getShowMenuOrBack().observe(this,
                showMenuOrBack -> {
                    if (!showMenuOrBack) {
                        uiBind.contentMainActivityLayout.toolbarLayout.menuIV.setVisibility(View.GONE);
                        uiBind.contentMainActivityLayout.toolbarLayout.backIV.setVisibility(View.VISIBLE);
                        uiBind.contentMainActivityLayout.toolbarLayout.backIV.setOnClickListener(v -> onBackPressed());
                    } else {
                        uiBind.contentMainActivityLayout.toolbarLayout.backIV.setVisibility(View.GONE);
                        uiBind.contentMainActivityLayout.toolbarLayout.menuIV.setVisibility(View.VISIBLE);
                    }
                });

        mainActivityViewModel.setShowMenuOrBack(true);
    }

    private void logout() {
        userAppPreferences.clearPreferences();
        onBackPressed();
    }

    @Override
    public void clickItemMenu(ItemMenuNavView itemMenuNavView) {
        Log.e("click", "click " + itemMenuNavView.getTitle());
        goToStartTransitionNavView();

        if (!itemMenuNavView.isSelected()) {
            itemMenuAdapter.selectedItemMenu(itemMenuNavView);
            navigateByItemMenu(itemMenuNavView);
        }

    }

    private void navigateByItemMenu(ItemMenuNavView itemMenuNavView) {
        if (itemMenuNavView.getTitle().equals(getString(R.string.inicio_fragment)))
            initNavigate(R.id.inicioFragment, itemMenuNavView.getTitle());
        else if (itemMenuNavView.getTitle().equals(getString(R.string.nuevo_pedido_fragment)))
            initNavigate(R.id.menuFragment, itemMenuNavView.getTitle());
    }

    private void initNavigate(int destino, String title) {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);
        if (navController.getCurrentDestination() != null)
            navController.navigate(destino, new Bundle(),
                    new NavOptions.Builder().setPopUpTo(Objects.requireNonNull(
                            navController.getCurrentDestination())
                            .getId(), true).build());
    }

    private void initNavigateWithoutPopUpTo(int destino, String title) {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);
        if (navController.getCurrentDestination() != null)
            navController.navigate(destino, new Bundle());
    }
}