package com.mechanitis.demo.spock

import org.example.Polygon
import org.example.TooFewSidesException
import spock.lang.Specification


class ExampleSpecification extends Specification{

    def "should be a simple assertion"(){
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then"() {

        when:
        def sides = new Polygon(4).numberOfSides

        then:
        sides == 4
    }

    def "should expect Exceptions"(){

        when:
        new Polygon(0)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == 0
    }
}
