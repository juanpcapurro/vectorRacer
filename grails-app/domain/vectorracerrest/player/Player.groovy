package vectorracerrest.player

import vectorracerrest.Color
import vectorracerrest.raceTrack.RaceTrack

class Player {

    PlayerState playerState
    static mapping={
        playerState lazy: false
    }


    Player(String name, Color color){
        playerState= new NotYetReady(this, name, color)
    }

    public isReadyToPlay(){
        return playerState.isReadyToPlay()
    }
    public toggleReady(){
        return playerState.toggleReady()
    }
    def getName(){
        playerState.getName()
    }
    def getColor(){
        playerState.getColor()
    }
    def bindToRaceTrack(RaceTrack raceTrack){
        playerState.bindToRaceTrack(raceTrack)
    }
    
}
