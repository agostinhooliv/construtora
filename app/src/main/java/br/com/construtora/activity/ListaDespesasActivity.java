package br.com.construtora.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.construtora.R;
import br.com.construtora.adapter.DespesasAdapter;
import br.com.construtora.dao.DespesasDAO;
import br.com.construtora.model.Despesas;
import br.com.construtora.task.DespesaTask;

public class ListaDespesasActivity extends AppCompatActivity {

    private ListView listaDespesasView;
    private DespesasDAO despesasDAO;
    private CheckBox checkBoxSincronizar;
    private List<Despesas> listaDespesas;
    private TextView tipoDespesasLabel;

    @Override
    protected void onResume() {
        super.onResume();
        buildDespesasList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_despesas);

        listaDespesasView = (ListView) findViewById(R.id.listaDespesasId);
        registerForContextMenu(listaDespesasView);
        listaDespesas = new ArrayList<>();

//        tipoDespesasLabel = (TextView) findViewById(R.id.tipo_listaId);
//        tipoDespesasLabel.setTextColor(Color.RED);
    }

    private void buildDespesasList() {

        despesasDAO = new DespesasDAO(getContext());
        DespesasAdapter studentsListViewAdapter = new DespesasAdapter(this, despesasDAO.listaDespesas());
        listaDespesasView.setAdapter(studentsListViewAdapter);

        despesasDAO.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem alterarMenuItem = menu.add("Alterar");
        MenuItem deleteMenuItem = menu.add("Delete");
       // MenuItem sincronizarMenuItem = menu.add("Sincronizar");

        AdapterView.AdapterContextMenuInfo adapterMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Despesas despesa = (Despesas) listaDespesasView.getItemAtPosition(adapterMenuInfo.position);

        buildUpdate(alterarMenuItem, despesa);
        buildDelete(deleteMenuItem, despesa);
        //buildSincronizar(sincronizarMenuItem, despesa);
    }
/*
    private void buildSincronizar(MenuItem menuItem, final Despesas despesa) {
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                listaDespesas.add(despesa);
                return false;
            }
        });

        alert("Lista: " + listaDespesas.size());

    }*/

    private void buildDelete(MenuItem menuItem, final Despesas despesa) {
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                despesasDAO = new DespesasDAO(getContext());
                despesasDAO.delete(despesa.getId());
                buildDespesasList();
                alert("Despesa apagada");
                return false;
            }
        });
    }

    private void buildUpdate(MenuItem menuItem, final Despesas despesa) {
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(ListaDespesasActivity.this, MainActivity.class);
                intent.putExtra("despesa", despesa);
                startActivity(intent);
                return false;
            }
        });
    }

    /*
    private void sincronizar() {

        DespesaTask despesaTask = null;

        for (Despesas despesa : listaDespesas) {

            despesaTask = new DespesaTask(getContext());
            despesaTask.setDespesa(despesa);
            despesaTask.execute();

            try {
                Boolean cadastroOK = new Boolean(despesaTask.get().toString().substring(despesaTask.get().toString().indexOf(":") + 1,
                        despesaTask.get().toString().lastIndexOf("}")));

                if (cadastroOK == true) {
                    alert("Despesas Sincronizadas com Sucesso!");
                } else {
                    alert("Error..");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            despesaTask = null;
        }
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_pag_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {

           /* case R.id.menu_list_sincronizarId:

                this.sincronizar();

                break;*/
            case R.id.menu_list_mainId:
                intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_list_sairId:
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
