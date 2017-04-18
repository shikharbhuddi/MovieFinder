package com.codebrat.moviefinder;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Shikhar on 17-04-2017.
 */

public class MyApplication extends Application {
	@Override
	public void onCreate(){
		super.onCreate();
		Fabric.with(this, new Crashlytics.Builder().core(new CrashlyticsCore.Builder()
			.disabled(BuildConfig.DEBUG).build()).build());
	}
}
