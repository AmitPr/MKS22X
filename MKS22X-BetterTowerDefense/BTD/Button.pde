public class Button{
  private float x,y,w,h,r,g,b;
  public String name;
  public PImage icon;
  public Button(float x, float y, float w, float h, String name,PImage icon){
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
    r=g=b=255;
    this.name=name;
    this.icon = icon;
  }
  void setRGB(float r, float g, float b){
    this.r=r;
    this.g=g;
    this.b=b;
  }
  void display(){
    fill(r,g,b);
    rect(x,y,w,h,10);
    if(icon!=null){
      image(icon,x+(w/2)-(h/2),y,h,h);
    }
  }
  boolean mouseOver(){
    return mouseX>=x&&mouseX<=x+w&&mouseY>=y&&mouseY<=y+h;
  }
}
