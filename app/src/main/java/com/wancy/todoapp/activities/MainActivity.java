package com.wancy.todoapp.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.wancy.todoapp.models.Item;
import com.wancy.todoapp.R;
import com.wancy.todoapp.adapters.ItemAdapter;
import com.wancy.todoapp.fragments.EditDialogFragment;
import com.wancy.todoapp.utils.ItemDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditDialogFragment.EditDialogListener {
    ArrayList<Item> todoItems;
    ItemAdapter itemAdapter;
    ListView lvItems;
    EditText etEditText;
    ItemDAO itemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemDAO = new ItemDAO();
        populateArrayItems();
        etEditText = (EditText) findViewById(R.id.etEditText);
        // Add LongClickListener for removing action.
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = todoItems.get(i);
                todoItems.remove(i);
                itemDAO.deleteItem(item);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });
        // Add quick click listener for editing action.
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showEditDialog(i);
            }
        });
    }
    // Get data from file and convert into adapter to init the UI
    public void populateArrayItems() {
        todoItems = itemDAO.getItems();
        itemAdapter = new ItemAdapter(this, todoItems);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemAdapter);
    }

    public void onAddItem(View view) {
        String content = etEditText.getText().toString();
        if (content == null || content.length() == 0) return;
        Item item = new Item();
        item.setContent(content);
        itemAdapter.add(item);
        itemDAO.addOrUpdateItem(item);
        etEditText.setText("");
    }
    // A method for the editing function
    public void showEditDialog(final int index) {
        FragmentManager fm = getSupportFragmentManager();
        EditDialogFragment editDialogFragment =
                EditDialogFragment.newInstance(todoItems.get(index));
        editDialogFragment.show(fm, "edit_dialog");
    }

    @Override
    public void onFinishEditDialog(Item item) {
        itemDAO.addOrUpdateItem(item);
        itemAdapter.notifyDataSetChanged();
    }
}
