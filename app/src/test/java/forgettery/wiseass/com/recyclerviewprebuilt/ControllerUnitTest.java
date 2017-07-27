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

package forgettery.wiseass.com.recyclerviewprebuilt;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import roomdemo.wiseass.com.roomdemo.R;
import roomdemo.wiseass.com.roomdemo.data.DataSourceInterface;
import roomdemo.wiseass.com.roomdemo.data.ListItem;
import roomdemo.wiseass.com.roomdemo.logic.Controller;
import roomdemo.wiseass.com.roomdemo.view.ViewInterface;


/**
 * Test Driven Development Bonus: Before writing the methods themselves (a.k.a. writing the
 * "Implementation"), I've written some Unit Tests. In a nutshell, these Tests help me to figure out
 * what methods and logic I'll need for the class which I'm testing. This process is slow at first,
 * but once you become fast at writing Tests, you'll start to see how they actually help you write
 * Better Code, often at a Faster Pace.
 * <p>
 * I have some Videos on this topic on my youtube channel. Check them out for more info.
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerUnitTest {

    //We technically could've just used the FakeDataSource here, but you don't always want to use
    //Mocks over Fakes and vice versa. Depends on your use case.
    @Mock
    DataSourceInterface dataSource;

    @Mock
    ViewInterface view;

    @Mock
    View testViewRoot;

    private static final ListItem TEST_ITEM = new ListItem(
            "6:30AM 06/01/2017",
            "Look at Open Source Projects",
            R.drawable.red_drawable);


    private static final int POSITION = 3;

    Controller controller;

    @Before
    public void setUpTest() {
        controller = new Controller(view, dataSource);
    }

    @Test
    public void onGetListDataSuccessful() {
        //Since we require a List<ListItem> to be returned by the dataSource, we can define it here:
        List<ListItem> listOfData = new ArrayList<>();
        listOfData.add(TEST_ITEM);


        //This is where we tell our "Mocks" what to do when our Controller talks to them. Since they
        //aren't real objects, we must tell them exactly what to do if we want responses from them.
        Mockito.when(dataSource.getListOfData())
                .thenReturn(listOfData);

        //This is the method we are testing
        controller.getListFromDataSource();

        //Check how the Tested Class responds to the data it receives
        //or test it's behaviour
        Mockito.verify(view).setUpAdapterAndView(listOfData);
    }



    @Test
    public void onListItemClicked() {
        controller.onListItemClick(TEST_ITEM, testViewRoot);

        Mockito.verify(view).startDetailActivity(
                TEST_ITEM.getDateAndTime(),
                TEST_ITEM.getMessage(),
                TEST_ITEM.getColorResource(),
                testViewRoot);
    }

    @Test
    public void onCreateNewListItemClick() {
        //1 Set up your Mock dataSource
        Mockito.when(dataSource.createNewListItem())
                .thenReturn(TEST_ITEM);

        //2 Call the method you wish to test on the Controller
        controller.createNewListItem();

        //3 Verify the behaviour of the View, based on the event
        Mockito.verify(view).addNewListItemToView(
                TEST_ITEM
        );
    }

    @Test
    public void onListItemSwiped() {

        controller.onListItemSwiped(POSITION, TEST_ITEM);

        //ensure consistency between View and Data Layers
        Mockito.verify(dataSource).deleteListItem(TEST_ITEM);
        Mockito.verify(view).deleteListItemAt(POSITION);

        //give user the option to undo action
        Mockito.verify(view).showUndoSnackbar();

    }

    @Test
    public void onUndoDeleteOperation() {
        Mockito.when(dataSource.createNewListItem())
                .thenReturn(TEST_ITEM);

        //this test requires temporary position and item to be set. We can achieve this by calling
        //controller.onListItemSwiped() first.
        controller.onListItemSwiped(POSITION, TEST_ITEM);

        controller.onUndoConfirmed();

        Mockito.verify(dataSource).insertListItem(TEST_ITEM);

        Mockito.verify(view).insertListItemAt(
                POSITION,
                TEST_ITEM
        );
    }

    @Test
    public void onSnackbarTimeout() {
        controller.onListItemSwiped(POSITION, TEST_ITEM);

        controller.onSnackbarTimeout();
    }


}