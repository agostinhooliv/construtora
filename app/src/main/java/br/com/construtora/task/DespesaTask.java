package br.com.construtora.task;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import br.com.construtora.converter.JsonConverter;
import br.com.construtora.model.Despesas;
import br.com.construtora.servico.ServicoWeb;

/**
 * Created by agostinhooliv on 04/05/17.
 */

public class DespesaTask extends AsyncTask {

    private Context context;
    private Dialog dialog;
    private JsonConverter jsonConverter;
    private Despesas despesa;

    public DespesaTask(Context context) {
        this.context = context;
        jsonConverter = new JsonConverter();
    }

    @Override
    public Object doInBackground(Object[] params) {
        String json = jsonConverter.convertDespesa(despesa);
        ServicoWeb servicoWeb = new ServicoWeb();
        String response = servicoWeb.postDespesas(json);
        return response;
    }

    @Override
    protected void onPostExecute(Object o) {
        String response = (String) o;
        super.onPostExecute(o);
        dialog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde...", "Verificando os dados!", true, true);
    }

    public Despesas getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesas despesa) {
        this.despesa = despesa;
    }
}
