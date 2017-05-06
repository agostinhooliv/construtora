package br.com.construtora.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.construtora.model.Despesas;

/**
 * Created by agostinhooliv on 04/05/17.
 */

public class DespesasDAO extends SQLiteOpenHelper {

    public DespesasDAO(Context context) {
        super(context, "Despesas", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableStudents =
                "CREATE TABLE despesas (" +
                        "idDespesas INTEGER PRIMARY KEY," +
                        "dt_dep TEXT NOT NULL," +
                        "sg_stat TEXT NOT NULL," +
                        "de_obs TEXT)";

        db.execSQL(sqlCreateTableStudents);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch (oldVersion) {
            case 1:
                String columnVencimento = "ALTER TABLE despesas ADD COLUMN dt_venc TEXT";
                db.execSQL(columnVencimento);
                String columnTipo = "ALTER TABLE despesas ADD COLUMN de_tipo TEXT";
                db.execSQL(columnTipo);
                String columValor = "ALTER TABLE despesas ADD COLUMN vr_desp TEXT";
                db.execSQL(columValor);
                //versão 2
                break;
        }
    }

    public void create(Despesas despesa) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues despesasValues = getContentValues(despesa);
        database.insert("Despesas", null, despesasValues);
    }

    public void delete(int id) {

        SQLiteDatabase database = getWritableDatabase();
        String[] params = {String.valueOf(id)};
        database.delete("Despesas", "idDespesas = ?", params);
    }

    public void update(Despesas despesa) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues studentValues = getContentValues(despesa);
        String[] params = {String.valueOf(despesa.getId())};
        database.update("Despesas", studentValues, "idDespesas = ?", params);

    }

    @NonNull
    private ContentValues getContentValues(Despesas despesas) {
        ContentValues despesasValues = new ContentValues();
        despesasValues.put("dt_dep", despesas.getData());
        despesasValues.put("dt_venc", despesas.getVencimento());
        despesasValues.put("de_tipo", despesas.getTipo());
        despesasValues.put("vr_desp", despesas.getValor());
        despesasValues.put("sg_stat", despesas.getStatus());
        despesasValues.put("de_obs", despesas.getObservacao());
        return despesasValues;
    }

    public Despesas getDespesa(int id) {

        Despesas despesa = null;

        SQLiteDatabase database = getReadableDatabase();
        String sqlReadStudents =
                "SELECT * FROM despesas " +
                        "WHERE idDespesas = " + id;

        Cursor cursorReadDespesas = database.rawQuery(sqlReadStudents, null);

        while (cursorReadDespesas.moveToNext()) {
            System.out.println("Existe");
            despesa = new Despesas();
            despesa.setId(cursorReadDespesas.getInt(cursorReadDespesas.getColumnIndex("idDespesas")));
            despesa.setData(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("dt_dep")));
            despesa.setVencimento(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("dt_venc")));
            despesa.setTipo(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("de_tipo")));
            despesa.setValor(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("vr_desp")));
            despesa.setStatus(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("sg_stat")));
            despesa.setObservacao(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("de_obs")));
        }

        return despesa;
    }

    public List<Despesas> listaDespesas() {

        SQLiteDatabase database = getReadableDatabase();
        String sqlReadStudents =
                "SELECT * FROM despesas";

        Cursor cursorReadDespesas = database.rawQuery(sqlReadStudents, null);

        List<Despesas> listaDespesas = new ArrayList<>();
        while (cursorReadDespesas.moveToNext()) {

            Despesas despesa = new Despesas();
            despesa.setId(cursorReadDespesas.getInt(cursorReadDespesas.getColumnIndex("idDespesas")));
            despesa.setData(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("dt_dep")));
            despesa.setVencimento(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("dt_venc")));
            despesa.setTipo(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("de_tipo")));
            despesa.setValor(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("vr_desp")));
            despesa.setStatus(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("sg_stat")));
            despesa.setObservacao(cursorReadDespesas.getString(cursorReadDespesas.getColumnIndex("de_obs")));

            listaDespesas.add(despesa);
        }

        cursorReadDespesas.close();

        return listaDespesas;
    }
}
