package org.eclipse.xtext.example.arithmetics.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.parsetree.reconstr.impl.CrossReferenceSerializer;

public class CrossRefSerializer1 extends CrossReferenceSerializer {
	@Override
	protected String getUnconvertedLinkText(EObject object,
			EReference reference, EObject context) {
		String text = super.getUnconvertedLinkText(object, reference, context);
		if (text != null) {
			int index = text.indexOf("(");
			if (index >= 0)
				text = text.substring(0, index);
		}
		return text;
	}
}
