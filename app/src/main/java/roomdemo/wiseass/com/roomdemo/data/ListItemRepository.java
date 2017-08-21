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

import java.util.List;

import javax.inject.Inject;

public class ListItemRepository {

    private final ListItemDao listItemDao;

    @Inject
    public ListItemRepository(ListItemDao listItemDao){
        this.listItemDao = listItemDao;
    }


    public LiveData<List<ListItem>> getListOfData(){
        return listItemDao.getListItems();
    }

    public LiveData<ListItem> getListItem(String itemId){
        return listItemDao.getListItemById(itemId);
    }

    public Long createNewListItem(ListItem listItem){
        return listItemDao.insertListItem(listItem);
    }

    public void deleteListItem(ListItem listItem){
        listItemDao.deleteListItem(listItem);
    }



}
