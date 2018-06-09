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

	// main container
	private OldCards oldcards;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public OldCards getOldCards() {
		return oldcards;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema.
	 */
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

		// parse XML document
		Document document = db.parse(xmlFileName);

		// get root element
		Element root = document.getDocumentElement();

		// create container
		oldcards = new OldCards();

		// obtain questions nodes
		NodeList oldcardNodes = root.getElementsByTagName(XML.OLDCARD.value());

		// process questions nodes
		for (int j = 0; j < oldcardNodes.getLength(); j++) {
			OldCard oldcard = getOldCard(oldcardNodes.item(j));
			// add question to container
			oldcards.getOldCards().add(oldcard);
		}
	}

	private OldCard getOldCard(Node oNode) {
		OldCard oldcard = new OldCard();
		Element oElement = (Element) oNode;

		// process oldcard thema
		Node otNode = oElement.getElementsByTagName(XML.THEMA.value()).item(0);
		oldcard.setThema(otNode.getTextContent());

		// process oldcard tyma
		Node otypeNode = oElement.getElementsByTagName(XML.TYPE.value()).item(0);
		oldcard.setType(otypeNode.getTextContent());

		// process oldcard country
		Node ocNode = oElement.getElementsByTagName(XML.COUNTRY.value()).item(0);
		oldcard.setCountry(ocNode.getTextContent());

		// process oldcard valuabe
		Node ovNode = oElement.getElementsByTagName(XML.VALUABLE.value()).item(0);
		oldcard.setValuable(ovNode.getTextContent());

		String send = oElement.getAttribute(XML.SEND.value());
		oldcard.setSend(Boolean.valueOf(send));

		String year = oElement.getAttribute(XML.YEAR.value());
		oldcard.setYear(Integer.parseInt(year));

		// process authors
		NodeList aNodeList = oElement.getElementsByTagName(XML.AUTHOR.value());
		for (int j = 0; j < aNodeList.getLength(); j++) {
			Author author = getAuthor(aNodeList.item(j));

			// add answer
			oldcard.getAuthors().add(author);
		}

		return oldcard;
	}

	private Author getAuthor(Node aNode) {
		Author author = new Author();
		Element aElement = (Element) aNode;

		// process author name
		Node auNode = aElement.getElementsByTagName(XML.AUTHOR.value()).item(0);
		author.setName(auNode.getTextContent());

		return author;
	}

	// //////////////////////////////////////////////////////
	// Static util methods
	// //////////////////////////////////////////////////////

	/**
	 * Creates and returns DOM of the Test container.
	 * 
	 * @param test
	 *            Test object.
	 * @throws ParserConfigurationException
	 */
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

//			// process oldcard thema
//			Node otNode = oElement.getElementsByTagName(XML.THEMA.value()).item(0);
//			oldcard.setThema(otNode.getTextContent());
//
//			// process oldcard tyma
//			Node otypeNode = oElement.getElementsByTagName(XML.TYPE.value()).item(0);
//			oldcard.setType(otypeNode.getTextContent());
//
//			// process oldcard country
//			Node ocNode = oElement.getElementsByTagName(XML.COUNTRY.value()).item(0);
//			oldcard.setCountry(ocNode.getTextContent());
//
//			// process oldcard valuabe
//			Node ovNode = oElement.getElementsByTagName(XML.VALUABLE.value()).item(0);
//			oldcard.setValuable(ovNode.getTextContent());
//
//			String send = oElement.getAttribute(XML.SEND.value());
//			oldcard.setSend(Boolean.valueOf(send));
//
//			String year = oElement.getAttribute(XML.YEAR.value());
//			oldcard.setYear(Integer.parseInt(year));
			
			// add question text
			Element thElement = document.createElement(XML.THEMA.value());
			thElement.setTextContent(oldcard.getThema());
			oElement.appendChild(thElement);

			Element tyElement = document.createElement(XML.TYPE.value());
			tyElement.setTextContent(oldcard.getType());
			oElement.appendChild(tyElement);
			
			Element coElement = document.createElement(XML.COUNTRY.value());
			coElement.setTextContent(oldcard.getCountry());
			oElement.appendChild(coElement);
			
			// add answers
//			for (Answer answer : question.getAnswers()) {
//				Element aElement = document.createElement(XML.ANSWER.value());
//				aElement.setTextContent(answer.getContent());
//
//				// set attribute
//				if (answer.isCorrect()) {
//					aElement.setAttribute(XML.CORRECT.value(), "true");
//				}
//				qElement.appendChild(aElement);
//			}
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

	public static void main(String[] args) throws Exception {

		// try to parse NOT valid XML document with validation on (failed)
		DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
		try {
			// parse with validation (failed)
			domContr.parse(true);
		} catch (SAXException ex) {
			System.err.println("====================================");
			System.err.println("XML not valid");
			System.err.println("Test object --> " + domContr.getOldCards());
			System.err.println("====================================");
		}

		// try to parse NOT valid XML document with validation off (success)
		domContr.parse(false);

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + domContr.getOldCards());
		System.out.println("====================================");

		// save test in XML file
		OldCards oldcards = domContr.getOldCards();
		DOMController.saveToXML(oldcards, Constants.INVALID_XML_FILE + ".dom-result.xml");
	}
}
