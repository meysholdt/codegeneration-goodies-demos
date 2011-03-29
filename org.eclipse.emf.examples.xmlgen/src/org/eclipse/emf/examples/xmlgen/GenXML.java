package org.eclipse.emf.examples.xmlgen;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.examples.xmlgen.Rss20.DocumentRoot;
import org.eclipse.emf.examples.xmlgen.Rss20.Rss20Factory;
import org.eclipse.emf.examples.xmlgen.Rss20.RssChannel;
import org.eclipse.emf.examples.xmlgen.Rss20.RssType;
import org.eclipse.emf.examples.xmlgen.Rss20.util.Rss20ResourceFactoryImpl;

public class GenXML {

	public static void main(String[] args) throws IOException {

		// register file extension
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"rss", new Rss20ResourceFactoryImpl());

		// create model instance
		DocumentRoot root = Rss20Factory.eINSTANCE.createDocumentRoot();
		RssType rss = Rss20Factory.eINSTANCE.createRssType();
		RssChannel channel = Rss20Factory.eINSTANCE.createRssChannel();
		root.setRss(rss);
		rss.setChannel(channel);
		channel.getTitle().add("Planet Eclipse");
		channel.getLink().add("http://planeteclipse.org/planet/");

		// save file to stdout
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource res = resourceSet.createResource(URI.createURI("test.rss"));
		res.getContents().add(root);
		res.save(System.out, null);
	}

}
