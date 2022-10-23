package com.mechanitis.demo.spock

import org.example.Polygon
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
}
