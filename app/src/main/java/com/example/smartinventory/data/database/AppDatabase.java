package com.example.smartinventory.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.smartinventory.data.dao.ProductDao;
import com.example.smartinventory.data.dao.SalesDao;
import com.example.smartinventory.data.dao.UserDao;
import com.example.smartinventory.data.entity.Product;
import com.example.smartinventory.data.entity.Sale;
import com.example.smartinventory.data.entity.User;

@Database(
        entities = {
                Product.class,
                Sale.class,
                User.class
        },
        version = 4, // 🔥 MUST INCREMENT AFTER ADDING USER
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    // DAOs
    public abstract ProductDao productDao();
    public abstract SalesDao salesDao();
    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "smart_inventory_db"
                            )
                            // ✅ DEV MODE SAFE
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
