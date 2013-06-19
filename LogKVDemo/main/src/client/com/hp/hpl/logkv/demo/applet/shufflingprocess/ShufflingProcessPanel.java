package com.hp.hpl.logkv.demo.applet.shufflingprocess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.commons.collections15.Transformer;

import com.hp.hpl.logkv.demo.applet.StringUtil;
import com.hp.hpl.logkv.demo.applet.UIUtil;
import com.hp.hpl.logkv.demo.model.LogKVEdge;
import com.hp.hpl.logkv.demo.model.Node;
import com.hp.hpl.logkv.demo.model.WorkerNode;
import com.hp.hpl.logkv.demo.model.WorkerNodeWorkerNodeEdge;
import com.hp.hpl.logkv.demo.topology.WorkbenchPanel.DemoVertexIconShapeTransformer;
import com.hp.hpl.logkv.demo.topology.WorkbenchPanel.WorkbenchIconTransformer;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.EllipseVertexShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * Workbench panel.
 * @author Edmond
 */
public class ShufflingProcessPanel extends JPanel {

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
	 * layout.
	 */
	private CircleLayout<Node, LogKVEdge> layout;

	/**
	 * constructor method.
	 * @param size	panel size
	 */
	public ShufflingProcessPanel(Dimension size) {

		graph = new SparseMultigraph<Node, LogKVEdge>();
		CircleLayout<Node, LogKVEdge> layout = new CircleLayout<Node, LogKVEdge>(
				graph);

		vv = new VisualizationViewer<Node, LogKVEdge>(layout);

		if (size != null) {
			vv.setSize(size);
		}
		vv.setBackground(Color.white);


		DefaultModalGraphMouse<Node, LogKVEdge> gm = new DefaultModalGraphMouse<Node, LogKVEdge>();

		gm.setMode(ModalGraphMouse.Mode.PICKING);

		// Controlling the Mouse Mode via Key Listeners
		vv.addKeyListener(gm.getModeKeyListener());

		vv.setGraphMouse(gm);

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Node>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<LogKVEdge>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.S);

		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.CubicCurve<Node, LogKVEdge>());

		WorkbenchIconTransformer workbenchIconTransformer = new WorkbenchIconTransformer();
		vv.getRenderContext().setVertexIconTransformer(workbenchIconTransformer);

		vv.getRenderContext().setVertexShapeTransformer(new DemoVertexIconShapeTransformer(new EllipseVertexShapeTransformer<Node>()));

        vv.getRenderContext().setEdgeLabelTransformer(new Transformer<LogKVEdge, String>() {
			public String transform(LogKVEdge input) {

				WorkerNodeWorkerNodeEdge wwe = (WorkerNodeWorkerNodeEdge) input;

				if (wwe.getWorkerNode1().getName().equals(wwe.getWorkerNode2().getName())) {
					return "<html><table border='0'><tr><td><div style='color:black;font-size:14px'><br/>" +  input.toString();
				} else {

					int nodeStartX = (int) ShufflingProcessPanel.this.layout.getX(wwe.getWorkerNode1());
					int nodeStartY = (int) ShufflingProcessPanel.this.layout.getY(wwe.getWorkerNode1());

					int nodeEndX = (int) ShufflingProcessPanel.this.layout.getX(wwe.getWorkerNode2());
					int nodeEndY = (int) ShufflingProcessPanel.this.layout.getY(wwe.getWorkerNode2());

					int nodeConvertEndX = nodeEndX - nodeStartX;
					int nodeConvertEndY = nodeEndY - nodeStartY;

					String alignStyle = "center";

					if (nodeConvertEndX == 0 || nodeConvertEndY == 0) {
						alignStyle = "center";
					} else if (nodeConvertEndX < 0) {
						alignStyle = "right";
					} else if (nodeConvertEndX > 0) {
						alignStyle = "left";
					}

					int divWidth = (int) Math.sqrt(Math.pow(Math.abs(nodeStartX - nodeEndX), 2) + Math.pow(Math.abs(nodeStartY - nodeEndY), 2)) - 100;

					return "<html><table border='0'><tr><td><div style='color:black;font-size:14px;width:"
							+ divWidth + "px;text-align:" + alignStyle + "'>" +  input.toString() + "";
				}

			}
			}
		);

		Transformer<Node, Paint> vpf =
	            new PickableVertexPaintTransformer<Node>(vv.getPickedVertexState(), Color.white, Color.green);
	        vv.getRenderContext().setVertexFillPaintTransformer(vpf);

		GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);


		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Animation", panel);

		tabbedPane.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * remove all edge.
	 */
	public void removeAllEdge() {
		UIUtil.removeEdge(graph);
		vv.repaint();
	}

	/**
	 * insert worker node to graph.
	 * @param workerNodeList	worker node list
	 */
	public void insertWorkerNodeToGraph(
			List<WorkerNode> workerNodeList) {

		layout = new CircleLayout<Node, LogKVEdge>(
				graph);

		layout.setRadius(200);

		if (workerNodeList != null && workerNodeList.size() != 0) {
			for (WorkerNode wn : workerNodeList) {
				if (!graph.containsVertex(wn)) {
					graph.addVertex(wn);
				}
			}
		}

		layout.setSize(new Dimension((int) vv.getSize().getWidth(), (int) (vv.getSize().getHeight() - 20)));
		vv.setGraphLayout(layout);

		vv.repaint();
	}

	/**
	 * refresh edge in graph.
	 * @param shuffleStatus		shuffle status
	 */
	public void refreshEdgeInGraph(
			ShuffleStatus shuffleStatus) {

		WorkerNode wn1 = null;
		WorkerNode wn2 = null;

		for (Node n : graph.getVertices()) {

			WorkerNode wn = (WorkerNode) n;

			if (wn.getName().equals(StringUtil.convertNodeName(shuffleStatus.getSrcIpStr()))) {
				wn1 = wn;
			}

			if (wn.getName().equals(StringUtil.convertNodeName(shuffleStatus.getDestIpStr()))) {
				wn2 = wn;
			}

		}

		if (wn1 == null || wn2 == null) {
			throw new RuntimeException("wn1 or wn2 is null.");
		}

		WorkerNodeWorkerNodeEdge wwEdge = new WorkerNodeWorkerNodeEdge(wn1, wn2, shuffleStatus.getTruId(), shuffleStatus.getSize());

		if (shuffleStatus.isbStart()) {
			graph.addEdge(wwEdge, wwEdge.getWorkerNode1(), wwEdge.getWorkerNode2(), EdgeType.DIRECTED);
		}
//		else {
//			//graph.removeEdge(wwEdge);
//		}

		vv.repaint();
	}


	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Shuffling Process Demo");

	    ShufflingProcessPanel shufflingProcessPanel = new ShufflingProcessPanel(frame.getSize());
	    frame.getContentPane().add(shufflingProcessPanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 1200, 800);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    List<WorkerNode> workerNodeList = new ArrayList<WorkerNode>();
	    workerNodeList.add(new WorkerNode("WN1"));
	    workerNodeList.add(new WorkerNode("WN2"));
	    workerNodeList.add(new WorkerNode("WN3"));

	    shufflingProcessPanel.insertWorkerNodeToGraph(workerNodeList);

	    ShuffleStatus shuffleStatus = new ShuffleStatus("WN1", "WN1", 1, true);
	    shufflingProcessPanel.refreshEdgeInGraph(shuffleStatus);
	    shuffleStatus = new ShuffleStatus("WN1", "WN2", 1, true);
	    shufflingProcessPanel.refreshEdgeInGraph(shuffleStatus);
	    shuffleStatus = new ShuffleStatus("WN2", "WN3", 1, true);
	    shufflingProcessPanel.refreshEdgeInGraph(shuffleStatus);
	    shuffleStatus = new ShuffleStatus("WN3", "WN3", 1, true);
	    shufflingProcessPanel.refreshEdgeInGraph(shuffleStatus);
	    shuffleStatus = new ShuffleStatus("WN1", "WN3", 1, true);
	    shufflingProcessPanel.refreshEdgeInGraph(shuffleStatus);
	 }

}
