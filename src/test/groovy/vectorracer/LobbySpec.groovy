package vectorracer


import vectorracer.player.*
import vectorracer.raceTrack.RaceTrack
import vectorracer.IncompleteDomainObjectException
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import spock.lang.Shared

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Lobby)
@Mock([PlayerState, NotYetReady, ReadyToPlay, Match, Player])
class LobbySpec extends Specification {
    def lobby
    def charly
    def dami 
    def gabi
    @Shared
    def otherCharly, capitalizedCharlie, capsLockCharlie, sameName

    def setup() {
		lobby = new Lobby()
		charly = new Player("charly",Color.BLACK)
        dami = new Player("damian", Color.RED)
        gabi = new Player("gabriel",Color.BLUE)
        otherCharly = new Player("charly",Color.BLUE)
        capitalizedCharlie = new Player("Charly", Color.RED)
        capsLockCharlie = new Player("CHARLY", Color.RED)
        
    }

    def cleanup() {
    }

    def "a player can be recovered"(){
	given:
		lobby.addPlayer(charly)
	expect:
		lobby.getPlayerList().contains(charly)
    }

    def "several players can be recovered"(){
	given:
		lobby.addPlayer(charly)
        lobby.addPlayer(dami)
	expect:
		lobby.getPlayerList().contains(charly)
    and:
        lobby.getPlayerList().contains(dami)
    }
    def "two players can't have the same color"(){
        given:
            def blackPlayer=new Player("michael",Color.BLACK)
            lobby.addPlayer(charly)
        when:
            lobby.addPlayer(blackPlayer)
        then:
            ColorAlreadyInUse ex = thrown()
    }

    def "two players can't have the same name"(){
        given:
            lobby.addPlayer(charly)
        when:
            lobby.addPlayer(sameName)
        then:
            NameAlreadyInUse ex = thrown()
        where:
            sameName << [ otherCharly, capitalizedCharlie, capsLockCharlie ]
    }
    def "a Match can be generated from a lobby"(){
        given:
            charly.toggleReady()
            dami.toggleReady()
            lobby.addPlayer(dami)
            lobby.addPlayer(charly)
            lobby.chooseTrack(new RaceTrack())
        when: 
            lobby.beginMatch()
        then:
            notThrown(IncompleteDomainObjectException)
    }
}
