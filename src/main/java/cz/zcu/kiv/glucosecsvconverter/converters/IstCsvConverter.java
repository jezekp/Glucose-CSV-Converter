package cz.zcu.kiv.glucosecsvconverter.converters;

import cz.zcu.kiv.glucosecsvconverter.ConvertException;
import cz.zcu.kiv.glucosecsvconverter.Converter;
import cz.zcu.kiv.glucosecsvconverter.data.MeasuredValue;
import cz.zcu.kiv.glucosecsvconverter.data.Subject;
import cz.zcu.kiv.glucosecsvconverter.data.TimeSegment;

import java.io.InputStream;
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
 * IstCsvConverter, 2015/09/21 11:18 petr-jezek
 **********************************************************************************************************************/
public class IstCsvConverter implements CsvConverter {

    public static final String HEADER_VALUE = "Last Name";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public Subject convert(List<String[]> rows) throws ConvertException {
        boolean firstRun = true;
        Subject subject = new Subject();
        String[] name = rows.get(0);
        if(name != null) {
            subject.setSurname(name[0]);
            subject.setGivenName(name[1]);
        }
        String[] metadata = rows.get(4);
        String deviceName = null;
        if (metadata != null) {
            deviceName = metadata[1] + " " + metadata[2] + " "+ metadata[3];
        }
        List<TimeSegment> timeSegments = new LinkedList<>();
        subject.setTimeSegments(timeSegments);
        TimeSegment timeSegment = new TimeSegment();
        List<MeasuredValue> measuredValues = new LinkedList<>();
        MeasuredValue previous = new MeasuredValue();
        timeSegment.setMeasuredValues(measuredValues);
        try {
            for (String[] line : rows.subList(6, rows.size())) {
                if(Utils.isNumeric(line[0]) && line.length > 3) {
                    MeasuredValue measuredValue = new MeasuredValue();
                    measuredValue.setMeasuredAt(simpleDateFormat.parse(line[1] + " " + line[2]));
                    if (line.length > 4) {
                        String blood = line[4];
                        if (blood != null && !blood.isEmpty() && Utils.isNumeric(blood)) {
                            measuredValue.setBlood(Double.parseDouble(blood.replace(',', '.')));
                        }
                    }
                    if (line.length > 30) {
                        String sensor = line[30];
                        if (sensor != null && !sensor.isEmpty() && Utils.isNumeric(sensor)) {
                            measuredValue.setIst(Double.parseDouble(sensor.replace(',', '.')));
                        }
                    }

                    if (firstRun || Math.abs(measuredValue.getMeasuredAt().getTime() - previous.getMeasuredAt().getTime()) > Utils.SEGMENTS_DELAY) {

                        timeSegment.setDevice(deviceName);
                        timeSegments.add(timeSegment);
                        timeSegment = new TimeSegment();
                        measuredValues = new LinkedList<>();
                        measuredValue.setTimeSegment(timeSegment);
                        timeSegment.setMeasuredValues(measuredValues);
                        firstRun = false;
                    }
                    measuredValues.add(measuredValue);
                    previous = measuredValue;
                }
            }

        } catch (Exception e) {
            throw new ConvertException(e);
        }
        return subject;
    }
}
