package browser;

import exceptions.BrowserTypeException;

import java.util.HashMap;

public class BrowserPool {// delete browser type
    private static final HashMap<BrowserType, Browser> browsers = new HashMap<>();

    public static Browser getInstance(BrowserType type) throws BrowserTypeException {
        Browser browserImpl = browsers.get(type);
        BrowserType implType = null;
        if(browserImpl == null){
            if(type.equals(BrowserType.CHROME)){
                implType = BrowserType.CHROME;
            } else if(type.equals(BrowserType.EDGE)){
                implType = BrowserType.EDGE;
            } else if(type.equals(BrowserType.FIREFOX)){
                implType = BrowserType.FIREFOX;
            }
           if (implType != null){
               browserImpl = new Browser(implType);
               browsers.put(type, browserImpl);
           }
           else {
               throw new BrowserTypeException("The required browser type is not supported");
           }
        }
        else{
            return browserImpl;
        }
        return browserImpl;
    }

}
