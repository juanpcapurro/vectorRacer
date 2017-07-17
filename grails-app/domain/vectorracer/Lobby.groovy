package vectorracer

import vectorracer.player.Player
import vectorracer.raceTrack.RaceTrack

class Lobby {

    static constraints = {
        raceTrack nullable:true
    }
    static mapping={
        playerList lazy: false
    }
    static hasMany = [playerList: Player]
    def raceTrack
    List<Player> playerList

    void addPlayer(player){
        if(playerList.any{it.getColor()==player.getColor()})
            throw new ColorAlreadyInUse()
        if(playerList.any{it.getName().toLowerCase() == player?.getName()?.toLowerCase()})
            throw new NameAlreadyInUse()
    	playerList.add(player)
	}
    def getPlayerList(){
        return playerList.clone()
    }
    def chooseTrack(RaceTrack track){
        this.raceTrack=track
    }
    Match beginMatch(){
        return new Match(playerList,raceTrack)
    }
    Lobby(){
        this.playerList= new ArrayList<Player>()
    }
    int playerCount(){
        return this.playerList.size()
    }

}
