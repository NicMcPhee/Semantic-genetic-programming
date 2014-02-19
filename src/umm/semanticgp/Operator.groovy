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

	static binaryOpArr = ["plus", "sub", "mult","divi"]

	static singularOpArr = ["sin", "cos", "log"]
	
	static trinaryOpArr = ["gpif"]
	static plus(double a, double b) {
		return a + b
	}
	static sub(double a, double b)  {
		return a - b
	}
	static mult(double a, double b){
		return a * b
	}
	static divi(double a, double b) {
		if (Math.abs(b)== 0) {
			1
		} else {
			a / b
		}
	}
	static sin(double a) {
		Math.sin(a)
	}
	static cos(double a) {
		Math.cos(a)
	}
	static log(double a) {
		if (a > 0) {
			Math.log(a)
		} else {
			-100000
		}
	}
	static gpif(double test,double positive,double negative) {
		if (test > 0) {
			positive
		} else {
			negative
		}
	}

	static numArgs(function) {
		if (isBinary(function)) {
			2
		} else if (isSingular(function)) {
			1
		} else if (isTrinary(function)) {
			3
		} else {
			0
		}
	}

	static isBinary(function) {
		binaryOpArr.contains(function)
	}
	
	static isTrinary(function) {
		trinaryOpArr.contains(function)
	}
	
	static isSingular(function) {
		singularOpArr.contains(function)
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