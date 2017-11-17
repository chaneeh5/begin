package jwei.league.main.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.db.VideoGameJDOWrapper;
import app.services.GamesToXMLConverter;
import app.types.VideoGame;

public class GetVideoGames extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // HTTP GET REQUEST SINCE WE'RE REQUESTING FOR DATA
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String platform = request.getParameter("type");

        // USE OUR JDO WRAPPER TO QUERY FOR GAMES BY PLATFORM
        List<VideoGame> games = VideoGameJDOWrapper.getGamesByPlatform(platform);

        // WRAP GAMES INTO XML FORMAT
        String ret = GamesToXMLConverter.convertGamesToXML(games);

        // SET THE RESPONSE TYPE TO XML
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        // WRITE DATA TO RESPONSE
        response.getWriter().write(ret);
    }

}

