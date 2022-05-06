package com.company.model;

import java.math.BigDecimal;

/**
 * Выходные данные
 * {“id”:1,“orderId”:1,”amount”:100,”comment”:”оплата заказа”,”filename”:”orders.csv”,”line”:1,”result”:”OK”}
 * {“id”:2,“orderId”:2,”amount”:123,”comment”:”оплата заказа”,”filename”:”orders.csv”,”line”:2,”result”:”OK”}
 * {“id”:3,“orderId”:3,”amount”:1.23,”comment”:”оплата заказа”,”filename”:”orders.json”,”line”:1,”result”:”OK”}
 * {“id”:4,“orderId”:4,”amount”:1.24,”comment”:”оплата заказа”,”filename”:”orders.json”,”line”:2,”result”:”OK”}
 * <p>
 * id - идентификатор ордера
 * amount - сумма ордера
 * currency - валюта суммы ордера
 * comment - комментарий по ордеру
 * filename - имя исходного файла
 * line - номер строки исходного файла
 * result - результат парсинга записи исходного файла.
 * OK - если запись конвертирована корректно,
 * или описание ошибки если запись не удалось конвертировать.
 */
public class OrderDTO {
    private Long id;
    private Long orderId;
    private BigDecimal amount;
    private String comment;
    private String filename;
    private Long line;
    private String result;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Long orderId, BigDecimal amount, String comment, String filename, Long line, String result) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.comment = comment;
        this.filename = filename;
        this.line = line;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getLine() {
        return line;
    }

    public void setLine(Long line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDTO orderDTO = (OrderDTO) o;

        if (id != null ? !id.equals(orderDTO.id) : orderDTO.id != null) return false;
        if (orderId != null ? !orderId.equals(orderDTO.orderId) : orderDTO.orderId != null) return false;
        if (amount != null ? !amount.equals(orderDTO.amount) : orderDTO.amount != null) return false;
        if (comment != null ? !comment.equals(orderDTO.comment) : orderDTO.comment != null) return false;
        if (filename != null ? !filename.equals(orderDTO.filename) : orderDTO.filename != null) return false;
        if (line != null ? !line.equals(orderDTO.line) : orderDTO.line != null) return false;
        return result != null ? result.equals(orderDTO.result) : orderDTO.result == null;
    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (orderId != null ? orderId.hashCode() : 0);
        result1 = 31 * result1 + (amount != null ? amount.hashCode() : 0);
        result1 = 31 * result1 + (comment != null ? comment.hashCode() : 0);
        result1 = 31 * result1 + (filename != null ? filename.hashCode() : 0);
        result1 = 31 * result1 + (line != null ? line.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount='" + amount + '\'' +
                ", comment='" + comment + '\'' +
                ", filename='" + filename + '\'' +
                ", line=" + line +
                ", result='" + result + '\'' +
                '}';
    }
}
