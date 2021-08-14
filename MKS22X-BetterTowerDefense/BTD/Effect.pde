public class Effect{
  private float speedMultiplier;
  private float duration;
  private String name;
  private int ticksTillNextEffect;
  private int ticksLeft;
  private int damagePerEffect;
  public Effect(float speedMult, float duration, String name, int ticksLeft, int ticksTillNextEffect,int damagePerEffect){
   this.speedMultiplier=speedMult;
   this.duration=duration;
   this.name=name;
   this.ticksTillNextEffect=ticksTillNextEffect;
   this.ticksLeft = ticksLeft;
   this.damagePerEffect=damagePerEffect;
  }
  public Effect(float speedMult, float duration, String name){
   this.speedMultiplier=speedMult;
   this.duration=duration;
   this.name=name;
   this.ticksTillNextEffect=Integer.MAX_VALUE;
   this.ticksLeft = Integer.MAX_VALUE;
   this.damagePerEffect=0;
  }
  public float getMultiplier(){
     return speedMultiplier; 
  }
  public void reduceDuration(float elapsed){
    duration-=elapsed;
  }
  public boolean isExpired(){
    return duration <=0;
  }
  public String getName(){
    return name;
  }
  public int getTicksTillNextEffect(){
    return ticksTillNextEffect;
  }
  public int getTicksLeft(){
    return ticksLeft;
  }
  public void setTicksLeft(int ticks){
    ticksLeft=ticks;
  }
  public int getDamagePerEffect(){
    return damagePerEffect;
  }
  public void reduceTicksLeft(float elapsed){
    ticksLeft-=elapsed;
  }
}
