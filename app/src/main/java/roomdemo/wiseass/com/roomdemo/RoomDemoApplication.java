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

package roomdemo.wiseass.com.roomdemo;

import android.app.Application;

import roomdemo.wiseass.com.roomdemo.dependencyinjection.ApplicationComponent;
import roomdemo.wiseass.com.roomdemo.dependencyinjection.ApplicationModule;
import roomdemo.wiseass.com.roomdemo.dependencyinjection.DaggerApplicationComponent;
import roomdemo.wiseass.com.roomdemo.dependencyinjection.RoomModule;

/**
 * DispatchingAndroidInjector helps to inject Member Variables into Activities, Fragments, and so
 * forth.
 * <p>
 * https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/GithubApp.java
 * Created by R_KAY on 8/15/2017.
 */

public class RoomDemoApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
