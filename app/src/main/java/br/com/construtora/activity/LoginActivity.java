package br.com.construtora.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.construtora.R;
import br.com.construtora.model.Usuarios;
import br.com.construtora.task.LoginTask;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private Button btnLogar;
    private Usuarios usuario;
    private LoginTask loginTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (EditText) findViewById(R.id.loginId);
        senha = (EditText) findViewById(R.id.senhaId);

        btnLogar = (Button) findViewById(R.id.btnEntrarId);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;

                if (getString(R.string.ambiente_producao).equals("true")) {
                    usuario = new Usuarios(login.getText().toString(), senha.getText().toString());
                    loginTask = new LoginTask(getContext());
                    loginTask.setUsuario(usuario);
                    loginTask.execute();

                    try {
                        Boolean logado = new Boolean(loginTask.get().toString().substring(loginTask.get().toString().indexOf(":") + 1,
                                loginTask.get().toString().lastIndexOf("}")));

                        if (logado == true) {
                            intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            login.setText(null);
                            senha.setText(null);
                            alert("Login ou Senha inválidos..");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    loginTask = null;
                } else {
                    if(login.getText().toString().equals("usuario")
                            && senha.getText().toString().equals("123")){
                        intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        login.setText(null);
                        senha.setText(null);
                        alert("Login ou Senha inválidos..");
                    }
                }
            }
        });
    }

    private void alert(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    private Context getContext() {
        return this;
    }
}
