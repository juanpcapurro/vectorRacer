package vectorracerrest.player

import groovy.transform.InheritConstructors
import vectorracerrest.raceTrack.RaceTrack

@InheritConstructors
class ReadyToPlay extends PlayerState{
    @Override
    public isReadyToPlay(){
        return true
    }
    @Override
    public toggleReady(){
        this.context.playerState= new NotYetReady(this);
    }

    @Override
    public bindToRaceTrack(RaceTrack raceTrack){
        this.raceTrack=raceTrack
        this.context.playerState= new PlayingNormally(this)
    }
}
