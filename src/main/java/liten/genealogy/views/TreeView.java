/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import liten.genealogy.mainEntities.Genre;
import liten.genealogy.mainEntities.Person;
import liten.genealogy.mainEntities.PersonGenre;
import liten.genealogy.utilitiesDB.ConstantValues;
import liten.genealogy.utilitiesDB.MainDAO;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author bayomock.a
 */
@Named
@ViewScoped
public class TreeView implements Serializable {

    private DefaultDiagramModel model;
    private RootNodeView rootNodeView;
    HashMap<String, Object> endPointsHashMap;

    public TreeView() {
    }

    @PostConstruct
    public void init() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        FlowChartConnector connector = new FlowChartConnector();
        //connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
         connector.setPaintStyle("{stroke:'#404a4e', strokeWidth:3}");
        model.setDefaultConnector(connector);
        endPointsHashMap = new HashMap<String, Object>();

        Person rootPerson = new Person();
        rootPerson.setPersonID("PERS_1");
        rootPerson.setFirstName("Darrell");
        rootPerson.setLastName("Coulibaly");
        rootPerson.setDateOfBirth(new Date(1965, 01, 17));
        rootPerson.setGenre(MainDAO.findGenre("GNR_1"));

        Person rootPartner = new Person();
        rootPartner.setPersonID("PERS_2");
        rootPartner.setFirstName("Anitche");
        rootPartner.setLastName("Gorgette");
        rootPartner.setDateOfBirth(new Date(1966, 04, 24));
        rootPartner.setGenre(MainDAO.findGenre("GNR_2"));

        Person father = new Person();
        father.setPersonID("PERS_3");
        father.setFirstName("Honamonpi");
        father.setLastName("Coulibaly");
        father.setDateOfBirth(new Date(1925, 02, 25));
        father.setGenre(MainDAO.findGenre("GNR_1"));

        Person mother = new Person();
        mother.setPersonID("PERS_4");
        mother.setFirstName("Clotilde");
        mother.setLastName("Hie");
        mother.setDateOfBirth(new Date(1931, 10, 31));
        mother.setGenre(MainDAO.findGenre("GNR_2"));

        Person partnerFather = new Person();
        partnerFather.setPersonID("PERS_5");
        partnerFather.setFirstName("jinwoo");
        partnerFather.setLastName("Sung");
        partnerFather.setDateOfBirth(new Date(1936, 05, 05));
        partnerFather.setGenre(MainDAO.findGenre("GNR_1"));

        Person partnerMother = new Person();
        partnerMother.setPersonID("PERS_6");
        partnerMother.setFirstName("Hae In");
        partnerMother.setLastName("Cha");
        partnerMother.setDateOfBirth(new Date(1945, 8, 15));
        partnerMother.setGenre(MainDAO.findGenre("GNR_2"));

        Person paternalGrandFather = new Person();
        paternalGrandFather.setPersonID("PERS_7");
        paternalGrandFather.setFirstName("aaaaa");
        paternalGrandFather.setLastName("bbbbbb");
        paternalGrandFather.setDateOfBirth(new Date(1870, 03, 20));
        paternalGrandFather.setGenre(MainDAO.findGenre("GNR_1"));

        Person paternalGrandMother = new Person();
        paternalGrandMother.setPersonID("PERS_8");
        paternalGrandMother.setFirstName("cccc");
        paternalGrandMother.setLastName("ddddd");
        paternalGrandMother.setDateOfBirth(new Date(1869, 11, 12));
        paternalGrandMother.setGenre(MainDAO.findGenre("GNR_2"));

        Person maternalGrandFather = new Person();
        maternalGrandFather.setPersonID("PERS_9");
        maternalGrandFather.setFirstName("eeeeee");
        maternalGrandFather.setLastName("fffff");
        maternalGrandFather.setDateOfBirth(new Date(1890, 01, 30));
        maternalGrandFather.setGenre(MainDAO.findGenre("GNR_1"));

        Person maternalGrandMother = new Person();
        maternalGrandMother.setPersonID("PERS_10");
        maternalGrandMother.setFirstName("ggggg");
        maternalGrandMother.setLastName("hhhhh");
        maternalGrandMother.setDateOfBirth(new Date(1901, 9, 12));
        maternalGrandMother.setGenre(MainDAO.findGenre("GNR_2"));

