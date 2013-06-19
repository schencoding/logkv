package com.hp.hpl.logkv.demo.applet;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import edu.uci.ics.jung.graph.Graph;

/**
 * UI utility.
 * @author Edmond
 */
public final class UIUtil {

	/**
	 * prevent to instance.
	 */
	private UIUtil() {
	}

	/**
	 * get component's parent frame.
	 * @param component	component
	 * @return component's parent frame
	 */
	public static Frame getParentFrame(Component component) {

		Container c = component.getParent();

		while (c != null) {
			if (c instanceof Frame) {
				return (Frame) c;
			}
		}

		return null;
	}

	/**
	 * remove edges for graph.
	 * @param g	graph
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeEdge(Graph g) {

		List edges = new ArrayList();

		for (Object edge : g.getEdges()) {
			edges.add(edge);
		}

		for (Object edge : edges) {
			g.removeEdge(edge);
		}
	}

	/**
	 * remove vertices for graph.
	 * @param g	graph
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeVertex(Graph g) {

		List vertexs = new ArrayList();

		for (Object node : g.getVertices()) {
			vertexs.add(node);
		}

		for (Object node : vertexs) {
			g.removeVertex(node);
		}
	}

}
