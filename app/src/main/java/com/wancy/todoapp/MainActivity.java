package com.wancy.todoapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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
        Item item = new Item();
        item.setContent(content);
        itemAdapter.add(item);
        itemDAO.addOrUpdateItem(item);
        etEditText.setText("");
    }
    // A method for the editing function
    public void showEditDialog(final int index) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Edit Item");
        dialog.setContentView(R.layout.edit_dialog);
        final EditText txtEdit = (EditText) dialog.findViewById(R.id.txtEdit);
        txtEdit.setText(todoItems.get(index).getContent());
        // Set cursor to the end of the text
        int position = txtEdit.length();
        Editable text = txtEdit.getText();
        Selection.setSelection(text, position);
        Button btnEdit = (Button) dialog.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = todoItems.get(index);
                item.setContent(txtEdit.getText().toString());
                todoItems.set(index, item);
                itemDAO.addOrUpdateItem(item);
                itemAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
