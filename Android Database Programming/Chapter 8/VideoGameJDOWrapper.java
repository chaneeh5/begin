package jwei.apps.dataforandroid.ch8;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import app.PMF;
import app.types.VideoGame;
import app.types.VideoGame.VideoGameConsole;

public class VideoGameJDOWrapper {

    /**
     * INSERT A SINGLE VIDEOGAME OBJECT
     * 
     * @param g
     *            - a video game object
     */
    public static void insertGame(VideoGame g) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(g);
        } finally {
            pm.close();
        }
    }

    /**
     * INSERT MULTIPLE VIDEOGAME OBJECTS - MORE EFFICIENT METHOD
     * 
     * @param games
     *            - a list of video game objects
     */
    public static void batchInsertGames(List<VideoGame> games) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            // ONLY NEED TO RETRIEVE AND USE PERSISTENCEMANAGER ONCE
            pm.makePersistentAll(games);
        } finally {
            pm.close();
        }
    }

    /**
     * GET ALL VIDEO GAMES OF A CERTAIN PLATFORM
     * 
     * @param platform
     *            - desired platform of games
     * @return
     */
    public static List<VideoGame> getGamesByType(String platform) {
        PersistenceManager pm = PMF.get().getPersistenceManager();

        // CONVERT STRING OF PLATFORM TO INT TYPE
        int type = VideoGameConsole.convertStringToInt(platform);

        // INIT A NEW QUERY AND SPECIFY THE OBJECT TYPE
        Query query = pm.newQuery(VideoGame.class);

        // SET THE FILTER - EQUIVALENT TO SQL WHERE FILTER
        query.setFilter("consoleType == inputType");

        // TELL THE QUERY WHAT PARAMETERS YOU WILL SEND
        query.declareParameters("int inputType");
        List<VideoGame> ret = null;
        try {
            // EXECUTE QUERY WITH PARAMETERS
            ret = (List<VideoGame>) query.execute(type);
        } finally {
            // CLOSE THE QUERY AT THE END
            query.closeAll();
        }
        return ret;
    }

    /**
     * GET ALL VIDEO GAMES OF A GIVEN PLATFORM WITH A LIMIT ON THE NUMBER OF
     * RESULTS
     * 
     * @param platform
     *            - desired platform of games
     * @param limit
     *            - max number of results to return
     * @return
     */
    public static List<VideoGame> getGamesByTypeWithLimit(String platform, int limit) {
        int type = VideoGameConsole.convertStringToInt(platform);
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(VideoGame.class);
        query.setFilter("consoleType == inputType");
        query.declareParameters("int inputType");

        // SAME QUERY AS ABOVE BUT THIS TIME SET A MAX RETURN LIMIT
        query.setRange(0, limit);
        List<VideoGame> ret = null;
        try {
            ret = (List<VideoGame>) query.execute(type);
        } finally {
            query.closeAll();
        }
        return ret;
    }

    /**
     * QUICKEST WAY TO RETRIEVE OBJECT IF YOU HAVE THE ID
     * 
     * @param id
     *            - row id of the object
     * @return
     */
    public static VideoGame getVideoGamesById(long id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        return (VideoGame) pm.getObjectById(VideoGame.class, id);
    }

    /**
     * METHOD FOR UPDATING THE NAME OF A VIDEO GAME
     * 
     * @param newName
     *            - new name of the game
     * @param id
     *            - the row id of the object
     * @return
     */
    public static boolean updateVideoGameName(String newName, long id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        boolean success = false;
        try {
            // AS LONG AS PERSISTENCE MANAGER IS OPEN THEN ANY CHANGES TO OBJECT
            // WILL AUTOMATICALLY GET UPDATED AND STORED
            VideoGame v = (VideoGame) pm.getObjectById(VideoGame.class, id);
            if (v != null) {
                // KEEP PERSISTENCEMANAGER OPEN
                v.setName(newName);
                success = true;
            }
        } catch (JDOObjectNotFoundException e) {
            e.printStackTrace();
            success = false;
        } finally {
            // ONCE CHANGES ARE MADE - CLOSE MANAGER
            pm.close();
        }
        return success;
    }

    /**
     * DELETE ALL GAMES OF A CERTAIN PLATFORM
     * 
     * @param platform
     *            - specify the platform of the games you want to delete
     */
    public static void deleteGamesByType(String platform) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        int type = VideoGameConsole.convertStringToInt(platform);

        // INIT QUERY AGAIN
        Query query = pm.newQuery(VideoGame.class);

        // SAME WHERE FILTERS
        query.setFilter("consoleType == inputType");
        query.declareParameters("int inputType");

        // NOW CALL THE DELETE METHOD
        query.deletePersistentAll(type);
    }

}
