package org.town.android.noteapp;


import android.content.Context;
import android.icu.util.ValueIterator;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;

public class ListAdapter extends ArrayAdapter<Note> {
    private Context context = null;
    private int resource = 0;
    private int count = 0;
    private List<Note> noteList = null;
    private boolean isRemoveEnabled=false;
    private TextView title = null;
    private CheckBox checkBox = null;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Note> noteList, boolean isRemoveEnabled) {
        super(context, resource, noteList);
        this.context = context;
        this.noteList = noteList;
        this.isRemoveEnabled = isRemoveEnabled;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);
        }

        title = view.findViewById(R.id.title);
        checkBox = view.findViewById(R.id.isRemoved);
        checkBox.setOnClickListener((View v) -> {
            if(count == 0){
                noteList.get(position).setIsChecked(true);
                count = 1;
            }
                else{
                    noteList.get(position).setIsChecked(false);
            }
        });
        if(isRemoveEnabled && checkBox.isChecked() == true)
            noteList.removeAll(noteList.stream().filter(note ->  note.isChecked()).collect(Collectors.toList()));
            return view;
    }
}
