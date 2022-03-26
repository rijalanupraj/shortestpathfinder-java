package frontend;

import backend.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class ShortestPathViewGraph {
	ArrayList<Integer> path;
	String distance;

	ShortestPathViewGraph(ArrayList<Integer> path, String distance) {
		this.distance = distance;
		this.path = path;
		Collections.reverse(path);

		DirectedSparseGraph g = new DirectedSparseGraph();

		ArrayList<Device> devicesList = Device.getAllDevices();

		for (int i = 0; i < devicesList.size(); i++) {
			Device device = devicesList.get(i);
			g.addVertex(getStringForDevice(device));
		}

		for (int j = 0; j < path.size() - 1; j++) {
			String currentId = Device.getKeyFromValue(path.get(j));
			Device currentDevice = Device.getDeviceById(currentId);
			String nextId = Device.getKeyFromValue(path.get(j + 1));
			Device nextDevice = Device.getDeviceById(nextId);
			String edgeName = String.valueOf(j+1);
			g.addEdge(edgeName, getStringForDevice(currentDevice), getStringForDevice(nextDevice));

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

		// JLabel
		String labelText = "Distance: " + distance;
		JLabel lDistance = new JLabel(labelText);
		lDistance.setBounds(20, 20, 100, 30);
		frame.add(lDistance);

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
