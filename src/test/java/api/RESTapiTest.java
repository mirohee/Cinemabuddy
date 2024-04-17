package api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RESTapiTest {

    @Mock // Use @Mock for field injection
    private HttpClient httpClientMock;

    private RESTapi restApi;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this); // Initialize mocks
        restApi = new RESTapi(); // Create a new RESTapi instance

        // Inject the mock using reflection
        Field field = RESTapi.class.getDeclaredField("httpClient");
        field.setAccessible(true);
        field.set(restApi, httpClientMock);
    }

    @Test
    public void testGetRecommendations_success() throws Exception {
        // Mock successful response for recommendations
        String expectedResponse = "Recommendations";
        HttpResponse<String> responseMock = mock(HttpResponse.class);
        when(responseMock.body()).thenReturn(expectedResponse);

        // Assuming methods use the injected HttpClient, mock the response with specific types
        when(httpClientMock.send(ArgumentMatchers.<HttpRequest>any(), ArgumentMatchers.<BodyHandler<String>>any()))
                .thenReturn(responseMock);

        String recommendations = restApi.getRecommendations(169, 3); // Call without arguments

        // Assert the returned value
        assertEquals(expectedResponse, recommendations);
    }

    @Test
    public void testSearchAndRecommendShows_success() throws Exception {
        // Mock successful response for search and recommend
        String expectedResponse = "Search Result";
        HttpResponse<String> responseMock = mock(HttpResponse.class);
        when(responseMock.body()).thenReturn(expectedResponse);

        // Assuming methods use the injected HttpClient, mock the response with specific types
        when(httpClientMock.send(ArgumentMatchers.<HttpRequest>any(), ArgumentMatchers.<BodyHandler<String>>any()))
                .thenReturn(responseMock);

        String searchResult = restApi.searchAndRecommendShows("Breaking bad", 3); // Call without arguments

        // Assert the returned value
        assertEquals(expectedResponse, searchResult);
    }
}
