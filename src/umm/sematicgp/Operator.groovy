package umm.sematicgp;

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
//	def operator
//    /*def apply(x, y) {
//        func(x, y)
//    }*/
//	def Operator(operator) {
//		this.operator = operator
//	}
	
	static plus = {
		x, y -> x + y
	}
	static sub =  {
		x, y -> x - y
	}
	static mult =  {
		x, y -> x * y
	}
	static divi =  {x, y -> 
	if (y== 0) {
		1
	}
	else {
		x / y 
		}
	}
	static sin = {
		x -> Math.sin(x)
	}
	static cos = {
		x -> Math.cos(x)
	}
	static log = {
		x -> Math.log(x)
	}
	static gpif = {
		test, positive, negative ->
		if (test > 0) {
			positive
		} else {
			negative
		}
	}
	
	static isFunction(node) {
		(node instanceof Closure)
	}
	
	static numArgs(node) {
		if (isFunction(node)) {
			node.parameterTypes.length
		}
		else {
			0
		}
	}
	
	static toString(node) {
		
		if (node == plus) {
			return "+"
		} else if (node == sub) {
			return "-"
		} else if (node == mult) {
			return "*"
		} else if (node == divi) {
			return "/"
		} else if (node == sin) {
			return "sin"
		} else if (node == cos) {
			return "cos"
		} else if (node == log) {
			return "log"
		} else if (node == gpif) {
			return "if"
		} 
	
	}
		
	
	static random() {
		def operatorArr = [plus, sub, mult, divi, sin, cos, log, gpif]
		Random rand = new Random()
		return operatorArr[rand.nextInt(operatorArr.size())]
	}
}
