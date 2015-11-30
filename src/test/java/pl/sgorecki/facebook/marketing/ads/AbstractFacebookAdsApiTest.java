package pl.sgorecki.facebook.marketing.ads;

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.client.MockRestServiceServer;
import pl.sgorecki.facebook.marketing.ads.impl.FacebookAdsTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Sebastian G�recki
 */
public class AbstractFacebookAdsApiTest {
	protected static final String ACCESS_TOKEN = "someAccessToken";
	protected static final double EPSILON = 0.000000000001;
	private static final DateFormat FB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
	protected FacebookAdsTemplate facebookAds;
	protected FacebookAdsTemplate unauthorizedFacebookAds;
	protected MockRestServiceServer mockServer;
	protected MockRestServiceServer unauthorizedMockServer;

	@Before
	public void setUp() throws Exception {
		facebookAds = new FacebookAdsTemplate(ACCESS_TOKEN);
		mockServer = MockRestServiceServer.createServer(facebookAds.getRestTemplate());

		unauthorizedFacebookAds = new FacebookAdsTemplate();
		unauthorizedMockServer = MockRestServiceServer.createServer(unauthorizedFacebookAds.getRestTemplate());
	}

	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json", getClass());
	}

	protected Date toDate(String dateString) {
		try {
			return FB_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

}
