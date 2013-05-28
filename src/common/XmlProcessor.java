package common;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XmlProcessor {
	
	private XPath xpath;
	private Document doc;
	
	public XmlProcessor(String data) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		this.doc = builder.parse(data);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		this.xpath = xPathfactory.newXPath();
	}
	
	public String getValue(String xpathString) throws Exception{
		XPathExpression expr = xpath.compile("xpathString"); 
		return (String)expr.evaluate(doc, XPathConstants.STRING); 
	}
	
}
