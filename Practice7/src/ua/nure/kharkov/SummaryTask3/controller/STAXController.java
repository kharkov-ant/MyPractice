package ua.nure.kharkov.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

import ua.nure.kharkov.SummaryTask3.constants.XML;
import ua.nure.kharkov.SummaryTask3.entity.Author;
import ua.nure.kharkov.SummaryTask3.entity.OldCard;
import ua.nure.kharkov.SummaryTask3.entity.OldCards;



public class STAXController {
	private String xmlFileName;

	// main container
	private OldCards oldcards;

	public OldCards getOldCards() {
		return oldcards;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse() throws ParserConfigurationException, SAXException,
			IOException, XMLStreamException {

		OldCard oldcard = null;
		Author author = null;
		// current element name holder
		String currentElement = null;
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(
				new StreamSource(xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			// handler for start tags
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();
				
				if (XML.OLDCARDS.equalsTo(currentElement)) {
					oldcards = new OldCards();
					continue;
				}
				
				if (XML.OLDCARD.equalsTo(currentElement)) {
					oldcard = new OldCard();
					continue;
				}

				if (XML.AUTHOR.equalsTo(currentElement)) {
					author = new Author();
					continue;
				}
				
			}

			// handler for contents
			if (event.isCharacters()) {
				Characters characters = event.asCharacters();
		
				if (XML.THEMA.equalsTo(currentElement)) {
					oldcard.setThema(characters.getData());
					continue;
				}

				if (XML.TYPE.equalsTo(currentElement)) {
					oldcard.setType(characters.getData());
					continue;
				}
				
				if (XML.COUNTRY.equalsTo(currentElement)) {
					oldcard.setCountry(characters.getData());
					continue;
				}
				
				if (XML.YEAR.equalsTo(currentElement)) {
					oldcard.setYear(Integer.parseInt(characters.getData()));
					continue;
				}
				
				if (XML.AUTHOR.equalsTo(currentElement)) {
					author.setName(characters.getData());
					continue;
				}
				
				if (XML.VALUABLE.equalsTo(currentElement)) {
					oldcard.setValuable(characters.getData());
					continue;
				}
			}

			// handler for end tags
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.OLDCARD.equalsTo(localName)) {
					oldcards.getOldCards().add(oldcard);
					continue;
				}

				if (XML.AUTHOR.equalsTo(localName)) {
					oldcard.getAuthors().add(author);
				}
			}
		}
		reader.close();
	}

}
