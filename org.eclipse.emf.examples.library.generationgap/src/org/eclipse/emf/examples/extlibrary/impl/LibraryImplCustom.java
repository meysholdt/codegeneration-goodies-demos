package org.eclipse.emf.examples.extlibrary.impl;

import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.examples.extlibrary.impl.LibraryImpl;

public class LibraryImplCustom extends LibraryImpl {

	@Override
	public Writer getWriter(String name) {
		for (Writer writer : getWriters())
			if (name.equals(writer.getName()))
				return writer;
		return null;
	}
}
