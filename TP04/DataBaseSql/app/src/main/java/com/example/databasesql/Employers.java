package com.example.databasesql;

import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class Employers extends AppCompatActivity {
    private ListView listView;
    EmployerDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employers);
        Button exit = findViewById(R.id.return_button);
        listView = findViewById(R.id.list);
        db = new EmployerDbHelper(this);
        viewData();

        // Closing the Intent is better for memory optimization
        exit.setOnClickListener(v -> finish());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String []items = {"Modifier", "Supprimer"};
            AlertDialog.Builder builder = new AlertDialog.Builder(Employers.this);
            builder.setTitle("Action");
            builder.setItems(items, (dialogInterface, i) -> {
                if (i == 0) {
                    showUpdate(Employers.this, listView, position);
                } else if (i == 1) {
                    delete(listView, position);
                }
            });
            builder.show();
        });
    }

    private void viewData() {
        Cursor cursor = db.getAllData();
        ArrayList<String> list = new ArrayList<>();

        if (cursor.getCount() == 0) {
            Toast.makeText(Employers.this, "La base est vide", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                list.add(cursor.getString(0) + " " +
                        // So firstname and lastname doesn't get separated later in modifying
                        cursor.getString(1).replace(" ", "-") + " " +
                        cursor.getString(2) + " " +
                        cursor.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(Employers.this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                listView.setAdapter(listAdapter);
            }
        }
    }
    private void showUpdate(Activity activity, ListView listView, int position) {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_db);
        dialog.setTitle("Update");
        final EditText name = dialog.findViewById(R.id.newNameValue);
        final EditText mail = dialog.findViewById(R.id.newEmailValue);
        final EditText phone = dialog.findViewById(R.id.newPhoneValue);
        Button update = dialog.findViewById(R.id.update_button);
        final String[] chaine = listView.getAdapter().getItem(position).toString().split(" ");
        name.setText(chaine[1]);
        mail.setText(chaine[2]);
        phone.setText(chaine[3]);
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels*0.9);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        update.setOnClickListener(view -> {
            int id = Integer.parseInt(chaine[0]);
            db.update(name.getText().toString(), mail.getText().toString(), phone.getText().toString(), id);
            Toast.makeText(Employers.this, "Update succeeded", Toast.LENGTH_SHORT).show();
            // closing the dialog better fo memory optimization
            dialog.cancel();
            viewData();
        });
    }
    private void delete(ListView listView, int position) {
        String[] chaine = listView.getAdapter().getItem(position).toString().split(" ");
        int id = Integer.parseInt(chaine[0]);
        db.delete(id);
        Toast.makeText(this, "Deletion Succeeded", Toast.LENGTH_SHORT).show();
        viewData();
    }

}
