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

    def "should expect an Exception to be thrown for a number of invalid inputs: #sides"(){
        when:
        new Polygon(sides)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides

        where:
        sides << [-1, 0, 1, 2]
    }

    def "should be able to create a polygon with #sides sides"(){
        expect:
        new Polygon(sides).numberOfSides == sides

        where:
        sides << [3, 4, 5, 6]
    }

    def "should use data tables for calculating max"(){
        expect: Math.max(a, b) == max

        where:
        a | b | max
        1 | 3 | 3
        7 | 4 | 7
        0 | 0 | 0
    }
}
