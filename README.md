**_Accessing a Stateless Session Bean Using Application Clients and Standalone Java Clients._**

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
$ jar cvfm /DIRECTORY_PATH/build/Ourstatelessappclient.jar /DIRECTORY_PATH/OurStatelessManifest.mf myejb/*.class

...where DIRECTORY_PATH leads to the 'build' directory. &nbsp; From within this directory the application client can be run alone with:

$ appclient -client  Ourstatelessappclient.jar

... and the stand-alone java client can be run on its own, from the directory build/javaclient/, with:

$ java -cp /home/USRNAME/Downloads/glassfish4/glassfish/lib/javaee.jar:/home/USRNAME/Downloads/glassfish4/glassfish/lib/appserv-rt.jar:. myejb.OurStatelessJavaClient

(Note of course that both the stand-alone java client and the application client require OurStatelessejb to be deployed and accessible on the server.)

-------------------------

The stand-alone java client uses the JNDI API package. &nbsp; It can't use the component naming environment or the @EJB annotation, and therefore has to 'lookup' the global JNDI name. &nbsp This global name is looked up in OurStatelessJavaClient.java using one of three alternative methods. &nbsp; The simplest method is option 3 but this doesn't account for vendor-specific implementations. &nbsp; The most portable approach is therefore that of option 1.

Also provided in OurStatelessJavaClient.java is an optional block of code that is needed when a remote EJB component is accessed from a non-Java EE web container.