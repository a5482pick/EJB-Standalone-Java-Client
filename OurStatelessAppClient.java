package myejb;

import javax.ejb.EJB;

public class OurStatelessAppClient {             //Application client.

    @EJB
    private static OurStateless ourStateless;

    public static void main(String args[]) {

        System.out.println("Returned from entry-point OurStatelessAppClient : " + ourStateless.hello());

    }

}
