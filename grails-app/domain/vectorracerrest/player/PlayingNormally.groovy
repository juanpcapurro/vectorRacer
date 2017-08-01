package vectorracerrest.player

import groovy.transform.InheritConstructors

@InheritConstructors
class PlayingNormally extends PlayerState{
    public isReadyToPlay(){
        return true
    }

}
