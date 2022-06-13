package cu.gob.ith.presentation.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import cu.gob.ith.R;
import cu.gob.ith.databinding.MyProgressDialogBinding;

public class MyProgressDialog {
    private AlertDialog alertDialog;
    private MyProgressDialogBinding uiBind;

    public MyProgressDialog(Context context, String msg) {
        uiBind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.my_progress_dialog,
                null, false);
        uiBind.setMsg(msg);

        alertDialog = new MaterialAlertDialogBuilder(context)
                .setView(uiBind.getRoot())
                .setCancelable(false)
                .create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void showProgress(){
        if(alertDialog != null && !alertDialog.isShowing())
            alertDialog.show();
    }

    public void dissmisProgress(){
        if(alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }
}
