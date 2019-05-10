#include <Adafruit_NeoPixel.h>
#include <Wire.h>

// AnimationMode
typedef enum {
  ALL_OFF,
  AUTO,
  ALLIANCE_RED,
  ALLIANCE_BLUE,
  SHOOT,
  ANIM_MAX
} AnimationMode;
void (*animationFunctions[ANIM_MAX])();
char animationCodes[ANIM_MAX];

// constants
#define PIN_LEFT 4
#define PIN_RIGHT 6
#define LEDS_PER_STRIP 23
#define LEDS_MODE NEO_GRB+NEO_KHZ800
#define I2C_ADDR 42

// variables
Adafruit_NeoPixel stripLeft(LEDS_PER_STRIP, PIN_LEFT, LEDS_MODE);
Adafruit_NeoPixel stripRight(LEDS_PER_STRIP, PIN_RIGHT, LEDS_MODE);
uint32_t red;
uint32_t blue;
uint32_t green;
uint32_t black;
int ticks = 0;
int shootStartTicks = 0;
AnimationMode currentMode = ALL_OFF;
AnimationMode preShootMode = ALL_OFF;


void setup() {
  // initialize function table and i2c codes array
  animationFunctions[ALL_OFF] = animation_ALL_OFF;
  animationCodes[ALL_OFF] = 'O';
  animationFunctions[AUTO] = animation_AUTO;
  animationCodes[AUTO] = 'A';
  animationFunctions[ALLIANCE_RED] = animation_ALLIANCE_RED;
  animationCodes[ALLIANCE_RED] = 'R';
  animationFunctions[ALLIANCE_BLUE] = animation_ALLIANCE_BLUE;
  animationCodes[ALLIANCE_BLUE] = 'B';
  animationFunctions[SHOOT] = animation_SHOOT;
  animationCodes[SHOOT] = 'S';

  // LED strip setup
  stripLeft.begin();
  stripRight.begin();
  stripLeft.show();
  stripRight.show();
  red = stripLeft.Color(255, 0, 0);
  blue = stripLeft.Color(0, 0, 255);
  green = stripLeft.Color(0, 255, 0);
  black = stripLeft.Color(0, 0, 0);

  // communication
  Wire.begin(I2C_ADDR);
  Wire.onReceive(i2cReceive); // register event
  Serial.begin(9600);
  Serial.println("Completed setup()");
}

void i2cReceive(int numBytes) {
  Serial.print("We have a ");
  Serial.print(numBytes);
  Serial.println(" byte transmission:");
  AnimationMode previousMode = currentMode;
  while (Wire.available()) {
    char c = Wire.read();

    for (int i = 0; i < ANIM_MAX; i++) {
      if (animationCodes[i] == c) {
        currentMode = (AnimationMode) i;
        break;
      }
    }

    Serial.print(c);
  }
  Serial.println();

  if (currentMode == SHOOT) {
    shootStartTicks = ticks;
    ticks = 0;
    preShootMode = previousMode;
  }
}

void loop() {
  ticks++;

  (*animationFunctions[currentMode])();
  stripLeft.show();
  stripRight.show();
  delay(20);
}

void animate_fade(int r, int g, int b){
  int val_r = (int) (((double) abs((ticks % 50) - 25)) * r / 25.0);
  int val_g = (int) (((double) abs((ticks % 50) - 25)) * g / 25.0);
  int val_b = (int) (((double) abs((ticks % 50) - 25)) * b / 25.0);
  uint32_t color = stripLeft.Color(val_r, val_g, val_b);
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    stripLeft.setPixelColor(i, color);
    stripRight.setPixelColor(i, color);
  }
}

void animation_ALL_OFF() {
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    stripLeft.setPixelColor(i, black);
    stripRight.setPixelColor(i, black);
  }
}
void animation_AUTO() {
  animate_fade(255, 255, 0);
}
void animation_ALLIANCE_RED() {
  int move_pos = ticks % (LEDS_PER_STRIP + 10);
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    uint32_t color = (i < move_pos && i > move_pos - 10) ? red : black;
    stripLeft.setPixelColor(i, color);
    stripRight.setPixelColor(i, color);
  }
}
void animation_ALLIANCE_BLUE() {
  int move_pos = ticks % (LEDS_PER_STRIP + 10);
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    uint32_t color = (i < move_pos && i > move_pos - 10) ? blue : black;
    stripLeft.setPixelColor(i, color);
    stripRight.setPixelColor(i, color);
  }
}
//void animation_SHOOT() {
//  int display_pos = LEDS_PER_STRIP - ticks;
//  for (int i = 0; i < LEDS_PER_STRIP; i++) {
//    uint32_t color = (i > display_pos) ? green : black;
//    stripLeft.setPixelColor(i, color);
//    stripRight.setPixelColor(i, color);
//  }
//  if((ticks) > LEDS_PER_STRIP * 3){
//    currentMode = preShootMode;
//  }
//}

void animation_SHOOT() {
  if (ticks < 40) {
    uint32_t color = stripLeft.Color(255-(ticks*8), ticks*8, 0);
    stripLeft.setPixelColor(LEDS_PER_STRIP-ticks, color);
    stripRight.setPixelColor(LEDS_PER_STRIP-ticks, color);
  }
  else {
    currentMode = preShootMode;
  }
}
