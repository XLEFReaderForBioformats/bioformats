package loci.formats.in.LeicaMicrosystemsMetadata.extract;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import loci.common.DataTools;
import ome.units.quantity.Length;
import ome.units.unit.Unit;

public class Extractor {
  public static Node getChildNodeWithName(Node node, String nodeName) {
    NodeList children = node.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      if (children.item(i).getNodeName().equals(nodeName))
        return children.item(i);
    }
    return null;
  }

  public static Element getChildNodeWithNameAsElement(Node node, String nodeName){
    Node child = getChildNodeWithName(node, nodeName);
    Element element = null;
    try {
      element = (Element)child;
    } catch (Exception e){}

    return element;
  }

  public static Node getNodeWithAttribute(NodeList nodes, String attributeName, String attributeValue) {
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      Node attribute = node.getAttributes().getNamedItem(attributeName);
      if (attribute != null && attribute.getTextContent().equals(attributeValue))
        return node;
    }
    return null;
  }

  public static String getAttributeValue(Node node, String attributeName) {
    Node attribute = node.getAttributes().getNamedItem(attributeName);
    if (attribute != null)
      return attribute.getTextContent();
    else
      return "";
  }

  /**
   * Returns all (grand*n)children nodes with given node name
   * 
   * @param root
   *          root node
   * @param nodeName
   *          name of children that shall be searched
   * @return list of child nodes with given name
   */
  public static NodeList getDescendantNodesWithName(Element root, String nodeName) {
    NodeList nodes = root.getElementsByTagName(nodeName);
    if (nodes.getLength() == 0) {
      NodeList children = root.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        Object child = children.item(i);
        if (child instanceof Element) {
          NodeList childNodes = getDescendantNodesWithName((Element) child, nodeName);
          if (childNodes != null) {
            return childNodes;
          }
        }
      }
      return null;
    } else
      return nodes;
  }

  public static long parseLong(String value) {
    return value == null || value.trim().isEmpty() ? 0 : Long.parseLong(value.trim());
  }

  public static int parseInt(String value) {
    return value == null || value.trim().isEmpty() ? 0 : Integer.parseInt(value.trim());
  }

  public static double parseDouble(String value) {
    return value == null || value.trim().isEmpty() ? 0d : DataTools.parseDouble(value.trim());
  }

  public static Length parseLength(String value, Unit<Length> unit){
    double valueD = parseDouble(value);
    return new Length(valueD, unit);
  }

  public static void printNode(Node node) {
    try {
      // Set up the output transformer
      TransformerFactory transfac = TransformerFactory.newInstance();
      Transformer trans = transfac.newTransformer();
      trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      trans.setOutputProperty(OutputKeys.INDENT, "yes");

      // Print the DOM node

      StringWriter sw = new StringWriter();
      StreamResult result = new StreamResult(sw);
      DOMSource source = new DOMSource(node);
      trans.transform(source, result);
      String xmlString = sw.toString();

      System.out.println(xmlString);
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }
}
