package com.company.parser;

import com.company.exeption.ParserException;
import org.springframework.beans.factory.FactoryBean;

import java.io.InputStream;
import java.util.Properties;

public class ParserFactoryBean implements FactoryBean<ParserOrder> {

    public static final String PROPERTIES_FILENAME = "application.properties";
    public static final String UNABLE_TO_LOAD = "Unable to load ";
    public static final String PARSER_BY_NAME = "-parser by name ";
    public static final String FILES_ARE_NOT_SUPPORTED = "-files are not supported";
    public static final String FILE_FORMAT_NOT_SPECIFIED = "File format not specified";

    private Properties parsers;
    private String filename;

    public ParserFactoryBean() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILENAME)) {
            parsers = new Properties();
            parsers.load(in);
        } catch (Exception e) {
            throw new ParserException(UNABLE_TO_LOAD + PROPERTIES_FILENAME);
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public ParserOrder getObject() {
        return getParserByFileName(filename);
    }

    @Override
    public Class<?> getObjectType() {
        return ParserOrder.class;
    }


    public ParserOrder getParserByFileName(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if ((dotIndex == -1) || (dotIndex == filename.length() - 1)) {
            throw new ParserException(FILE_FORMAT_NOT_SPECIFIED);
        }

        String extension = filename.substring(dotIndex + 1).toUpperCase();
        String className = parsers.getProperty(extension);
        if (className == null) {
            throw new ParserException(extension + FILES_ARE_NOT_SUPPORTED);
        }

        ParserOrder parser;
        try {
            parser = (ParserOrder) Class.forName(className).getDeclaredConstructor(String.class).newInstance(filename);
        } catch (Exception e) {
            throw new ParserException(UNABLE_TO_LOAD + extension + PARSER_BY_NAME + className);
        }
        return parser;
    }

}