        Person childBoy = new Person();
        childBoy.setPersonID("PERS_11");
        childBoy.setFirstName("iiiii");
        childBoy.setLastName("jjjjjj");
        childBoy.setDateOfBirth(new Date(2015, 10, 15));
        childBoy.setGenre(MainDAO.findGenre("GNR_1"));

        Person childGirl = new Person();
        childGirl.setPersonID("PERS_12");
        childGirl.setFirstName("kkkkk");
        childGirl.setLastName("lllll");
        childGirl.setDateOfBirth(new Date(2000, 07, 19));
        childGirl.setGenre(MainDAO.findGenre("GNR_2"));

        createTree(rootPerson, rootPartner);
        addRootFirstPartner(rootPartner, rootNodeView); 
        addRootParent(father, mother, rootNodeView);
        addRootPartnerParent(rootPartner, partnerFather, partnerMother, rootNodeView);
        addGrandParent(father, rootNodeView.getCurPersonParent(), paternalGrandFather, paternalGrandMother);
        addGrandParent(mother, rootNodeView.getCurPersonParent(), maternalGrandFather, maternalGrandMother);
        addRootChild(childGirl, rootPartner, rootNodeView);
        addRootChild(childBoy, rootPartner, rootNodeView);

        model.addElement(rootNodeView.getCurElement());
        model.addElement(rootNodeView.getPartnerElement());
        model.addElement(rootNodeView.getCurPersonParent().getFatherElement());
        model.addElement(rootNodeView.getCurPersonParent().getMotherElement());
        model.addElement(rootNodeView.getPartnerParent().getFatherElement());
        model.addElement(rootNodeView.getPartnerParent().getMotherElement());
        model.addElement(rootNodeView.getCurPersonParent().getNextFatherParentNode().getFatherElement());
        model.addElement(rootNodeView.getCurPersonParent().getNextFatherParentNode().getMotherElement());
        model.addElement(rootNodeView.getCurPersonParent().getNextMotherParentNode().getFatherElement());
        model.addElement(rootNodeView.getCurPersonParent().getNextMotherParentNode().getMotherElement());

        if (rootPerson.getGenre().getName().equals("Masculin")) {
            model.connect(createTopLeftConnection(rootNodeView.getCurElement(), rootNodeView.getCurPersonParent(), null));
            model.connect(createBottomLeftConnection(rootNodeView.getPartnerElement(), rootNodeView.getPartnerParent(), null));
        } else {
            model.connect(createBottomLeftConnection(rootNodeView.getCurElement(), rootNodeView.getCurPersonParent(), null));
            model.connect(createTopLeftConnection(rootNodeView.getPartnerElement(), rootNodeView.getPartnerParent(), null));
        }

