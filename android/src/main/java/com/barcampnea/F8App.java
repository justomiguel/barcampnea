/**
 * Copyright 2014 Facebook, Inc.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.barcampnea;

import android.app.Application;

import com.barcampnea.model.GeneralInfo;
import com.barcampnea.model.Message;
import com.barcampnea.model.Room;
import com.barcampnea.model.Slot;
import com.barcampnea.model.Speaker;
import com.barcampnea.model.Talk;
import com.barcampnea.view.AlertsActivity;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

public class F8App extends Application {

	public void onCreate() {
		// Register ParseObject subclasses
		ParseObject.registerSubclass(Talk.class);
		ParseObject.registerSubclass(Slot.class);
		ParseObject.registerSubclass(Speaker.class);
		ParseObject.registerSubclass(Message.class);
		ParseObject.registerSubclass(Room.class);
		ParseObject.registerSubclass(GeneralInfo.class);
		// Initialize Parse
		Parse.initialize(this, getString(R.string.parse_application_id),
				getString(R.string.parse_client_key));
		// Initialize Facebook
		String appId = getString(R.string.facebook_app_id);

		ParseFacebookUtils.initialize(this);

		// Set up Push
		PushService.setDefaultPushCallback(this, AlertsActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}

}
