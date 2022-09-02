package cu.gob.ith.presentation.activities.main.fragments.completarpedido.detalle.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetListProductosByDocumentUseCase;
import cu.gob.ith.domain.interactors.RequestOrderUseCase;
import cu.gob.ith.domain.model.InformePedido;
import cu.gob.ith.domain.model.Pedido;
import cu.gob.ith.domain.model.Producto;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class DetallePedidoIncompletoViewModel extends ViewModel {
    private final GetListProductosByDocumentUseCase getListProductosByDocumentUseCase;
    private final RequestOrderUseCase solicitarPedidoUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    // TODO: Implement the ViewModel
    @Inject
    public DetallePedidoIncompletoViewModel(GetListProductosByDocumentUseCase getListProductosByDocumentUseCase,
                                            RequestOrderUseCase solicitarPedidoUseCase) {
        this.getListProductosByDocumentUseCase = getListProductosByDocumentUseCase;
        this.solicitarPedidoUseCase = solicitarPedidoUseCase;
    }

    public Observable<List<Producto>> getListProductos(int numdoc) {
        return getListProductosByDocumentUseCase.execute(numdoc)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public Observable<InformePedido> solicitarPedido(List<Producto> pedidoList){
        return solicitarPedidoUseCase.execute(initListPedidos(pedidoList))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public List<Pedido> initListPedidos(List<Producto> listProducts){
        List<Pedido> pedidoList = new ArrayList<>();
        for(Producto producto : listProducts) {
           /* BigDecimal bd = BigDecimal.valueOf(producto.getPv() * producto.getCantProducto())
                    .setScale(2, RoundingMode.HALF_UP);
            float finalValue = bd.floatValue();*/
            pedidoList.add(new Pedido(
                    producto.getReferencia(),
                    producto.getPv(),
                    producto.getCantNecesaria(),
                    producto.getImporte()
            ));
        }

        return pedidoList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}