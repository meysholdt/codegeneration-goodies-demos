/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.example.arithmetics.services.ArithmeticsGrammarAccess;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example
 */
public class ArithmeticsFormatter extends AbstractDeclarativeFormatter {

	@Override
	protected void configureFormatting(FormattingConfig c) {
		ArithmeticsGrammarAccess ga = (ArithmeticsGrammarAccess) getGrammarAccess();
		for (Pair<Keyword, Keyword> p : ga.findKeywordPairs("(", ")")) {
			c.setNoSpace().after(p.getFirst());
			c.setNoSpace().before(p.getSecond());
		}
		for (Keyword k : ga.findKeywords(";")) {
			c.setNoSpace().before(k);
			c.setLinewrap(2).after(k);
		}
		for (Keyword k : ga.findKeywords(":")) {
			c.setNoSpace().before(k);
			c.setLinewrap().after(k);
		}
		for (Keyword k : ga.findKeywords(",")) {
			c.setNoSpace().before(k);
		}
		c.setLinewrap(2).after(ga.getModuleAccess().getNameAssignment_1());
		c.setNoSpace().after(ga.getDefinitionAccess().getNameAssignment_1());
		c.setNoSpace()
				.before(ga.getPrimaryExpressionAccess()
						.getLeftParenthesisKeyword_3_2());
		c.setIndentation(ga.getDefinitionAccess().getColonKeyword_3(), ga
				.getDefinitionAccess().getSemicolonKeyword_5());
	}
}
