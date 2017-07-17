package vectorracer

import vectorracer.player.Player
import vectorracer.raceTrack.RaceTrack

class Lobby {

    static constraints = {
        raceTrack nullable:true
        name empty:false, size: 4..30
    }
    static mapping={
        playerList lazy: false
    }
    static hasMany = [playerList: Player]
    RaceTrack raceTrack
    List<Player> playerList
    String name

    void addPlayer(player){
        if(playerList.any{it.getColor()==player.getColor()})
            throw new ColorAlreadyInUse()
        if(playerList.any{it.getName().toLowerCase() == player?.getName()?.toLowerCase()})
            throw new NameAlreadyInUse()
    	playerList.add(player)
	}
    List<Player> getPlayerList(){
        return playerList.toList()
    }
    void chooseTrack(RaceTrack track){
        this.raceTrack=track
    }
    Match beginMatch(){
        return new Match(playerList,raceTrack)
    }
    Lobby(String name){
        this.name = name
        this.playerList= new ArrayList<Player>()
    }
    int playerCount(){
        return this.playerList.size()
    }

}
