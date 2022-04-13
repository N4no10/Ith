package cu.gob.ith.presentation.activities.main.fragments.mispedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentMisPedidosBinding;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.viewmodel.MisPedidosViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MisPedidosFragment extends Fragment {
    private FragmentMisPedidosBinding uiBind;
    private MisPedidosViewModel mViewModel;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_mis_pedidos, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
        currentToolBar();

        initMotionOptionSelected();

    }

    private void initMotionOptionSelected() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.navegacionFragments);

        uiBind.contentCL.addTransitionListener(new MotionLayout.TransitionListener() {

            private int lastDestino;

            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
                lastDestino = getDestinoForIdConstriaintSet(startId);
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                if (lastDestino != 0)
                    navController.navigate(getDestinoForIdConstriaintSet(currentId), new Bundle(),
                            new NavOptions.Builder().setPopUpTo(lastDestino, true).build());
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
            }
        });
    }

    private int getDestinoForIdConstriaintSet(int id) {
        switch (id) {
            case R.id.start:
                return R.id.pedidosMesFragment;
            case R.id.opc2:
                return R.id.pedidosSinProcesarFragment;
            case R.id.opc3:
                return R.id.pedidosProcesadosFragment;
            case R.id.opc4:
                return R.id.pedidosEntregadosFragment;
            default:
                return 0;
        }
    }

    public void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(MisPedidosViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    private void currentToolBar() {
        mainActivityViewModel.setTitleToolBar(getString(R.string.mis_pedidos));
        mainActivityViewModel.setShowMenuOrBack(true);
    }


}