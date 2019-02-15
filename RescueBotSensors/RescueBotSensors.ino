#include <Wire.h>
#include <Adafruit_MLX90614.h>
//---------------------------------------------------------------------Sensor pins
int MQ5_PIN=A8;
int MQ8_PIN=A9;
int MQ9_PIN=A10;
int MQ135_PIN=A11;
int VIBRATION_PIN=A12;
int FIRE_PIN=A13;
int SOUND_PIN=A14;
int UV_PIN=A15;

Adafruit_MLX90614 Temp_mlx = Adafruit_MLX90614();
//---------------------------------------------------------------------Sensor Values
String MQ5_VAL;
String MQ8_VAL;
String MQ9_VAL;
String MQ135_VAL;
String Temp_mlx_VAL;
String VIBRATION_VAL;
String FIRE_VAL;
String SOUND_VAL;
String UV_VAL; //UV index

void setup() {
Serial.begin(115200);
Temp_mlx.begin();

pinMode(MQ5_PIN, INPUT);
pinMode(MQ8_PIN, INPUT);
pinMode(MQ9_PIN, INPUT);
pinMode(MQ135_PIN, INPUT);
pinMode(VIBRATION_PIN, INPUT);
pinMode(FIRE_PIN, INPUT);
pinMode(SOUND_PIN, INPUT);
pinMode(UV_PIN, INPUT);

//Serial.println("MQ5---MQ8---MQ9---MQ135---VIBR---FIRE---SOUND---UV---TEMP");
}

void loop()
{
MQ5_VAL= analogRead(MQ5_PIN);
MQ8_VAL= analogRead(MQ8_PIN);
MQ9_VAL= analogRead(MQ9_PIN);
MQ135_VAL= analogRead(MQ135_PIN);
VIBRATION_VAL= analogRead(VIBRATION_PIN);
FIRE_VAL= 1023-analogRead(FIRE_PIN);
SOUND_VAL=1023-analogRead(SOUND_PIN);
UV_VAL=readSensor();
Temp_mlx_VAL=(int)Temp_mlx.readAmbientTempC();
//Serial.print(MQ5_VAL);
//Serial.print("---");
//Serial.print(MQ8_VAL);
//Serial.print("---");
//Serial.print(MQ9_VAL);
//Serial.print("---");
//Serial.print(MQ135_VAL);
//Serial.print("---");
//Serial.print(VIBRATION_VAL);
//Serial.print("---");
//Serial.print(FIRE_VAL);
//Serial.print("---");
//Serial.println(SOUND_VAL);
//Serial.print("---");
//Serial.print(UV_VAL);
//Serial.print("---");
//Serial.println(Temp_mlx_VAL);

Serial.println(MQ5_VAL+" "+MQ8_VAL+" "+MQ9_VAL+" "+MQ135_VAL+" "+VIBRATION_VAL+" "+FIRE_VAL+" "+SOUND_VAL+" "+UV_VAL+" "+Temp_mlx_VAL);
delay(100);
}




//----------------------------------------------------------------method for reading UV INDEX

String readSensor()
{
  String UVIndex ="0";
  int sensorValue = 0;
  
  sensorValue = analogRead(UV_PIN);
  int voltage = (sensorValue * (5.0 / 1023.0))*1000;
  
  if(voltage<50)
  {
    UVIndex ="0";
  }else if (voltage>50 && voltage<=227)
  {
    UVIndex = "0";
  }else if (voltage>227 && voltage<=318)
  {
    UVIndex = 1;
  }
  else if (voltage>318 && voltage<=408)
  {
    UVIndex = 2;
  }else if (voltage>408 && voltage<=503)
  {
    UVIndex = 3;
  }
  else if (voltage>503 && voltage<=606)
  {
    UVIndex = 4;
  }else if (voltage>606 && voltage<=696)
  {
    UVIndex = 5;
  }else if (voltage>696 && voltage<=795)
  {
    UVIndex = 6;
  }else if (voltage>795 && voltage<=881)
  {
    UVIndex = 7;
  }
  else if (voltage>881 && voltage<=976)
  {
    UVIndex = 8;
  }
  else if (voltage>976 && voltage<=1079)
  {
    UVIndex = 9;
  }
  else if (voltage>1079 && voltage<=1170)
  {
    UVIndex = 10;
  }else if (voltage>1170)
  {
    UVIndex = 11;
  }
  return UVIndex;
}

