package vectorracer

import vectorracer.raceTrack.RaceTrack

class Match{
    def playerList
    def raceTrack
    static constraints = {
        playerList minSize:1, validator:{it?.every{player -> player?.isReadyToPlay()}}, nullable: false
        raceTrack nullable: false
    }
    Match(Collection playerList,RaceTrack raceTrack){
        this.playerList = playerList
        this.playerList?.collect{
            it?.bindToRaceTrack(raceTrack)
        }
        this.raceTrack = raceTrack
        if(!this.validate())
            throw new IncompleteDomainObjectException()
    }
//    Match(){
//        throw new IncompleteDomainObjectException()
//    }
}
