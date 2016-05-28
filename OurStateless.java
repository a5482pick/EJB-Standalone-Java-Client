package myejb;

import javax.ejb.Remote;

@Remote
public interface OurStateless {           //The remote interface.

    public String hello();

}
