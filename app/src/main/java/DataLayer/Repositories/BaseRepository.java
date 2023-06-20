package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BaseRepository<T>{

    @Insert
    void insertAll(T... items);

    @Delete
    void delete(T item);

}
