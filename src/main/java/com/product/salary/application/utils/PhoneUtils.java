package com.product.salary.application.utils;

import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsTextualMessage;

import java.util.Collections;
import java.util.ResourceBundle;

public class PhoneUtils {
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
	private final static String BASE_URL = BUNDLE.getString("phone.url");
	private final static String API_KEY = BUNDLE.getString("phone.apiKey");

	/**
	 * Gửi thông báo SMS
	 * 
	 * @param recipient: người nhận định dạng: 84........
	 * @param message:   tin nhắn
	 */
	public static void sendNotification(String recipient, String message) {

		var apiClient = ApiClient.forApiKey(ApiKey.from(API_KEY)).withBaseUrl(BaseUrl.from(BASE_URL)).build();
		
		var sendSmsApi = new SmsApi(apiClient);

		// Create a message to send.
		var smsMessage = new SmsTextualMessage().addDestinationsItem(new SmsDestination().to(recipient)).text(message);

		// Create a send message request.
		var smsMessageRequest = new SmsAdvancedTextualRequest().messages(Collections.singletonList(smsMessage));

		try {
			// Send the message.
			var smsResponse = sendSmsApi.sendSmsMessage(smsMessageRequest).execute();
			System.out.println("Response body: " + smsResponse);

			// Get delivery reports. It may take a few seconds to show the above-sent
			// message.
			var reportsResponse = sendSmsApi.getOutboundSmsMessageDeliveryReports().execute();
			System.out.println(reportsResponse.getResults());
		} catch (ApiException e) {
			System.out.println("HTTP status code: " + e.responseStatusCode());
			System.out.println("Response body: " + e.rawResponseBody());
		}
	}

	public static void main(String[] args) {
		PhoneUtils.sendNotification("+8456667886", "CTK Home thông báo: Lương tháng 10 năm 2023 của nhân viên Hà Thế Kiệt - 1020230182 là 8,750,000 VNĐ");
	}
}
