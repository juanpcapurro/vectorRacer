package vectorracer

import vectorracer.player.*
import vectorracer.raceTrack.RaceTrack

class BootStrap {

    def init = { servletContext ->
        def charlyCompleto = new Player("charly",Color.BLACK)
        charlyCompleto.save(failOnError:true)
        def damiCompleto = new Player("dami",Color.RED)
        damiCompleto.save(flush:true)
        def lobby = new Lobby("Die engel der huevurer")
        lobby.addPlayer(charlyCompleto)
        lobby.addPlayer(damiCompleto)
        charlyCompleto.toggleReady()
        damiCompleto.toggleReady()
        lobby.chooseTrack(new RaceTrack())
        def match = lobby.beginMatch().save(failOnError:true)
        lobby.save(failOnError:true)
        def otherLobby = new Lobby("empty lobby")
        otherLobby.save(failOnError:true)
        lobby.save(failOnError:true, flush: true)
        println(lobby.playerCount())
        println(otherLobby.playerCount())
    }
    def destroy = {
    }
}
