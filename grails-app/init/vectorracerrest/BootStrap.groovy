package vectorracer

import vectorracer.player.*
import vectorracer.raceTrack.RaceTrack

class BootStrap {

    def init = { servletContext ->
        def charlyCompleto = new Player("charly",Color.BLACK)
        charlyCompleto.save(failOnError:true)
        def damiCompleto = new Player("dami",Color.RED)
        damiCompleto.save(flush:true)
        def lobby = new Lobby()
        lobby.addPlayer(charlyCompleto)
        lobby.addPlayer(damiCompleto)
        charlyCompleto.toggleReady()
        damiCompleto.toggleReady()
        lobby.chooseTrack(new RaceTrack())
        def match = lobby.beginMatch().save(failOnError:true)
        lobby.save(failOnError:true)
        def otherLobby = new Lobby()
        otherLobby.save(failOnError:true)
    }
    def destroy = {
    }
}
