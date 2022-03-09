package cu.gob.ith.presentation.model;

import android.graphics.drawable.Drawable;

import java.util.List;

public class ItemMenuNavView {
    private String title;
    private Drawable icono;
    private List<ItemMenuNavView> listSubMenu;
    private boolean selected;

    public ItemMenuNavView(String title, Drawable icono) {
        this.title = title;
        this.icono = icono;
    }

    public ItemMenuNavView(String title, Drawable icono, List<ItemMenuNavView> listSubMenu) {
        this.title = title;
        this.icono = icono;
        this.listSubMenu = listSubMenu;
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

    public List<ItemMenuNavView> getListSubMenu() {
        return listSubMenu;
    }

    public void setListSubMenu(List<ItemMenuNavView> listSubMenu) {
        this.listSubMenu = listSubMenu;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
