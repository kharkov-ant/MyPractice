package ua.nure.kharkov.SummaryTask3;


import ua.nure.kharkov.SummaryTask3.controller.DOMController;
import ua.nure.kharkov.SummaryTask3.entity.OldCards;
import ua.nure.kharkov.SummaryTask3.util.Sorter;

public class Main {
	public static void usage() {
		System.out.println("Usage:\njava -jar ST3ExampleSimple.jar xmlFileName");
		System.out.println("java ua.nure.your_last_name.SummaryTask3.Main xmlFileName");
	}

	public static void main(String[] args) {
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
		Sorter.sortQuestionsByQuestionText(test);
		
		// save
		String outputXmlFile = "output.dom.xml";
		DOMController.saveToXML(test, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
	}
}
