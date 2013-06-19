package com.hp.hpl.logkv.demo.applet.logsources;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import com.hp.hpl.logkv.demo.applet.UIUtil;
import com.hp.hpl.logkv.demo.model.LogKVEdge;
import com.hp.hpl.logkv.demo.model.LogSource;
import com.hp.hpl.logkv.demo.model.Node;
import com.hp.hpl.logkv.demo.model.WorkerNode;
import com.hp.hpl.logkv.demo.model.WorkerNodeLogSourceEdge;
import com.hp.hpl.logkv.demo.topology.WorkbenchPanel.DemoVertexIconShapeTransformer;
import com.hp.hpl.logkv.demo.topology.WorkbenchPanel.OutlineRenderer;
import com.hp.hpl.logkv.demo.topology.WorkbenchPanel.WorkbenchIconTransformer;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EllipseVertexShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer;
import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import edu.uci.ics.jung.visualization.transform.shape.ShapeTransformer;
import edu.uci.ics.jung.visualization.transform.shape.TransformingGraphics;

/**
 * Workbench panel.
 * @author Edmond
 */
public class LogSourcePanel extends JPanel {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * graph.
	 */
	Graph<Node, LogKVEdge> graph;

	/**
	 * Visualization viewer.
	 */
	private VisualizationViewer<Node, LogKVEdge> vv;

	/**
	 * constructor method.
	 * @param logSourcesApplet	log source applet
	 */
	public LogSourcePanel(LogSourcesApplet logSourcesApplet) {

		graph = new SparseMultigraph<Node, LogKVEdge>();
		AbstractLayout<Node, LogKVEdge> layout = new StaticLayout<Node, LogKVEdge>(
				graph);

		vv = new VisualizationViewer<Node, LogKVEdge>(layout);
		vv.setBackground(Color.white);

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Node>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<LogKVEdge>());

		vv.getRenderer().setVertexLabelRenderer(new BasicVertexLabelRenderer<Node, LogKVEdge>() {

			@Override
		    public void labelVertex(RenderContext<Node, LogKVEdge> rc, Layout<Node, LogKVEdge> layout, Node v, String label) {
		    	Graph<Node, LogKVEdge> graph = layout.getGraph();
		        if (!rc.getVertexIncludePredicate().evaluate(Context.<Graph<Node, LogKVEdge>, Node>getInstance(graph, v))) {
		        	return;
		        }
		        Point2D pt = layout.transform(v);
		        pt = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, pt);

		        float x = (float) pt.getX();
		        float y = (float) pt.getY();

		        Component component = prepareRenderer(rc, rc.getVertexLabelRenderer(), label,
		        		rc.getPickedVertexState().isPicked(v), v);
		        GraphicsDecorator g = rc.getGraphicsContext();
		        Dimension d = component.getPreferredSize();
		        AffineTransform xform = AffineTransform.getTranslateInstance(x, y);

		    	Shape shape = rc.getVertexShapeTransformer().transform(v);
		    	shape = xform.createTransformedShape(shape);
		    	if (rc.getGraphicsContext() instanceof TransformingGraphics) {
		    		BidirectionalTransformer transformer = ((TransformingGraphics) rc.getGraphicsContext()).getTransformer();
		    		if (transformer instanceof ShapeTransformer) {
		    			ShapeTransformer shapeTransformer = (ShapeTransformer) transformer;
		    			shape = shapeTransformer.transform(shape);
		    		}
		    	}
		    	Rectangle2D bounds = shape.getBounds2D();

		    	Point p = null;
		    	if (position == Position.AUTO) {
		    		Dimension vvd = rc.getScreenDevice().getSize();
		    		if (vvd.width == 0 || vvd.height == 0) {
		    			vvd = rc.getScreenDevice().getPreferredSize();
		    		}
		    		p = getAnchorPoint(bounds, d, this.getPositioner().getPosition(x, y, vvd));
		    	} else {
		    		if (v instanceof WorkerNode) {
		    			p = getAnchorPoint(bounds, d, Position.N);
		    		} else {
		    			p = getAnchorPoint(bounds, d, Position.S);
		    		}
		    	}
		        g.draw(component, rc.getRendererPane(), p.x, p.y, d.width, d.height, true);
		    }

			@Override
			public Position getPosition() {

				return super.getPosition();
			}

		}); //.setPosition(Position.N);

