package umm.semanticgp;
import umm.util.SharedPRNG;

import java.util.Arrays;
import java.util.Random;

public class OperatorJava {
	/**
	 * A simple Java class that wraps a function that isn't
	 * specified here, but is presumably specified when the instance
	 * of Operator is constructed (or in a subclass of Operator).
	 * We wouldn't actually do this in the GP system because we
	 * could/would just pass the function around instead of wrapping
	 * it like this, but I thought I'd show some of the stuff you can
	 * do.
	 */


		public static String[] operatorArr = {
			"plus",
			"sub",
			"mult",
			"divi",
			"sin",
			"cos",
			"log",
			"gpif"
		};

		public static String[] binaryOpArr = {"plus", "sub", "mult","divi"};

		public static String[] singularOpArr = {"sin", "cos", "log"};
		
		public static String[] trinaryOpArr = {"gpif"};
				
		public static double plus(double a, double b) {
			return a + b;
		}
		public static double sub(double a, double b)  {
			return a - b;
		}
		public static double mult(double a, double b){
			return a * b;
		}
		public static double divi(double a, double b) {
			if (Math.abs(b)== 0) {
				return 1;
			} else {
				return a / b;
			}
		}
		public static double sin(double a) {
			return Math.sin(a);
		}
		public static double cos(double a) {
			return Math.cos(a);
		}
		public static double log(double a) {
			if (a > 0) {
				return Math.log(a);
			} else {
				return -100000;
			}
		}
		public static double gpif(double test,double positive,double negative) {
			if (test > 0) {
				return positive;
			} else {
				return negative;
			}
		}

		public static int numArgs(Object function) {
			if (isBinary(function)) {
				return 2;
			} else if (isSingular(function)) {
				return 1;
			} else if (isTrinary(function)) {
				return 3;
			} else {
				return 0;
			}
		}

		public static boolean isBinary(Object function) {
			return Arrays.asList(binaryOpArr).contains(function);
		}
		
		public static boolean isTrinary(Object function) {
			return Arrays.asList(trinaryOpArr).contains(function);
		}
		
		public static boolean isSingular(Object function) {
			return Arrays.asList(singularOpArr).contains(function);
		}

		public static boolean isFunction(Object function) {
			return Arrays.asList(operatorArr).contains(function);
		}

		public static Object toString(Object node) {
			//String node = (String) nodes;
			
			if (node.equals("plus")) {
				return "+";
			} else if (node.equals("sub")) {
				return "-";
			} else if (node.equals("mult")) {
				return "*";
			} else if (node.equals("divi")) {
				return "/";
			} else if (node.equals("gpif")) {
				return "if";
			} else {
				return node;
			}
		}

		public static String random() {
			Random rand = SharedPRNG.instance();
			return operatorArr[rand.nextInt(operatorArr.length)];
		}
}
