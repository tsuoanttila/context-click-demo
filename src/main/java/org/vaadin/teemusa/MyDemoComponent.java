package org.vaadin.teemusa;

import org.vaadin.fwteam.annotations.DemoComponent;
import org.vaadin.fwteam.annotations.SourceHighlight;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ContextClickEvent.ContextClickListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.grid.GridConstants.Section;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.GridContextClickEvent;
import com.vaadin.ui.Tree.TreeContextClickEvent;

@DemoComponent(projectName = "context-click-demo", author = "tsuoanttila", description = "ContextClick demo with several different components", title = "ContextClick Demo")
public class MyDemoComponent extends VerticalLayout {

	public MyDemoComponent() {
		Panel panel = new Panel();
		FormLayout content = new FormLayout();
		content.addComponent(new TextField("Email"));
		content.addComponent(new Button("Submit"));
		panel.setContent(content);

		/* Form panel ContextMenu */
		panel.addContextClickListener(new ContextClickListener() {

			@Override
			public void contextClick(ContextClickEvent event) {
				Notification.show("ContextClick on panel in coordinates ("
						+ event.getClientX() + ", " + event.getClientY() + ")");
			}
		});

		/* Grid */
		Grid grid = new Grid();

		grid.addColumn("firstName", String.class);
		grid.addColumn("lastName", String.class);
		grid.addColumn("email", String.class);

		grid.setHeightMode(HeightMode.ROW);
		grid.setHeightByRows(4);

		grid.addRow("John", "Doe", "john.doe[at]unknown.com");
		// TODO: Add test data

		addGridContextClickListener(grid);

		Tree tree = new Tree();

		tree.addItem("Python");
		tree.addItem("Monty Python");
		tree.addItem("Snake");
		tree.addItem("Programming Language");
		tree.addItem("The Black Knight");
		tree.setParent("Monty Python", "Python");
		tree.setParent("Snake", "Python");
		tree.setParent("Programming Language", "Python");
		tree.setParent("The Black Knight", "Monty Python");

		tree.addContextClickListener(new ContextClickListener() {

			@Override
			public void contextClick(ContextClickEvent event) {
				if (!(event instanceof TreeContextClickEvent)) {
					return;
				}
				TreeContextClickEvent e = (TreeContextClickEvent) event;

				Notification.show("ContextClick in Tree on item "
						+ e.getItemId());
			}
		});

		addComponent(panel);
		addComponent(grid);
		addComponent(tree);
	}

	@SourceHighlight
	private void addGridContextClickListener(Grid grid) {
		grid.addContextClickListener(new ContextClickListener() {

			@Override
			public void contextClick(ContextClickEvent event) {
				if (!(event instanceof GridContextClickEvent)) {
					return;
				}
				GridContextClickEvent e = (GridContextClickEvent) event;
				Notification.show("ContextClick in Grid "
						+ e.getSection()
						+ (e.getSection() == Section.BODY ? " at item "
								+ e.getItemId() : "") + " on property "
						+ e.getPropertyId());
			}
		});
	}
}
