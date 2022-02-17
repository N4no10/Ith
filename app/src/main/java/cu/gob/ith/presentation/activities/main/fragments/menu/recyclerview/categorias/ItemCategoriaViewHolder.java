package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias;

import android.util.Log;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemCategoriaBinding;
import cu.gob.ith.domain.model.Categoria;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemCategoriaViewHolder extends ViewHolderGlobal<Categoria, ItemCategoriaBinding> {
    public ItemCategoriaViewHolder(@NonNull ItemCategoriaBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(Categoria param) {

        getUiBind().setCategoria(param);

        Log.e("bind","bind " + param.getNombreFamilia() + " " + param.isSelected());
        if(param.isSelected()) {
            /*getUiBind().motionItemML.setScaleY(1.0f);
            getUiBind().titleTV.setTextColor(Color.parseColor("#FF03DAC5"));*/
            getUiBind().motionItemML.transitionToEnd();
            getUiBind().fotoFondoForegroundIV.setAlpha(0.0f);
        }else {
            /*getUiBind().motionItemML.setScaleY(0.9f);
            getUiBind().titleTV.setTextColor(Color.parseColor("#000000"));*/
            getUiBind().motionItemML.setProgress(0.0f);
            getUiBind().fotoFondoForegroundIV.setAlpha(1.0f);
        }

        super.bind(param);
    }
}