        for (ChildNodeView childNodeView : rootNodeView.getPartnerChildrenList()) {
            model.addElement(childNodeView.getCurElement());
            model.connect(createRightLeftConnection(childNodeView.getCurElement(), rootNodeView, null));

        }
        model.connect(createTopLeftConnection(rootNodeView.getCurPersonParent().getFatherElement(), rootNodeView.getCurPersonParent().getNextFatherParentNode(), null));
        model.connect(createBottomLeftConnection(rootNodeView.getCurPersonParent().getMotherElement(), rootNodeView.getCurPersonParent().getNextMotherParentNode(), null));
       
        
    }

    public void createTree(Person person, Person partner) {
        rootNodeView = new RootNodeView(person, partner);
        initElement(rootNodeView.getCurElement(), person, ConstantValues.INIT_X + "em", ConstantValues.INIT_Y + "em");
        if (person.getGenre().getName().equals("Masculin")) {
            addLeftEndPoint(rootNodeView.getCurElement());
            addTopEndPoint(rootNodeView.getCurElement());
        } else {
            addLeftEndPoint(rootNodeView.getCurElement());
            addBottomEndPoint(rootNodeView.getCurElement());
        }
    }

    public void addRootFirstPartner(Person person, RootNodeView rootNodeView) {
        if (rootNodeView.getPartnerList().isEmpty()) {
            rootNodeView.setSelectedPartner(person);
            //rootNodeView.setPartnerElement(new Element(person));
            rootNodeView.getPartnerList().add(person);
            if (person.getGenre().getName().equals("Masculin")) {
                initElement(rootNodeView.getPartnerElement(), person, ConstantValues.INIT_X + "em", ConstantValues.INIT_Y - ConstantValues.ELT_HEIGHT + "em");
                addTopEndPoint(rootNodeView.getPartnerElement());
                addLeftEndPoint(rootNodeView.getPartnerElement());
            } else {
                initElement(rootNodeView.getPartnerElement(), person, ConstantValues.INIT_X + "em", ConstantValues.INIT_Y + ConstantValues.ELT_HEIGHT + "em");
                addBottomEndPoint(rootNodeView.getPartnerElement());
                addLeftEndPoint(rootNodeView.getPartnerElement());
            }
        }
    }

    public void addRootParent(Person father, Person mother, RootNodeView rootNodeView) {
        if (rootNodeView.getCurPersonParent().getFather().getPersonID() == null
                && rootNodeView.getCurPersonParent().getMother().getPersonID() == null) {
            ParentNodeView parentNodeView = new ParentNodeView(father, mother);
            parentNodeView.setNextFatherParentNode(new ParentNodeView());
            parentNodeView.setNextMotherParentNode(new ParentNodeView());
            Person person = rootNodeView.getCurPerson();
            if (person.getGenre().getName().equals("Masculin")) {
                initElement(parentNodeView.getFatherElement(), father, ConstantValues.INIT_X + ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y - ConstantValues.DELTA_Y + "em");
                initElement(parentNodeView.getMotherElement(), mother, ConstantValues.INIT_X + ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y - ConstantValues.DELTA_Y + ConstantValues.ELT_HEIGHT + "em");
            } else {
                initElement(parentNodeView.getFatherElement(), father, ConstantValues.INIT_X + ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y + ConstantValues.DELTA_Y + "em");
                initElement(parentNodeView.getMotherElement(), mother, ConstantValues.INIT_X + ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y + ConstantValues.DELTA_Y + ConstantValues.ELT_HEIGHT + "em");
            }
            rootNodeView.setCurPersonParent(parentNodeView);
            rootNodeView.getCurPersonParent().setFather(father);
            rootNodeView.getCurPersonParent().setMother(mother);
            addLeftEndPoint(parentNodeView.getFatherElement());
            addTopEndPoint(parentNodeView.getFatherElement());
            addBottomEndPoint(parentNodeView.getMotherElement());
            addLeftEndPoint(parentNodeView.getMotherElement());
        }
    }

    public void addRootPartnerParent(Person partner, Person partnerFather, Person partnerMother, RootNodeView rootNodeView) {
        if (rootNodeView.getPartnerParent().getFather().getPersonID() == null
                && rootNodeView.getPartnerParent().getMother().getPersonID() == null) {
            ParentNodeView parentNodeView = new ParentNodeView(partnerFather, partnerMother);
            parentNodeView.setNextFatherParentNode(new ParentNodeView());
            parentNodeView.setNextMotherParentNode(new ParentNodeView());
            if (partner.getGenre().getName().equals("Masculin")) {
                initElement(parentNodeView.getFatherElement(), partnerFather, ConstantValues.INIT_X + ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y - ConstantValues.DELTA_Y + "em");
                initElement(parentNodeView.getMotherElement(), partnerMother, ConstantValues.INIT_X + ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y - ConstantValues.DELTA_Y + ConstantValues.ELT_HEIGHT + "em");
            } else {
                initElement(parentNodeView.getFatherElement(), partnerFather, ConstantValues.INIT_X + ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y + ConstantValues.DELTA_Y + "em");
                initElement(parentNodeView.getMotherElement(), partnerMother, ConstantValues.INIT_X + ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y + ConstantValues.DELTA_Y + ConstantValues.ELT_HEIGHT + "em");
            }
            rootNodeView.setPartnerParent(parentNodeView);
            rootNodeView.getPartnerParent().setFather(partnerFather);
            rootNodeView.getPartnerParent().setMother(partnerMother);
            addTopEndPoint(parentNodeView.getFatherElement());
            addLeftEndPoint(parentNodeView.getFatherElement());
            addBottomEndPoint(parentNodeView.getMotherElement());
            addLeftEndPoint(parentNodeView.getMotherElement());
        }
    }

    public void addGrandParent(Person parent, ParentNodeView parentNode, Person parentFather, Person parentMother) {
        ParentNodeView nextParentNodeView = new ParentNodeView(parentFather, parentMother);
        if (parent.getGenre().getName().equals("Masculin")) {
            String yVal = parentNode.getFatherElement().getY();
            int yCurVal = Integer.parseInt(yVal.substring(0, yVal.length() - 2));
            initElement(nextParentNodeView.getFatherElement(), parentFather, ConstantValues.INIT_X + 2 * ConstantValues.DELTA_X + "em", yCurVal - ConstantValues.DELTA_Y + "em");
            initElement(nextParentNodeView.getMotherElement(), parentMother, ConstantValues.INIT_X + 2 * ConstantValues.DELTA_X + "em", yCurVal - ConstantValues.DELTA_Y + ConstantValues.ELT_HEIGHT + "em");
            parentNode.setNextFatherParentNode(nextParentNodeView);
        } else {
            String yVal = parentNode.getFatherElement().getY();
            int yCurVal = Integer.parseInt(yVal.substring(0, yVal.length() - 2));
            initElement(nextParentNodeView.getFatherElement(), parentFather, ConstantValues.INIT_X + 2 * ConstantValues.DELTA_X + "em", yCurVal + ConstantValues.DELTA_Y + "em");
            initElement(nextParentNodeView.getMotherElement(), parentMother, ConstantValues.INIT_X + 2 * ConstantValues.DELTA_X + "em", yCurVal + ConstantValues.DELTA_Y + ConstantValues.ELT_HEIGHT + "em");
            parentNode.setNextMotherParentNode(nextParentNodeView);
        }
        addLeftEndPoint(nextParentNodeView.getFatherElement());
        addTopEndPoint(nextParentNodeView.getFatherElement());
        addBottomEndPoint(nextParentNodeView.getMotherElement());
        addLeftEndPoint(nextParentNodeView.getMotherElement());
    }

    public void addRootChild(Person child, Person rootPartner, RootNodeView rootNodeView) {
        ChildNodeView childNodeView = new ChildNodeView(child);
        childNodeView.addParents(rootNodeView.getCurPerson(), rootNodeView.getSelectedPartner());
        int nbrOfChildren = rootNodeView.getPartnerChildrenList().size();
        if (rootNodeView.getChildrenList().isEmpty()) {
            if (child.getGenre().getName().equals("Masculin")) {
                initElement(childNodeView.getCurElement(), child, ConstantValues.INIT_X - ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y + "em");
                rootNodeView.getChildrenList().add(child);
                rootNodeView.getPartnerChildrenList().add(childNodeView);
                addRightEndPoint(childNodeView.getCurElement());
            } else {
                initElement(childNodeView.getCurElement(), child, ConstantValues.INIT_X - ConstantValues.DELTA_X + "em", ConstantValues.INIT_Y + ConstantValues.ELT_HEIGHT + "em");
                rootNodeView.getChildrenList().add(child);
                rootNodeView.getPartnerChildrenList().add(childNodeView);
                addRightEndPoint(childNodeView.getCurElement());
            }
        } else {
            if (nbrOfChildren % 2 == 0) {
                if (child.getGenre().getName().equals("Masculin")) {
                    String yVal = rootNodeView.getPartnerChildrenList().get(nbrOfChildren - 1).getCurElement().getY();
                    initElement(childNodeView.getCurElement(), child, rootNodeView.getPartnerChildrenList().get(nbrOfChildren - 1).getCurElement().getX(), Integer.parseInt(yVal.substring(0, yVal.length() - 2)) - ConstantValues.DELTA_Y + "em");
                    rootNodeView.getChildrenList().add(child);
                    rootNodeView.getPartnerChildrenList().add(childNodeView);
                    addRightEndPoint(childNodeView.getCurElement());
                } else {
                    String yVal = rootNodeView.getPartnerChildrenList().get(nbrOfChildren - 1).getCurElement().getY();
                    initElement(childNodeView.getCurElement(), child, rootNodeView.getPartnerChildrenList().get(nbrOfChildren - 1).getCurElement().getX(), Integer.parseInt(yVal.substring(0, yVal.length() - 2)) - ConstantValues.DELTA_Y + ConstantValues.ELT_HEIGHT + "em");
                    rootNodeView.getChildrenList().add(child);
                    rootNodeView.getPartnerChildrenList().add(childNodeView);
                    addRightEndPoint(childNodeView.getCurElement());
                }
            } else {
                if (child.getGenre().getName().equals("Masculin")) {
                    String yVal = rootNodeView.getPartnerChildrenList().get(nbrOfChildren - 1).getCurElement().getY();
                    initElement(childNodeView.getCurElement(), child, rootNodeView.getPartnerChildrenList().get(nbrOfChildren - 1).getCurElement().getX(), Integer.parseInt(yVal.substring(0, yVal.length() - 2)) + ConstantValues.DELTA_Y + "em");
                    rootNodeView.getChildrenList().add(child);
                    rootNodeView.getPartnerChildrenList().add(childNodeView);
                    addRightEndPoint(childNodeView.getCurElement());
                } else {
                    String yVal = rootNodeView.getPartnerChildrenList().get(nbrOfChildren - 1).getCurElement().getY();
                    initElement(childNodeView.getCurElement(), child, rootNodeView.getPartnerChildrenList().get(nbrOfChildren - 1).getCurElement().getX(), Integer.parseInt(yVal.substring(0, yVal.length() - 2)) + ConstantValues.DELTA_Y + ConstantValues.ELT_HEIGHT + "em");
                    rootNodeView.getChildrenList().add(child);
                    rootNodeView.getPartnerChildrenList().add(childNodeView);
                    addRightEndPoint(childNodeView.getCurElement());
                }
            }
        }
    }

    public void addParentChild(Person child, ParentNodeView parentNode) {
        ChildNodeView childNode = new ChildNodeView(child);
        parentNode.getChildrenList().add(childNode);
    }

    public int retrievePartnerIndex(Person partner, List<Person> partnerList) {
        int index = -1;
        int i = 0;
        for (Person curPartner : partnerList) {
            if (curPartner.getPersonID().equals(partner.getPersonID())) {
                index = i;
                break;
            }
            i++;
        }
        return index;
    }

    public Person checkPersonExistence(String firstName, String lastName, Date dob, String pob,
            String fnFather, String lnFather, String fnMother, String lnMother) {
        return MainDAO.findPersonPerFilter(firstName, lastName, dob, pob, fnFather, lnFather, fnMother, lnMother);
    }

    /* public void addMaleAncestorConnector(ParentNodeView curNodeView, ParentNodeView nextNodeView) {
        curNodeView.getFatherElement().getEndPoints().get(0).getAnchor()
    }*/
    public DiagramModel getModel() {
        return model;
    }

    public RootNodeView getRootNodeView() {
        return rootNodeView;
    }

    public HashMap<String, Object> getEndPointsHashMap() {
        return endPointsHashMap;
    }

    public int determineHeight(ParentNodeView parentNodeView, int height) {
        if (parentNodeView.getNextFatherParentNode() != null && parentNodeView.getNextFatherParentNode().getFather() != null) {
            parentNodeView = parentNodeView.getNextFatherParentNode();
            height = determineHeight(parentNodeView, ++height);
        } else if (parentNodeView.getNextMotherParentNode() != null && parentNodeView.getNextMotherParentNode().getMother() != null) {
            parentNodeView = parentNodeView.getNextMotherParentNode();
            height = determineHeight(parentNodeView, ++height);
        }
        return height;
    }

    public List<ParentNodeView> getAncestorsPerLevel(ParentNodeView parentNodeView, List<ParentNodeView> foundList, int height, int level) {
        if (parentNodeView.getNextFatherParentNode() != null && parentNodeView.getNextFatherParentNode().getFather() != null && height < level) {
            parentNodeView = parentNodeView.getNextFatherParentNode();
            ++height;
            if (height == level) {
                foundList.add(parentNodeView);
            }
            foundList = getAncestorsPerLevel(parentNodeView, foundList, height, level);
        } else if (parentNodeView.getNextMotherParentNode() != null && parentNodeView.getNextMotherParentNode().getMother() != null && height < level) {
            parentNodeView = parentNodeView.getNextMotherParentNode();
            if (height == level) {
                foundList.add(parentNodeView);
            }
            foundList = getAncestorsPerLevel(parentNodeView, foundList, height, level);
        }
        return foundList;
    }

    public void initElement(Element curElement, Person curPerson, String startPosX, String startPosY) {
        curElement.setId(curPerson.getPersonID());
        curElement.setTitle(curPerson.getLastName() + " " + curPerson.getFirstName());
        curElement.setX(startPosX);
        curElement.setY(startPosY);
    }

    public void addLeftEndPoint(Element curElement) {
        EndPoint leftEndPointAnchor = new BlankEndPoint(EndPointAnchor.LEFT);
        endPointsHashMap.put(curElement.getId() + "_" + "LEFT", leftEndPointAnchor);
        curElement.addEndPoint(leftEndPointAnchor);
        curElement.setStyleClass("ui-diagram-left");
    }

    public void addRightEndPoint(Element curElement) {
        EndPoint rightEndPointAnchor = new BlankEndPoint(EndPointAnchor.RIGHT);
        endPointsHashMap.put(curElement.getId() + "_" + "RIGHT", rightEndPointAnchor);
        curElement.addEndPoint(rightEndPointAnchor);
        curElement.setStyleClass("ui-diagram-right");
    }

    public void addBottomEndPoint(Element curElement) {
        EndPoint bottomEndPointAnchor = new BlankEndPoint(EndPointAnchor.BOTTOM);
        endPointsHashMap.put(curElement.getId() + "_" + "BOTTOM", bottomEndPointAnchor);
        curElement.addEndPoint(bottomEndPointAnchor);
        curElement.setStyleClass("ui-diagram-bottom");
    }

    public void addTopEndPoint(Element curElement) {
        EndPoint topEndPointAnchor = new BlankEndPoint(EndPointAnchor.TOP);
        endPointsHashMap.put(curElement.getId() + "_" + "TOP", topEndPointAnchor);
        curElement.addEndPoint(topEndPointAnchor);
        curElement.setStyleClass("ui-diagram-top");
    }

    private Connection createTopLeftConnection(Element from, ParentNodeView to, String label) {
        Connection conn = null;
        EndPoint fromTop = (EndPoint) endPointsHashMap.get(from.getId() + "_TOP");
        if (to.getFatherElement() != null && (to.getFatherElement().getData()) != null) {
            EndPoint toLeft = (EndPoint) endPointsHashMap.get(to.getFatherElement().getId() + "_LEFT");
            conn = new Connection(fromTop, toLeft);
            conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        } else {
            EndPoint toLeft = (EndPoint) endPointsHashMap.get(to.getMotherElement().getId() + "_LEFT");
            conn = new Connection(fromTop, toLeft);
            conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        }
        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
        return conn;
    }

    private Connection createBottomLeftConnection(Element from, ParentNodeView to, String label) {
        Connection conn = null;
        EndPoint fromBottom = (EndPoint) endPointsHashMap.get(from.getId() + "_BOTTOM");
        if (to.getFatherElement() != null && (to.getFatherElement().getData()) != null) {
            EndPoint toLeft = (EndPoint) endPointsHashMap.get(to.getFatherElement().getId() + "_LEFT");
            conn = new Connection(fromBottom, toLeft);
            conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        } else {
            EndPoint toLeft = (EndPoint) endPointsHashMap.get(to.getMotherElement().getId() + "_LEFT");
            conn = new Connection(fromBottom, toLeft);
            conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        }
        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
        return conn;
    }

    private Connection createRightLeftConnection(Element from, RootNodeView to, String label) {
        Connection conn = null;
        EndPoint fromRight = (EndPoint) endPointsHashMap.get(from.getId() + "_RIGHT");
        if (to.getCurElement() != null && (to.getCurElement().getData()) != null) {
            EndPoint toLeft = (EndPoint) endPointsHashMap.get(to.getCurElement().getId() + "_LEFT");
            conn = new Connection(fromRight, toLeft);
            conn.getOverlays().add(new ArrowOverlay(20, 20, 10, 10));
        } else {
            EndPoint toLeft = (EndPoint) endPointsHashMap.get(to.getPartnerElement().getId() + "_LEFT");
            conn = new Connection(fromRight, toLeft);
            conn.getOverlays().add(new ArrowOverlay(20, 20, 10, 10));
        }
        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
        return conn;
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
