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
Adafruit_NeoPixel stripLeft(LEDS_PER_STRIP, PIN_LEFT, LEDS_MODE);
Adafruit_NeoPixel stripRight(LEDS_PER_STRIP, PIN_RIGHT, LEDS_MODE);
int ticks = 0;
int shootStartTicks = 0;

//Full LED configs

void setup() {
  // put your setup code here, to run once:
  stripLeft.begin();
  stripRight.begin();
  stripLeft.show();
  stripRight.show();

  black = stripLeft.Color(0, 0, 0);
  purple = stripLeft.Color(128, 0, 128);
  red = stripLeft.Color(255, 0, 0);
  blue = stripLeft.Color(0, 0, 255);

  ticks = 0;
}

int thread1 = 0;
int thread2 = 12;

void loop() {
  thread1++;
  thread2++;

  stripLeft.setPixelColor(thread1 % 23, red);
  stripLeft.setPixelColor(thread2 % 23, blue);
  stripRight.setPixelColor(thread1 % 23, red);
  stripRight.setPixelColor(thread2 % 23, blue);

  stripRight.setPixelColor(thread1 % 23 - 1, black);
  stripRight.setPixelColor(thread2 % 23 - 1, black);
  stripLeft.setPixelColor(thread1 % 23 - 1, black);
  stripLeft.setPixelColor(thread2 % 23 - 1, black);
 
  stripLeft.show();
  stripRight.show();
  delay(30);
}

void reset_leds() {
  for (int i = 0; i < LEDS_PER_STRIP; i++) {
    stripLeft.setPixelColor(i, black);
    stripRight.setPixelColor(i, black);
  }
}
