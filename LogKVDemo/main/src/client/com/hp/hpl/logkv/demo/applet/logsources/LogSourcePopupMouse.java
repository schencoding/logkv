package com.hp.hpl.logkv.demo.applet.logsources;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;

import com.hp.hpl.logkv.demo.model.LogSource;
import com.hp.hpl.logkv.demo.model.Node;
import com.hp.hpl.logkv.demo.model.WorkerNodeLogSourceEdge;
import com.hp.hpl.logkv.ui.AddLogSourceRequest;
import com.hp.hpl.logkv.ui.AddLogSourceResponse;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;

/**
 * Workbench popup mouse plugin.
 * @author Edmond
 */
public class LogSourcePopupMouse extends AbstractPopupGraphMousePlugin {

	/**
	 * add log source dialog.
	 */
	private AddLogSourceDialog addLogSourceDialog;

    /**
     * popup menu.
     */
    protected JPopupMenu popup = new JPopupMenu();

    /**
     * frame.
     */
    private Frame frame;

	/**
	 * log source applet.
	 */
	private LogSourcesApplet logSourcesApplet;

    /**
     * constructor method.
     * @param frame			frame
     * @param logSourcesApplet	log source applet
     */
    public LogSourcePopupMouse(Frame frame, LogSourcesApplet logSourcesApplet) {
    	this.frame = frame;
    	this.addLogSourceDialog = new AddLogSourceDialog(frame, "Add Log Source");
    	this.logSourcesApplet = logSourcesApplet;
    }


	/**
	 * handle popup.
	 * @param	e	mouse event
	 */
	@SuppressWarnings("serial")
	protected void handlePopup(MouseEvent e) {
        @SuppressWarnings("unchecked")
		final VisualizationViewer<Node, WorkerNodeLogSourceEdge> vv
			= (VisualizationViewer<Node, WorkerNodeLogSourceEdge>) e.getSource();
        final Layout<Node, WorkerNodeLogSourceEdge> layout = vv.getGraphLayout();
        final Graph<Node, WorkerNodeLogSourceEdge> graph = layout.getGraph();
        final Point2D p = e.getPoint();

        GraphElementAccessor<Node, WorkerNodeLogSourceEdge> pickSupport = vv.getPickSupport();

        popup.removeAll();

        if (pickSupport != null) {

            popup.add(new AbstractAction("Create Log Source") {
                public void actionPerformed(ActionEvent e) {

                	addLogSourceDialog.setLocationRelativeTo(frame);
                	addLogSourceDialog.setVisible(true);

                	if (logSourcesApplet == null) {
	                	LogSource logSource = new LogSource("Dummy Log Source");
	                    graph.addVertex(logSource);
	                    layout.setLocation(logSource, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p));
	                    vv.repaint();
                	} else {

	            		try {

	            			if (addLogSourceDialog.isRequireAdd()) {

	            				AddLogSourceRequest requestObj = addLogSourceDialog.getAddLogSourceRequest();
	            				Object object = logSourcesApplet.getUrlRequester().request(requestObj);

	            				if (object == null) {
		        		    		System.err.println("object is null");
		        		    		return;
		        		    	}

	            				AddLogSourceResponse responseObj = (AddLogSourceResponse) object;

		        		    	logSourcesApplet.refreshGraph(responseObj);
	            			}

	            		} catch (Exception t) {
	            			t.printStackTrace();
	            		}
                	}
                }
            });

            if (popup.getComponentCount() > 0) {
                popup.show(vv, e.getX(), e.getY());
            }
        }
    }
}

