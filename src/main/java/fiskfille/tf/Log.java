package fiskfille.tf;

import java.util.Arrays;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log
{
    private static Logger logger = LogManager.getLogger("Transformers Mod");
    
    public static void log(String s, Object... args)
    {
        log(Level.INFO, s, args);
    }
    
    public static void error(String s, Object... args)
    {
        log(Level.ERROR, s, args);
    }
    
    public static void warn(String s, Object... args)
    {
        log(Level.WARN, s, args);
    }
    
    private static void log(Level level, String s, Object... args)
    {
        if (args.length > 0)
        {
            s = String.format(s, Arrays.asList(args));
        }
        
        logger.log(level, s);
    }
}
