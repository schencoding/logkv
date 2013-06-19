package com.hp.hpl.logkv.demo.topology;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import com.hp.hpl.logkv.demo.model.LogKVEdge;
import com.hp.hpl.logkv.demo.model.Node;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.DefaultVertexIconTransformer;
import edu.uci.ics.jung.visualization.decorators.EllipseVertexShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.decorators.VertexIconShapeTransformer;
import edu.uci.ics.jung.visualization.renderers.BasicVertexRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;

/**
 * Workbench panel.
 * @author Edmond
 */
public class WorkbenchPanel extends JPanel {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructor method.
	 */
	public WorkbenchPanel() {

		Graph<Node, LogKVEdge> graph = new SparseMultigraph<Node, LogKVEdge>();
		AbstractLayout<Node, LogKVEdge> layout = new StaticLayout<Node, LogKVEdge>(
				graph, new Dimension(600, 600));

		VisualizationViewer<Node, LogKVEdge> vv = new VisualizationViewer<Node, LogKVEdge>(layout);
		vv.setBackground(Color.white);

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Node>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.S);

		WorkbenchIconTransformer workbenchIconTransformer = new WorkbenchIconTransformer();
		vv.getRenderContext().setVertexIconTransformer(workbenchIconTransformer);

		vv.getRenderContext().setVertexShapeTransformer(new DemoVertexIconShapeTransformer(new EllipseVertexShapeTransformer<Node>()));

		vv.getRenderer().setVertexRenderer(new OutlineRenderer(vv));

		WorkbenchGraphMouse graphMouse = new WorkbenchGraphMouse(vv.getRenderContext());

		vv.setGraphMouse(graphMouse);

		Transformer<Node, Paint> vpf =
	            new PickableVertexPaintTransformer<Node>(vv.getPickedVertexState(), Color.white, Color.green);
	        vv.getRenderContext().setVertexFillPaintTransformer(vpf);

		GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
	}

	/**
	 * Workbench icon transformer.
	 * @author Edmond
	 */
    public static class WorkbenchIconTransformer
    	extends DefaultVertexIconTransformer<Node> implements Transformer<Node, Icon> {

    	/**
    	 * constructor method.
    	 */
    	public WorkbenchIconTransformer() {
    	}

    	@Override
        public Icon transform(Node node) {

    		return IconResourceMap.ICON_MAP.get(node.getClass().getSimpleName());
        }
    }

    /**
     * Demo vertex icon shape transformer.
     * @author Edmond
     */
    public static class DemoVertexIconShapeTransformer extends VertexIconShapeTransformer<Node> {

        /**
         * constructor method.
         * @param delegate	delegate transformer
         */
        public DemoVertexIconShapeTransformer(Transformer<Node, Shape> delegate) {
            super(delegate);
        }

        @Override
        public Shape transform(Node node) {

			Icon icon = (Icon) IconResourceMap.ICON_MAP.get(node.getClass().getSimpleName());

				Image image = ((ImageIcon) icon).getImage();

				Shape shape = shapeMap.get(image);
				if (shape == null) {

					shape = new Rectangle2D.Float(0, 0, image.getWidth(null) + 4, image.getHeight(null) + 4);

                    if (shape.getBounds().getWidth() > 0 && shape.getBounds().getHeight() > 0) {
                        int width = image.getWidth(null);
                        int height = image.getHeight(null);
                        AffineTransform transform =
                            AffineTransform.getTranslateInstance(-width / 2, -height / 2);
                        shape = transform.createTransformedShape(shape);
                        shapeMap.put(image, shape);
                    }
				}
				return shape;
		}
    }

    /**
     * Outline renderer.
     * @author Edmond
     */
    public static class OutlineRenderer extends BasicVertexRenderer<Node, LogKVEdge> {

    	/**
    	 * visualization viewer.
    	 */
    	VisualizationViewer<Node, LogKVEdge> vv;

    	/**
    	 * constructor method.
    	 * @param vv	visualization viewer
    	 */
    	public OutlineRenderer(VisualizationViewer<Node, LogKVEdge> vv) {
    		this.vv = vv;
    	}

    	/**
    	 * paint icon for vertex.
    	 * @param rc		render context
    	 * @param node		node instance
    	 * @param layout	layout
    	 */
        public void paintIconForVertex(RenderContext<Node, LogKVEdge> rc, Node node, Layout<Node, LogKVEdge> layout) {

            Point2D p = layout.transform(node);
            p = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p);
            float x = (float) p.getX();
            float y = (float) p.getY();

            GraphicsDecorator g = rc.getGraphicsContext();
            boolean outlineImages = true;
            Transformer<Node , Icon> vertexIconFunction = rc.getVertexIconTransformer();

            Icon icon = vertexIconFunction.transform(node);
            if ((icon == null || outlineImages) && vv.getPickedVertexState().getPicked().contains(node)) {
                Shape s = AffineTransform.getTranslateInstance(x - 2 , y - 2).
                    createTransformedShape(rc.getVertexShapeTransformer().transform(node));
                paintShapeForVertex(rc, node, s);
            }
            if (icon != null) {
                int xLoc = (int) (x - icon.getIconWidth() / 2);
                int yLoc = (int) (y - icon.getIconHeight() / 2);
                icon.paintIcon(rc.getScreenDevice(), g.getDelegate(), xLoc, yLoc);
            }
        }
    }

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Workbench Demo");

	    WorkbenchPanel workbenchPanel = new WorkbenchPanel();
	    frame.getContentPane().add(workbenchPanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 600, 280);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }

}
