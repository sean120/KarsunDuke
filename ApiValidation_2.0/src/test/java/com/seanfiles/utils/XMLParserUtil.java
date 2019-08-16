package com.seanfiles.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XMLParserUtil {

	Document doc=null;
	XPath xpath=null;
	
	public XMLParserUtil(String xmlString) {
		try {
			InputSource is = new InputSource(new StringReader(xmlString));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			dbFactory.setValidating(false);
			dbFactory.setNamespaceAware(true);
			dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
			dbFactory.setFeature("http://xml.org/sax/features/validation", false);
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			xpath = xPathfactory.newXPath();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getElementValueByTagName(String tagName) {
		return doc.getElementsByTagName(tagName).item(0).getTextContent().trim();
	}
	
	public String getElementValueByXPath(String elementXPath) {
		return getElementValueByXPath(elementXPath, 0);
	}
	
	public String getElementValueByXPath(String elementXPath, int index) {
		String elementValue=null;
		try {
			XPathExpression expr = xpath.compile(elementXPath);
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			elementValue=nodes.item(index).getNodeValue();
		} catch (Exception e) {
		}
		return elementValue;
	}
	
	public String getElementValueByParentXPath(String parentXPath, int index, String elementName) {
		String elementValue=null;
		try {
			XPathExpression expr = xpath.compile(parentXPath);
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			Node parent=nodes.item(index);
			NodeList childNodes=parent.getChildNodes();
			for(int i=0;i<childNodes.getLength(); i++) {
				if(childNodes.item(i).getNodeName().equalsIgnoreCase(elementName)) {
					elementValue =childNodes.item(i).getTextContent();
					return elementValue;
				}
			}
		} catch (Exception e) {
		}
		return elementValue;
	}
	
	public String getDescendantElementValueByTagName(String tagName, String ancestorTagName) {
		Element ancestor=(Element) doc.getElementsByTagName(ancestorTagName).item(0);
		return ancestor.getElementsByTagName(tagName).item(0).getTextContent().trim();
	}

	public String getElementAttributeValue(String elementName, String attributeName) {
		Element elment=(Element) doc.getElementsByTagName(elementName).item(0);
		return elment.getAttribute(attributeName).trim();
	}
	
	public void writeContentToFile(String filePath) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Node getElementByXPath(String elementXPath) {
		return getElementByXPath(elementXPath, 0);
	}
	
	public Node getElementByXPath(String elementXPath, int index) {
		Node element=null;
		try {
			XPathExpression expr = xpath.compile(elementXPath);
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			element=nodes.item(index);
		} catch (Exception e) {
		}
		return element;
	}
	
	public void modifyElementValue(String xPath, String newValue) {
		Node element=getElementByXPath(xPath);
		element.setTextContent(newValue);
	}
	
	public static String prettifyXML(String xmlStr) {
		String prettyXML=xmlStr;
		try {
			Source xmlInput = new StreamSource(new StringReader(xmlStr));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", 2);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        prettyXML=xmlOutput.getWriter().toString();
		}
		catch(Exception e) {
			
		}
		return prettyXML;
	}
	
}