//		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.AUTO);

		WorkbenchIconTransformer workbenchIconTransformer = new WorkbenchIconTransformer();
		vv.getRenderContext().setVertexIconTransformer(workbenchIconTransformer);

		vv.getRenderContext().setVertexShapeTransformer(new DemoVertexIconShapeTransformer(new EllipseVertexShapeTransformer<Node>()));

		vv.getRenderer().setVertexRenderer(new OutlineRenderer(vv));

		LogSourceGraphMouse graphMouse = new LogSourceGraphMouse(vv.getRenderContext(), UIUtil.getParentFrame(this), logSourcesApplet);

		vv.setGraphMouse(graphMouse);

		vv.getRenderContext().setEdgeLabelTransformer(new Transformer<LogKVEdge, String>() {
			public String transform(LogKVEdge input) {

				return "<html><table border='0'><tr><td><div style='color:black;font-size:14px'>" +  input.toString();

			}
		}
		);


		Transformer<Node, Paint> vpf =
	            new PickableVertexPaintTransformer<Node>(vv.getPickedVertexState(), Color.white, Color.green);
	        vv.getRenderContext().setVertexFillPaintTransformer(vpf);

		GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);

	}


	/**
	 * insert worker node to graph.
	 * @param workerNodeList	worker node list
	 * @param logSourceList		log source list
	 * @param edgeList			edge list
	 */
	public void insertResourceToGraph(
			List<WorkerNode> workerNodeList,
			List<LogSource> logSourceList,
			List<LogKVEdge> edgeList) {

       	UIUtil.removeEdge(graph);
       	UIUtil.removeVertex(graph);

       	Dimension panelSize = this.getSize();

       	int workerNodeXLocation = 50;

       	if (workerNodeList != null && workerNodeList.size() != 0) {
       		workerNodeXLocation = ((int) (panelSize.getWidth() - 1) - workerNodeList.size() * 100) / 2;
       	}

       	int logSourceXLocation = 50;

       	if (logSourceList != null && logSourceList.size() != 0) {
       		logSourceXLocation = ((int) (panelSize.getWidth() - 1) - logSourceList.size() * 100) / 2;
       	}

       	int yLocation = ((int) panelSize.getHeight() - 200) / 2;

		Layout<Node, LogKVEdge> layout = vv.getGraphLayout();

		if (workerNodeList != null && workerNodeList.size() != 0) {
			for (WorkerNode wn : workerNodeList) {
				if (!graph.containsVertex(wn)) {
					graph.addVertex(wn);
					layout.setLocation(wn, new Point2D.Float(workerNodeXLocation, yLocation));
					workerNodeXLocation += 100;
				}
			}
		}

		if (logSourceList != null && logSourceList.size() != 0) {
			for (LogSource logSource : logSourceList) {
				if (!graph.containsVertex(logSource)) {
					graph.addVertex(logSource);
					layout.setLocation(logSource, new Point2D.Float(logSourceXLocation, yLocation + 200));
					logSourceXLocation += 100;
				}
			}
		}

		if (edgeList != null && edgeList.size() != 0) {
			for (LogKVEdge edge : edgeList) {
				WorkerNodeLogSourceEdge wlEdge = (WorkerNodeLogSourceEdge) edge;
				if (!graph.containsEdge(edge)) {
					graph.addEdge(edge, wlEdge.getLogSource(), wlEdge.getWorkerNode(), EdgeType.DIRECTED);
				}
			}
		}

		vv.repaint();

	}



	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Add Log Source Demo");

	    LogSourcePanel logSourcePanel = new LogSourcePanel(null);
	    frame.getContentPane().add(logSourcePanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 800, 600);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    List<WorkerNode> workerNodeList = new ArrayList<WorkerNode>();
	    workerNodeList.add(new WorkerNode("WN1"));
	    workerNodeList.add(new WorkerNode("WN2"));
	    workerNodeList.add(new WorkerNode("WN3"));

	    List<LogSource> logSourceList = new ArrayList<LogSource>();
	    logSourceList.add(new LogSource("LS1"));
	    logSourceList.add(new LogSource("LS2"));

	    List<LogKVEdge> edgeList = new ArrayList<LogKVEdge>();
	    edgeList.add(new WorkerNodeLogSourceEdge(workerNodeList.get(0), logSourceList.get(0), 22));
	    edgeList.add(new WorkerNodeLogSourceEdge(workerNodeList.get(2), logSourceList.get(1), 33));

	    logSourcePanel.insertResourceToGraph(workerNodeList, logSourceList, edgeList);
	 }

}
