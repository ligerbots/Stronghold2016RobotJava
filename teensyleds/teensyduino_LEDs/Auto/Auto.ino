#include <Adafruit_NeoPixel.h>
#include <Wire.h>

// constants
#define PIN_LEFT 4
#define PIN_RIGHT 6
#define LEDS_PER_STRIP 23
#define LEDS_MODE NEO_GRB+NEO_KHZ800
#define I2C_ADDR 42

uint32_t black;
uint32_t purple;
uint32_t red;
uint32_t blue;
uint32_t orange;
uint32_t green;

// variables
int ticks = 0;
Adafruit_NeoPixel stripLeft(LEDS_PER_STRIP, PIN_LEFT, LEDS_MODE);
Adafruit_NeoPixel stripRight(LEDS_PER_STRIP, PIN_RIGHT, LEDS_MODE);

// Rainbow variables
uint32_t colors[LEDS_PER_STRIP];
uint32_t colors2[LEDS_PER_STRIP];
int startColor[3] = {255, 0, 0};
int targetColor[3] = {50, 0, 100};
int stepPerLED[3] = {0, 0, 0};
int startColor2[3] = {0, 0, 255};
int targetColor2[3] = {100, 0, 50};
int stepPerLED2[3] = {0, 0, 0};
// Runway lights

//Full LED configs

void setup() {
  // put your setup code here, to run once:
  stripLeft.begin();
  stripRight.begin();
  stripLeft.show();
  stripRight.show();

  initialize_rainbow();
  initialize_runway();

  black = stripLeft.Color(0, 0, 0);
  purple = stripLeft.Color(128, 0, 128);
  red = stripLeft.Color(255, 0, 0);
  blue = stripLeft.Color(0, 0, 255);
  green = stripLeft.Color(0, 255, 0);
  orange = stripLeft.Color(255, 30, 0);
}

// Rainbow lights
void initialize_rainbow() {
  ticks = 0;
  stepPerLED[0] = (targetColor[0]-startColor[0])/LEDS_PER_STRIP;
  stepPerLED[1] = (targetColor[1]-startColor[1])/LEDS_PER_STRIP;
  stepPerLED[2] = (targetColor[2]-startColor[2])/LEDS_PER_STRIP;
  stepPerLED2[0] = (targetColor2[0]-startColor2[0])/LEDS_PER_STRIP;
  stepPerLED2[1] = (targetColor2[1]-startColor2[1])/LEDS_PER_STRIP;
  stepPerLED2[2] = (targetColor2[2]-startColor2[2])/LEDS_PER_STRIP;

  // gradient all the ticks
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    colors[i] = stripLeft.Color(startColor[0]+i*stepPerLED[0], startColor[1]+i*stepPerLED[1], startColor[2]+i*stepPerLED[2]);
    colors2[i] = stripRight.Color(startColor2[0]+i*stepPerLED2[0], startColor2[1]+i*stepPerLED2[1], startColor2[2]+i*stepPerLED2[2]);
  }
}

void rainbow_lights() {
  for (int i=0; i < LEDS_PER_STRIP; i++) {
    int index = (ticks + i) % 23;
    stripLeft.setPixelColor(i, colors[index]);
    stripRight.setPixelColor(i, colors2[index]);
  }
 
  stripLeft.show();
  stripRight.show();
  delay(40);
}

void rainbow_lights2() {
  for (int i=0; i < LEDS_PER_STRIP; i++) {
    int index = (ticks + i) % 23;
    stripLeft.setPixelColor(23-i, colors[index]);
    stripRight.setPixelColor(i, colors2[index]);
  }
 
  stripLeft.show();
  stripRight.show();
  delay(40);
}

// Runway lights
void initialize_runway() {
  ticks = 0;
}

void runway_lights(uint32_t color1, uint32_t color2) {
  int tick2 = ticks+12;

  stripLeft.setPixelColor(ticks % 23, color1);
  stripLeft.setPixelColor(tick2 % 23, color2);
  stripRight.setPixelColor(ticks % 23, color1);
  stripRight.setPixelColor(tick2 % 23, color2);

  stripRight.setPixelColor(ticks % 23 - 1, black);
  stripRight.setPixelColor(tick2 % 23 - 1, black);
  stripLeft.setPixelColor(ticks % 23 - 1, black);
  stripLeft.setPixelColor(tick2 % 23 - 1, black);
 
  stripLeft.show();
  stripRight.show();
  delay(30);
}

void runway_lights2(uint32_t color1, uint32_t color2) {
  int indexOn = ticks % 23;
  int indexOff = (indexOn + 20) % 23;
  stripLeft.setPixelColor(indexOn, color1);
  stripRight.setPixelColor(indexOn, color1);

  stripRight.setPixelColor(indexOff, black);
  stripLeft.setPixelColor(indexOff, black);
 
  stripLeft.show();
  stripRight.show();
  delay(30);
}

// Pulse
void pulse(int r, int g, int b) {
    float factor = abs(ticks % 50 - 25) / 25.0;
    uint32_t color = stripLeft.Color(int(r * factor), int(g * factor), int(b * factor));
    reset_leds(color);
    delay(30);
}

// 
// Running variables
int mode = 0;

void reset_leds(uint32_t color) {
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    stripLeft.setPixelColor(i, color);
    stripRight.setPixelColor(i, color);
  }
  stripLeft.show();
  stripRight.show();
}

void loop() {
  ticks++;

  if (ticks % 1800 == 0) {
    mode = (mode + 1) % 5;
    reset_leds(black);
  }

  switch (mode) {
    case 0:
      pulse(150, 0, 0);
      break;
    case 1:
      runway_lights(orange, green); 
      break;
    case 2:
      pulse(0, 0, 255);
      break;
    case 3:
      rainbow_lights();
      break;
    case 4:
      runway_lights2(purple, orange); 
      break;
    default:
      break;
  }
  
}
