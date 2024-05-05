package ru.wolfnord.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    ArrayAdapter<String> subjectsAdapter;
    ArrayList<String> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String info = bundle.getString("subject");
        switch (Objects.requireNonNull(info)) {
            case "Информатика":
                subjects = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.computer_science)));
                break;
            case "Математика":
                subjects = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.mathematics)));
                break;
            case "Биология":
                subjects = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.biology)));
                break;
            case "Химия":
                subjects = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.chemistry)));
                break;
            default:
                subjects = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.physics)));
                break;
        }
        subjectsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjects);
        ListView subjectsListView = findViewById(R.id.list2);
        subjectsListView.setAdapter(subjectsAdapter);
        Button addButton = findViewById(R.id.addButton);
        Button removeButton = findViewById(R.id.removeButton);
        addButton.setOnClickListener(this);
        removeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addButton)
        {
            Log.d("RRR", "1");
            if (!subjects.isEmpty())
                subjects.add(subjects.get(subjects.size() - 1));
        }
        else {
            Log.d("RRR", "2");
            if (!subjects.isEmpty()) {
                subjects.remove(subjects.size() - 1);
            }
        }
        subjectsAdapter.notifyDataSetChanged();
    }
}