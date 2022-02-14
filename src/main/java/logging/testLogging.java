package logging;

import org.apache.logging.log4j.*;

public class testLogging {

    private static Logger demoLogger = LogManager.getLogger(testLogging.class.getName());
    public static void main(String[] args){

        //api
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/org/apache/logging/log4j/Logger.class"));
        //core
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/org/apache/logging/log4j/core/Appender.class"));
        //config
        System.out.println(org.apache.logging.log4j.Logger.class.getResource("/log4j2.xml"));

        demoLogger.info("Info logged");
        demoLogger.error("Error logged");
        demoLogger.debug("Debug logged");
        demoLogger.fatal("Fatal logged");
    }
}