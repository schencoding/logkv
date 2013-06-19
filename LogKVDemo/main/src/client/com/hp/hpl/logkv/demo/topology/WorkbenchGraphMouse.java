package com.hp.hpl.logkv.demo.topology;

import java.awt.ItemSelectable;

import com.hp.hpl.logkv.demo.model.LogKVEdge;
import com.hp.hpl.logkv.demo.model.Node;
import com.hp.hpl.logkv.demo.model.WorkerNodeLogSourceEdge;

import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.AnimatedPickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;

/**
 * work bench graph mouse.
 * @author Edmond
 */
public class WorkbenchGraphMouse
	extends AbstractModalGraphMouse implements ModalGraphMouse, ItemSelectable {

	/**
	 * editing plugin.
	 */
	protected WorkbenchEditingMouse editingPlugin;

	/**
	 * popup editing plugin.
	 */
	protected WorkbenchPopupMouse popupEditingPlugin;


	/**
	 * create an instance with default values.
	 * @param rc				render context
	 */
	public WorkbenchGraphMouse(
			RenderContext<Node, LogKVEdge> rc) {
		this(rc, 1.1f, 1 / 1.1f);
	}

	/**
	 * create an instance with passed values.
	 * @param rc				render context
	 * @param in				override value for scale in
	 * @param out				override value for scale out
	 */
	public WorkbenchGraphMouse(
			RenderContext<Node, LogKVEdge> rc,
			float in, float out) {

		super(in, out);
		loadPlugins();
	}

	/**
	 * create the plugins, and load the plugins for PICKING mode.
	 */
	@Override
    protected void loadPlugins() {
		pickingPlugin = new PickingGraphMousePlugin<Node, WorkerNodeLogSourceEdge>();
		animatedPickingPlugin = new AnimatedPickingGraphMousePlugin<Node, WorkerNodeLogSourceEdge>();
		scalingPlugin = new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, in, out);
		editingPlugin = new WorkbenchEditingMouse();
		popupEditingPlugin = new WorkbenchPopupMouse();
		add(scalingPlugin);
		add(pickingPlugin);
		add(animatedPickingPlugin);
		add(editingPlugin);
		add(popupEditingPlugin);
	}

	/**
	 * setter for the Mode.
	 * @param mode	mode type
	 */
	@Override
    public void setMode(Mode mode) {
		throw new RuntimeException("only support picking mode");
	}

}

