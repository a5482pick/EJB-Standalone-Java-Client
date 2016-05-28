package myejb;

import javax.naming.InitialContext;
import java.util.*;

public class OurStatelessJavaClient {               //Runs outside of Java EE container.

    public static void main(String args[]) {

        try {
        
            //The following block is required when accessing a remote EJB component from a non-Java EE web
            //container like Tomcat.  This is because we have to override the JNDI name provider of the web server.
            //(If this following block is commented out, use the no-argument InitialContext() constructor.)
            //--------------------------------------------------------------------------------------------------

            Properties props = new Properties();

            props.setProperty("java.naming.factory.initial", 
                    "com.sun.enterprise.naming.SerialInitContextFactory");

            props.setProperty("java.naming.factory.url.pkgs", 
                    "com.sun.enterprise.naming");

            props.setProperty("java.naming.factory.state",
                    "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");


            // optional.  Defaults to localhost.  Only needed if web server is running 
            // on a different host than the appserver    
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");

            // optional.  Defaults to 3700.  Only needed if target orb port is not 3700.
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            InitialContext ic = new InitialContext(props);
            
            //----------------------------------------------------------------------------------------------------
              
            //Three representations of global JNDI lookups. Lookups are required because dependency injection is unavailable.
            //Stand-alone clients don't have access java:comp/env.
              
            //Option 1: The most general, portable case.  (The JNDI name entry is accessible from inside and outside of the application):
            OurStateless ourStateless = (OurStateless) ic.lookup("java:global/OurStatelessejb/OurStatelessBean!myejb.OurStateless");
            
            //Option 2:
            //OurStateless ourStateless = (OurStateless) ic.lookup("java:global/OurStatelessejb/OurStatelessBean");
            
            //Option3:  This is generally not portable (i.e. does not account for different vendor implementations.)
            //OurStateless ourStateless = (OurStateless) ic.lookup("myejb.OurStateless");
            
            
            System.out.println("Returned from entry-point OurStatelessJavaClient : " + ourStateless.hello());

        } catch(Exception e) {
 
            e.printStackTrace();
        }
    }
}
