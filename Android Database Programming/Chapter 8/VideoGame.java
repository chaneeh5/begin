package jwei.apps.dataforandroid.ch8;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Link;

// NOTE HOW WE DECLARE OUR OBJECT AS PERSISTENCE CAPABLE
@PersistenceCapable
public class VideoGame extends ModelBase {

    // NOTE THE PERSISTENT TAGS
    @Persistent
    private String name;

    // USE A SPECIAL GOOGLE APP ENGINE LINK CLASS FOR URLS
    @Persistent
    private Link imgUrl;

    @Persistent
    private int consoleType;

    public VideoGame(String name, String url, String consoleType) {
        this.name = name;
        this.imgUrl = new Link(url);
        // CONVERT ALL CONSOLES TO INTEGER TYPES
        this.consoleType = VideoGameConsole.convertStringToInt(consoleType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Link getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Link imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(int consoleType) {
        this.consoleType = consoleType;
    }

    public static class VideoGameConsole {

        public static final String XBOX = "Xbox";

        public static final String PS3 = "Ps3";

        public static final String WII = "Wii";

        public static final String PSP = "Psp";

        public static final String DS = "NintendoDS";

        public static final String PS2 = "Ps2";

        public static final String[] CATEGORIES = { "Xbox", "Ps3", "Wii", "Psp", "NintendoDS", "Ps2" };

        public static int convertStringToInt(String type) {
            if (type == null) { return -1; }
            if (type.equalsIgnoreCase(XBOX)) {
                return 0;
            } else if (type.equalsIgnoreCase(PS3)) {
                return 1;
            } else if (type.equalsIgnoreCase(PS2)) {
                return 2;
            } else if (type.equalsIgnoreCase(PSP)) {
                return 3;
            } else if (type.equals(WII)) {
                return 4;
            } else if (type.equals(DS)) {
                return 5;
            } else {
                return -1;
            }
        }

        public static String convertIntToString(int type) {
            switch (type) {
                case 0:
                    return XBOX;
                case 1:
                    return PS3;
                case 2:
                    return PS2;
                case 3:
                    return PSP;
                case 4:
                    return WII;
                case 5:
                    return DS;
                default:
                    return null;
            }
        }
    }

}
