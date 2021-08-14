class Dart extends Bullet{
  public Dart (Tower parent, Enemy target){
    super(parent,target);
    this.damage=3;
    this.damagePerEnemy=1;
    speed=0.3;
  }
}
