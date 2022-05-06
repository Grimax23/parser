package com.company.parser.impl;

import com.company.converter.Converter;
import com.company.exeption.ParserException;
import com.company.model.Order;
import com.company.model.OrderDTO;
import com.company.parser.ParserOrder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ParserCSV extends ParserOrder {
    public static final String COLUMNS_COUNT_IS_INVALID = "columns count is invalid = ";

    public ParserCSV(String filename) {
        super(filename);
    }

    @Override
    public List<OrderDTO> parse(BufferedReader input) {
        Iterable<CSVRecord> records;
        try {
            records = CSVFormat.EXCEL.parse(input);
        } catch (IOException e) {
            throw new ParserException(e.getMessage());
        }

        List<OrderDTO> orders = StreamSupport.stream(records.spliterator(), true)
                .map(this::parseRecord).collect(Collectors.toList());

        if (orders.isEmpty()) {
            throw new ParserException(FILE_IS_EMPTY);
        } else {
            return orders;
        }
    }

    private OrderDTO parseRecord(CSVRecord record) {
        OrderDTO target;
        if (record.size() == 4) {
            Order source = new Order(record.get(0), record.get(1), record.get(2), record.get(3));
            target = Converter.convert(source, filename, record.getRecordNumber(), null);
        } else {
            target = Converter.convert(null, filename,
                    record.getRecordNumber(), COLUMNS_COUNT_IS_INVALID + record.size());
        }
        return target;

    }
}
