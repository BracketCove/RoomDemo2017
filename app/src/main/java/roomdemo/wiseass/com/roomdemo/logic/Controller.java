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

package roomdemo.wiseass.com.roomdemo.logic;

import android.view.View;

import roomdemo.wiseass.com.roomdemo.data.DataSourceInterface;
import roomdemo.wiseass.com.roomdemo.data.ListItem;
import roomdemo.wiseass.com.roomdemo.view.ViewInterface;


/**
 * 16.
 * It's an unfortunate fact that in Programming, people like to use many names for the same thing,
 * and one name for many things at the same time. I would normally call a class like this a
 * "Presenter", since I currently use Model-View-Presenter (Passive View) style Architecture in my
 * own Apps. However, since this isn't an MVP App but a RecyclerView tutorial which is likely to be
 * watched by a fair number of Beginners, let me explain this a little further:
 * <p>
 * Whether it's MVP, MVC, MVVM, etc., a rough (and INCOMPLETE!!!) idea of how these three layer
 * Architectures seperate their concerns (what they do) is:
 * <p>
 * 1. Drawing things on the Screen and Listening for Click Events Layer. In Android, this will
 * almost always be a Fragment or Activity (although there are other options to create Views with
 * 3rd Party Libs). Most Architectures will call this the "View". In most cases, it doesn't
 * make it's own decisions, but simply passes "Events" to Layer 2, and let's Layer 2 do the
 * thinking.
 * <p>
 * 2. Decision Making and Communication Layer. This Layer will generally have the responsibility of
 * coordinating "Events" from other Layers, such as a "Click" on the UI, or an Error Message from
 * the Database. It receives these events, and then using Logic, decides how the App will respond to
 * such events. It turns out that if you write your App well, there will be a relatively finite
 * number of "Events" and "Decisions" we can choose based on those events. Controller and Presenter
 * are common names for Classes in this Layer, but at this point there seems very little consistency
 * for naming this Layer.
 *
 * <p>
 * 3. Data Layer. This Layer manages access to a Database/Repository of some kind. It could be Local
 * (stored on the device) or it could be remote (cloud storage). Generally it will just manage
 * whatever Database it is using, and provide Layer 2 with Methods it can use to Create, Read,
 * Update, Delete (CRUD) Data, and error Messages from the Database.
 * <p>
 * Created by R_KAY on 6/3/2017.
 */

public class Controller {

    private ListItem temporaryListItem;
    private int temporaryListItemPosition;

    /*
    All that's going on with these Variables, is that we're talking to both ListActivity and
    FakeDataSource through Interfaces. This has many benefits, but I'd invite you to research
    "Code to an Interface" for a fairly clear example.
     */
    private ViewInterface view;


    private DataSourceInterface dataSource;

    /**
     * As soon as this object is created, it does a few things:
     * 1. Assigns Interfaces Variables so that it can talk to the DataSource and that Activity
     * 2. Tells the dataSource to fetch a List of ListItems.
     * 3. Tells the View to draw the fetched List of Data.
     * @param view
     * @param dataSource
     */
    public Controller(ViewInterface view, DataSourceInterface dataSource) {
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

    /**
     * In a real App, I would normally talk to this DataSource using RxJava 2. This is because most
     * calls to Services like a Database/Server should be executed on a seperate thread that the
     * mainThread (UI Thread). See my full projects for examples of this.
     */
    public void getListFromDataSource(){
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }


    public void createNewListItem() {
        /*
        To simulate telling the DataSource to create a new record and waiting for it's response,
        we'll simply have it return a new ListItem.

        In a real App, I'd use RxJava 2 (or some other
        API/Framework for Asynchronous Communication) to have the Datasource do this on the
         IO thread, and respond via an Asynchronous callback to the Main thread.
         */

        ListItem newItem = dataSource.createNewListItem();

        view.addNewListItemToView(newItem);
    }

    public void onListItemSwiped(int position, ListItem listItem) {
        //ensure that the view and data layers have consistent state
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
