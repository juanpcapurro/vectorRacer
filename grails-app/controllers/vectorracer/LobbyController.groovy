package vectorracer

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LobbyController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ([lobbyList: Lobby.getAll()])
    }

    def show(Long id) {
        respond Lobby.findById(id)
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
