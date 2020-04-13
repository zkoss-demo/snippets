/* RecaptchaV2VM.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar 30 15:13:29 CST 2020, Created by rudyhuang

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.demo.snippets.recaptcha;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.ToServerCommand;
import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.util.Notification;

/**
 * @author rudyhuang
 */
@ToServerCommand({"onChallenge"})
public class RecaptchaV2VM {
	private static final String SECRET = "6Lcj5AYTAAAAANcaQYWvFkHVSkqR6FsBaCXXw54r";
	private String name;
	private String email;
	private String message;
	private String response;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Command
	@NotifyChange("challenge")
	public void onChallenge(@BindingParam("response") String response) {
		this.response = response;
	}

	@Command
	public void submit() {
		boolean isSuccess;
		try {
			JSONObject result = RecaptchaVerifier.verifyResponse(SECRET, response);
			isSuccess = (Boolean) result.get("success");
		} catch (Exception e) {
			isSuccess = false;
		}

		if (isSuccess)
			Notification.show("reCAPTCHA is successful");
		else
			Notification.show("reCAPTCHA is failed", Notification.TYPE_ERROR, null, null, -1);
	}
}
