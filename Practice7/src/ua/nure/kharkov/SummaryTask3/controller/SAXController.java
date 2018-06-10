package ua.nure.kharkov.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.kharkov.SummaryTask3.constants.Constants;
import ua.nure.kharkov.SummaryTask3.constants.XML;
import ua.nure.kharkov.SummaryTask3.entity.Author;
import ua.nure.kharkov.SummaryTask3.entity.OldCard;
import ua.nure.kharkov.SummaryTask3.entity.OldCards;

public class SAXController extends DefaultHandler {
	private String xmlFileName;

	// current element name holder
	private String currentElement;

	// main container
	private OldCards oldcards;

	private OldCard oldcard;

	private Author author;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {

		// obtain sax parser factory
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// XML document contains namespaces
		factory.setNamespaceAware(true);

		// set validation
		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	// ///////////////////////////////////////////////////////////
	// ERROR HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		// if XML document not valid just throw exception
		throw e;
	};

	public OldCards getOldCards() {
		return oldcards;
	}

	// ///////////////////////////////////////////////////////////
	// CONTENT HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		currentElement = localName;

		if (XML.OLDCARDS.equalsTo(currentElement)) {
			oldcards = new OldCards();
			return;
		}

		if (XML.OLDCARD.equalsTo(currentElement)) {
			oldcard = new OldCard();
			return;
		}

		if (qName.equals("Type")) {
			oldcard.setSend(Boolean.parseBoolean(attributes.getValue("send")));
		}


            
		if (XML.AUTHOR.equalsTo(currentElement)) {
			author = new Author();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		String elementText = new String(ch, start, length).trim();

		// return if content is empty
		if (elementText.isEmpty()) {
			return;
		}

		if (XML.THEMA.equalsTo(currentElement)) {
			oldcard.setThema(elementText);
			return;
		}

		if (XML.TYPE.equalsTo(currentElement)) {
			oldcard.setType(elementText);
			return;
		}
		
		if (XML.COUNTRY.equalsTo(currentElement)) {
			oldcard.setCountry(elementText);
			return;
		}
		
		if (XML.YEAR.equalsTo(currentElement)) {
			oldcard.setYear(Integer.parseInt(elementText));
			return;
		}
		
		if (XML.AUTHOR.equalsTo(currentElement)) {
			author.setName(elementText);
			return;
		}
		
		if (XML.VALUABLE.equalsTo(currentElement)) {
			oldcard.setValuable(elementText);
			return;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (XML.OLDCARD.equalsTo(localName)) {
			// just add question to container
			oldcards.getOldCards().add(oldcard);
			return;
		}

		if (XML.AUTHOR.equalsTo(localName)) {
			// just add answer to container
			oldcard.getAuthors().add(author);
			return;
		}
	}

}
