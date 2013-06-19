package com.hp.hpl.logkv.demo.topology;

import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.JOptionPane;

import com.hp.hpl.logkv.demo.model.Node;
import com.hp.hpl.logkv.demo.model.WorkerNodeLogSourceEdge;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;

/**
 * Workbench editing mouse.
 * @author Edmond
 */
public class WorkbenchEditingMouse
	extends AbstractGraphMousePlugin implements MouseListener {

    /**
	 * create an instance with default settings.
	 */
	public WorkbenchEditingMouse() {
	    this(InputEvent.BUTTON1_MASK);
	}

	/**
	 * create an instance with overrides.
	 * @param selectionModifiers for primary selection
	 */
    public WorkbenchEditingMouse(int selectionModifiers) {
        super(selectionModifiers);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }

	/**
	 * For primary modifiers (default, MouseButton1):
	 * pick a single Vertex or Edge that
     * is under the mouse pointer. If no Vertex or edge is under
     * the pointer, unselect all picked Vertices and edges, and
     * set up to draw a rectangle for multiple selection
     * of contained Vertices.
     * For additional selection (default Shift+MouseButton1):
     * Add to the selection, a single Vertex or Edge that is
     * under the mouse pointer. If a previously picked Vertex
     * or Edge is under the pointer, it is un-picked.
     * If no vertex or Edge is under the pointer, set up
     * to draw a multiple selection rectangle (as above)
     * but do not unpick previously picked elements.
	 * @param e the event
	 */
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {

    	if (e.getModifiers() == modifiers && e.getClickCount() == 2) {

    		VisualizationViewer<Node, WorkerNodeLogSourceEdge> vv = (VisualizationViewer<Node, WorkerNodeLogSourceEdge>) e.getSource();
    		GraphElementAccessor<Node, WorkerNodeLogSourceEdge> pickSupport = vv.getPickSupport();

    		if (pickSupport != null) {

				Layout<Node, WorkerNodeLogSourceEdge> layout = vv.getGraphLayout();
				// p is the screen point for the mouse event
				Point2D p = e.getPoint();

				Node vertex = pickSupport.getVertex(layout, p.getX(), p.getY());

				if (vertex != null) {

					String newLabel = JOptionPane.showInputDialog("New Vertex Label for " + vertex.getClass().getSimpleName());

					if (newLabel != null) {
						vertex.setName(newLabel);
						vv.repaint();
					}

					return;
				}
    		}
     		e.consume();
     	}
    }

    /**
	 * @param e		mouse event
	 */
    public void mouseReleased(MouseEvent e) {
    }

    /**
	 * @param e		mouse event
	 */
    public void mousePressed(MouseEvent e) {
    }

    /**
	 * @param e		mouse event
	 */
	public void mouseEntered(MouseEvent e) {
	}

    /**
	 * @param e		mouse event
	 */
	public void mouseExited(MouseEvent e) {
	}

}
