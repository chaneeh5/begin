package jwei.league.main.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.db.DareJDOWrapper;
import app.db.VideoGameJDOWrapper;
import app.scrapers.VideoGameScraper;
import app.types.Constants;
import app.types.DareVideoGame;
import app.types.VideoGame;
import app.types.VideoGame.VideoGameConsole;

// WE EXTEND THE HTTPSERVLET CLASS TO MAKE THIS METHOD AVAILABLE
// TO EXTERNAL WEB REQUESTS, NAMELY CLIENTS AND CRON JOBS
public class VideoGameScrapeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ArrayList<VideoGame> games;

	/**
	 * METHOD THAT IS HIT WHEN HTTP GET REQUEST IS MADE
	 * 
	 * @param request
	 *            a servlet request object (any params passed can be retrieved
	 *            with this)
	 * @param response
	 *            a servlet response that you can embed data back to user
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		games = new ArrayList<VideoGame>();

		try {
			// GRAAB GAMES FROM ALL PLATFORMS
			games.addAll(VideoGameScraper.getVideoGamesByConsole(VideoGameConsole.DS));
			games.addAll(VideoGameScraper.getVideoGamesByConsole(VideoGameConsole.PS2));
			games.addAll(VideoGameScraper.getVideoGamesByConsole(VideoGameConsole.PS3));
			games.addAll(VideoGameScraper.getVideoGamesByConsole(VideoGameConsole.PSP));
			games.addAll(VideoGameScraper.getVideoGamesByConsole(VideoGameConsole.WII));
			games.addAll(VideoGameScraper.getVideoGamesByConsole(VideoGameConsole.XBOX));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// HERE WE ADD ALL GAMES TO OUR VIDEOGAME JDO WRAPPER!
		VideoGameJDOWrapper.batchInsertGames(games);

		// WRITE A RESPONSE BACK TO ORIGINAL HTTP REQUESTER
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("Success");
	}
}
