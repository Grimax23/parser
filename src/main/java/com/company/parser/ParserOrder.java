package com.company.parser;

import com.company.exeption.ParserException;
import com.company.model.OrderDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class ParserOrder {
	public static final String FILE_NOT_FOUND = "File not found";
	public static final String FILE_IS_EMPTY = "File is empty";

	public String filename;


	public ParserOrder(String filename) {
		this.filename = filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<OrderDTO> execute() {
		try (BufferedReader input = Files.newBufferedReader(Paths.get(filename))) {
			return parse(input);
		} catch (IOException e) {
			throw new ParserException(FILE_NOT_FOUND);
		}
	}

	protected abstract List<OrderDTO> parse(BufferedReader input);

}
