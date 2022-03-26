package frontend;

import backend.*;
import java.awt.*;

import javax.swing.JFrame;
import java.util.*;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class ViewGraph {
	ViewGraph() {
		DirectedSparseGraph g = new DirectedSparseGraph();

		ArrayList<Device> devicesList = Device.getAllDevices();

		for (int i = 0; i < devicesList.size(); i++) {
			Device device = devicesList.get(i);
			g.addVertex(getStringForDevice(device));
			GraphMatrix graphMatrixObj = new GraphMatrix();
			graphMatrixObj.createIfFileDoesnotExist();
			graphMatrixObj.readMatrixFromFile();
			ArrayList<Integer> connectedPointOfSelectedDevice = graphMatrixObj.getConnectedPoint(i);
			for (int j = 0; j < connectedPointOfSelectedDevice.size(); j++) {
				String id = Device.getKeyFromValue(connectedPointOfSelectedDevice.get(j));
				Device secondDevice = Device.getDeviceById(id);
				String edgeName = String.valueOf(j) + String.valueOf(i);
				g.addEdge(edgeName, getStringForDevice(device), getStringForDevice(secondDevice));
			}
		}

		float dash[] = { 10.0f };
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
				0.0f);
		Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
			public Stroke transform(String s) {
				return edgeStroke;
			}
		};

		VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g), new Dimension(800, 500));

		vs.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vs.getRenderer().getVertexLabelRenderer().setPosition(Position.S);
		JFrame frame = new JFrame();
		frame.getContentPane().add(vs);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static String getStringForDevice(Device device) {
		String data = device.id + " - " + device.modelId + " - " + device.name + " - ";
		return data;
	}

}
