package br.com.construtora.util;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import br.com.construtora.R;
import br.com.construtora.model.Despesas;

/**
 * Created by agostinhooliv on 05/05/17.
 */

public class DespesasInsertUtil {

    private EditText dataDespesa;
    private EditText dataVencimento;
    private EditText tipo;
    private EditText valor;
    private EditText observacao;
    private Despesas despesa;

    public DespesasInsertUtil(AppCompatActivity appCompatActivity){
        dataDespesa = (EditText) appCompatActivity.findViewById(R.id.dataId);
        dataVencimento = (EditText) appCompatActivity.findViewById(R.id.vencimentoId);
        tipo = (EditText) appCompatActivity.findViewById(R.id.tipoId);
        valor = (EditText) appCompatActivity.findViewById(R.id.valorId);
        observacao = (EditText) appCompatActivity.findViewById(R.id.obsId);
    }

    public Despesas buildDespesaForInsert() throws Exception{

        if (dataDespesa.getText().toString().equals("")) {
            throw new Exception("Campo 'dataDespesa' obrigatório!");
        }

        if (dataVencimento.getText().toString().equals("")) {
            throw new Exception("Campo 'dataVencimento' obrigatório!");
        }

        if (tipo.getText().toString().equals("")) {
            throw new Exception("Campo 'tipo' obrigatório!");
        }

        if (valor.getText().toString().equals("")) {
            throw new Exception("Campo 'valor' obrigatório!");
        }

        despesa = new Despesas();
        this.despesa.setData(dataDespesa.getText().toString());
        this.despesa.setVencimento(dataVencimento.getText().toString());
        this.despesa.setTipo(tipo.getText().toString());
        this.despesa.setValor(valor.getText().toString());
        this.despesa.setObservacao(observacao.getText().toString());
        this.despesa.setStatus("A");

        return this.despesa;
    }

    public void buildEditDespesa(Despesas  despesa){

        dataDespesa.setText(despesa.getData());
        dataVencimento.setText(despesa.getVencimento());
        tipo.setText(despesa.getTipo());
        valor.setText(despesa.getValor());
        observacao.setText(despesa.getObservacao());

        this.despesa = despesa;
    }
}
