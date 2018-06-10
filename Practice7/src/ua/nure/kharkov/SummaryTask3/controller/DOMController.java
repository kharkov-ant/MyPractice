package ua.nure.kharkov.SummaryTask3.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.kharkov.SummaryTask3.constants.Constants;
import ua.nure.kharkov.SummaryTask3.constants.XML;
import ua.nure.kharkov.SummaryTask3.entity.Author;
import ua.nure.kharkov.SummaryTask3.entity.OldCard;
import ua.nure.kharkov.SummaryTask3.entity.OldCards;

public class DOMController {

	private String xmlFileName;

	private OldCards oldcards;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public OldCards getOldCards() {
		return oldcards;
	}

	public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {

		// obtain DOM parser
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// set properties for Factory

		// XML document contains namespaces
		dbf.setNamespaceAware(true);

		// make parser validating
		if (validate) {
			// turn validation on
			dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

			// turn on xsd validation
			dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder db = dbf.newDocumentBuilder();

		// set error handler
		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				// throw exception if XML document is NOT valid
				throw e;
			}
		});

		Document document = db.parse(xmlFileName);

		Element root = document.getDocumentElement();

		oldcards = new OldCards();

		NodeList oldcardNodes = root.getElementsByTagName(XML.OLDCARD.value());

		for (int j = 0; j < oldcardNodes.getLength(); j++) {
			OldCard oldcard = getOldCard(oldcardNodes.item(j));
			oldcards.getOldCards().add(oldcard);
		}
	}

	private OldCard getOldCard(Node oNode) {
		OldCard oldcard = new OldCard();
		Element oElement = (Element) oNode;

		// process oldcard thema
		Node otNode = oElement.getElementsByTagName(XML.THEMA.value()).item(0);
		oldcard.setThema(otNode.getTextContent());

		// process oldcard type
		Node otypeNode = oElement.getElementsByTagName(XML.TYPE.value()).item(0);
		oldcard.setType(otypeNode.getTextContent());

		Element otElement = (Element) otypeNode;
		String send = otElement.getAttribute(XML.SEND.value());
		oldcard.setSend(Boolean.valueOf(send));

		// process oldcard country
		Node ocNode = oElement.getElementsByTagName(XML.COUNTRY.value()).item(0);
		oldcard.setCountry(ocNode.getTextContent());

		Node yearNode = oElement.getElementsByTagName(XML.YEAR.value()).item(0);
		oldcard.setYear(Integer.valueOf(yearNode.getTextContent()));

		// process authors
		NodeList aNodeList = oElement.getElementsByTagName(XML.AUTHOR.value());
		for (int j = 0; j < aNodeList.getLength(); j++) {
			Author author = getAuthor(aNodeList.item(j));
			oldcard.getAuthors().add(author);
		}

		// process oldcard valuabe
		Node ovNode = oElement.getElementsByTagName(XML.VALUABLE.value()).item(0);
		oldcard.setValuable(ovNode.getTextContent());

		return oldcard;
	}

	private Author getAuthor(Node aNode) {
		Author author = new Author();
		Element aElement = (Element) aNode;
		String content = aElement.getTextContent();
		author.setName(content);
		return author;
	}

	// //////////////////////////////////////////////////////
	// Static util methods
	// //////////////////////////////////////////////////////

	public static Document getDocument(OldCards oldcards) throws ParserConfigurationException {

		// obtain DOM parser
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// set properties for Factory

		// XML document contains namespaces
		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		// create root element
		Element oElement = document.createElement(XML.OLDCARDS.value());

		// add root element
		document.appendChild(oElement);

		// add questions elements
		for (OldCard oldcard : oldcards.getOldCards()) {

			Element ooElement = document.createElement(XML.OLDCARD.value());
			oElement.appendChild(ooElement);

			Element thElement = document.createElement(XML.THEMA.value());
			thElement.setTextContent(oldcard.getThema());
			ooElement.appendChild(thElement);

			Element tyElement = document.createElement(XML.TYPE.value());
			tyElement.setTextContent(oldcard.getType());
			ooElement.appendChild(tyElement);
			
			if (oldcard.isSend()) {
				tyElement.setAttribute(XML.SEND.value(), "true");
			}

			Element coElement = document.createElement(XML.COUNTRY.value());
			coElement.setTextContent(oldcard.getCountry());
			ooElement.appendChild(coElement);
			
			Element yearElement = document.createElement(XML.YEAR.value());
			yearElement.setTextContent(String.valueOf(oldcard.getYear()));
			ooElement.appendChild(yearElement);
			
			for (Author author : oldcard.getAuthors()) {
				Element aElement = document.createElement(XML.AUTHOR.value());
				aElement.setTextContent(author.getName());
				
				ooElement.appendChild(aElement);
			}
			
			Element valElement = document.createElement(XML.VALUABLE.value());
			valElement.setTextContent(oldcard.getValuable());
			ooElement.appendChild(valElement);
		}

		return document;
	}

	public static void saveToXML(OldCards oldcards, String xmlFileName)
			throws ParserConfigurationException, TransformerException {
		// Test -> DOM -> XML
		saveToXML(getDocument(oldcards), xmlFileName);
	}

	public static void saveToXML(Document document, String xmlFileName) throws TransformerException {

		StreamResult result = new StreamResult(new File(xmlFileName));

		// set up transformation
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");

		// run transformation
		t.transform(new DOMSource(document), result);
	}

}
