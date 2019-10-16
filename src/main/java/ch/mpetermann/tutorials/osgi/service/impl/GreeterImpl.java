package ch.mpetermann.tutorials.osgi.service.impl;

import ch.mpetermann.tutorials.osgi.service.api.Greeter;
import java.util.Hashtable;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class GreeterImpl implements Greeter, BundleActivator {

  private ServiceReference<Greeter> reference;
  private ServiceRegistration<Greeter> registration;

  public String sayHiTo(String name) {
    return "Hello " + name;
  }

  public void start(BundleContext context) throws Exception {
    System.out.println("Registering service.");
    registration = context.registerService(
        Greeter.class,
        new GreeterImpl(),
        new Hashtable<String, String>());
    reference = registration
        .getReference();
  }

  public void stop(BundleContext context) throws Exception {
    System.out.println("Unregistering service.");
    registration.unregister();
  }

}
