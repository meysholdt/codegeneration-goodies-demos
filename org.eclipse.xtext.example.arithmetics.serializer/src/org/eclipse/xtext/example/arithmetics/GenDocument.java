package org.eclipse.xtext.example.arithmetics;

import java.io.IOException;
import java.math.BigDecimal;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.example.arithmetics.arithmetics.AbstractDefinition;
import org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsFactory;
import org.eclipse.xtext.example.arithmetics.arithmetics.DeclaredParameter;
import org.eclipse.xtext.example.arithmetics.arithmetics.Definition;
import org.eclipse.xtext.example.arithmetics.arithmetics.Evaluation;
import org.eclipse.xtext.example.arithmetics.arithmetics.Expression;
import org.eclipse.xtext.example.arithmetics.arithmetics.FunctionCall;
import org.eclipse.xtext.example.arithmetics.arithmetics.Minus;
import org.eclipse.xtext.example.arithmetics.arithmetics.Module;
import org.eclipse.xtext.example.arithmetics.arithmetics.Multi;
import org.eclipse.xtext.example.arithmetics.arithmetics.NumberLiteral;
import org.eclipse.xtext.example.arithmetics.arithmetics.ParameterRef;
import org.eclipse.xtext.example.arithmetics.arithmetics.Plus;

import com.google.common.collect.Lists;

public class GenDocument {

	public static void main(String[] args) throws IOException {
		
		// set up arithmetics-language
		ArithmeticsStandaloneSetup.doSetup();

		// create model
		Module module = newModule("HelloWorld");
		Definition binom = newDefinition("binom3");
		DeclaredParameter a = newParameter("a");
		DeclaredParameter b = newParameter("b");
		binom.getArgs().add(a);
		binom.getArgs().add(b);
		Expression l = newPlus(newParamRef(a), newParamRef(b));
		Expression r = newMinus(newParamRef(a), newParamRef(b));
		binom.setExpr(newMult(l, r));
		module.getStatements().add(binom);
		FunctionCall fibonacciCall = newCall(binom, newNumber(4), newNumber(5));
		Evaluation eval = newEvaluation(newPlus(fibonacciCall, newNumber(42)));
		module.getStatements().add(eval);

		// serialize model
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource res = resourceSet.createResource(URI.createURI("test.calc"));
		res.getContents().add(module);
		res.save(System.out, null);
	}

	private static FunctionCall newCall(AbstractDefinition def,
			Expression... param) {
		FunctionCall call = ArithmeticsFactory.eINSTANCE.createFunctionCall();
		call.setFunc(def);
		call.getArgs().addAll(Lists.newArrayList(param));
		return call;
	}

	private static Definition newDefinition(String name) {
		Definition def = ArithmeticsFactory.eINSTANCE.createDefinition();
		def.setName(name);
		return def;
	}

	private static Evaluation newEvaluation(Expression expr) {
		Evaluation ev = ArithmeticsFactory.eINSTANCE.createEvaluation();
		ev.setExpression(expr);
		return ev;
	}

	private static Minus newMinus(Expression left, Expression right) {
		Minus minus = ArithmeticsFactory.eINSTANCE.createMinus();
		minus.setLeft(left);
		minus.setRight(right);
		return minus;
	}

	private static Module newModule(String name) {
		Module mod = ArithmeticsFactory.eINSTANCE.createModule();
		mod.setName(name);
		return mod;
	}

	private static Multi newMult(Expression left, Expression right) {
		Multi multi = ArithmeticsFactory.eINSTANCE.createMulti();
		multi.setLeft(left);
		multi.setRight(right);
		return multi;
	}

	private static NumberLiteral newNumber(int number) {
		NumberLiteral nl = ArithmeticsFactory.eINSTANCE.createNumberLiteral();
		nl.setValue(new BigDecimal(number));
		return nl;
	}

	private static DeclaredParameter newParameter(String name) {
		DeclaredParameter param = ArithmeticsFactory.eINSTANCE
				.createDeclaredParameter();
		param.setName(name);
		return param;
	}

	private static ParameterRef newParamRef(DeclaredParameter param) {
		ParameterRef ref = ArithmeticsFactory.eINSTANCE.createParameterRef();
		ref.setParameter(param);
		return ref;
	}

	private static Plus newPlus(Expression left, Expression right) {
		Plus plus = ArithmeticsFactory.eINSTANCE.createPlus();
		plus.setLeft(left);
		plus.setRight(right);
		return plus;
	}

}
