package cz.zcu.kiv.glucosecsvconverter.converters;

import cz.zcu.kiv.glucosecsvconverter.ConvertException;
import cz.zcu.kiv.glucosecsvconverter.data.MeasuredValue;
import cz.zcu.kiv.glucosecsvconverter.data.Subject;
import cz.zcu.kiv.glucosecsvconverter.data.TimeSegment;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


/***********************************************************************************************************************
 * This file is part of the Glucose-CSV-CsvConverterImpl project
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
 * MgsegCsvConverter, 2015/09/17 16:23 petr-jezek
 **********************************************************************************************************************/
public class MgsegCsvConverter implements CsvConverter {

    public static final String HEADER_VALUE = "Date";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Subject convert(List<String[]> rows) throws ConvertException {
        boolean firstRun = true;
        Subject subject = new Subject();
        List<TimeSegment> timeSegments = new LinkedList<>();
        subject.setTimeSegments(timeSegments);

        subject.setTimeSegments(timeSegments);
        List<MeasuredValue> measuredValues = new LinkedList<>();
        MeasuredValue previous = new MeasuredValue();
        try {
            for (String[] line : rows) {
                MeasuredValue measuredValue = new MeasuredValue();
                if (line.length > 1) {
                    measuredValue.setMeasuredAt(simpleDateFormat.parse(line[0] + " " + line[1]));
                }
                if (line.length > 2) {
                    String bg = line[2];
                    if (bg != null && !bg.isEmpty()) {
                        measuredValue.setBlood(Double.parseDouble(bg.replace(',', '.')));
                    }
                }
                if (line.length > 3) {
                    String sensor = line[3];
                    if (sensor != null && !sensor.isEmpty()) {
                        measuredValue.setIst(Double.parseDouble(sensor.replace(',', '.')));
                    }
                }


                if (firstRun || measuredValue.getMeasuredAt().getTime() - previous.getMeasuredAt().getTime() > Utils.SEGMENTS_DELAY) {
                    TimeSegment timeSegment = new TimeSegment();
                    measuredValues = new LinkedList<>();
                    timeSegment.setMeasuredValues(measuredValues);
                    timeSegments.add(timeSegment);
                    measuredValue.setTimeSegment(timeSegment);
                    timeSegment.setMeasuredValues(measuredValues);
                    firstRun = false;
                }
                measuredValues.add(measuredValue);
                previous = measuredValue;
            }
        } catch (Exception e) {
            throw new ConvertException(e);
        }

        return subject;
    }
}
