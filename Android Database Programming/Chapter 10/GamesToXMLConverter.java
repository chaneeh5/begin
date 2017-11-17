package jwei.league.main.web;

public class GamesToXMLConverter {

	public static String convertGamesToXML(List<VideoGame> games) {
		String content = "";
		for (VideoGame g : games) {
			// WRAP EACH GAME IN ITS OWN TAG
			content += convertGameToXml(g);
		}
		String ret = addTag("games", content);
		return ret;
	}

	/**
	 * METHOD FOR CONVERTING OBJECT TO XML FORMAT
	 * 
	 * @param g
	 *            a video game object
	 * @return
	 */
	public static String convertGameToXml(VideoGame g) {
		String content = "";
		// ADD TAG FOR NAME
		content += addTag("name", g.getName().replaceAll("&", "and"));
		
		// ADD TAG FOR ID
		content += addTag("id", String.valueOf(g.getId()));
		
		// ADD TAG FOR IMAGE IF NOT NULL
		if (g.getImgUrl() != null) {
			content += addTag("imgUrl", g.getImgUrl().getValue());
		}
		
		// ADD TAG FOR TYPE
		content += addTag("type", VideoGameConsole.convertIntToString(g.getConsoleType()));
		
		// WRAP ENTIRE GAME IN <game> TAGS
		String ret = addTag("game", content);
		return ret;
	}

	public static String addTag(String tag, String value) {
		return ("<" + tag + ">" + value + "</" + tag + ">");
	}

}
