package daiq.core.lang

import spock.lang.Specification

class UtilsTest extends Specification {
    
    def "Str"() {
        expect:
        Langs.str(a, b) == c
        where:
        a | b || c
        "xxx"      | null || "xxx"
        "x{}x"     | "A"  || "xAx"
        "x{}x{}x"  | ["AA", "BB"] as String[] || "xAAxBBx"
    }

    def "#name should have length #length"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }
}
