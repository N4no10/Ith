package cu.gob.ith.presentation.activities.main.fragments.ajustes;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import cu.gob.ith.data.preferences.PreferenceConstants;
import cu.gob.ith.data.preferences.UserAppPreferences;
import cu.gob.ith.databinding.CambiarPassDialogBinding;
import cu.gob.ith.databinding.FragmentAjustesBinding;
import cu.gob.ith.presentation.activities.main.fragments.ajustes.viewmodel.AjustesViewModel;
import cu.gob.ith.R;
import cu.gob.ith.presentation.activities.main.fragments.informe.viewmodel.InformePedidoFragmentViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import cu.gob.ith.presentation.util.MyProgressDialog;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@AndroidEntryPoint
public class AjustesFragment extends Fragment {

    private FragmentAjustesBinding uiBind;
    private AjustesViewModel mViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private AlertDialog alertDialog;
    private MyProgressDialog myProgressDialog;

    @Inject
    UserAppPreferences userAppPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_ajustes, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        initViewModels();
        setupViewComponents();
        listenerItemConfiguracion();
    }

    private void setupViewComponents() {
        mainActivityViewModel.setTitleToolBar(getString(R.string.menu_settings));
        mainActivityViewModel.getShowMenuOrBack().setValue(true);
    }

    private void initViewModels() {
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        mViewModel = new ViewModelProvider(this).get(AjustesViewModel.class);
    }

    private void listenerItemConfiguracion() {
        uiBind.opcCambiarPassLayout.getRoot().setOnClickListener(v -> initCambiarPass());
    }

    private void updatePassword(String passOld, String passNew) {
        if(myProgressDialog == null)
            myProgressDialog = new MyProgressDialog(requireContext(), getString(R.string.msg_change_pass_dialog));
        myProgressDialog.showProgress();

        mViewModel.addCompositeDisposable(
                mViewModel.updatePassword(userAppPreferences.getUsernamePreferences(
                        PreferenceConstants.USERNAME),passOld, passNew)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                            Log.e("Complete","complete");
                                    myProgressDialog.dissmisProgress();
                                    Snackbar.make(uiBind.getRoot(), getString(R.string.success_pass_dialog),
                                            Snackbar.LENGTH_LONG).show();
                                },
                                e -> {
                                    Log.e("Error","error " + e.getMessage());

                                    myProgressDialog.dissmisProgress();
                                    Snackbar.make(uiBind.getRoot(), "Error " + e.getMessage(),
                                            Snackbar.LENGTH_LONG).show();
                                })
        );
    }

    private void initCambiarPass() {
        CambiarPassDialogBinding cambiarPassDialogBinding = DataBindingUtil.inflate(
                LayoutInflater.from(requireContext()), R.layout.cambiar_pass_dialog,
                null, false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (cambiarPassDialogBinding.msgErrorTV.getVisibility() == View.VISIBLE)
                    cambiarPassDialogBinding.msgErrorTV.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        cambiarPassDialogBinding.passNewTextInputET.addTextChangedListener(textWatcher);
        cambiarPassDialogBinding.passNewTwoTextInputET.addTextChangedListener(textWatcher);
        cambiarPassDialogBinding.passOldTextInputET.addTextChangedListener(textWatcher);

        cambiarPassDialogBinding.buttonSaveIB.setOnClickListener(v2 -> {
            cambiarPassDialogBinding.setValidate(true);
            if (cambiarPassDialogBinding.passOldTextInputET.getText() != null &&
                    cambiarPassDialogBinding.passNewTextInputET.getText() != null &&
                    cambiarPassDialogBinding.passNewTwoTextInputET.getText() != null) {

                String passOld = cambiarPassDialogBinding.passOldTextInputET.getText().toString();
                String passNew = cambiarPassDialogBinding.passNewTextInputET.getText().toString();
                String passNewTwo = cambiarPassDialogBinding.passNewTwoTextInputET.getText().toString();

                if (!passOld.isEmpty() && !passNew.isEmpty() && !passNewTwo.isEmpty()) {
                    if (!passNew.equals(passNewTwo)) {
                        cambiarPassDialogBinding.msgErrorTV.setText(R.string.compare_pass_new);
                        cambiarPassDialogBinding.msgErrorTV.setVisibility(View.VISIBLE);
                    } else {
                        alertDialog.dismiss();
                        Log.e("user", "user " + PreferenceConstants.USERNAME + "   pass " + passNew);
                        updatePassword(passOld, passNew);
                    }
                } else {
                    cambiarPassDialogBinding.msgErrorTV.setText(R.string.credentials_empty);
                    cambiarPassDialogBinding.msgErrorTV.setVisibility(View.VISIBLE);
                }
            }


        });


        cambiarPassDialogBinding.buttonCancelIB.setOnClickListener(v2 -> {
            alertDialog.dismiss();
        });

        createAlertDialogCambiarPass(cambiarPassDialogBinding.getRoot());
    }

    private void createAlertDialogCambiarPass(View v) {
        if (alertDialog == null) {
            alertDialog = new MaterialAlertDialogBuilder(requireContext())
                    .setCancelable(false)
                    .create();

            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        alertDialog.setView(v);
        alertDialog.show();
    }
}