package com.tarannelavelli.notes;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteActivity extends AppCompatActivity {
    private EditText editText;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editText = findViewById(R.id.note_edit_text);
        FloatingActionButton button = findViewById(R.id.remove_note_button);
        String contents = getIntent().getStringExtra("contents");
        id = getIntent().getIntExtra("id", 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.noteDao().delete(id);
                finish();
            }
        });
        editText.setText(contents);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.database.noteDao().save(editText.getText().toString(), id);
    }
}
