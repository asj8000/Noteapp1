package org.town.android.noteapp;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Note extends SugarRecord{
    private  String title; //제목
    private  String content; //내용
    private  boolean isChecked=false; //삭제 체크 됬는지

    public Note(String title,String content){
        this.title=title;
        this.content=content;
    }
    public void setIsChecked(boolean isChecked){
        this.isChecked=isChecked;
    }
}
