import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import app.helpers.HTMLNavigator;
import app.types.VideoGame;

public class VideoGameScraper {

	private static String content;

	// XPATH FOR GETTING TITLE NAMES
	private static String TITLE_EXPR = "//div[@class='%s']/a[1]";

	// XPATH FOR GETTING IMAGE URLS
	private static String IMG_EXPR = "//div[@class='%s']/a[1]/img";

	// BASE OF BLOCKBUSTER URL
	public static final String BASE_URL = "http://www.blockbuster.com/games/platforms/gamePlatform";

	/**
	 * QUERY FOR GAMES OF CERTAIN PLATFORM
	 * 
	 * @param type
	 *            the platform type
	 * @return
	 * @throws IOException
	 * @throws XPatherException
	 */
	public static List<VideoGame> getVideoGamesByConsole(String type) throws IOException, XPatherException {
		// CONSTRUCT FULL URL
		String query = BASE_URL + type;

		// USE HTMLCLEANER TO STRUCTURE HTML
		TagNode node = getAndCleanHTML(query);

		// ADD GAMES
		List<VideoGame> games = new ArrayList<VideoGame>();
		games.addAll(grabGamesWithTag(node, "addToQueueEligible game  sizeb gb6 bvr-gamelistitem    ", type));
		return games;
	}

	/**
	 * GIVEN THE STRUCTURED HTML, PARSE OUT NODES OF THE PASSED IN TAG
	 * 
	 * @param head
	 *            the head of the structured html
	 * @param tag
	 *            the tag we are looking for
	 * @param type
	 *            the platform type
	 * @return
	 * @throws XPatherException
	 */
	private static List<VideoGame> grabGamesWithTag(TagNode head, String tag, String type) throws XPatherException {
		// RUN VIDEO GAME TITLE AND IMAGE XPATHS
		Object[] gameTitleNodes = head.evaluateXPath(String.format(TITLE_EXPR, tag));
		Object[] imgUrlNodes = head.evaluateXPath(String.format(IMG_EXPR, tag));

		// ITERATE THROUGH VIDEO GAMES
		List<VideoGame> games = new ArrayList<VideoGame>();
		for (int i = 0; i < gameTitleNodes.length; i++) {
			TagNode gameTitleNode = (TagNode) gameTitleNodes[i];
			TagNode imgUrlNode = (TagNode) imgUrlNodes[i];
			// BY LOOKING AT THE HTML, WE CAN DETERMINE
			// WHICH ATTRIBUTES OF THE NODE TO PULL
			String title = gameTitleNode.getAttributeByName("title");
			String imgUrl = imgUrlNode.getAttributeByName("src");

			// BUILD OUR VIDEO GAME OBJECT AND ADD TO LIST
			VideoGame v = new VideoGame(title, imgUrl, type);
			games.add(v);
		}
		return games;
	}

	/**
	 * CLEAN AND STRUCTURE THE PASSED IN HTML
	 * 
	 * @param result
	 *            the underlying html
	 * @return
	 * @throws IOException
	 */
	private static TagNode getAndCleanHTML(String result) throws IOException {
		String content = HTMLNavigator.navigateAndGetContents(result).toString();
		VideoGameScraper.content = content;
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		props.setOmitDoctypeDeclaration(true);
		return cleaner.clean(content);
	}

	public static String getContent() {
		return content;
	}

}
