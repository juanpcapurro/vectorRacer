package vectorracerrest.player

import vectorracerrest.Color
import groovy.transform.InheritConstructors
import vectorracerrest.IncompleteDomainObjectException

@InheritConstructors
class NotYetReady extends PlayerState{

    NotYetReady(Player context,String name, Color color){
        this.context=context
        this.name=name
        this.color=color
        
        if(!this.validate())
            throw new IncompleteDomainObjectException()
    }
    @Override 
    public isReadyToPlay(){
        return false
    }
    @Override
    public toggleReady(){
        this.context.playerState=new ReadyToPlay(this)
    }
}

