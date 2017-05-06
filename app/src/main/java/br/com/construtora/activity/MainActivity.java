package br.com.construtora.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.construtora.R;
import br.com.construtora.dao.DespesasDAO;
import br.com.construtora.model.Despesas;
import br.com.construtora.task.DespesaTask;
import br.com.construtora.util.DespesasInsertUtil;
import br.com.construtora.util.Mascaras;

public class MainActivity extends AppCompatActivity {

    private EditText dataDespesa;
    private EditText dataVencimento;
    private EditText tipo;
    private EditText valor;
    private EditText observacao;
    private Button btnSalvar;
    private DespesaTask despesaTask;
    private DespesasDAO despesasDAO;
    private DespesasInsertUtil despesasInsertUtil;
    private Intent intent = null;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataDespesa = (EditText) findViewById(R.id.dataId);
        dataDespesa.addTextChangedListener(Mascaras.insert(Mascaras.DATA_MASK, dataDespesa));
        dataVencimento = (EditText) findViewById(R.id.vencimentoId);
        dataVencimento.addTextChangedListener(Mascaras.insert(Mascaras.DATA_MASK, dataVencimento));
        tipo = (EditText) findViewById(R.id.tipoId);
        valor = (EditText) findViewById(R.id.valorId);
        valor.addTextChangedListener(Mascaras.monetario(valor));
        observacao = (EditText) findViewById(R.id.obsId);

        intent = getIntent();
        final Despesas despesaIntent = (Despesas) intent.getSerializableExtra("despesa");

        if (despesaIntent != null) {
            if (despesaIntent.getData() == null) {
                dataDespesa.setText("");
            } else {
                dataDespesa.setText(despesaIntent.getData());
            }

            if (despesaIntent.getVencimento() == null) {
                dataVencimento.setText("");
            } else {
                dataVencimento.setText(despesaIntent.getVencimento());
            }

            if (despesaIntent.getTipo() == null) {
                tipo.setText("");
            } else {
                tipo.setText(despesaIntent.getTipo());
            }

            if (despesaIntent.getValor() == null) {
                valor.setText("");
            } else {
                valor.setText(despesaIntent.getValor());
            }


            if (despesaIntent.getObservacao() == null) {
                observacao.setText("");
            } else {
                observacao.setText(despesaIntent.getObservacao());
            }
        }

        despesasDAO = new DespesasDAO(getContext());
        despesasInsertUtil = new DespesasInsertUtil(MainActivity.this);

        btnSalvar = (Button) findViewById(R.id.btnSalvarId);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Despesas despesa = new Despesas();
                despesa.setData(dataDespesa.getText().toString());
                despesa.setVencimento(dataVencimento.getText().toString());
                despesa.setTipo(tipo.getText().toString());
                despesa.setValor(valor.getText().toString());
                despesa.setObservacao(observacao.getText().toString());
                despesa.setStatus("A");

                if (despesaIntent != null &&
                        despesasDAO.getDespesa(despesaIntent.getId()) != null) {
                    despesa.setId(despesaIntent.getId());
                    despesasDAO.update(despesa);
                    alert("Despesa Salva");
                } else {
                    despesasDAO.create(despesa);
                    alert("Despesa Cadastrada");
                }

                try {
                    despesasInsertUtil.buildDespesaForInsert();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getContext(), ListaDespesasActivity.class);
                startActivity(intent);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_pag_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.menu_main_listaId:
                intent = new Intent(getContext(), ListaDespesasActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_main_sairId:
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }

        intent = null;

        return super.onOptionsItemSelected(item);
    }

    private void alert(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    private Context getContext() {
        return this;
    }
}
