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

package roomdemo.wiseass.com.roomdemo.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import roomdemo.wiseass.com.roomdemo.R;
import roomdemo.wiseass.com.roomdemo.util.BaseActivity;

public class DetailActivity extends BaseActivity {

    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";
    private static final String DETAIL_FRAG = "DETAIL_FRAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent i = getIntent();

        //if extra is null, not worth even bothering to set up the MVVM stuff; Kill it with fire.
        if (i.hasExtra(EXTRA_ITEM_ID)){
            String itemId = i.getStringExtra(EXTRA_ITEM_ID);

        FragmentManager manager = getSupportFragmentManager();

        DetailFragment fragment = (DetailFragment) manager.findFragmentByTag(DETAIL_FRAG);

        if (fragment == null) {
            fragment = DetailFragment.newInstance(itemId);
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_detail,
                DETAIL_FRAG
        );

        } else {
            Toast.makeText(this, R.string.error_no_extra_found, Toast.LENGTH_LONG).show();
        }

    }
}
