package pl.sgorecki.facebook.marketing.ads;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.MultiValueMap;

/**
 * @author Sebastian Górecki
 */
public interface MarketingApi {
	<T> T fetchObject(String objectId, Class<T> type, String... fields);

	<T> T fetchObject(String objectId, Class<T> type, MultiValueMap<String, String> queryParameters);

	<T> PagedList<T> fetchConnections(String objectId, String connectionName, Class<T> type, String... fields);

	<T> PagedList<T> fetchConnections(String objectId, String connectionName, Class<T> type, MultiValueMap<String, String> queryParameters);

	String publish(String objectId, String connectionName, MultiValueMap<String, Object> data);

	void post(String objectId, MultiValueMap<String, Object> data);

	void post(String objectId, String connectionName, MultiValueMap<String, Object> data);

	boolean update(String objectId, MultiValueMap<String, Object> data);

	void delete(String objectId);

	static final String GRAPH_API_URL = "https://graph.facebook.com/v2.4/";
}
