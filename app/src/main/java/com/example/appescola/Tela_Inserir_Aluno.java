package com.example.appescola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Inserir_Aluno extends AppCompatActivity {

    SQLiteDatabase db;
    EditText nome;
    EditText idade;
    EditText curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__inserir__aluno);

        nome = findViewById(R.id.ed_nome);
        idade = findViewById(R.id.ed_idade);
        curso = findViewById(R.id.ed_curso);

        criarBancoDados();
    }

    public void salvarDados(View v){

        String no = nome.getText().toString().trim();
        String cur = curso.getText().toString().trim();
        String ids = idade.getText().toString().trim();

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO aluno(nome, curso, idade) VALUES (");
        sql.append("'" + no + "'" + ", ");
        sql.append("'" + cur + "'" + ", ");
        sql.append(ids);
        sql.append(")");

        try {
            Toast.makeText(getApplicationContext(), sql.toString(), Toast.LENGTH_LONG).show();
            db.execSQL(sql.toString());
        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "Maluco deu treta: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void criarBancoDados(){
        db = openOrCreateDatabase("appEscola.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS aluno(");
        sql.append("id SERIAL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome VARCHAR(255), ");
        sql.append("idade VARCHAR(3), ");
        sql.append("curso varchar(255) ");
        sql.append(")");

        try {
            db.execSQL(sql.toString());
        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void selectTest (View v){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from aluno");

        try {
            Toast.makeText(getApplicationContext(), sql.toString(), Toast.LENGTH_LONG).show();
            db.execSQL(sql.toString());
        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "Maluco deu treta: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
