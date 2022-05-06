package com.company;

import com.company.converter.Converter;
import com.company.model.OrderDTO;
import com.company.parser.ParserFactoryBean;
import com.company.parser.ParserOrder;
import org.springframework.context.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@Configuration
@ComponentScan
public class App {
    private static final String INCORRECT_COMMAND = "Incorrect command: source files not specified";
    private static AnnotationConfigApplicationContext context;
    private static ParserFactoryBean parserFactory;
    private static Converter orderConverter;

    @Bean
    public static ParserFactoryBean ParserFactoryBean() {
        return new ParserFactoryBean();
    }

    @Bean
    public static Converter ConverterOrderToOrderDTO() {
        return new Converter();
    }

    public static void main(String[] args) {
//        args = new String[]{"src/main/resources/orders1.csv", "src/main/resources/orders2.json"};
        if (args.length > 0) {
            context = new AnnotationConfigApplicationContext(App.class);
            parserFactory = context.getBean(ParserFactoryBean.class);
            orderConverter = context.getBean(Converter.class);
            Stream.of(args).distinct().parallel().forEach(App::processFile);
        } else {
            System.out.println(INCORRECT_COMMAND);
        }
    }

    private static void processFile(String filename) {
        parserFactory.setFilename(filename);
        ParserOrder parser = parserFactory.getObject();
        try {
            List<OrderDTO> orders = parser.execute();
            orders.parallelStream().forEach(o -> System.out.println(orderConverter.convertDTOToString(o)));
        } catch (Exception e) {
            System.out.println(Converter.buildErrorString(filename, null, e.getMessage()));
        }
    }
}
