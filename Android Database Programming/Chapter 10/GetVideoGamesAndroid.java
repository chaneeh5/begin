package jwei.league.main.web;

import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;

import com.juicy.app.objects.Constants;
import com.juicy.app.objects.VideoGame;
import com.juicy.app.service.ObjectParsers;

public class GetVideoGamesAndroid {

    private static String URL_BASE = "http://entertainmentapp.appspot.com";

    private static String REQUEST_BASE = "/getVideoGames?type=";

    // THIS RETRIEVES THE HTTP CLIENT CONFIGURED ABOVE
    private static DefaultHttpClient httpClient = ConnectionManager.getClient();

    // HERE YOU PASS IN THE PLATFORM YOU WANT I.E. XBOX, PS3, ETC
    public static List<VideoGame> getGamesByPlatform(String platform) {
        // construct the URL
        String url = URL_BASE + REQUEST_BASE + platform;

        // get the XML response back as a String
        String response = GetMethods.doGetWithResponse(url, httpClient);

        // create a simple String parser
        // here we want to convert XML to a list of VideoGame objects
        List<VideoGame> games = ObjectParsers.parseGameResponse(response);
        return games;
    }

}

