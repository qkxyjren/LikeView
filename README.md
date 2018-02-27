
 # LikeView

LikeView(Just like Twitter) 

![](images/screenshot.gif)

How to use
----------
	
**1.Add naturalloadingview in your xml layout:**

  ```xml
     <com.jaren.lib.view.LikeView
        android:id="@+id/lv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:cirRadius="10dp"
        app:cycleTime="2000"
        app:defaultColor="#ff657487" />
```
**2.Add OnClickListener in your Java file:**

  ```java

     lv = (LikeView) findViewById(R.id.lv);
        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LikeView", "onClick"+lv.isChecked());
            }
        });
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
