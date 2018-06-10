package ua.nure.kharkov.SummaryTask3;

import ua.nure.kharkov.SummaryTask3.controller.DOMController;
import ua.nure.kharkov.SummaryTask3.controller.SAXController;
import ua.nure.kharkov.SummaryTask3.controller.STAXController;
import ua.nure.kharkov.SummaryTask3.entity.OldCards;
import ua.nure.kharkov.SummaryTask3.util.Sorter;


public class Main {
	public static void usage() {
		System.out.println("Usage:\njava -jar ST3ExampleSimple.jar xmlFileName");
		System.out.println("java ua.nure.your_last_name.SummaryTask3.Main xmlFileName");
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			usage();
			return;
		}

		String xmlFileName = args[0];
		System.out.println("Input ==> " + xmlFileName);

		////////////////////////////////////////////////////////
		// DOM
		////////////////////////////////////////////////////////

		// get
		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);
		OldCards oldcards = domController.getOldCards();

		// sort (case 1)
		Sorter.sortOldCardByCountry(oldcards);

		// save
		String outputXmlFile = "output.dom.xml";
		DOMController.saveToXML(oldcards, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);

		////////////////////////////////////////////////////////
		// SAX
		////////////////////////////////////////////////////////

		// get
		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse(true);
		oldcards = saxController.getOldCards();

		// sort (case 2)
		Sorter.sortOldCardBySend(oldcards);

		// save
		outputXmlFile = "output.sax.xml";

		// other way:
		DOMController.saveToXML(oldcards, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
		////////////////////////////////////////////////////////
		// StAX
		////////////////////////////////////////////////////////

		// get
		STAXController staxController = new STAXController(xmlFileName);
		staxController.parse();
		oldcards = staxController.getOldCards();

		// sort (case 3)
		Sorter.sortOldCardByYear(oldcards);

		// save
		outputXmlFile = "output.stax.xml";
		DOMController.saveToXML(oldcards, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
	}
}
