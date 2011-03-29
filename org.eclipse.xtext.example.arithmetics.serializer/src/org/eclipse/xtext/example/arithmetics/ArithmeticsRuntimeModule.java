/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics;

import org.eclipse.xtext.example.arithmetics.scoping.CrossRefSerializer1;
import org.eclipse.xtext.example.arithmetics.scoping.CrossRefSerializer2;
import org.eclipse.xtext.example.arithmetics.scoping.NameProvider;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.parsetree.reconstr.ITokenSerializer;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.serializer.impl.Serializer;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;

public class ArithmeticsRuntimeModule extends AbstractArithmeticsRuntimeModule {

	@Override
	public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return NameProvider.class;
	}

	@Override
	public Class<? extends ILinkingService> bindILinkingService() {
		return org.eclipse.xtext.example.arithmetics.scoping.LinkingService.class;
	}

	public Class<? extends ITokenSerializer.ICrossReferenceSerializer> bindICrossReferenceSerializer1() {
		return CrossRefSerializer1.class;
	}

	public Class<? extends ICrossReferenceSerializer> bindICrossReferenceSerializer2() {
		return CrossRefSerializer2.class;
	}

	public Class<? extends ISerializer> bindISerializer() {
		return Serializer.class;
	}

}
