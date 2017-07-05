package vectorracer.player

import vectorracer.Color
import groovy.transform.InheritConstructors
import grails.validation.ValidationException
import vectorracer.IncompleteDomainObjectException

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

