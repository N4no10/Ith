package cu.gob.ith.presentation.model;

import android.graphics.drawable.Drawable;

public class ItemMenuNavView {
    private String title;
    private Drawable icono;

    public ItemMenuNavView(String title, Drawable icono) {
        this.title = title;
        this.icono = icono;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcono() {
        return icono;
    }

    public void setIcono(Drawable icono) {
        this.icono = icono;
    }
}
