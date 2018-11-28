package com.vaadin.tutorial.flow.layout.nestedlayouts.layout;

import java.util.Objects;

import org.rapidpm.dependencies.core.logger.HasLogger;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MainLayout extends Composite<Div> implements RouterLayout , HasLogger {

  //Component to delegate content through.
  private Div content = new Div();

  private final VerticalLayout layout = new VerticalLayout(
      new Span("from MainLayout top") ,
      content ,
      new Span("from MainLayout bottom")
  );

  public MainLayout() {
    getContent().add(layout);
  }

  @Override
  public void showRouterLayoutContent(HasElement hasElement) {
    logger().info("showRouterLayoutContent - MainLayout");
    Objects.requireNonNull(hasElement);
    Objects.requireNonNull(hasElement.getElement());
    content.removeAll();
    content.getElement().appendChild(hasElement.getElement());
  }
}
