package com.example.recyclercard;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.AppDataBase;
import com.example.db.Node;

public class popup extends AppCompatActivity {

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

        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        int id = Integer.parseInt(getIntent().getStringExtra("ID:"));
                        EditText title = findViewById(R.id.editTitle);
                        EditText desc = findViewById(R.id.editDesc);
                        EditText date = findViewById(R.id.editDate);

                        Node temp = new Node();
                        temp.setName(title.getText().toString());
                        temp.setDescription(desc.getText().toString());
                        temp.setDate(date.getText().toString());
                        temp.setParent_id(id);
                        AppDataBase.getAppDatabase(getApplicationContext()).nodeDao().insertAll(temp);

                        Intent intent = new Intent();
                        intent.putExtra("id","1");
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
        );
    }
}
