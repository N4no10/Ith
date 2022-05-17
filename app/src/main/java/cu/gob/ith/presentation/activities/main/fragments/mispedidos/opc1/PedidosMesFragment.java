package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.gob.ith.R;
import cu.gob.ith.common.Util;
import cu.gob.ith.databinding.FragmentPedidosMesBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.OnClickDelegateRV;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.recyclerview.PedidosAdapter;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.viewmodel.PedidosMesViewModel;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.viewmodel.MisPedidosViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

@AndroidEntryPoint
public class PedidosMesFragment extends Fragment {

    private FragmentPedidosMesBinding uiBind;
    private MainActivityViewModel mainActivityViewModel;
    private MisPedidosViewModel misPedidosViewModel;
    private PedidosMesViewModel mViewModel;
    private PedidosAdapter pedidosAdapter;
    private OnClickDelegateRV onClickDelegateRV;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_pedidos_mes, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
        initOnClickInViewHolderItem();
        observers();

        initCalendario();
    }

    private void initCalendario() {
        MaterialDatePicker<Pair<Long, Long>> datePicker = MaterialDatePicker
                .Builder
                .dateRangePicker()
                .setTitleText("Seleccionar Fecha")
                .setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
                .setSelection(new Pair<>(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()))
                .build();

        datePicker.addOnPositiveButtonClickListener(selection ->
                updateViewFecha(selection.first + 86400000, selection.second + 86400000));

        uiBind.calendarioIB.setOnClickListener(v -> datePicker.show(
                requireActivity().getSupportFragmentManager(), "Calendario"));
    }

    private void updateAdapter(List<DatosPedido> datosPedidoList) {
        if (pedidosAdapter == null) {
            pedidosAdapter = new PedidosAdapter(datosPedidoList, onClickDelegateRV);
            uiBind.listPedidosRV.setAdapter(pedidosAdapter);
        } else {
            Log.e("reload List", "reload " + datosPedidoList.size());
            pedidosAdapter.loadList(datosPedidoList);
            uiBind.listPedidosRV.setAdapter(pedidosAdapter);
        }
    }

    private void observers() {
        mainActivityViewModel.isColapsedMainContent().observe(getViewLifecycleOwner(), collapsedMainContent -> {
            Log.e("ColapsedMenu", "ColapsedMenu " + collapsedMainContent);
            if (!collapsedMainContent) {
                updateViewFecha(MaterialDatePicker.thisMonthInUtcMilliseconds() + 86400000,
                        MaterialDatePicker.todayInUtcMilliseconds() + 86400000);
            }
        });

        mViewModel.getInicFecha().observe(getViewLifecycleOwner(), fechaInic -> uiBind.setFechaInic(Util.formatDate(fechaInic)));
        mViewModel.getFinFecha().observe(getViewLifecycleOwner(), fechaFin -> uiBind.setFechaFin(Util.formatDate(fechaFin)));
    }

    private void loadContent(Map<String, Object> paramQuery) {
        mViewModel.addCompositeDisposable(
                misPedidosViewModel.getListPedidos(paramQuery)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                datosPedidos -> {
                                    Log.e("onNext", "next" + datosPedidos.size());
                                    updateAdapter(datosPedidos);
                                    if (uiBind.calendarioIB.getVisibility() == View.INVISIBLE)
                                        uiBind.calendarioIB.setVisibility(View.VISIBLE);
                                }
                                ,
                                e -> Log.e("Error", "error cargando pedidos " + e.getMessage())
                        ));
    }

    private void updateViewFecha(long fechaInic, long fechaFin) {
       /* if ((mViewModel.getInicFecha().getValue() == null && mViewModel.getFinFecha().getValue() == null)
                || (mViewModel.getInicFecha().getValue() != null && fechaInic != mViewModel.getInicFecha().getValue() ||
                mViewModel.getFinFecha().getValue() != null && fechaFin != mViewModel.getFinFecha().getValue())) {*/
        mViewModel.setInicFecha(fechaInic);
        mViewModel.setFinFecha(fechaFin);

            Map<String, Object> param = new HashMap<>();
            param.put("options.fechaInicio", Util.formatDateCalendarView(mViewModel.getInicFecha().getValue()));
            param.put("options.fechaFin", Util.formatDateCalendarView(mViewModel.getFinFecha().getValue()));

        loadContent(param);
        // }
    }

    private void initViewModel() {
        misPedidosViewModel = new ViewModelProvider(requireParentFragment().requireParentFragment())
                .get(MisPedidosViewModel.class);
        mViewModel = new ViewModelProvider(this).get(PedidosMesViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    private void initOnClickInViewHolderItem() {
        onClickDelegateRV = id -> {
            Bundle bundle = new Bundle();
            bundle.putString("pedidoId", id);
            Navigation.findNavController(requireParentFragment().requireParentFragment().requireView())
                    .navigate(R.id.to_detallesPedidoFragment, bundle);
            Log.e("TAG", "onClick: RV: " + id);
        };
    }
}