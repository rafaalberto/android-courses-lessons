package br.com.todo.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task ORDER BY priority")
    List<TaskEntry> findAll();

    @Query("SELECT * FROM task WHERE id = :id")
    TaskEntry findById(int id);

    @Insert
    void insert(TaskEntry taskEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(TaskEntry taskEntry);

    @Delete
    void delete(TaskEntry taskEntry);

}
