public class Balloon extends Enemy{
  int bal;
  public Balloon(int x, int y, int bal){
    super( x,  y,  0);
    setHealth(balloonHealths[bal]);
    setSpeed(balloonSpeeds[bal]);
    setRGB(balloonCols[bal]);
    setRadiusAsPercent(balloonRadiiAsPercent[bal]);
    this.bal=bal;
  }
  public void damage(int amt){
    this.health-=amt; 
    if(amt>0){
      player.money+=amt;
    } 
    if(this.health>balloonHealths[bal]){
      this.health+=amt;
    }
    if(health >0 && bal<MOAB_START_INDEX){
      setSpeed(balloonSpeeds[health-1]);
      setRGB(balloonCols[health-1]);
    }
    
    
  }
  
}
