package cu.gob.ith.domain.interactors;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiProductoToProducto;
import cu.gob.ith.domain.model.Producto;
import io.reactivex.rxjava3.core.Observable;

public class GetDetallesPedidoITHUseCase
        implements GlobalUseCase<Observable<List<Producto>>, Integer> {

    private final ImplRepository implRepository;

    @Inject
    public GetDetallesPedidoITHUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<Producto>> execute(Integer numPedido) {
        return implRepository.findAllDetallesPedidoDespachadosyNoDespachados(numPedido)
                .map(apiListDetallesPedido -> {
                    Log.e("TAG", "execute: " + apiListDetallesPedido.getApiDatosPedido());
                    return TransformApiProductoToProducto
                            .mapToDetalleProductoList(apiListDetallesPedido.getApiDatosPedido());
                });
    }

}
