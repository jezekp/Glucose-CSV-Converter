package cz.zcu.kiv.glucosecsvconverter;

import cz.zcu.kiv.glucosecsvconverter.data.MeasuredValue;
import cz.zcu.kiv.glucosecsvconverter.data.Subject;
import cz.zcu.kiv.glucosecsvconverter.data.TimeSegment;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/***********************************************************************************************************************
 * This file is part of the Glucose-CSV-Converter project
 * <p>
 * ==========================================
 * <p>
 * Copyright (C) 2015 by University of West Bohemia (http://www.zcu.cz/en/)
 * <p>
 * **********************************************************************************************************************
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * **********************************************************************************************************************
 * <p>
 * Main, 2015/09/18 09:47 petr-jezek
 **********************************************************************************************************************/
public class Main {

    public static void main(String[] args) {
        Converter c = new CsvConverterImpl();
        try {
           // Subject s = c.convert(new FileInputStream("/media/petr-jezek/data/Projekt_Glukoza/Data/pac 1 krev.csv"));
         //  Subject s = c.convert(new FileInputStream("/media/petr-jezek/data/Projekt_Glukoza/Data/mgseg1.csv"));
          //  Subject s = c.convert(new FileInputStream("/media/petr-jezek/data/Projekt_Glukoza/Data/pac 4.csv"));
            //  Subject s = c.convert(new FileInputStream("/media/petr-jezek/data/Projekt_Glukoza/Data/pacient 2.csv"));
           //  Subject s = c.convert(new FileInputStream("/media/petr-jezek/data/Projekt_Glukoza/Data/pac 1 ist.csv"));
            Subject s = c.convert(new FileInputStream("/media/petr-jezek/data/Projekt_Glukoza/Data/pacient 2.csv"));
            for(TimeSegment timeSegment : s.getTimeSegments()) {
                System.out.println("------------------");
                for(MeasuredValue mv : timeSegment.getMeasuredValues()) {
                    System.out.println(mv.getMeasuredAt() + " blood: " + mv.getBlood() + " ist:" + mv.getIst());
                }
            }
        } catch (ConvertException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
