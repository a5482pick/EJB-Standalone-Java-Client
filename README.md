**_Accessing a Stateless Session Bean Using Application Clients and Standalone Java Clients._**

The stand-alone java client uses the JNDI API package and execution can be initiated using the usual java command-line command (see the final command provided below). &nbsp; It can't use the component naming environment or the @EJB annotation, and therefore has to 'lookup' the global JNDI name. &nbsp; This lookup is achieved using one of the three alternative methods listed in OurStatelessJavaClient.java : The simplest method is 'option 3' but this doesn't account for vendor-specific implementations... to account for these (and therefore improve portability) 'option 1' should be used.

Also provided in OurStatelessJavaClient.java is an optional block of code that is needed when a remote EJB component is accessed from a non-Java EE web container. &nbsp; Normally, the zero-argument InitialContext() constructor can be used instead.

The communication to and from the stateless session bean (called OurStatelessBean.java) is performed via a remote business interface called OurStateless.java.

--------------------------------------------

_To use:_

In the ant script (build.xml) modify the property on line 7 to point to your application server home. &nbsp; When your server is running, execute with:

$ ant clean build deploy runappclient runjavaclient


The verbose compilation and build is as follows:

$ mkdir build

$ cd build

$ mkdir javaclient

$ mkdir appclient

$ javac -classpath .:/home/USRNAME/Downloads/glassfish4/glassfish/lib/javaee.jar -d ./build/ejb/ OurStateless.java OurStatelessBean.java

$ javac -classpath .:/home/USRNAME/Downloads/glassfish4/glassfish/lib/javaee.jar -d ./build/appclient/ OurStateless.java OurStatelessAppClient.java

$ javac -classpath .:/home/USRNAME/Downloads/glassfish4/glassfish/lib/javaee.jar -d ./build/javaclient/ OurStateless.java OurStatelessJavaClient.java

$ cd build/ejb

$ jar cf /DIRECTORY_PATH/build/OurStatelessejb.jar myejb/*.class

$ jar cvfm /DIRECTORY_PATH/build/OurStatelessappclient.jar /DIRECTORY_PATH/OurStatelessManifest.mf myejb/*.class

...where DIRECTORY_PATH leads to the 'build' directory. &nbsp; From within this directory the application client can be run alone with Glassfish's bin/appclient:

$ appclient -client  OurStatelessappclient.jar

... and the stand-alone java client can be run on its own, from the directory build/javaclient/, with:

$ java -cp /home/USRNAME/Downloads/glassfish4/glassfish/lib/javaee.jar:/home/USRNAME/Downloads/glassfish4/glassfish/lib/appserv-rt.jar:. myejb.OurStatelessJavaClient

(Note of course that both the stand-alone java client and the application client require OurStatelessejb to be deployed and accessible on the server.)


