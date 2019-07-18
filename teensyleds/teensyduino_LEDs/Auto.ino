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

// variables
int thread = 0;
Adafruit_NeoPixel stripLeft(LEDS_PER_STRIP, PIN_LEFT, LEDS_MODE);
Adafruit_NeoPixel stripRight(LEDS_PER_STRIP, PIN_RIGHT, LEDS_MODE);

// Rainbow variables
uint32_t colors[LEDS_PER_STRIP];
uint32_t colors2[LEDS_PER_STRIP];
int startColor[3] = {0, 0, 0};
int targetColor[3] = {50, 0, 100};
int stepPerLED[3] = {0, 0, 0};
int startColor2[3] = {0, 0, 0};
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
}

// Rainbow lights
void initialize_rainbow() {
  thread = 0;
  stepPerLED[0] = (targetColor[0]-startColor[0])/LEDS_PER_STRIP;
  stepPerLED[1] = (targetColor[1]-startColor[1])/LEDS_PER_STRIP;
  stepPerLED[2] = (targetColor[2]-startColor[2])/LEDS_PER_STRIP;
  stepPerLED2[0] = (targetColor2[0]-startColor2[0])/LEDS_PER_STRIP;
  stepPerLED2[1] = (targetColor2[1]-startColor2[1])/LEDS_PER_STRIP;
  stepPerLED2[2] = (targetColor2[2]-startColor2[2])/LEDS_PER_STRIP;

  // gradient all the threads
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    colors[i] = stripLeft.Color(startColor[0]+i*stepPerLED[0], startColor[1]+i*stepPerLED[1], startColor[2]+i*stepPerLED[2]);
    colors2[i] = stripRight.Color(startColor2[0]+i*stepPerLED2[0], startColor2[1]+i*stepPerLED2[1], startColor2[2]+i*stepPerLED2[2]);
  }
}

void rainbow_lights() {
  for (int i=0; i < LEDS_PER_STRIP; i++) {
    stripLeft.setPixelColor(i, colors[(thread + i) % 23]);
    stripRight.setPixelColor(i, colors2[(thread + i) % 23]);
  }

  thread++;
 
  stripLeft.show();
  stripRight.show();
  delay(40);
}

// Runway lights
void initialize_runway() {
  thread = 0;
  black = stripLeft.Color(0, 0, 0);
  purple = stripLeft.Color(128, 0, 128);
  red = stripLeft.Color(255, 0, 0);
  blue = stripLeft.Color(0, 0, 255);
}

void runway_lights() {
  thread++;
  int thread2 = thread+12;

  stripLeft.setPixelColor(thread % 23, red);
  stripLeft.setPixelColor(thread2 % 23, blue);
  stripRight.setPixelColor(thread % 23, red);
  stripRight.setPixelColor(thread2 % 23, blue);

  stripRight.setPixelColor(thread % 23 - 1, black);
  stripRight.setPixelColor(thread2 % 23 - 1, black);
  stripLeft.setPixelColor(thread % 23 - 1, black);
  stripLeft.setPixelColor(thread2 % 23 - 1, black);
 
  stripLeft.show();
  stripRight.show();
  delay(30);
}
// Running variables
int ticks = 0;
int mode = 0;

void reset_leds() {
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    stripLeft.setPixelColor(i, black);
    stripRight.setPixelColor(i, black);
  }
}

void loop() {
  ticks++;

  if (ticks % 1800 == 0) {
    mode = (mode + 1) % 2;
    reset_leds();
  }

  if (mode == 0) {
    runway_lights();  
  }
  else {
    rainbow_lights();
  }
  
}
