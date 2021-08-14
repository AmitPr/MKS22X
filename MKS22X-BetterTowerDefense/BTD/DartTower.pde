public class DartTower extends Tower{
   public DartTower(int x, int y, int maxHealth){
     super(x,y,maxHealth); 
     timeTillNextFire=reloadTime=1;
     r=g=115;
     b=55;
     this.price=towerPrices[0];
   }
   public void display(){
     super.display();
     image(towerImages[0],WIDTH/BTD.WORLD_WIDTH * x,HEIGHT/BTD.WORLD_HEIGHT*y,WIDTH/BTD.WORLD_WIDTH,HEIGHT/BTD.WORLD_HEIGHT);
   }
   public void fire(Enemy e){
     super.fire(e);
   }
}
