/*
* Bluetooh Basic: LED ON OFF - Avishkar
* Coder - Mayoogh Girish
* Website - http://bit.do/Avishkar
* Download the App : https://github.com/Mayoogh/Arduino-Bluetooth-Basic
* This program lets you to control a LED on pin 13 of arduino using a bluetooth module
*/
char data = 0;            //Variable for storing received data
void setup()
{
    Serial.begin(115200);   //Sets the baud for serial data transmission                               
    pinMode(13, OUTPUT);  //Sets digital pin 13 as output pin
}
void loop()
{   int randNumber=random(0,1023);
    int randNumber2 = random(0,1023);
    int randNumber3 = random(0,1023);
    int randNumber4 = random(0,1023);
    int randNumber5 = random(0,1023);
    int randNumber6 = random(0,1023);
    int randNumber7 = random(0,1023);
    int randNumber8 = random(0,1023);
    Serial.print(randNumber);
    Serial.print(" ");
    Serial.print(randNumber2);
    Serial.print(" ");Serial.print(randNumber3);
    Serial.print(" ");Serial.print(randNumber4);
    Serial.print(" ");Serial.print(randNumber5);
    Serial.print(" ");Serial.print(randNumber6);
    Serial.print(" ");Serial.print(randNumber7);
    Serial.print(" ");Serial.print(randNumber7);
    Serial.print(" ");Serial.print(randNumber7);
    Serial.println("");
    delay(500);     
   }


