import pandas as pd
from pandas import *
from openpyxl import load_workbook

def titikturun(atas, bawah, titik):
    y = (atas-titik)/(atas-bawah)
    return y

def titiknaik(atas, bawah, titik):
    y = (titik-bawah)/(atas-bawah)
    return y

def emosi(titik):
    status=[]
    if 0<=titik<=36:
        status.append(['rendah',1])
    elif (36<titik<40):
        status.append(['rendah', titikturun(40,36,titik)])
        status.append(['sedang', titiknaik(40,36,titik)])
    elif 40<=titik<=61:
        status.append(['sedang', 1])
    elif 61<titik<65:
        status.append(['sedang', titikturun(65,61,titik)])
        status.append(['tinggi', titiknaik(65,61,titik)])
    elif 65<=titik<=76:
        status.append(['tinggi', 1])
    elif 76<titik<80:
        status.append(['tinggi', titikturun(80,76,titik)])
        status.append(['sangat tinggi', titiknaik(80,76,titik)])
    elif 80<=titik<=100:
        status.append(['sangat tinggi', 1])
    else:
        print('out of bound')
    return status

def provokasi(titik):
    status=[]
    if 0<=titik<=25:
        status.append(['low',1])
    elif 25<titik<30:
        status.append(['low', titikturun(30,25,titik)])
        status.append(['normal', titiknaik(30,25,titik)])
    elif 30<=titik<=60:
        status.append(['normal',1])
    elif 60<titik<65:
        status.append(['normal', titikturun(65,60,titik)])
        status.append(['high', titiknaik(65,60,titik)])
    elif 65<=titik<=85:
        status.append(['high', 1])
    elif 85<titik<90:
        status.append(['high', titikturun(90,85,titik)])
        status.append(['highest', titiknaik(90,85,titik)])
    elif 90<=titik<=100:
        status.append(['highest', 1])
    else:
        print('out of bound')
    return status

def fuzzyrule(emo, income):
    inference=[]
    for i in range (len(emo)):
        for j in range (len(income)):
            if (income[j][0]=='low' and emo[i][0]=='rendah') or (income[j][0]=='low' and emo[i][0]=='tinggi') or (income[j][0]=='low' and emo[i][0]=='sangat tinggi'):
                inference.append(['small', min(income[j][1], emo[i][1])])
            elif (income[j][0]=='low' and emo[i][0]=='sedang'):
                inference.append(['medium', min(income[j][1], emo[i][1])])
            if (income[j][0]=='normal' and emo[i][0]=='rendah') or (income[j][0]=='normal' and emo[i][0]=='sangat tinggi'):
                inference.append(['medium', min(income[j][1], emo[i][1])])
            elif (income[j][0]=='normal' and emo[i][0]=='sedang') or (income[j][0]=='normal' and emo[i][0]=='tinggi'):
                inference.append(['small', min(income[j][1], emo[i][1])])
            if (income[j][0]=='high' and emo[i][0]=='rendah') or (income[j][0]=='high' and emo[i][0]=='tinggi') or (income[j][0]=='high' and emo[i][0]=='sangat tinggi'):
                inference.append(['large', min(income[j][1], emo[i][1])])
            elif (income[j][0]=='high' and emo[i][0]=='sedang'):
                inference.append(['small', min(income[j][1], emo[i][1])])
            if (income[j][0]=='highest' and emo[i][0]=='rendah') or (income[j][0]=='highest' and emo[i][0]=='sedang') or (income[j][0]=='highest' and emo[i][0]=='tinggi') or (income[j][0]=='highest' and emo[i][0]=='sangat tinggi'):
                inference.append(['large', min(income[j][1], emo[i][1])])
    # print(inference)
    return inference

def inferensi(inference):
    defuzzy=[0,0,0]
    for i in range (len(inference)):
        if inference[i][0] == 'small'and inference[i][1]>defuzzy[0]:
            defuzzy[0] = inference[i][1]
        elif inference[i][0] == 'medium' and inference[i][1]>defuzzy[1]:
            defuzzy[1] = inference[i][1]
        elif inference[i][0] == 'large' and inference[i][1]>defuzzy[2]:
            defuzzy[2]= inference[i][1]
    # print(defuzzy)
    return defuzzy

def sugeno(defuzzification):
    # penyebut = defuzzification[0]*25+defuzzification[1]*50+defuzzification[2]*75
    # pembilang = defuzzification[0]+defuzzification[1]+defuzzification[2]
    # print(defuzzification, penyebut, pembilang)
    return float(defuzzification[0]*25+defuzzification[1]*50+defuzzification[2]*75)/(defuzzification[0]+defuzzification[1]+defuzzification[2])

def Main(path):
    data = pd.read_excel(path)
    result = []
    for i in range (len(data)):
        emotion = emosi(data['Emosi'][i])
        provocation = provokasi(data['Provokasi'][i])
        # print(i,'emosi',data['Emosi'][i], emotion, 'provokasi',data['Provokasi'][i],provocation)
        rule = fuzzyrule(emotion, provocation)
        inference = inferensi(rule)
        if sugeno(inference)<50:
            result.append('Tidak')
        else:
            result.append('Ya')
        # print('')

    hasil = pd.DataFrame({'Hoax':result})
    writer=ExcelWriter(path)
    book = load_workbook(path)
    writer.book = book 
    writer.sheets = dict((ws.title, ws) for ws in book.worksheets)
    hasil.to_excel(writer, sheet_name='Sheet1',index=False, startcol=5)
    writer.save()

# Main('Data Training.xlsx')
Main('Data Testing.xlsx')