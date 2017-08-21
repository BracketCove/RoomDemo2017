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

package roomdemo.wiseass.com.roomdemo.dependencyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import roomdemo.wiseass.com.roomdemo.RoomDemoApplication;
import roomdemo.wiseass.com.roomdemo.data.ListItemDao;
import roomdemo.wiseass.com.roomdemo.data.ListItemDatabase;
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository;
import roomdemo.wiseass.com.roomdemo.viewmodel.CustomViewModelFactory;

/**
 *
 *
 * Created by R_KAY on 8/15/2017.
 */
@Module
public class ApplicationModule {
    private final RoomDemoApplication application;
    public ApplicationModule(RoomDemoApplication application) {
        this.application = application;
    }

    //may need to add a scope here?
    @Provides
    RoomDemoApplication provideRoomDemoApplication(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }
}
