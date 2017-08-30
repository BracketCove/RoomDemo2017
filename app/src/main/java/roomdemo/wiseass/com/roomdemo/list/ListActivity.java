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

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import roomdemo.wiseass.com.roomdemo.R;
import roomdemo.wiseass.com.roomdemo.util.BaseActivity;


/**
 *
 * Please Read: It's never my intention to mislead anyone. If something in this Code is sub-optimal
 * or downright wrong, please consider creating an issue on GitHub. I greatly appreciate well informed
 * critiques, and I'm happy to give shout outs for good explanations. Putting my Code OS is partly
 * an attempt to get peer review.
 *
 * One doesn't sharpen a knife by rubbing the blade against butter.
 *
 */
public class ListActivity extends BaseActivity {

    private static final String LIST_FRAG = "LIST_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager manager = getSupportFragmentManager();

        ListFragment fragment = (ListFragment) manager.findFragmentByTag(LIST_FRAG);

        if (fragment == null) {
            fragment = ListFragment.newInstance();
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_list,
                LIST_FRAG
        );

    }
}
