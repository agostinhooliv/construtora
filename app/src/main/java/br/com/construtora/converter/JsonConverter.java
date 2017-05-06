package br.com.construtora.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import br.com.construtora.R;
import br.com.construtora.model.Despesas;
import br.com.construtora.model.Usuarios;

/**
 * Created by agostinhooliv on 04/05/17.
 */

public class JsonConverter {

    public String convertUsuario(Usuarios usuarios) {

        JSONStringer js = new JSONStringer();

        try {
            js.object();
            js.key("login").value(usuarios.getLogin());
            js.key("senha").value(usuarios.getSenha());
            js.endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }

    public String convertDespesa(Despesas despesa) {

        JSONStringer js = new JSONStringer();

        try {
            js.object();
            js.key("dataDespesa").value(despesa.getData());
            js.key("dataVencimento").value(despesa.getVencimento());
            js.key("tipoDespesa").value(despesa.getTipo());
            js.key("valorDespesa").value(despesa.getValor());
            js.key("observacao").value(despesa.getObservacao());
            js.endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
