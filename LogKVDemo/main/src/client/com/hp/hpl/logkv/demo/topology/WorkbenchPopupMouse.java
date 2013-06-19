package com.hp.hpl.logkv.demo.topology;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import com.hp.hpl.logkv.demo.model.LogSource;
import com.hp.hpl.logkv.demo.model.Node;
import com.hp.hpl.logkv.demo.model.WorkerNode;
import com.hp.hpl.logkv.demo.model.WorkerNodeLogSourceEdge;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;

/**
 * Workbench popup mouse plugin.
 * @author Edmond
 */
public class WorkbenchPopupMouse extends AbstractPopupGraphMousePlugin {


    /**
     * popup menu.
     */
    protected JPopupMenu popup = new JPopupMenu();

    /**
     * constructor method.
     */
    public WorkbenchPopupMouse() {
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
        final Point2D ivp = p;

        GraphElementAccessor<Node, WorkerNodeLogSourceEdge> pickSupport = vv.getPickSupport();

        popup.removeAll();

        if (pickSupport != null) {

            final Node vertex = pickSupport.getVertex(layout, ivp.getX(), ivp.getY());
            final WorkerNodeLogSourceEdge edge = pickSupport.getEdge(layout, ivp.getX(), ivp.getY());
            final PickedState<Node> pickedVertexState = vv.getPickedVertexState();
            final PickedState<WorkerNodeLogSourceEdge> pickedEdgeState = vv.getPickedEdgeState();

            if (vertex != null) {

            	Set<Node> picked = pickedVertexState.getPicked();

            	if (picked.size() >= 2) {

                	Set<WorkerNode> wokerNodeSet = new HashSet<WorkerNode>();
                	Set<LogSource> logSourceSet = new HashSet<LogSource>();

                	for (Node node : picked) {
                		if (node.getName() != null) {
                			if (node.getClass() == WorkerNode.class) {
                				wokerNodeSet.add((WorkerNode) node);
                			} else if (node.getClass() == LogSource.class) {
                				logSourceSet.add((LogSource) node);
                			}
                		}
                	}

                	if (wokerNodeSet.size() >= 1 && logSourceSet.size() >= 1) {

	        			JMenu directedMenu = new JMenu("Create Relation");
	        			popup.add(directedMenu);

	        			for (final LogSource logSource : logSourceSet) {
	        				for (final WorkerNode workerNode : wokerNodeSet) {
		        				directedMenu.add(new AbstractAction("[" + logSource.getName() + "," + workerNode.getName() + "]") {
		        					public void actionPerformed(ActionEvent e) {
		        						graph.addEdge(new WorkerNodeLogSourceEdge(workerNode, logSource, 22),
		        								logSource, workerNode, EdgeType.DIRECTED);
		        						vv.repaint();
		        					}
		        				});
	        				}
	        			}
                	}

                }

                popup.add(new AbstractAction("Delete Node") {
                    public void actionPerformed(ActionEvent e) {
                        pickedVertexState.pick(vertex, false);
                        graph.removeVertex(vertex);
                        vv.repaint();
                    }
                });

            } else if (edge != null) {

                popup.add(new AbstractAction("Delete Edge") {
                    public void actionPerformed(ActionEvent e) {
                        pickedEdgeState.pick(edge, false);
                        graph.removeEdge(edge);
                        vv.repaint();
                    }
                });

            } else {
                popup.add(new AbstractAction("Create Worker Node") {
                    public void actionPerformed(ActionEvent e) {
                    	WorkerNode workerNode = new WorkerNode();
                        graph.addVertex(workerNode);
                        layout.setLocation(workerNode, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p));
                        vv.repaint();
                    }
                });

                popup.add(new AbstractAction("Create Log Server") {
                    public void actionPerformed(ActionEvent e) {
                    	LogSource logSource = new LogSource();
                        graph.addVertex(logSource);
                        layout.setLocation(logSource, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p));
                        vv.repaint();
                    }
                });
            }

            if (popup.getComponentCount() > 0) {
                popup.show(vv, e.getX(), e.getY());
            }
        }
    }
}

