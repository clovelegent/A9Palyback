package com.softsz.a9palyback;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsz.a9palyback.BaseActivity;
import com.softsz.a9palyback.R;

import java.util.ArrayList;
import java.util.List;

public class MediaClassActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "MediaClassActivity";

    private GridView mGridView;
    String item_title[] = null;
    private int item_viewId[] = null;
    private Resources resources;

    @Override
    void initViewRes() {
        mGridView = (GridView) findViewById(R.id.mainview);
        item_title = resources.getStringArray(R.array.itemTitle);
        item_viewId = new int[]{R.drawable.ic_palyback_image,R.drawable.ic_palyback_video,R.drawable.ic_playback_soundrecord,R.drawable.ic_password};
    }

    @Override
    void setMyContentView() {
        setContentView(R.layout.media_class_activity);
        resources = getResources();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter(this);
        mGridView.setAdapter(myGridViewAdapter);
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < 3){
            Intent intent = new Intent(this, MediaDateActivity.class);
            intent.putExtra("playback_model",position+1);
            startActivity(intent);
        }else{
            startActivity(new Intent(this,SettingsActivity.class));
        }
    }

    class MyGridViewAdapter extends BaseAdapter{
        private Context mContext;
        private List<Picture> pictures=new ArrayList<Picture>();

        MyGridViewAdapter(Context context){
            mContext = context;
            for (int i = 0; i < item_title.length; i++) {
                Picture picture = new Picture(item_title[i], item_viewId[i]);
                pictures.add(picture);
            }
        }

        @Override
        public int getCount() {
            return pictures.size();
        }

        @Override
        public Object getItem(int position) {
            return pictures.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                // 获得容器
                convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, null);

                // 初始化组件
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                // 给converHolder附加一个对象
                convertView.setTag(viewHolder);
            } else {
                // 取得converHolder附加的对象
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // 给组件设置资源
            Picture picture = pictures.get(position);
            viewHolder.image.setImageResource(picture.getImageId());
            viewHolder.title.setText(picture.getTitle());

            return convertView;
        }

        class ViewHolder {
            public TextView title;
            public ImageView image;
        }
    }

    class Picture {

        private String title;
        private int imageId;

        public Picture(String title, Integer imageId) {
            this.imageId = imageId;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public int getImageId() {
            return imageId;
        }

    }
}
