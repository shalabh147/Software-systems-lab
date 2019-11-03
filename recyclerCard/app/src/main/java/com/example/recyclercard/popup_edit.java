package com.example.recyclercard;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.AppDataBase;
import com.example.db.Node;

public class popup_edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.5));

        Button button = findViewById(R.id.Save);

        final int id = Integer.parseInt(getIntent().getStringExtra("ID:"));

        final EditText title = findViewById(R.id.editTitle);
        final EditText desc = findViewById(R.id.editDesc);
        final EditText date = findViewById(R.id.editDate);

        Node n= AppDataBase.getAppDatabase(getApplicationContext()).nodeDao().findById(id);
        title.setText(n.getName());
        desc.setText(n.getDescription());
        date.setText(n.getDate());
        final int parent = n.getParent_id();

        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        AppDataBase.getAppDatabase(getApplicationContext()).nodeDao().updateById(id, title.getText().toString(),desc.getText().toString(),date.getText().toString());
                        finish();
                    }
                }
        );
    }
}
