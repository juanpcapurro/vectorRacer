package vectorracer

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Unroll
import vectorracer.raceTrack.RaceTrack
import vectorracer.player.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Match)
@Mock([Player,RaceTrack, PlayerState, NotYetReady, ReadyToPlay])
class MatchSpec extends Specification {

    @Shared
    Player nonReadyCharly, nonReadyDami
    @Shared
    Player readyCharly, readyDami, readyGabi

    def setup() {
        nonReadyCharly=new Player("charly",Color.BLACK)
        readyCharly=new Player("charly",Color.BLACK)
        nonReadyDami=new Player("dami",Color.BLACK)
        readyDami=new Player("dami",Color.BLACK)
        readyGabi= new Player("gabi",Color.RED)
        nonReadyCharly.toggleReady()
        nonReadyCharly.toggleReady()
        readyCharly.toggleReady()
        readyDami.toggleReady()
        readyGabi.toggleReady()

    }

    def cleanup() {
    }
    def "a match can't be generated from non-ready players and it needs a racetrack"(){
        when:
            new Match(playerList,racetrack)
        then:
            IncompleteDomainObjectException ex = thrown()
        where:
        playerList << [null, [],[nonReadyDami],[nonReadyCharly],[nonReadyDami,readyCharly],[readyDami,nonReadyCharly] ]
        racetrack << [null, null, new RaceTrack(), null, new RaceTrack(),null]
        
    }
    def "every player in a match is in a valid state"(){
        given:
            def racetrack= new RaceTrack()
            playerList.collect{
                println it.playerState.class
            }
            def match = new Match(playerList,racetrack)
        when:
            match.playerList.collect{it.toggleReady()}
        then:
            InvalidPlayerState ex = thrown()
        where:
            playerList << [ [readyCharly],[readyDami,readyGabi] ]
        
    }

}
