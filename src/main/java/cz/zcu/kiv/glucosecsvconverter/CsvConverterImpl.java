package cz.zcu.kiv.glucosecsvconverter;


import cz.zcu.kiv.glucosecsvconverter.converters.BloodCsvConverter;
import cz.zcu.kiv.glucosecsvconverter.converters.CsvConverter;
import cz.zcu.kiv.glucosecsvconverter.converters.MgsegCsvConverter;
import cz.zcu.kiv.glucosecsvconverter.data.Subject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.InputStream;
import java.io.InputStreamReader;
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
 * CsvConverterImpl, 2015/09/17 14:27 petr-jezek
 **********************************************************************************************************************/
public class CsvConverterImpl implements Converter {

    public Subject convert(InputStream csv) throws ConvertException {
        Subject s;
        try {

            LineIterator it = IOUtils.lineIterator(csv, "UTF-8");

            List<String[]> rows = new LinkedList<>();
            while (it.hasNext()) {
                String line = it.nextLine();
                String[] row = line.split("\t|;");
                rows.add(row);
            }

            it.close();

            if (rows.size() == 0) {
                throw new ConvertException("Unsupported format");
            }

            CsvConverter converter = getConverter(rows.get(0));
            rows.remove(0);
            s = converter.convert(rows);

        } catch (Exception e) {
            throw new ConvertException(e);
        }

        return s;
    }

    private CsvConverter getConverter(String[] header) throws ConvertException {
        CsvConverter converter;
        switch (header[0]) {
            case BloodCsvConverter.HEADER_VALUE:
                converter = new BloodCsvConverter();
                break;
            case MgsegCsvConverter.HEADER_VALUE:
                converter = new MgsegCsvConverter();
                break;
            default:
                throw new ConvertException("Suitable converter does not exist");
        }
        return converter;
    }
}
