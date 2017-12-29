package com.jsnk77.quitsmoking;

import android.app.Application;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by jsnk77 on 14/12/12.
 */
public class ApplicationControler extends Application {

    public static final String TAG =ApplicationControler.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static ApplicationControler mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ApplicationControler getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new BitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}

class BitmapCache implements ImageLoader.ImageCache {

   private LruCache<String, Bitmap> mCache;

   public BitmapCache() {
       int maxSize = 10 * 1024 * 1024;
       mCache = new LruCache<String, Bitmap>(maxSize) {
           @Override
           protected int sizeOf(String key, Bitmap value) {
               return value.getRowBytes() * value.getHeight();
           }
       };
   }

   @Override
   public Bitmap getBitmap(String url) {
       return mCache.get(url);
   }

   @Override
   public void putBitmap(String url, Bitmap bitmap) {
       mCache.put(url, bitmap);
   }

}