package cz.zcu.kiv.glucosecsvconverter.converters;

import cz.zcu.kiv.glucosecsvconverter.ConvertException;
import cz.zcu.kiv.glucosecsvconverter.data.MeasuredValue;
import cz.zcu.kiv.glucosecsvconverter.data.Subject;
import cz.zcu.kiv.glucosecsvconverter.data.TimeSegment;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

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
 * FourthConverter, 2015/09/18 15:21 petr-jezek
 **********************************************************************************************************************/
public class FourthConverter implements CsvConverter {

    public static final String HEADER_VALUE = "PatientInfoField";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public Subject convert(List<String[]> rows) throws ConvertException {
        boolean firstRun = true;
        Subject subject = new Subject();
        List<TimeSegment> timeSegments = new LinkedList<>();
        subject.setTimeSegments(timeSegments);
        TimeSegment timeSegment = new TimeSegment();
        List<MeasuredValue> measuredValues = new LinkedList<>();
        MeasuredValue previous = new MeasuredValue();
        timeSegment.setMeasuredValues(measuredValues);
        try {
            for (String[] line : rows) {
                MeasuredValue measuredValue = new MeasuredValue();
                measuredValues.add(measuredValue);
                measuredValue.setTimeSegment(timeSegment);
                measuredValue.setMeasuredAt(simpleDateFormat.parse(line[2]));
               // System.out.println(measuredValue.getMeasuredAt());
                if (line.length > 4) {
                    String sensor = line[4];
                    if (sensor != null && !sensor.isEmpty() && Utils.isNumeric(sensor)) {
                        measuredValue.setIst(Double.parseDouble(sensor.replace(',', '.')));
                    }
                }
                if (firstRun || measuredValue.getMeasuredAt().getTime() - previous.getMeasuredAt().getTime() > Utils.SEGMENTS_DELAY) {
                    timeSegments.add(timeSegment);
                    timeSegment = new TimeSegment();
                    measuredValues = new LinkedList<>();
                    timeSegment.setMeasuredValues(measuredValues);
                    firstRun = false;
                }

                previous = measuredValue;
            }

        } catch (Exception e) {
            throw new ConvertException(e);
        }
        return subject;
    }
}
