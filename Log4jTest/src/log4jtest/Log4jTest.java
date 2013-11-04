/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package log4jtest;

import org.apache.log4j.*;

/**
 *
 * @author ANDre1
 */
public class Log4jTest {

private static org.apache.log4j.Logger log = Logger.getLogger(Log4jTest.class);

public static void main(String[] args) {

 PropertyConfigurator.configure(Log4jTest.class.getClassLoader().getResource("log4jtest/log4j.properties"));

 for(int i=0;i<200;i++){
     log.trace("Trace"); 
     log.debug("Debug"); 
     log.info("Info"); 
     log.warn("Warn");
     log.error("Error"); 
     log.fatal("Fatal");}

 }
}
