package cu.gob.ith.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import cu.gob.ith.R;
import cu.gob.ith.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding uiBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBind = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setContentView(uiBind.getRoot());

        initView();
    }

    private void initView() {
        uiBind.layoutFormLogin.buttonLoginIB.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class));
        });

    }
}