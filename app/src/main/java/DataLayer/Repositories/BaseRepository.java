package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface BaseRepository<T>{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(T... items);

    @Delete
    void delete(T item);
}
