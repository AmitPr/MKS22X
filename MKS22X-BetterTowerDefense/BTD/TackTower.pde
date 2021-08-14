public class TackTower extends Tower{
   
   public TackTower(int x, int y, int maxHealth){
     super(x,y,maxHealth); 
     timeTillNextFire=reloadTime=2;
     r=g=120;
     b=255;
     this.price=towerPrices[2];
   }
   public void display(){
     fill(r,g,b);
    timeTillNextFire-=1/frameRate;
    if(timeTillNextFire<=0){
      boolean hasFired = false;
      for(int i = 0; i < world.enemies.size(); i++){
        Enemy e = world.enemies.get(i);
        float squareDist=abs(pow((e.getX()-x),2)+pow((e.getY()-y),2));
        if(squareDist<9){
          fire(e);
          hasFired=true;
        }
        if(i>=world.enemies.size() || e!=world.enemies.get(i)){
          i--;
        }
      }
      if(hasFired)
       timeTillNextFire=reloadTime;
      else
        timeTillNextFire=0;
    }
    image(towerImages[3],WIDTH/BTD.WORLD_WIDTH * x,HEIGHT/BTD.WORLD_HEIGHT*y,WIDTH/BTD.WORLD_WIDTH,HEIGHT/BTD.WORLD_HEIGHT);
   }
   public void fire(Enemy e){
     for(int i= 1; i < 9; i++){
       world.bullets.add(new Tack(this,e,i));
     } 
   }
}
