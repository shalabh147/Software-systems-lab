package com.example.recyclercard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.AppDataBase;
import com.example.db.Node;
import com.example.db.NodeDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int root = setupDataBase();

        Intent home = new Intent(this,Home.class);
        home.putExtra("ID:", String.valueOf(root));
        home.putExtra("ROOT:", String.valueOf(root));
        startActivity(home);
    }

    public int setupDataBase()
    {
        NodeDao dao = AppDataBase.getAppDatabase(getApplicationContext()).nodeDao();
        dao.purge();
        Node temp = new Node();

        temp.setName("Zen");
        temp.setDescription("If you chase two rabbits, you catch none");
        dao.insertAll(temp);
        int rootid = dao.findByName("Zen").getId();

        temp.setName("Acads");
        temp.setDescription("Padhai ki baatein");
        temp.setDate("2019-12-31");
        temp.setParent_id(rootid);
        dao.insertAll(temp);

        temp.setName("Self improvement");
        temp.setDescription("Reading list, blogs, exercise, etc.");
        temp.setDate("2019-12-30");
        temp.setParent_id(rootid);
        dao.insertAll(temp);
        int parentid = dao.findByName("Self improvement").getId();

        temp.setName("Exercise");
        temp.setDescription("Someday?");
        temp.setDate("2021-02-27");
        temp.setParent_id(parentid);
        dao.insertAll(temp);

        temp.setName("Reading list");
        temp.setDescription("My bucket list:\nHear the Wind Sing\nThe foundation \nAtlas Shrugged\nA prisoner of birth");
        temp.setDate(null);
        temp.setParent_id(parentid);
        dao.insertAll(temp);

        temp.setName("Research");
        temp.setDescription("Pet Projects");
        temp.setDate(null);
        temp.setParent_id(rootid);
        dao.insertAll(temp);

        temp.setName("Hobbies");
        temp.setDescription("<3");
        temp.setDate(null);
        temp.setParent_id(rootid);
        dao.insertAll(temp);
        parentid = dao.findByName("Hobbies").getId();

        temp.setName("Origami");
        temp.setDescription("crames and tigers");
        temp.setDate("2021-10-29");
        temp.setParent_id(parentid);
        dao.insertAll(temp);

        temp.setName("");
        temp.setName("Drum practice");
        temp.setDescription("Aim: \nHallowed be thy name,\nAcid Rain (LTE)");
        temp.setDate("2021-10-29");
        temp.setParent_id(parentid);
        dao.insertAll(temp);
        return rootid;
    }
}
