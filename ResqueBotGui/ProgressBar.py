import serial
ser = serial.Serial("COM6", 9600)
while True:
     text=ser.readline()
     text=str(text)
     lenght=len(text)
     text=text[:lenght-5]
     text=text[2:]
     print(text)
     cc=text.split(' ')
     val=int(cc[0])