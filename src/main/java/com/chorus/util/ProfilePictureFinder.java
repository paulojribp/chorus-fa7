package com.chorus.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.caelum.vraptor.ioc.Component;

import de.bripkens.gravatar.DefaultImage;
import de.bripkens.gravatar.Gravatar;
import de.bripkens.gravatar.Rating;

@Component
public class ProfilePictureFinder {

	public String getPictureFromEmail(String email) throws MalformedURLException, IOException {
		return this.validateUrl(
				new Gravatar()
					.setSize(85)
					.setHttps(true)
					.setRating(Rating.PARENTAL_GUIDANCE_SUGGESTED)
					.setStandardDefaultImage(DefaultImage.HTTP_404)
					.getUrl(email));
	}

	private String validateUrl(String gravataUrl) throws MalformedURLException, IOException {
		return isNotFound(new URL(gravataUrl)) ? this.getDefaultAvatar()
				: gravataUrl;
	}

	private boolean isNotFound(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		return connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND;
	}

	private String getDefaultAvatar() {
		return "/images/defauluser.jpg";
	}
	
}
