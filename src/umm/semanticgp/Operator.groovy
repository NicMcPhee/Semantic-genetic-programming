package umm.semanticgp
import umm.util.SharedPRNG;

/**
 * A simple Groovy class that wraps a function that isn't
 * specified here, but is presumably specified when the instance
 * of Operator is constructed (or in a subclass of Operator).
 * We wouldn't actually do this in the GP system because we
 * could/would just pass the function around instead of wrapping
 * it like this, but I thought I'd show some of the stuff you can
 * do.
 */

import java.util.Random

class Operator {
	static operatorArr = [
		"plus",
		"sub",
		"mult",
		"divi",
		"sin",
		"cos",
		"log",
		"gpif"
	]
	static plus(a,b) {
		return a+b
	}
	static sub(a,b)  {
		a-b
	}
	static mult(a,b){
		return a*b
	}
	static divi(a,b) {
		if (Math.abs(b)== 0) {
			1
		} else {
			a / b
		}
	}
	static sin(a) {
		Math.sin(a)
	}
	static cos(a) {
		Math.cos(a)
	}
	static log(a) {
		if (a > 0) {
			Math.log(a)
		} else {
			-100000
		}
	}
	static gpif(test,positive,negative) {
		if (test > 0) {
			positive
		} else {
			negative
		}
	}

	static numArgs(function) {
		if (function == "plus" || function == "sub" || function == "mult" || function == "divi") {
			2
		} else if (function == "sin" || function == "cos" || function == "log") {
			1
		} else if (function == "gpif") {
			3
		} else {
			0
		}
	}
	
	static isFunction(function) {
		operatorArr.contains(function)
	}
	
	static toString(node) {
		if (node == "plus") {
			return "+"
		} else if (node == "sub") {
			return "-"
		} else if (node == "mult") {
			return "*"
		} else if (node == "divi") {
			return "/"
		} else if (node == "gpif") {
			return "if"
		} else {
			return node
		}
	}

	static random() {
		Random rand = SharedPRNG.instance()
		return operatorArr[rand.nextInt(operatorArr.size())]
	}
}