package com.wancy.todoapp.utils;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.wancy.todoapp.models.MyDatabase;
import com.wancy.todoapp.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private DatabaseDefinition database = FlowManager.getDatabase(MyDatabase.class);
    public void addOrUpdateItem(Item item) {

        ProcessModelTransaction<Item> processModelTransaction =
                new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<Item>() {
                            @Override
                            public void processModel(Item item, DatabaseWrapper databaseWrapper) {
                                item.save();
                            }
                        }).add(item).build();
        database.beginTransactionAsync(processModelTransaction)
        .build().execute();
    }


    public ArrayList<Item> getItems() {
        List<Item> list = SQLite.select()
                .from(Item.class)
                .queryList();
        return (ArrayList<Item>) list;
    }


    public void deleteItem(Item item) {
        ProcessModelTransaction<Item> processModelTransaction =
                new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<Item>() {
                            @Override
                            public void processModel(Item item, DatabaseWrapper databaseWrapper) {
                                item.delete();
                            }
                        }).add(item).build();
        database.beginTransactionAsync(processModelTransaction)
                .build().execute();
    }
}
