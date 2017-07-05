package vectorracer.player

import groovy.transform.*
import vectorracer.IncompleteDomainObjectException
import vectorracer.Color
import vectorracer.raceTrack.RaceTrack

class Player {

    PlayerState playerState
    static mappings={
        lazy: false
    }


    Player(String name, Color color){
        playerState= new NotYetReady(this, name, color)
    }
//    Player(){
//        throw new IncompleteDomainObjectException()
//    }
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
