package cu.gob.ith.presentation.activities.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import cu.gob.ith.R;
import cu.gob.ith.databinding.ActivityLoginBinding;
import cu.gob.ith.presentation.activities.main.ui.MainActivity;

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
            uiBind.layoutFormLogin.buttonLoginIB.startAnimation();
            startActivity(new Intent(this, MainActivity.class));

        });

    }
}