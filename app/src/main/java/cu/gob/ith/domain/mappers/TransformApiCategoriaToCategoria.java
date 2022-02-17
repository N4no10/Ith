package cu.gob.ith.domain.mappers;

import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.domain.model.Categoria;

public class TransformApiCategoriaToCategoria {


    public static Categoria map(ApiCategoria param) {
        return new Categoria(param.getNombreFamilia(),
                param.getCodFamilia(),
                param.getProductosFamilia());
    }

    public static List<Categoria> mapList(List<ApiCategoria> param) {
        List<Categoria> categoriaList = new ArrayList<>();
        for (ApiCategoria apiCategoria : param
        ) {
            categoriaList.add(map(apiCategoria));
        }
        return categoriaList;
    }
}
