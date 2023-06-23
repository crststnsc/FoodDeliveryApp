package DataLayer.Repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import java.lang.reflect.ParameterizedType;

@Dao
public interface BaseRepository<T>{

    @Insert
    void insertAll(T... items);

    @Delete
    void delete(T item);

    @SuppressWarnings("unchecked")
    default Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
    }
}
