/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.*;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.model.Result;

import static java.lang.System.getProperty;
import static org.rapidpm.frp.model.Result.failure;
import static org.stagemonitor.web.servlet.initializer.ServletContainerInitializerUtil.registerStagemonitorServletContainerInitializers;

/**
 *
 */
public class CoreUIService
    implements HasLogger {

  public static final String JAR_PATTERN = "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern";


  public static final String CORE_UI_SERVER_HOST_DEFAULT = "0.0.0.0";
  public static final String CORE_UI_SERVER_PORT_DEFAULT = "8899";

  public static final String CORE_UI_SERVER_HOST = "core-ui-server-host";
  public static final String CORE_UI_SERVER_PORT = "core-ui-server-port";

  public Result<Server> jetty = failure("not initialised so far");

  public void startup() {

    try {
      WebAppContext context = new WebAppContext();
      context.setLogUrlOnStart(true);
      context.setConfigurationDiscovered(true);
      context.setConfigurations(new Configuration[]{
          new AnnotationConfiguration(), new WebInfConfiguration(), new WebXmlConfiguration(),
          new MetaInfConfiguration()
      });

      context.setContextPath("/");
      Resource classPathResource = Resource.newClassPathResource("/META-INF/resources", true, true);
      context.setBaseResource(classPathResource);
      context.setAllowNullPathInfo(true);
      context.setAttribute(JAR_PATTERN, ".*");

      Server server = new Server(Integer.parseInt(getProperty(CORE_UI_SERVER_PORT, CORE_UI_SERVER_PORT_DEFAULT)));
      server.setHandler(context);

      context.getServletHandler()
             .addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {
               @Override
               public void lifeCycleStarting(LifeCycle event) {
                 registerStagemonitorServletContainerInitializers(context.getServletContext());
               }
             });

      server.start();
      server.join();
      jetty = Result.success(server);
    } catch (Exception e) {
      logger().warning(e.getLocalizedMessage());
    }
  }
}
