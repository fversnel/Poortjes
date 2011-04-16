private Enemy enemy;

void setup() {
  size(400,300); 
  frameRate(60);
  enemy = new Enemy(this);
  enemy.position.x = width/2f;
  enemy.position.y = height/2f;
}

void draw() {
  background(0);
  enemy.draw();
  enemy.target = new PVector(mouseX, mouseY);
}
