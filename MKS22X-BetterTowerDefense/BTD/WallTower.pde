public class WallTower extends Tower{
   public WallTower(int x, int y, int maxHealth){
     super(x,y,maxHealth); 
     timeTillNextFire=reloadTime=Float.MAX_VALUE;
     r=g=b=115;
     this.price=towerPrices[1];
   }
   public void display(){
     super.display();
     image(towerImages[1],WIDTH/BTD.WORLD_WIDTH * x,HEIGHT/BTD.WORLD_HEIGHT*y,WIDTH/BTD.WORLD_WIDTH,HEIGHT/BTD.WORLD_HEIGHT);
   }
   public void fire(Enemy e){}
}
