package com.softsz.a9palyback;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import static com.softsz.a9palyback.MediaFileListFragment.KEY_IMAGE_BROWSE;

public class ImageBrowserFragment extends Fragment {

   /* Context context;
    PhotoView photoView;
    String resourcePath;
    //static List<DataCommon> mList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        resourcePath = getArguments().getString(KEY_IMAGE_BROWSE);
        //mList = MediaFileListFragment.getmRecords();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_browse_fragment,null);
       //ViewPager viewPager = view.findViewById(R.id.view_pager);

        //viewPager.setAdapter(new SamplePagerAdapter());
        photoView = view.findViewById(R.id.photo_view);
        photoView.setImageURI(Uri.parse(resourcePath));
        return view;
    }*/

    /*static class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            String imgPath = (mList.get(position)).getData();
            photoView.setImageURI(Uri.parse(imgPath));

            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }*/
}
