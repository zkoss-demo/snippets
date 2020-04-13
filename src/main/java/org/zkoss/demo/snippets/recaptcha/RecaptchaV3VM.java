/* RecaptchaV3VM.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar 30 15:13:29 CST 2020, Created by rudyhuang

Copyright (C) 2020 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.demo.snippets.recaptcha;

import java.util.Objects;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ToServerCommand;
import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.util.Notification;

/**
 * @author rudyhuang
 */
@ToServerCommand({"submit"})
public class RecaptchaV3VM {
	private static final String SECRET = "6LcVaeUUAAAAAP2zypDMVE-7wuGSogproLKPWNzw";
	private String name;
	private String email;
	private String message;

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

	@Command
	public void submit(@BindingParam("token") String token) {
		boolean isSuccess;
		try {
			JSONObject result = RecaptchaVerifier.verifyResponse(SECRET, token);
			isSuccess = (Boolean) result.get("success");
			if (isSuccess)
				isSuccess = "comments".equals(Objects.toString(result.get("action"))) // verify action
						&& (Double) result.get("score") >= 0.5; // score threshold
		} catch (Exception e) {
			isSuccess = false;
		}

		if (isSuccess)
			Notification.show("reCAPTCHA is successful");
		else
			Notification.show("reCAPTCHA is failed", Notification.TYPE_ERROR, null, null, -1);
	}
}
