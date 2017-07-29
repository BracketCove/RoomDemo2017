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

package roomdemo.wiseass.com.roomdemo.list;

import android.view.View;

import roomdemo.wiseass.com.roomdemo.data.DataSourceInterface;
import roomdemo.wiseass.com.roomdemo.data.ListItem;


/**
 * Describes interactions between View and Presenter within List Feature.
 */
public class ListPresenter implements ListContract.Presenter {

    private ListItem temporaryListItem;
    private int temporaryListItemPosition;

    private ListContract view;


    private DataSourceInterface dataSource;

    public ListPresenter(ListContract view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        getListFromDataSource();
    }

    public void onListItemClick(ListItem selectedItem, View viewRoot){
        view.startDetailActivity(
                selectedItem.getDateAndTime(),
                selectedItem.getMessage(),
                selectedItem.getColorResource(),
                viewRoot
                );
    }

    @Override
    public void onListItemClick(ListItem selectedItem, ListContract.View viewRoot) {

    }

    public void getListData(){
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }

    @Override
    public void onCreateItemButtonClick() {

    }

    public void onListItemSwiped(int position, ListItem listItem) {
        dataSource.deleteListItem(listItem);
        view.deleteListItemAt(position);

        temporaryListItemPosition = position;
        temporaryListItem = listItem;

        view.showUndoSnackbar();

    }

    public void onUndoConfirmed() {
        if (temporaryListItem != null){
            //ensure View/Data consistency
            dataSource.insertListItem(temporaryListItem);
            view.insertListItemAt(temporaryListItemPosition, temporaryListItem);

            temporaryListItem = null;
            temporaryListItemPosition = 0;

        } else {

        }

    }

    public void onSnackbarTimeout() {
        temporaryListItem = null;
        temporaryListItemPosition = 0;
    }
}
