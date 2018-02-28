
 # LikeView

LikeView(Just like Twitter) 

![](images/screenshot.gif)

How to use
----------
**To include naturalloadingview to your project:**

add the dependency to the the  build.gradle file

```gradle
    compile 'com.jaren:likeview:1.0.0'
```
**Add naturalloadingview in your xml layout:**

  ```xml
     <com.jaren.lib.view.LikeView
        android:id="@+id/lv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:cirRadius="10dp"
        app:cycleTime="2000"
        app:defaultColor="#ff657487" />
```
**Add OnClickListener :**

  ```java

        lv = (LikeView) findViewById(R.id.lv);
        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LikeView", "onClick"+lv.isChecked());
            }
        });
```
**Change the checked state :**

  ```java

        //Change the checked state of the view
        lv.setChecked(true);

        //Change the checked state of the view to the inverse of its current state
        lv.toggle();
```
LikeView has the following xml attributes
----------
You can customize the look and behavior of the `LikeView` in xml. Use the following attributes in xml.

- `defaultColor` -unchecked color
- `checkedColor` -checked color
- `cirRadius` -the only attributes that can determine the LikeView size
- `cycleTime` -the animation-duration(ms)

Example
----------
[Examples](https://github.com/qkxyjren/LikeView/tree/master/app)

Change log
---------
License
----------

    Copyright 2017 jaren

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.