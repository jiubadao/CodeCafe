/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.codecafe.app.ui;


import android.view.View;
import android.widget.ProgressBar;

import top.codecafe.app.R;
import top.codecafe.kjframe.KJActivity;
import top.codecafe.kjframe.KJBitmap;
import top.codecafe.kjframe.bitmap.BitmapCallBack;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 图片预览
 *
 * @author kymjs (http://www.kymjs.com/)
 */
public class ImageActivity extends KJActivity {

    public static String URL_KEY = "ImageActivity_url";

    private ProgressBar mProgressBar;
    private PhotoView mImg;

    private String url;
    private KJBitmap kjb;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_image);
    }

    @Override
    public void initData() {
        super.initData();
        url = getIntent().getStringExtra(URL_KEY);
        kjb = new KJBitmap();

    }

    @Override
    public void initWidget() {
        super.initWidget();
        mImg = bindView(R.id.images);
        mProgressBar = bindView(R.id.progress);

        kjb.display(mImg, url, new BitmapCallBack() {
            @Override
            public void onPreLoad() {
                super.onPreLoad();
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mProgressBar.setVisibility(View.GONE);
            }
        });
        mImg.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                ImageActivity.this.finish();
            }
        });
    }
}
