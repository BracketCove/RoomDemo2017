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

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * entities:
 * The Models which we will use to convert to/from data stored in an SQL Table.
 * <p>
 * version:
 * In order to preserve Data when an SQL Table changes it's structure, we increment the version
 * number. Room also requires that you create a "Migration" class which allow the Database to be
 * restructured as desired, based on the Database structure of the initial version, to the structure
 * after the version is updated.
 * Created by R_KAY on 7/31/2017.
 */

@Database(entities = {ListItem.class}, version = 1)
public abstract class ListItemDatabase extends RoomDatabase {

    public abstract ListItemDao listItemDao();

}
