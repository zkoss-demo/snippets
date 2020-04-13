package org.zkoss.demo.snippets.recaptcha;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.zkoss.json.JSONObject;
import org.zkoss.json.JSONValue;

public class RecaptchaVerifier {
	private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	/**
	 * Refer to https://developers.google.com/recaptcha/docs/verify
	 * You can simplify this method with Apache HttpClient library.
	 * @param secret A secret key
	 * @param captchaResponse A reCAPTCHA response
	 * @return a JSON object
	 * <pre>{@code
	 * {
	 *  "success": true|false,
	 *  "challenge_ts": timestamp,  // timestamp of the challenge load (ISO format yyyy-MM-dd'T'HH:mm:ssZZ)
	 *  "hostname": string,         // the hostname of the site where the reCAPTCHA was solved
	 *  "error-codes": [...]        // optional
	 *  }}</pre>
	 * @throws Exception
	 */
	public static JSONObject verifyResponse(String secret, String captchaResponse) throws Exception {
		URL obj = new URL(VERIFY_URL);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "response=" + captchaResponse + "&secret=" + secret;

		// Send a request
		con.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
			wr.writeBytes(urlParameters);
			wr.flush();
		}

		JSONObject result;
		try (InputStreamReader in = new InputStreamReader(con.getInputStream())) {
			result = (JSONObject) JSONValue.parse(in);
		}
		return result;
	}
}
