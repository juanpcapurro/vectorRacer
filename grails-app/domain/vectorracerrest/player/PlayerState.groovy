package vectorracerrest.player

import vectorracerrest.Color
import vectorracerrest.raceTrack.RaceTrack

abstract class PlayerState{
        
    static constraints = {
        name nullable: false, blank:false, size: 4..20
        raceTrack nullable:true
    }
    Color color 
    String name
    Player context
    RaceTrack raceTrack

    PlayerState(PlayerState currentPlayerState){
        this.context=currentPlayerState.context
        this.name=currentPlayerState.name
        this.color=currentPlayerState.color 
        this.raceTrack=currentPlayerState.raceTrack
    }
    
    abstract public isReadyToPlay();

    public toggleReady(){
        throw new InvalidPlayerState()
    }

    public bindToRaceTrack(RaceTrack racetrack){
        throw new InvalidPlayerState()
    }
}

