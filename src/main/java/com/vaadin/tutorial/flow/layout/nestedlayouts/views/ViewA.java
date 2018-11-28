package com.vaadin.tutorial.flow.layout.nestedlayouts.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.flow.layout.nestedlayouts.layout.LayoutWithMenuBar;

@Route(value = "ViewA", layout = LayoutWithMenuBar.class)
public class ViewA extends Composite<Div> {

  public ViewA() {
    getContent().add(new Span("ViewA"));
  }
}
