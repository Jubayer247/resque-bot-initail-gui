import tkinter as tk
from tkinter import ttk

import tk_tools
import serial

ser=None
try:
    ser = serial.Serial("COM0", 9600)
except:
    pass

root = tk.Tk()
root.title("SENSOR LIVE DATA VISUALIZATION")

max_value = 1024

# Typical one-sided gauge that you might be expecting for a
# speedometer or tachometer.  Starts at zero and goes to
# max_value when full-scale.
mq5 = tk_tools.Gauge(root,  height=120, width=250,
                     max_value=max_value,
                     label='MQ5',
                     bg='lavender')
mq5.grid(row=0, column=0, sticky='news')

mq8 = tk_tools.Gauge(root,height=120, width=250,
                     max_value=max_value,
                     label='MQ8',

                     bg='lavender')
mq8.grid(row=0, column=1, sticky='news')

mq9 = tk_tools.Gauge(root,height=120, width=250,
                     max_value=max_value,
                     label='MQ9',
                     bg='lavender')
mq9.grid(row=0, column=2, sticky='news')

# The battery voltage gauge has a lower voltage limit and an
# upper voltage limit.  These are automatically created when
# one imposes values on red_low and yellow_low along with
# using the min_value.
mq135 = tk_tools.Gauge(root, height=120, width=250,
                       max_value=max_value,
                       label='MQ135',
                       bg='lavender')
mq135.grid(row=0, column=3, sticky='news')

# Similar to the previous gauge with bi-directional, but shows an
# imbalanced configuration along with support for negative numbers.
vibration = tk_tools.Gauge(root, height=120, width=250,
                           max_value=max_value,
                           label='VIBRATION',
                           bg='lavender')
vibration.grid(row=1, column=0, sticky='news')

fire = tk_tools.Gauge(root, height=120, width=250,
                      max_value=max_value,
                      label='FIRE',
                      bg='lavender')
fire.grid(row=1, column=1, sticky='news')
# initialization of some variables.


sound = tk_tools.Gauge(root, height=120, width=250,
                       max_value=max_value,
                       label='SOUND',
                       bg='lavender')
sound.grid(row=1, column=2, sticky='news')
# initialization of some variables.

uv = tk_tools.Gauge(root, height=120, width=250,
                    max_value=11,
                    min_value=0,
                    label='UV',
                    bg='lavender')
uv.grid(row=1, column=3, sticky='news')
# initialization of some variables.
temperature = tk_tools.Gauge(root, height=120, width=250,
                             max_value=500,
                             label='TEMPERATURE',
                             bg='lavender')
temperature.grid(row=2, column=0, sticky='news')

coms = (
        "COM0", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11", "COM12",
        "COM13", "COM14", "COM15", "COM16", "COM17", "COM18", "COM19", "COM20", "COM21", "COM22", "COM23", "COM24",
        "COM25", "COM26", "COM27", "COM28", "COM29", "COM30", "COM31", "COM32", "COM33", "COM34", "COM35", "COM36",
        "COM37", "COM38", "COM39", "COM40", "COM41", "COM42", "COM43", "COM44", "COM45", "COM46", "COM47", "COM48",
        "COM49", "COM50", "COM51", "COM52", "COM53", "COM54", "COM55", "COM56", "COM57", "COM58", "COM59", "COM60",
        "COM61", "COM62", "COM63", "COM64", "COM65", "COM66", "COM67", "COM68", "COM69", "COM70", "COM71", "COM72",
        "COM73", "COM74", "COM75", "COM76", "COM77", "COM78", "COM79", "COM80", "COM81", "COM82", "COM83", "COM84",
        "COM85", "COM86", "COM87", "COM88", "COM89", "COM90", "COM91", "COM92", "COM93", "COM94", "COM95", "COM96",
        "COM97", "COM98", "COM99", "COM100", "COM101", "COM102", "COM103", "COM104", "COM105", "COM106", "COM107",
        "COM108", "COM109", "COM110", "COM111", "COM112", "COM113", "COM114", "COM115", "COM116", "COM117", "COM118",
        "COM119", "COM120", "COM121", "COM122", "COM123", "COM124", "COM125", "COM126", "COM127", "COM128", "COM129",
        "COM130", "COM131", "COM132", "COM133", "COM134", "COM135", "COM136", "COM137", "COM138", "COM139", "COM140",
        "COM141", "COM142", "COM143", "COM144", "COM145", "COM146", "COM147", "COM148", "COM149", "COM150", "COM151",
        "COM152", "COM153", "COM154", "COM155", "COM156", "COM157", "COM158", "COM159", "COM160", "COM161", "COM162",
        "COM163", "COM164", "COM165", "COM166", "COM167", "COM168", "COM169", "COM170", "COM171", "COM172", "COM173",
        "COM174", "COM175", "COM176", "COM177", "COM178", "COM179", "COM180", "COM181", "COM182", "COM183", "COM184",
        "COM185", "COM186", "COM187", "COM188", "COM189", "COM190", "COM191", "COM192", "COM193", "COM194", "COM195",
        "COM196", "COM197", "COM198", "COM199", "COM200", "COM201", "COM202", "COM203", "COM204", "COM205", "COM206",
        "COM207", "COM208", "COM209", "COM210", "COM211", "COM212", "COM213", "COM214", "COM215", "COM216", "COM217",
        "COM218", "COM219", "COM220", "COM221", "COM222", "COM223", "COM224", "COM225", "COM226", "COM227", "COM228",
        "COM229", "COM230", "COM231", "COM232", "COM233", "COM234", "COM235", "COM236", "COM237", "COM238", "COM239",
        "COM240", "COM241", "COM242", "COM243", "COM244", "COM245", "COM246", "COM247", "COM248", "COM249", "COM250",
        "COM251", "COM252", "COM253", "COM254", "COM255", "COM256")



com_check_box=ttk.Combobox()

com_check_box.grid(row=2, column=3, sticky='news')
com_check_box.set('COM0')

com_check_box.configure(values=coms)
def update_gauge():
    try:
        global ser
        global com_check_box
        com=str(com_check_box.get())
        try:
            ser = serial.Serial(com, 9600)
        except:
            pass
        cc = str(ser.readline())
        #print(cc)
        lenght=len(cc)
        #cc = cc[2:]
        cc=cc[2:lenght-5]
        sensor_values=cc.split(" ")

        sensor_values_int=[]

        for value in sensor_values:
            sensor_values_int.append(int(value))



        mq5.set_value(sensor_values_int[0])
        mq8.set_value(sensor_values_int[1])
        mq9.set_value(sensor_values_int[2])
        mq135.set_value(sensor_values_int[3])
        vibration.set_value(sensor_values_int[4])
        fire.set_value(sensor_values_int[5])
        sound.set_value(sensor_values_int[6])
        uv.set_value(sensor_values_int[7])
        temperature.set_value(sensor_values_int[8])
    except:
        pass
    root.after(50, update_gauge)





if __name__ == '__main__':

    root.after(100, update_gauge)

    root.mainloop()


