package com.example.recyclercard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.db.AppDataBase;
import com.example.db.Node;

public class SubTaskDetail extends AppCompatActivity {

    Node parentNode;
    Toolbar toolbar;
    TextView desc;
    int id;
    int rootid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_task_detail);

        id = Integer.parseInt(getIntent().getStringExtra("ID:"));
        rootid = Integer.parseInt(getIntent().getStringExtra("ROOT:"));

        parentNode = AppDataBase.getAppDatabase(getApplicationContext()).nodeDao().findById(id);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(parentNode.getName());

        desc = findViewById(R.id.sub_desc);
        desc.setText(parentNode.getDescription());

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit)
        {
            Intent intent = new Intent(SubTaskDetail.this, popup_edit.class);
            intent.putExtra("ID:", String.valueOf(parentNode.getId()));
            startActivityForResult(intent, 1);
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1)
        {
            parentNode = AppDataBase.getAppDatabase(getApplicationContext()).nodeDao().findById(id);
            toolbar.setTitle(parentNode.getName());
            desc.setText(parentNode.getDescription());

        }

    }
}
