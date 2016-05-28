package myejb;

import javax.ejb.Stateless;

@Stateless
public class OurStatelessBean implements OurStateless {

    public String hello() {
        return "hello, world!\n";
    }

}
