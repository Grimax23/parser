package com.company.parser.impl;

import com.company.converter.Converter;
import com.company.exeption.ParserException;
import com.company.model.Order;
import com.company.model.OrderDTO;
import com.company.parser.ParserOrder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParserJson extends ParserOrder {
    public static final String JSON_IS_INVALID = "json is invalid";

    private ObjectMapper jsonMapper;

    public ParserJson(String fileName) {
        super(fileName);
        jsonMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public List<OrderDTO> parse(BufferedReader input) {
        List<String> lines = input.lines().collect(Collectors.toList());
        if (lines.isEmpty()) {
            throw new ParserException(FILE_IS_EMPTY);
        }

        List<OrderDTO> orders = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            orders.add(parseRecord(line, (long) i+1));
        }

        if (orders.isEmpty()) {
            throw new ParserException(FILE_IS_EMPTY);
        } else {
            return orders;
        }
    }

    private OrderDTO parseRecord(String line, Long lineNumber) {
        Order order;
        try {
            order = jsonMapper.readValue(line, Order.class);
        } catch (Exception e) {
            throw new ParserException(JSON_IS_INVALID);
        }
        return Converter.convert(order, filename, lineNumber, null);
    }

}
