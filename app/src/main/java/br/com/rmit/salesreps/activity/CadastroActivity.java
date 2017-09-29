package br.com.rmit.salesreps.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.rmit.salesreps.R;
import br.com.rmit.salesreps.activity.Model.Usuario;
import br.com.rmit.salesreps.activity.config.ConfiguracaoFirebase;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText senha;
    private EditText email;
    private Button botaoCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText)findViewById(R.id.text_usuario);
        email = (EditText)findViewById(R.id.text_email);
        senha = (EditText)findViewById(R.id.text_senha);
        botaoCadastrar = (Button) findViewById(R.id.button_cadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                cadastrarUsuario();

            }
        });


    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar o usuário", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(CadastroActivity.this, "Erro ao cadastrar usuário", Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }

    public void abrirLoginUsuario(View view){

        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}
