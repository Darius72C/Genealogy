/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;


import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author bayomock.a
 */
@Named("diagramFlowChartView")
@ViewScoped
public class FlowChartView implements Serializable {

    private DefaultDiagramModel model;

    @PostConstruct
    public void init() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{stroke:'#C7B097',strokeWidth:3}");
        model.setDefaultConnector(connector);

        Element start = new Element("Fight for your dream", "20em", "6em");
        start.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        start.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));

        Element trouble = new Element("Do you meet some trouble?", "20em", "18em");
        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));

        Element giveup = new Element("Do you give up?", "20em", "30em");
        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));

        Element succeed = new Element("Succeed", "50em", "18em");
        succeed.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        succeed.setStyleClass("ui-diagram-success");

        Element fail = new Element("Fail", "50em", "30em");
        fail.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        fail.setStyleClass("ui-diagram-fail");

        model.addElement(start);
        model.addElement(trouble);
        model.addElement(giveup);
        model.addElement(succeed);
        model.addElement(fail);

        model.connect(createConnection(start.getEndPoints().get(0), trouble.getEndPoints().get(0), null));
        model.connect(createConnection(trouble.getEndPoints().get(1), giveup.getEndPoints().get(0), "Yes"));
        model.connect(createConnection(giveup.getEndPoints().get(1), start.getEndPoints().get(1), "No"));
        model.connect(createConnection(trouble.getEndPoints().get(2), succeed.getEndPoints().get(0), "No"));
        model.connect(createConnection(giveup.getEndPoints().get(2), fail.getEndPoints().get(0), "Yes"));
    }

    public DiagramModel getModel() {
        return model;
    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }
}
