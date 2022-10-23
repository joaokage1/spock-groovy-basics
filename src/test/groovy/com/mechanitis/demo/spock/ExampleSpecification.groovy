package com.mechanitis.demo.spock

import org.example.Colour
import org.example.Palette
import org.example.Polygon
import org.example.Renderer
import org.example.ShapeFactory
import org.example.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject


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

    def "should be able to mock a concrete class"(){
        given:
        def renderer = Mock(Renderer)
        @Subject
        def polygon = new Polygon(4, renderer)

        when:
        polygon.draw()

        then:
        4 * renderer.drawLine()
    }

    def "should be able to create a stub"(){
        given:
        def palette = Stub(Palette)
        palette.getPrimaryColour() >> Colour.Red
        @Subject
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == Colour.Red
    }

    def "should use a helper method"(){
        given:
        def renderer = Mock(Renderer)
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        checkDegaultShape(polygon,renderer)
    }

    private void checkDegaultShape(Polygon polygon, Renderer renderer){
        assert polygon.numberOfSides == 4
        assert polygon.renderer == renderer
    }
}
