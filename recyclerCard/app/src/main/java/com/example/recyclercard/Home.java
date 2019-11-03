package com.example.recyclercard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db.AppDataBase;
import com.example.db.Node;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private List<Node> items;
    private List<Boolean> expand;

    private RecyclerView nodesView;
    private RecyclerView.Adapter nodesViewAdapter;
    private RecyclerView.LayoutManager nodesLayoutManager;

    private Node parentNode;
    private int rootid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int parent_id = Integer.parseInt(getIntent().getStringExtra("ID:"));
        rootid = Integer.parseInt(getIntent().getStringExtra("ROOT:"));
        parentNode = AppDataBase.getAppDatabase(getApplicationContext()).nodeDao().findById(parent_id);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //TextView toolBarParent = findViewById(R.id.parentNode);
        toolbar.setTitle(parentNode.getName());
        //toolBarParent.setText("Zen");
        setSupportActionBar(toolbar);

        items = new ArrayList<Node>();
        expand = new ArrayList<Boolean>();

        nodesView = findViewById(R.id.nodesView);

        nodesLayoutManager = new LinearLayoutManager(this);
        nodesView.setLayoutManager(new LinearLayoutManager(this));

        nodesViewAdapter = new MyAdapter(items, expand, getApplicationContext(), this, rootid);
        nodesView.setAdapter(nodesViewAdapter);

        TextView textView = findViewById(R.id.parentDesc);
        textView.setText(parentNode.getDescription());

        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.addButton)
        {
            Intent intent = new Intent(Home.this, popup.class);
            intent.putExtra("ID:", String.valueOf(parentNode.getId()));
            startActivityForResult(intent, 1);
            updateUI();
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 || requestCode == 2 || requestCode == 3 || requestCode == 4)
            updateUI();

    }

    public void updateUI()
    {
        items.clear();
        List<Node> temp = AppDataBase.getAppDatabase(getApplicationContext()).nodeDao().findbyParentId(parentNode.getId());
        for(int i=0; i<temp.size(); i++) {
            items.add(temp.get(i));
            expand.add(false);
        }
        nodesViewAdapter.notifyDataSetChanged();
    }
}
