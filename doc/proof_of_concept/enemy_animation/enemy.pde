// Quick sketch of poortjes enemy animation
// Not really designed nicely, but it shows the general principles.

class Enemy {
  
  private  PApplet parent;
  public PVector position;
  public PVector scale;
  public float rotation;
  
  public PVector target;
  
  public color primaryColor;
  
  // Animation
  private float scaleFactor = .33f; // How strongly the enemy is squished
  private float scaleSpeed = 1f; // The speed of the squishing
  private float rotationSpeed = 0.1f; // speed of rotation (rotation should be driven by player location, this is just for show)
  private float rotationAmount = PI/16f; // The amount that the enemy rotates left and right
  private float curTime = 0f; // real time use for animation
  private float deltaTime = 0f; // timestep per frame
  
  public Enemy(PApplet parent) {
    this.parent = parent;
    position = new PVector(0f, 0f);
    scale = new PVector(1f, 1f);
    rotation = 0f;
    
    target = new PVector(0f, 0f);
    
    primaryColor = #AAAAFF;
    
    deltaTime = 1f / parent.frameRate;
  }
  
  public void draw() {
    // Animate scale and rotation properties, keeping the animation
    // separated from the object's public properties. This is
    // so the enemy's transform can still be meaningfully
    // manipulated by the rest of the program without the animation
    // overriding it.
    float animScaleX = 1f + sin(curTime * scaleSpeed) * scaleFactor;
    float animScaleY = 1f + cos(curTime * scaleSpeed) * scaleFactor;
    float animRotation = sin(curTime * rotationSpeed) * rotationAmount;
    
    // push object's public transform properties
    translate(position.x, position.y);
    scale(scale.x, scale.y);
    rotate(rotation);
    pushMatrix();
    
    // push enemy's extra animated properties
    scale(animScaleX, animScaleY);
    rotate(PI/4f + animRotation);
    pushMatrix();
    
    // Draw graphics
    noFill();
    stroke(primaryColor);
    parent.rect(-20f,-20f,40f,40f);
    
    // pop animated properties
    rotate(-PI/4f);
    popMatrix();
    
    // pop public transform properties
    popMatrix();
    
    curTime += deltaTime;
  }
}
