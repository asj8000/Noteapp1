package org.town.android.noteapp;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import com.orm.SugarContext;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView = null;
    private FloatingActionButton add = null;
    private ListAdapter listAdapter = null;
    private List<Note> noteList = null;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(getApplicationContext());
        init();
    }

    private void init() {
        listView = findViewById(R.id.listView);
        add = findViewById(R.id.add);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setListView(false);
    }

    private void setListView(boolean isRemoveEnabled) {
        try {
            noteList = Note.listAll(Note.class);//Select * from note
            if (noteList != null) {
                listAdapter = new ListAdapter(getApplicationContext(), R.layout.item_default_note_list, noteList, isRemoveEnabled);
                listView.setAdapter(listAdapter);
                listView.deferNotifyDataSetChanged();//갱신
              //  listView.setOnItemClickListener((parent, view, position, id) -> {
                //    Intent intent = new Intent(MainActivity.this, NoteViewActivity.class);
                  //  intent.putExtra("id", listAdapter.getNoteList().get(position).getId());
                 //   startActivity(intent);
              //  });
            }
        } catch (SQLiteException e) {
        }
    }

//    private void setAddButtonListener(){
//        add.setOnClickListener(v ->{
//            startActivity(new Intent(MainActivity.this,AddActivity.class));
//        });
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}

