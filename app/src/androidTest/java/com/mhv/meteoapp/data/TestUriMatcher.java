package com.mhv.meteoapp.data;

import android.content.UriMatcher;
import android.net.Uri;
import android.test.AndroidTestCase;

/*
    Note that this class utilizes
    constants that are declared with package protection inside of the UriMatcher, which is why
    the test must be in the same data package as the Android app code.  Doing the test this way is
    a nice compromise between data hiding and testability.
 */
public class TestUriMatcher extends AndroidTestCase {
    private static final String LOCATION_QUERY = "London, UK";
    private static final long TEST_DATE = 1459641600L;  // April 3rd, 2016
    private static final long TEST_LOCATION_ID = 10L;

    // content://com.mhv.meteoapp/weather"
    private static final Uri TEST_WEATHER_DIR = DatabaseContract.WeatherEntry.CONTENT_URI;
    private static final Uri TEST_WEATHER_WITH_LOCATION_DIR = DatabaseContract.
            WeatherEntry.buildWeatherLocation(LOCATION_QUERY);
    private static final Uri TEST_WEATHER_WITH_LOCATION_AND_DATE_DIR = DatabaseContract.
            WeatherEntry.buildWeatherLocationWithDate(LOCATION_QUERY, TEST_DATE);
    // content://com.mhv.meteoapp/location"
    private static final Uri TEST_LOCATION_DIR = DatabaseContract.LocationEntry.CONTENT_URI;

    /*
        This function tests that your UriMatcher returns the correct integer value
        for each of the Uri types that our ContentProvider can handle.
     */
    public void testUriMatcher() {
        UriMatcher testMatcher = CustomContentProvider.buildUriMatcher();

        assertEquals("Error: The WEATHER URI was matched incorrectly.",
                testMatcher.match(TEST_WEATHER_DIR), CustomContentProvider.WEATHER);
        assertEquals("Error: The WEATHER WITH LOCATION URI was matched incorrectly.",
                testMatcher.match(TEST_WEATHER_WITH_LOCATION_DIR), CustomContentProvider.WEATHER_WITH_LOCATION);
        assertEquals("Error: The WEATHER WITH LOCATION AND DATE URI was matched incorrectly.",
                testMatcher.match(TEST_WEATHER_WITH_LOCATION_AND_DATE_DIR), CustomContentProvider.WEATHER_WITH_LOCATION_AND_DATE);
        assertEquals("Error: The LOCATION URI was matched incorrectly.",
                testMatcher.match(TEST_LOCATION_DIR), CustomContentProvider.LOCATION);
    }
}
