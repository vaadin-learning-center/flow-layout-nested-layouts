package com.vaadin.tutorial.flow.layout.nestedlayouts.layout;

import java.util.Objects;

import org.rapidpm.dependencies.core.logger.HasLogger;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.tutorial.flow.layout.nestedlayouts.views.VaadinApp;
import com.vaadin.tutorial.flow.layout.nestedlayouts.views.ViewA;
import com.vaadin.tutorial.flow.layout.nestedlayouts.views.ViewB;

@ParentLayout(value = MainLayout.class)
public class LayoutWithMenuBar extends Composite<Div> implements RouterLayout , HasLogger {

  private final Div content = new Div();

  private final HorizontalLayout menuBar = new HorizontalLayout(
      new RouterLink("ViewA" , ViewA.class) ,
      new RouterLink("ViewB" , ViewB.class) ,
      new RouterLink("Home" , VaadinApp.class)
  );

  private final VerticalLayout root = new VerticalLayout(menuBar, content);

  public LayoutWithMenuBar() {
    getContent().add(root);
  }

  @Override
  public void showRouterLayoutContent(HasElement hasElement) {
    logger().info("showRouterLayoutContent - LayoutWithMenuBar");
    Objects.requireNonNull(hasElement);
    Objects.requireNonNull(hasElement.getElement());
    content.removeAll();
    content.getElement().appendChild(hasElement.getElement());
  }
}
