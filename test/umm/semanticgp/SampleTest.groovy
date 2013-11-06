package umm.semanticgp;

import static org.junit.Assert.*;

import spock.lang.Specification;
import umm.sematicgp.Operator

import org.junit.Test;

/**
 * A simple test just to show the basics of how spock tests
 * work, and to show how we can pass around functions and do
 * other "Groovy" (ho, ho, ho) things.
 */
class SampleTest extends Specification {
    Operator plus = new Operator() {
        def func = { x, y -> x + y }
    }

    @Test
    public void "Adding 2 and 3 makes 5"() {
        given:
        def two = 2
        def three = 3
        
        when:
        def result = plus.apply(two, three)

        then:
        result == 5
    }
	Operator mult = new Operator() {
		def func = { x, y -> x * y }
	}
	@Test
	public void "Multi 2 and 3 makes 6"() {
		given:
		def two = 2
		def three = 3
		
		when:
		def result = mult.apply(two, three)

		then:
		result == 6
	}

}
