public class EnemyBase extends Tower{
  public EnemyBase(int x, int y, int maxHealth){
      super(x,y,maxHealth); 
  }
  public void display(){
    fill(255,0,0);
    rect(WIDTH/WORLD_WIDTH * x,HEIGHT/WORLD_HEIGHT*y,WIDTH/WORLD_WIDTH*2,HEIGHT/WORLD_HEIGHT*2); 
  }
}
