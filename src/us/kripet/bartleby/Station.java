package us.kripet.bartleby;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Station extends BartAPI {
  private final static String ACTION = "stn";
  private final static String LIST_CMD = "stns";

  public String name;
  public String abbreviation;

  public Station() {
  }

  public static List<Station> all() {
    ArrayList<Station> stations = new ArrayList<Station>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document dom = builder.parse(api(ACTION, LIST_CMD).openStream());
      Element root = dom.getDocumentElement();
      NodeList stationNodes = root.getElementsByTagName("station");
      for (int i = 0; i < stationNodes.getLength(); i++) {
        Station station = new Station();
        Node stationNode = stationNodes.item(i);
        NodeList stationInfo = stationNode.getChildNodes();
        for (int j = 0; j < stationInfo.getLength(); j++) {
          Node property = stationInfo.item(j);
          String pName = property.getNodeName().toLowerCase();
          String pValue = property.getFirstChild().getNodeValue();
          if (pName.equals("name")) station.name = pValue;
          else if (pName.equals("abbr")) station.abbreviation = pValue;
        }
        stations.add(station);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return stations;
  }

  public static String[] allNames() {
    List<Station> stations = all();
    String[] names = new String[stations.size()];
    for (int i = 0; i < names.length; i++) {
      names[i] = stations.get(i).name;
    }
    return names;
  }
}
