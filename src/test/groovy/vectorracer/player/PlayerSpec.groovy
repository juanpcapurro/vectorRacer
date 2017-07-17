package vectorracer.player

import vectorracer.IncompleteDomainObjectException
import vectorracer.Color
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import vectorracer.raceTrack.RaceTrack
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Player)
@Mock([PlayerState, NotYetReady, ReadyToPlay])
class PlayerSpec extends Specification {
    Player charly

    def setup() {
        charly = new Player("charly", Color.BLACK)
    }

    def cleanup() {
    }
    void "name can be retrieved"(){
        expect:
            charly.getName()=="charly"
    }

    void "color can be retrieved"(){
        expect:
            charly.getColor()==Color.BLACK
    }

    void "by default, it is not ready to play"(){
        expect:
            charly.isReadyToPlay()==false
    }
    void "can mark itself as ready"(){
        given: 
            charly.toggleReady()
        expect:
            charly.isReadyToPlay()==true
    }
    void "a player can go back to a not-ready state"(){
        given: 
            charly.toggleReady()
            charly.toggleReady()
        expect:
            charly.isReadyToPlay()==false
    }
    void "short names are rejected"(){
        when:
            charly=new Player("x",Color.RED)
        then:
            IncompleteDomainObjectException ex = thrown()
    }
    void "long names are rejected"(){
        when:
            charly=new Player("flasdhgoodgfahxsaduhbvchhjoj sadf asdf asdf qwetrt    esd",Color.RED)
        then:
            IncompleteDomainObjectException ex = thrown()
    }
    void "normally, it doesn't accept binding to a racetrack"(){
        when: 
            charly.bindToRaceTrack(new RaceTrack())
        then:
            InvalidPlayerState ex = thrown()
    }
    void "it accepts being bound to a racetrack when ready to play"(){
        when: 
            charly.toggleReady()
            charly.bindToRaceTrack(new RaceTrack())
        then:
             notThrown(InvalidPlayerState)
    }
    void "it doesn't accept being bound to a racetrack when not ready to play"(){
        when: 
            charly.toggleReady()
            charly.toggleReady()
            charly.bindToRaceTrack(new RaceTrack())
        then:
            InvalidPlayerState ex = thrown()
    }
    void "it accepts being bound to a racetrack after toggling ready 3 times"(){
        when: 
            charly.toggleReady()
            charly.toggleReady()
            charly.toggleReady()
            charly.bindToRaceTrack(new RaceTrack())
        then:
            notThrown(InvalidPlayerState )
    }

}
