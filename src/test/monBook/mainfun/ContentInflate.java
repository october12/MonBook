package test.monBook.mainfun;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import test.monBook.Connectnet;

public class ContentInflate extends DefaultHandler {
	// Connectnet connect;
	// public ContentInflate(Connectnet connect){
	// this.connect = connect;
	// }
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	public void endDocument() throws SAXException {
		super.endDocument();
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
	}

}
