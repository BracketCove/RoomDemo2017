/*
 * *
 *  * Copyright (C) 2017 Ryan Kay Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package roomdemo.wiseass.com.roomdemo.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * A DAO, or Data Access Object, is a layer of abstraction (interface) between Java Objects and
 * SQL Statements.
 *
 * For a given entity, it defines how we may manage said entity within the Database.
 * Created by R_KAY on 7/31/2017.
 */
@Dao
public interface ListItemDao {

    /**
     * Get entity by itemId. For this App, we will pass in an ID when the detail Activity starts;
     * therefore we need not use LiveData as the Data will not change during the Activity's
     * Lifecycle.
     * @param itemId A Unique identifier for a given record within the Database.
     * @return
     */
    @Query("SELECT * FROM ListItem WHERE itemId = :itemId")
    LiveData<ListItem> getListItemById(String itemId);

    /**
     * Get all entities of type ListItem
     * @return
     */
    @Query("SELECT * FROM ListItem")
    LiveData<List<ListItem>> getListItems();


    /**
     * Insert a new ListItem
     * @param listItem
     */
    @Insert(onConflict = REPLACE)
    Long insertListItem(ListItem listItem);

    /**
     * Delete a given ListItem
     * @param listItem
     */
    @Delete
    void deleteListItem(ListItem listItem);

}
