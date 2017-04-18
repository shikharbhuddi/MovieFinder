package com.codebrat.moviefinder.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.codebrat.moviefinder.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.concurrent.TimeoutException;

/**
 * Created by Shikhar on 17-04-2017.
 */

public class WebUtils {

	public static void setImage(Context activity, String url, final ImageView imageView) {
		if (!URLUtil.isValidUrl(url)) {
			imageView.setImageResource(R.drawable.ic_movie_placeholder);
			return;
		}

		imageView.setImageResource(R.drawable.ic_hourglass);

		Ion.with(activity)
			.load(url)
			.setLogging("MyLogs", Log.DEBUG)
			.withBitmap()
			.asBitmap()
			.setCallback(new FutureCallback<Bitmap>() {
				@Override
				public void onCompleted(Exception e, Bitmap result) {
					if (e == null && result != null) {
						imageView.setImageBitmap(result);
					} else {
						imageView.setImageResource(R.drawable.ic_movie_placeholder);
					}
				}
			});
	}

}
