package br.com.construtora.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.construtora.R;
import br.com.construtora.model.Despesas;

/**
 * Created by agostinhooliv on 05/05/17.
 */

public class DespesasAdapter extends BaseAdapter {

    private final Context context;
    private final List<Despesas> despesas;

    public DespesasAdapter(Context context, List<Despesas> despesas) {
        this.context = context;
        this.despesas = despesas;
    }

    @Override
    public int getCount() {
        return this.despesas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.despesas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.despesas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       Despesas despesa = this.despesas.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.despesas_lista, parent, false);
        }
        TextView despesaTipo = (TextView) view.findViewById(R.id.tipo_listaId);
        despesaTipo.setText(despesa.getTipo());

        TextView despesaValor = (TextView) view.findViewById(R.id.valor_listaId);
        despesaValor.setText(despesa.getValor());

//        ImageView studentPhoto = (ImageView) view.findViewById(R.id.studentList_imageViewPhoto);
//        if (student.getPathPhoto() != null) {
//            Bitmap bitmap = BitmapFactory.decodeFile(student.getPathPhoto());
//            Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
//            studentPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
//            studentPhoto.setImageBitmap(bitmapReduce);
//        }

        return view;
    }
}
