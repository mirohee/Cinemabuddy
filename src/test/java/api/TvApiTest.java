package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class TvApiTest {

    @Mock
    private HttpURLConnection connection;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    // Search Show Tests
    @Test
    void testSearchShow_Success() throws IOException {
        String expectedStart = "[{"; // Capture initial characters of the array

        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(connection.getContentType()).thenReturn("application/json"); // Optional, mimic content type
        when(connection.getInputStream()).thenReturn(mock(InputStream.class)); // Not used in this approach

        String actualJson = TvApi.searchShow("Better call saul");

        assertTrue(actualJson.startsWith(expectedStart)); // Use startsWith for initial characters
    }

    // Search Single Show Tests
    @Test
    void testSearchSingleShow_Success() throws IOException {
        String expectedStart = "{\"id\":618"; // Capture initial characters

        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(connection.getContentType()).thenReturn("application/json"); // Optional, mimic content type
        when(connection.getInputStream()).thenReturn(mock(InputStream.class)); // Not used in this approach

        String actualJson = TvApi.searchSingleShow("Better Call Saul");

        assertTrue(actualJson.startsWith(expectedStart)); // Use startsWith for initial characters
    }

    // Search Show by ID Tests
    @Test
    void testSearchShowByID_Success() throws IOException {
        String expectedStart = "{\"id\":618"; // Capture initial characters

        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(connection.getContentType()).thenReturn("application/json");
        when(connection.getInputStream()).thenReturn(mock(InputStream.class)); // Not used in this approach

        String actualJson = TvApi.searchShowByID("618");  // Replace with a valid show ID

        assertTrue(actualJson.startsWith(expectedStart)); // Use startsWith for initial characters
    }

    @Test
    void testSearchShowByID_NotFound() throws IOException {
        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);

        assertThrows(IOException.class, () -> TvApi.searchShowByID("InvalidShowID"));
    }
    @Test
    void testPeopleSearch_Success() throws IOException {
        String expectedStart = "[{";

        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(connection.getContentType()).thenReturn("application/json");
        InputStream mockStream = mock(InputStream.class);
        when(mockStream.read(any(byte[].class))).thenReturn(expectedStart.getBytes().length);
        when(connection.getInputStream()).thenReturn(mockStream);
        String actualJson = TvApi.peopleSearch("Will Smith");

        assertTrue(actualJson.startsWith(expectedStart));
    }


    @Test
    void testShowSearch_Success() throws IOException {
        String expectedStart = "[{";

        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(connection.getContentType()).thenReturn("application/json");
        InputStream mockStream = mock(InputStream.class);
        when(mockStream.read(any(byte[].class))).thenReturn(expectedStart.getBytes().length);
        when(connection.getInputStream()).thenReturn(mockStream);
        String actualJson = TvApi.searchShow("breaking bad");

        assertTrue(actualJson.startsWith(expectedStart));
    }


    @Test
    void testMakeApiCall_NetworkError() throws IOException {
        when(connection.getResponseCode()).thenThrow(new IOException("Mocked network error"));

        assertThrows(IOException.class, () -> TvApi.makeApiCall("https://api.tvmaze.com/books"));
    }

    @Test
    void testGetShowEpisodeList_Success() throws IOException {
        String showId = "618";
        boolean includeSpecials = true;

        String expectedStart = "[{";

        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(connection.getContentType()).thenReturn("application/json");
        InputStream mockStream = mock(InputStream.class);
        when(connection.getInputStream()).thenReturn(mockStream);
        when(mockStream.read(any(byte[].class))).thenReturn(expectedStart.getBytes().length);

        String actualJson = TvApi.getShowEpisodeList(showId, includeSpecials);

        assertTrue(actualJson.startsWith(expectedStart));
    }



}