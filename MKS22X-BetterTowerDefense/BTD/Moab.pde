public class Moab extends Balloon{
  public Moab(int x, int y, int bal){
    super( x,  y,  bal);
  }
  public void damage(int amt){
    
    super.damage(amt);
    player.money+=amt;
    
  }
}
