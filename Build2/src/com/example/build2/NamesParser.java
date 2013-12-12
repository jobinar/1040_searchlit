package com.example.build2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NamesParser {
	
	Item objItem;
	List<Item> listArray;

	//public List<Item> getData(String url) {
	public List<Item> getData(InputStream in) {
		try {

			listArray = new ArrayList<Item>();//
			
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(in);
			
			//Document doc = db.parse(responce.getEntity().getContent());
			
			
			//URL.openStream returns input stream reader

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					objItem = new Item();

					objItem.setId(getTagValue("id", eElement));
					objItem.setTitle(getTagValue("title", eElement));
					objItem.setDesc(getTagValue("desc", eElement));
					objItem.setPubdate(getTagValue("pubDate", eElement));
					objItem.setLink(getTagValue("link", eElement));
					objItem.setLink1(getTagValue("link1", eElement));
					objItem.setIsbn(getTagValue("isbn", eElement));
					
					
					
					listArray.add(objItem);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listArray;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();

	}
}
