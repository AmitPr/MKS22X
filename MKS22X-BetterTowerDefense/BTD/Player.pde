public class Player extends Tower{
  public int money;
  public Player(int x, int y, int maxHealth){
      super(x,y,maxHealth);
      money=STARTING_MONEY;
      health=STARTING_HEALTH;
  }
  public void display(){
    textSize(30);
    fill(255,0,0);
    rect(WIDTH/WORLD_WIDTH * x,HEIGHT/WORLD_HEIGHT*y,WIDTH/WORLD_WIDTH*2,HEIGHT/WORLD_HEIGHT*2*(STARTING_HEALTH-health)/STARTING_HEALTH);
    fill(0,255,0);
    rect(WIDTH/WORLD_WIDTH * x,HEIGHT/WORLD_HEIGHT*y+HEIGHT/WORLD_HEIGHT*2*(STARTING_HEALTH-health)/STARTING_HEALTH,WIDTH/WORLD_WIDTH*2,HEIGHT/WORLD_HEIGHT*2*(health)/STARTING_HEALTH); 
    fill(0,0,255);
    if(health<=0){
      world.die();
    }
  }
}
