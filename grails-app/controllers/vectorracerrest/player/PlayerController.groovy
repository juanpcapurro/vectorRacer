package vectorracerrest.player

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlayerController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Player.list(params), model:[playerCount: Player.count()]
    }

    def show(Player player) {
        respond player
    }

    @Transactional
    def save(Player player) {
        if (player == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (player.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond player.errors, view:'create'
            return
        }

        player.save flush:true

        respond player, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Player player) {
        if (player == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (player.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond player.errors, view:'edit'
            return
        }

        player.save flush:true

        respond player, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Player player) {

        if (player == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        player.delete flush:true

        render status: NO_CONTENT
    }
}
