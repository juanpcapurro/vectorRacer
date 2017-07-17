package vectorracer

import vectorracer.player.Player
import vectorracer.raceTrack.RaceTrack

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LobbyController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def list = Lobby.getAll()
        println list
        respond lobbyList: list
    }

    def show(Long id) {
        def lobby= Lobby.get(id)
//        def charlyCompleto = new Player("charly",Color.BLACK)
//        charlyCompleto.save(failOnError:true)
//        def damiCompleto = new Player("dami",Color.RED)
//        damiCompleto.save(flush:true)
//        def lobby = new Lobby()
//        lobby.addPlayer(charlyCompleto)
//        lobby.addPlayer(damiCompleto)
//        charlyCompleto.toggleReady()
//        damiCompleto.toggleReady()
//        lobby.chooseTrack(new RaceTrack())
//        lobby.save(failOnError:true)

        println(lobby.getPlayerList())
        respond lobby
    }

    @Transactional
    def save(Lobby lobby) {
        if (lobby == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (lobby.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lobby.errors, view:'create'
            return
        }

        lobby.save flush:true

        respond lobby, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Lobby lobby) {
        if (lobby == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (lobby.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lobby.errors, view:'edit'
            return
        }

        lobby.save flush:true

        respond lobby, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Lobby lobby) {

        if (lobby == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        lobby.delete flush:true

        render status: NO_CONTENT
    }
}
